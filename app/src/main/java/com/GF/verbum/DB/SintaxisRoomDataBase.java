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
                SintaxisEntity nuevoTipo = new SintaxisEntity("Sustantivo","");
                    dao.insert(nuevoTipo);
                    nuevoTipo = new SintaxisEntity("Adjetivo","Se encarga de modificar o reafirmar al sustantivo que acompaña. Desempeña la función de modificador, adyacente, complemento del nombre, atributo o complemento predicativo. \n" +
                            "\n" +
                            "Podríamos incluir dentro de esta categoría gramatical a todas las palabras que modifican al sustantivo y concuerda con él (adjetivos, demostrativos, posesivos…). Todos estos grupos los podríamos englobar dentro de los determinantes a primera vista, pero los adjetivos a diferencia de estos no tienen la capacidad de permitir que un nombre funcione como sujeto.  \n" +
                            "\n" +
                            "A la hora de delimitar los adjetivos podemos hacer dos distinciones:\n" +
                            "\n" +
                            "1º Morfología: los adjetivos son palabras variables, que posee género, número y grado. \n" +
                            " \n" +
                            "2º Semántica y sintáctica. Los Adjetivos a su vez se pueden dividir entres clases en función de sus distintos significados y sus propiedades sintácticas: \n" +
                            " \n" +
                            "-Adjetivos calificativos. Son aquellos que expresan propiedad, pueden ir antes del nombre (con una interpretación no restrictiva) o pospuestos (con una interpretación restrictiva).\n" +
                            "\n" +
                            "-Adjetivos relacionales. Que relacionan el sustantivo que acompañan y el sustantivo que el propio adjetivo tiene como lexema. Podemos encontrar en esta clasificación adjetivos de tipo clarificativos o temáticos. \n" +
                            " \n" +
                            "-Adjetivos adverbiales. Son aquellos que su significado es cercano al de los adverbios. En este grupo estarían los modales, los marcadores de la intensidad o referencia, los circunstanciales o los aspectuales.  \n");
                    dao.insert(nuevoTipo);
                    nuevoTipo = new SintaxisEntity("Pronombre","");
                    dao.insert(nuevoTipo);
                    nuevoTipo = new SintaxisEntity("Adverbio","");
                    dao.insert(nuevoTipo);
                    nuevoTipo = new SintaxisEntity("Verbo","");
                    dao.insert(nuevoTipo);
                    nuevoTipo = new SintaxisEntity("Preposición","Las preposiciones son palabras invariables, en su mayor parte átonas, que se caracterizan por introducir un complemento. Suelen introducir sintagmas nominales, pero también pueden introducir otros como sintagmas adverbiales, preposicionales, subordinadas sustantivas y oraciones de relativo sin antecedente expreso.  La preposición más su complemento puede tener función de argumento, complemento de régimen o complementos indirecto. \n" +
                            " \n" +
                            "La lista de preposiciones que proporciona la RAE (2009) es la siguiente: \n" +
                            " \n" +
                            "A, ante, bajo, cabe, con, contra, de, desde, en, entre, hacia, hasta, para, por, según, sin, so, sobre, tras, durante, mediante, versus, vía. \n ");
                    dao.insert(nuevoTipo);
                    nuevoTipo = new SintaxisEntity("Conjucción","Se encargan de establecer relaciones entre distintas oraciones. Dentro de las conjunciones podemos encontrar dos tipos:\n" +
                            "\n" +
                            "1º Conjunciones subordinadas, que introducen oraciones que se incluyen dentro de otras más amplias. A diferencia de los pronombres relativos las conjunciones subordinadas no cumplen ninguna función en la oración que están presentes. \n" +
                            "2º Establecen relaciones entre dos o más segmentos donde por lo general pertenecen a la misma categoría y nivel de complejidad. Si hay más de dos elementos, en general solo preceden al último. A su vez se dividen en copulativas (unen dos segmentos), disyuntivas (que dan a elegir) o adversativas (oposición entre alternativas). \n" +
                            "\n ");
                    dao.insert(nuevoTipo);
                    nuevoTipo = new SintaxisEntity("Interjección","");
                    dao.insert(nuevoTipo);
                    nuevoTipo = new SintaxisEntity("Artículo","");
                    dao.insert(nuevoTipo);

                }
            });
        }

    };
}
