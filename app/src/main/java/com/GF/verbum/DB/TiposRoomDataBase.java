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
                                TiposRoomDataBase.class, "tipos_v1_DataBase")
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

                    TiposDao dao = INSTANCE.DAO();
                    dao.deleteAll();

                    tiposEntity newTipo = new tiposEntity("Simple");
                    dao.insert(newTipo);
                    newTipo = new tiposEntity("Predicativa");
                    dao.insert(newTipo);
                    newTipo = new tiposEntity("Activa");
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("Transitiva");
                    dao.insert(newTipo);
                    newTipo = new tiposEntity("Reflexiva");
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("Recíproca");
                    dao.insert(newTipo);
                    newTipo = new tiposEntity("Intransitiva");
                    dao.insert(newTipo);
                    newTipo = new tiposEntity("Pasiva");
                    dao.insert(newTipo);
                    newTipo = new tiposEntity("Pasiva refleja");
                    dao.insert(newTipo);
                    newTipo = new tiposEntity("Copulativa");
                    dao.insert(newTipo);
                    //actitud del hablante
                            //enunciativa
                        newTipo = new tiposEntity("Enunciativa");
                        dao.insert(newTipo);
                    newTipo= new tiposEntity("Afirmativas");
                        dao.insert(newTipo);
                    newTipo= new tiposEntity("Negativas");
                        dao.insert(newTipo);
                            //interrogativa
                    newTipo= new tiposEntity("Interrogativa");
                        dao.insert(newTipo);
                    newTipo= new tiposEntity("directa");
                        dao.insert(newTipo);
                    newTipo= new tiposEntity("indirecta");
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("total");
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("parcial");
                    dao.insert(newTipo);

                    newTipo= new tiposEntity("Exclamativa");
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("Dubitativa");
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("Desiderativa");
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("Imperativa");
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("Impersonal");
                    dao.insert(newTipo);
                    //Compuestas
                    newTipo= new tiposEntity("compuesta");
                    dao.insert(newTipo);
                    //coordinadas
                    newTipo= new tiposEntity("coordinadas");
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("disyuntivas");
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("distributivas");
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("adversativas");
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("explicativas");
                    dao.insert(newTipo);
                    //subordinadas
                    newTipo= new tiposEntity("subordinadas;");
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("adjetivas");
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("adverbiales");
                    dao.insert(newTipo);
                    //yuxtapuestas
                    newTipo= new tiposEntity("yuxtapuestas");
                    dao.insert(newTipo);
                }
            });
        }

    };

}
