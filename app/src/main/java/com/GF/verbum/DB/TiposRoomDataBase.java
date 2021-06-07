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


@Database(entities = tiposEntity.class,version = 3 ,exportSchema = false)

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
                    newTipo = new tiposEntity("predicativa",1);
                    dao.insert(newTipo);
                    newTipo = new tiposEntity("activa",2);
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("transitiva",3);
                    dao.insert(newTipo);
                    newTipo = new tiposEntity("reflexiva",4);
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("recíproca",4);
                    dao.insert(newTipo);
                    newTipo = new tiposEntity("intransitiva",3);
                    dao.insert(newTipo);
                    newTipo = new tiposEntity("pasiva",2);
                    dao.insert(newTipo);
                    newTipo = new tiposEntity("pasiva refleja",8);
                    dao.insert(newTipo);
                    newTipo = new tiposEntity("copulativa",1);
                    dao.insert(newTipo);
                    //actitud del hablante
                            //enunciativa
                        newTipo = new tiposEntity("enunciativa");
                        dao.insert(newTipo);
                    newTipo= new tiposEntity("afirmativas",11);
                        dao.insert(newTipo);
                    newTipo= new tiposEntity("negativas",11);
                        dao.insert(newTipo);
                            //interrogativa
                    newTipo= new tiposEntity("interrogativa");
                        dao.insert(newTipo);
                    newTipo= new tiposEntity("directa",14);
                        dao.insert(newTipo);
                    newTipo= new tiposEntity("indirecta",14);
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("total",15);
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("parcial",15);
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("total",14);
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("parcial",14);
                    dao.insert(newTipo);

                    newTipo= new tiposEntity("exclamativa");
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("dubitativa");
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("desiderativa");
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("imperativa");
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("impersonal");
                    dao.insert(newTipo);
                    //Compuestas
                    newTipo= new tiposEntity("compuesta");
                    dao.insert(newTipo);
                    //coordinadas
                    newTipo= new tiposEntity("coordinadas",24);
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("copulativas",25);
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("disyuntivas",25);
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("distributivas",25);
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("adversativas",25);
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("explicativas",25);
                    dao.insert(newTipo);
                    //subordinadas
                    newTipo= new tiposEntity("subordinadas",24);
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("sustantivas",31);
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("adjetivas",31);
                    dao.insert(newTipo);
                    newTipo= new tiposEntity("adverbiales",31);
                    dao.insert(newTipo);
                    //yuxtapuestas
                    newTipo= new tiposEntity("yuxtapuestas",24);
                    dao.insert(newTipo);
                }
            });
        }

    };

}
