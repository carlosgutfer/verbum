package com.GF.verbum.DB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.GF.verbum.DB.DAO.TiposDao;
import com.GF.verbum.DB.Entities.tiposEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = tiposEntity.class,version = 1 ,exportSchema = false)

public abstract class TiposRoomDataBase extends RoomDatabase {

    public abstract TiposDao DAO();

    private static volatile TiposRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 10000;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static TiposRoomDataBase getRoomDataBase(final Context context) {

        if (INSTANCE==null){
            synchronized (TiposRoomDataBase.class){
                if(INSTANCE==null){
                        INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                                TiposRoomDataBase.class,"Preguntas_1_DataBase").addCallback(llamada).build();

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

                    TiposDao dao = INSTANCE.DAO();
                    dao.deleteAll();

                    dao.insert(new tiposEntity("Predicativa"));
                    dao.insert(new tiposEntity("Activa"));
                    dao.insert(new tiposEntity("Transitiva"));
                    dao.insert(new tiposEntity("Reflexiva"));
                    dao.insert(new tiposEntity("Recíproca"));
                    dao.insert(new tiposEntity("Intransitiva"));
                    dao.insert(new tiposEntity("Pasiva"));
                    dao.insert(new tiposEntity("Pasiva refleja"));
                    dao.insert(new tiposEntity("Copulativa"));
                    dao.insert(new tiposEntity("Enunciativa"));
                    dao.insert(new tiposEntity("Interrogativa"));
                    dao.insert(new tiposEntity("Exclamativa"));
                    dao.insert(new tiposEntity("Dubitativa"));
                    dao.insert(new tiposEntity("Desiderativa"));
                    dao.insert(new tiposEntity("Imperativa"));
                    dao.insert(new tiposEntity("Impersonal"));

                }
            });
        }

    };

}
