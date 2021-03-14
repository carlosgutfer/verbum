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

import java.util.Locale;
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
                    if(Locale.getDefault().getLanguage()=="es"){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            PreguntasRoomDataBase.class,"Preguntas_1_DataBase").addCallback(llamada).build();}
                    else if(Locale.getDefault().getLanguage()=="en"){
                        INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                                PreguntasRoomDataBase.class,"Preguntas_1_DataBase_en").addCallback(call).build();
                    } else{
                        INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                                PreguntasRoomDataBase.class,"Preguntas_1_DataBase").addCallback(llamada).build();
                    }
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
                    pregunta = new PreguntasEntity("¿Presenta concordancia con la palabra que acompañan?",true,true,true,false,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Precede a todos los componentes del grupo nominal al que pertenece?",true,true,true,false,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Es una palabra invariable?",false,false,false,false,false,true,true,true,true);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Puede funcionar  como sujeto de una oración?",true,false,false,true,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Denota entidades, materiales o inmateriales?",true,false,false,false,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Aparecen acompañados de determinativos?",true,false,false,false,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Modifica a la palabra que acompaña?",false,false,true,false,false,true,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿El grupo sintáctico tiene significado de cantidad, lugar, tiempo, manera, afirmación, negación o duda?",false,false,false,false,false,true,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Puede escribirse igual singular y en  plural?",false,false,true,false,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Puede denotar propiedades o cualidades de los objetos?",false,false,true,false,false,true,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Presenta rasgos gramaticales de persona?",false,false,false,true,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Posee grado?",false,false,true,false,false,true,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Su significado debe interpretarse en función de la oración?",false,false,false,true,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Especifica si lo designado por ese segmento de la oración constituye o no información ya sabida?",false,true,false,false,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Se puede conjugar?",false,false,false,false,true,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Comunica sentimientos e impresiones?",false,false,false,false,false,false,false,false,true);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Expresa por sí sola un estado de ánimo o capta la atención del oyente?",false,false,false,false,false,false,false,false,true);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Está especializada en la formación de enunciados exclamativos?",false,false,false,false,false,false,false,false,true);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("¿Modifica a la palabra que acompaña para que funcione como sujeto ?",false,true,false,false,false,false,false,false,false);
                    dao.insert(pregunta);
                }
            });
        }

    };

    private static RoomDatabase.Callback call = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {

            super.onCreate(db);

            databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {

                    PreguntasDao dao = INSTANCE.DAO();
                    dao.deleteAll();

                    PreguntasEntity pregunta = new PreguntasEntity("Does it support gender and number?",true,false,true,false,true,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("Does it match the word that accompanies?",true,true,true,false,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("Does it precede all the components of the nominal group to which it belongs?",true,true,true,false,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("Is it an invariable word?",false,false,false,false,false,true,true,true,true);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("Can it function as the subject of a sentence?",true,false,false,true,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("Does it denote entities, material or immaterial?",true,false,false,false,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("Are they accompanied by determinatives?",true,false,false,false,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("Modify the accompanying word?",false,false,true,false,false,true,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("Does the syntactic group have the meaning of quantity, place, time, manner, affirmation, negation or doubt?",false,false,false,false,false,true,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("Can the singular and plural be written the same?",false,false,true,false,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("Can it denote properties or qualities of objects?",false,false,true,false,false,true,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("Does it present grammatical characteristics of person?",false,false,false,true,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("Does it have a degree?",false,false,true,false,false,true,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("Should its meaning be interpreted in terms of the sentence?",false,false,false,true,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("Does it specify whether or not what is designated by that segment of the sentence constitutes information already known?",false,true,false,false,false,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("Can it be conjugated?",false,false,false,false,true,false,false,false,false);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("Does it communicate feelings and impressions?",false,false,false,false,false,false,false,false,true);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("Does it express a mood by itself or does it capture the listener's attention? ",false,false,false,false,false,false,false,false,true);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("Is it specialized in the formation of exclamatory sentences?",false,false,false,false,false,false,false,false,true);
                    dao.insert(pregunta);
                    pregunta = new PreguntasEntity("Does it modify the accompanying word so that it merges as a subject?",false,true,false,false,false,false,false,false,false);
                    dao.insert(pregunta);
                }
            });
        }

    };

}
