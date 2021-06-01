package com.GF.verbum.DB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.GF.verbum.DB.DAO.PalfraDao;
import com.GF.verbum.DB.Entities.palfraEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = palfraEntity.class,version = 1 ,exportSchema = false)
public  abstract  class palaFraRoomDataBase extends RoomDatabase {
    public abstract PalfraDao DAO();

    private static volatile palaFraRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 10000;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static palaFraRoomDataBase getRoomDataBase(final Context context) {

        if (INSTANCE == null) {
            synchronized (palaFraRoomDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            palaFraRoomDataBase.class, "PALFRA_v1_DataBase")
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

                    PalfraDao dao = INSTANCE.DAO();
                    dao.deleteAll();
                    primeraFrase(dao);

                }
            });
        }
    };

    private static void primeraFrase(PalfraDao dao) {

        palfraEntity palfra = new palfraEntity(1,304,1);
        dao.insert(palfra);
        palfra=new palfraEntity(1,414,2);
        dao.insert(palfra);
        palfra=new palfraEntity(1,413,3);
        dao.insert(palfra);
        palfra=new palfraEntity(1,415,4);
        dao.insert(palfra);
        palfra=new palfraEntity(1,65,5);
        dao.insert(palfra);
    }
}