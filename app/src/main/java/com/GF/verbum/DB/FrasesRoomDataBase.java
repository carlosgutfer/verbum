package com.GF.verbum.DB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.GF.verbum.DB.DAO.FrasesDao;

import com.GF.verbum.DB.Entities.frasesEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = frasesEntity.class,version = 2 ,exportSchema = false)
public abstract class FrasesRoomDataBase extends RoomDatabase {


   public abstract FrasesDao DAO();

    private static volatile FrasesRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 10000;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static FrasesRoomDataBase getRoomDataBase(final Context context) {

        if (INSTANCE == null) {
            synchronized (FrasesRoomDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FrasesRoomDataBase.class, "frases_v1_DataBase")
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

                    FrasesDao dao = INSTANCE.DAO();
                    dao.deleteAll();
                    frasesEntity newFrase = new frasesEntity(true);
                    dao.insert(newFrase);

                }
            });
        }
    };
}
