package com.GF.verbum.DB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.GF.verbum.DB.DAO.FrasesDao;
import com.GF.verbum.DB.DAO.FratipDao;
import com.GF.verbum.DB.Entities.frasesEntity;
import com.GF.verbum.DB.Entities.fratipEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = fratipEntity.class, version = 1,exportSchema = false)
public abstract  class FraTipRoomDataBase extends RoomDatabase {

    public abstract  FratipDao DAO();
    private static volatile FraTipRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 10000;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static FraTipRoomDataBase getRoomDataBase(final Context context) {

        if (INSTANCE == null) {
            synchronized (FraTipRoomDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FraTipRoomDataBase.class, "fratip_v1_DataBase")
                            .fallbackToDestructiveMigration()
                            .addCallback(llamada)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback llamada = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {

            super.onCreate(db);

            databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {

                    FratipDao dao = INSTANCE.DAO();
                    dao.deleteAll();
                    dao.insert(new fratipEntity(1,1,1));
                    dao.insert(new fratipEntity(1,10,2));
                    dao.insert(new fratipEntity(1,11,3));
                    dao.insert(new fratipEntity(1,12,4));

                    dao.insert(new fratipEntity(2,1,1));
                    dao.insert(new fratipEntity(2,2,2));
                    dao.insert(new fratipEntity(2,3,3));
                    dao.insert(new fratipEntity(2,7,4));
                    dao.insert(new fratipEntity(2,14,5));
                    dao.insert(new fratipEntity(2,15,6));
                    dao.insert(new fratipEntity(2,18,7));

                    dao.insert(new fratipEntity(3,1,1));
                    dao.insert(new fratipEntity(3,2,2));
                    dao.insert(new fratipEntity(3,3,3));
                    dao.insert(new fratipEntity(3,4,4));
                    dao.insert(new fratipEntity(3,11,5));
                    dao.insert(new fratipEntity(3,12,6));

                    dao.insert(new fratipEntity(4,1,1));
                    dao.insert(new fratipEntity(4,2,2));
                    dao.insert(new fratipEntity(4,3,3));
                    dao.insert(new fratipEntity(4,4,4));
                    dao.insert(new fratipEntity(4,11,5));
                    dao.insert(new fratipEntity(4,12,6));

                    dao.insert(new fratipEntity(5,1,1));
                    dao.insert(new fratipEntity(5,2,2));
                    dao.insert(new fratipEntity(5,3,3));
                    dao.insert(new fratipEntity(5,4,4));
                    dao.insert(new fratipEntity(5,5,5));
                    dao.insert(new fratipEntity(5,11,6));
                    dao.insert(new fratipEntity(5,12,7));

                    dao.insert(new fratipEntity(6,1,1));
                    dao.insert(new fratipEntity(6,2,2));
                    dao.insert(new fratipEntity(6,3,3));
                    dao.insert(new fratipEntity(6,4,4));
                    dao.insert(new fratipEntity(6,5,5));
                    dao.insert(new fratipEntity(6,11,6));
                    dao.insert(new fratipEntity(6,12,7));


















                }
            });
        }
    };
}
