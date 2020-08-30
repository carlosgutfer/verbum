package com.GF.verbum.DB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.GF.verbum.DB.DAO.PalabrasDao;
import com.GF.verbum.DB.Entities.PalabrasEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = PalabrasEntity.class,version = 1 ,exportSchema = false)

public abstract class PalabrasRoomDataBase extends RoomDatabase {

    public abstract PalabrasDao DAO();
    private static volatile PalabrasRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static PalabrasRoomDataBase getRoomDataBase(final Context context) {

        if (INSTANCE==null){
            synchronized (PalabrasRoomDataBase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            PalabrasRoomDataBase.class,"Palabras_1_DataBase").addCallback(llamada).build();
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

                    PalabrasDao dao = INSTANCE.DAO();
                    dao.deleteAll();

                    PalabrasEntity palabra = new PalabrasEntity("HIERRO",true,false,false,false,false,false,false,false,false);
                    dao.insert(palabra);
                     palabra = new PalabrasEntity("PATO",true,false,false,false,false,false,false,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("MESA",true,false,false,false,false,false,false,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("ESTRECHO",true,false,true,false,false,false,false,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("ROJO",true,false,true,false,false,false,false,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("UNA",false,true,false,false,false,false,false,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("LA",false,false,true,false,false,false,false,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("LO",false,true,false,false,false,false,false,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("YO",false,false,false,true,false,false,false,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("TE",false,false,false,true,false,false,false,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("VOS",false,false,false,true,false,false,false,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("ALGUNA",false,false,true,true,false,false,false,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("TRES",true,false,true,true,false,false,false,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("VIVIR",false,false,false,false,true,false,false,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("LEER",false,false,false,false,true,false,false,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("ESCRIBIR",false,false,false,false,true,false,false,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("TRANQUILAMENTE",false,false,false,false,false,true,false,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("SUMAMENTE",false,false,false,false,false,true,false,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("INCLUSO",false,false,false,false,false,true,false,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("ANTE",false,false,false,false,false,false,true,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("PARA",false,false,false,false,false,false,true,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("CABE",false,false,false,false,false,false,true,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("PERO",false,false,false,false,false,false,false,true,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("NI",false,false,false,false,false,false,false,true,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("SINO",false,false,false,false,false,false,false,true,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("AY",false,false,false,false,false,false,false,false,true);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("EPA",false,false,false,false,false,false,false,false,true);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("OLÉ",false,false,false,false,false,false,false,false,true);
                    dao.insert(palabra);




                }
            });
        }

    };



}
