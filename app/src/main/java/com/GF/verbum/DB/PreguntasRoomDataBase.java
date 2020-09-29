package com.GF.verbum.DB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.GF.verbum.DB.DAO.PalabrasDao;
import com.GF.verbum.DB.DAO.PreguntasDao;
import com.GF.verbum.DB.Entities.PalabrasEntity;
import com.GF.verbum.DB.Entities.PreguntasEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = PreguntasEntity.class,version = 1 ,exportSchema = false)

public abstract class PreguntasRoomDataBase extends RoomDatabase {

    public abstract PreguntasDao DAO();
    private static volatile PreguntasRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static PreguntasRoomDataBase getRoomDataBase(final Context context) {

        if (INSTANCE==null){
            synchronized (PreguntasRoomDataBase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            PreguntasRoomDataBase.class,"Preguntas_1_DataBase").addCallback(llamada).build();
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

                    PreguntasDao dao = INSTANCE.DAO();
                    dao.deleteAll();

                    PreguntasEntity pregunta = new PreguntasEntity("¿Admite género y número?",true,false,true,false,true,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Presenta coorcondancia con la palabra que acompañan?",true,true,true,false,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Precede a todos los componenetes del grupo nominal al que pertenece?",true,true,true,false,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Es una palabra invariable?",false,false,false,false,false,true,true,true,true);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Puede funcionar  como sujeto?",true,false,false,true,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Denota entidades, materiales o inmateriales?",true,false,false,false,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Aparecen acompañados de determinativos?",true,false,false,false,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Modifica a la palabra que acompaña?",false,false,true,false,false,true,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Tiene significado de cantidad, lugar, tiempo, manera, afirmación, negación o duda?",false,false,false,false,false,true,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Puede no variar en el plural?",false,false,true,false,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Puede denotar propiedades o cualidades?",false,false,true,false,false,true,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Presenta rasgos gramaticales de persona?",false,false,false,true,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Posee grado?",false,false,true,false,false,true,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Su significado debe interpretarse en función de la situación comunicativa?",false,false,false,true,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Especifica si lo designado por ese segmento constituye o no información consabida?",false,true,false,false,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Se puede conjugar?",false,false,false,false,true,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Comunica sentimientos e impresiones?",false,false,false,false,false,false,false,false,true);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Codifica verbalmente determinados comportamientos sociales convencionales?",false,false,false,false,false,false,false,false,true);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Esta especializada en la formación de enunciados exclamativos?",false,false,false,false,false,false,false,false,true);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Modifica a la palabra que acompaña para que fundione como sujeto ?",false,true,false,false,false,false,false,false,false);
                    dao.insert(pregunta);
                }
            });
        }

    };

}
