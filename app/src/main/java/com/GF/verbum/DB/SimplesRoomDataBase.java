package com.GF.verbum.DB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.GF.verbum.DB.DAO.PreguntasDao;
import com.GF.verbum.DB.DAO.SimplesDao;
import com.GF.verbum.DB.Entities.PreguntasEntity;
import com.GF.verbum.DB.Entities.simplesEntity;

import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = simplesEntity.class,version = 1 ,exportSchema = false)

public abstract class SimplesRoomDataBase extends RoomDatabase {

    public abstract SimplesDao DAO();

    private static volatile SimplesRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 10000;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static SimplesRoomDataBase getRoomDataBase(final Context context) {

        if (INSTANCE==null){
            synchronized (SimplesRoomDataBase.class){
                if(INSTANCE==null){
                        INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                                SimplesRoomDataBase.class,"Preguntas_1_DataBase").addCallback(llamada).build();

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

                    SimplesDao dao = INSTANCE.DAO();
                    dao.deleteAll();

                    dao.insert(new simplesEntity("Predicativa"));
                    dao.insert(new simplesEntity("Activa"));
                    dao.insert(new simplesEntity("Transitiva"));
                    dao.insert(new simplesEntity("Reflexiva"));
                    dao.insert(new simplesEntity("Recíproca"));
                    dao.insert(new simplesEntity("Intransitiva"));
                    dao.insert(new simplesEntity("Pasiva"));
                    dao.insert(new simplesEntity("Pasiva refleja"));
                    dao.insert(new simplesEntity("Copulativa"));
                    dao.insert(new simplesEntity("Enunciativa"));
                    dao.insert(new simplesEntity("Interrogativa"));
                    dao.insert(new simplesEntity("Exclamativa"));
                    dao.insert(new simplesEntity("Dubitativa"));
                    dao.insert(new simplesEntity("Desiderativa"));
                    dao.insert(new simplesEntity("Imperativa"));
                    dao.insert(new simplesEntity("Impersonal"));

                }
            });
        }

    };

}
