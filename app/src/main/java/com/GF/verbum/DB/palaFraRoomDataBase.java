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

        dao.insert(new palfraEntity(1,304,1));
        dao.insert(new palfraEntity(1,414,2));
        dao.insert(new palfraEntity(1,413,3));
        dao.insert(new palfraEntity(1,415,4));
        dao.insert(new palfraEntity(1,65,5));

        dao.insert(new palfraEntity(2,416,1));
        dao.insert(new palfraEntity(2,417,2));
        dao.insert(new palfraEntity(2,418,3));
        dao.insert(new palfraEntity(2,419,4));

        dao.insert(new palfraEntity(3,304,1));
        dao.insert(new palfraEntity(3,423,2));
        dao.insert(new palfraEntity(3,420,3));
        dao.insert(new palfraEntity(3,305,4));
        dao.insert(new palfraEntity(3,11,5));
        dao.insert(new palfraEntity(3,421,6));
        dao.insert(new palfraEntity(3,422,7));
        dao.insert(new palfraEntity(3,423,8));

        dao.insert(new palfraEntity(4,308,1));
        dao.insert(new palfraEntity(4,424,2));
        dao.insert(new palfraEntity(4,324,3));
        dao.insert(new palfraEntity(4,425,4));
        dao.insert(new palfraEntity(4,305,5));
        dao.insert(new palfraEntity(4,426,6));
        dao.insert(new palfraEntity(4,242,7));
        dao.insert(new palfraEntity(4,427,8));

        dao.insert(new palfraEntity(5,308,1));
        dao.insert(new palfraEntity(5,423,2));
        dao.insert(new palfraEntity(5,324,3));
        dao.insert(new palfraEntity(5,428,4));
        dao.insert(new palfraEntity(5,422,5));
        dao.insert(new palfraEntity(5,429,6));
        dao.insert(new palfraEntity(5,430,7));


        dao.insert(new palfraEntity(6,304,1));
        dao.insert(new palfraEntity(6,431,2));
        dao.insert(new palfraEntity(6,432,3));
        dao.insert(new palfraEntity(6,304,4));
        dao.insert(new palfraEntity(6,433,5));
        dao.insert(new palfraEntity(6,434,6));






    }
}