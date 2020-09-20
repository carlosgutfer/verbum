package com.GF.verbum.DB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.GF.verbum.DB.DAO.SintaxisDao;
import com.GF.verbum.DB.Entities.SintaxisEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = SintaxisEntity.class,version = 1 ,exportSchema = false)
public  abstract class SintaxisRoomDataBase extends RoomDatabase {
    public abstract SintaxisDao DAO();
    private static volatile SintaxisRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static SintaxisRoomDataBase getRoomDataBase(final Context context) {

        if (INSTANCE==null){
            synchronized (PreguntasRoomDataBase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            SintaxisRoomDataBase.class,"Sintaxis_1_DataBase").addCallback(llamada).build();
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

                    SintaxisDao dao = INSTANCE.DAO();
                    dao.deleteAll();


                }
            });
        }

    };
}
