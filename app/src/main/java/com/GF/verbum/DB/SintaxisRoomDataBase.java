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
                SintaxisEntity nuevoTipo = new SintaxisEntity("Sustantivo","Los sustantivos denotan entidades, materiales o inmateriales, de toda naturaleza y condición: personas, animales, cosas reales… Esta diversidad de funciones les permite agruparlos en varias clases gramaticales. Los sustantivos se dividen en dos grandes grupos tradicionalmente en común y propios.\n" +
                        "•\tEl nombre común o apelativo, es para todos los individuos de una clase. Se caracteriza por clasificar los sustantivos según una serie de rasgos comunes que los distinguen. \n" +
                        "•\tEl nombre propio identifica a un ser entre los demás que forman el grupo, pero sin informar de sus rasgos característicos. \n" +
                        "A su vez, los nombres comunes los podemos dividir en otros subgrupos.\n" +
                        "1.\tContables, son sustantivos que se pueden contar o enumerar.\n" +
                        "2.\tNo contables, son sustantivos que designan magnitudes que se interpretan como sustancias o materias.\n" +
                        "3.\tIndividuales, que denotan personas, animales o cosas que se perciben como una única entidad. \n" +
                        "4.\tColectivos, que hacen referencia a conjuntos de personas o cosas en su forma singular. \n" +
                        "5.\tAbstractos, que hacen referencia a lo que no es material. \n" +
                        "6.\tConcreto, que hacen referencia a seres que se les atribuye los sustantivos abstractos. \n");
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
                    nuevoTipo = new SintaxisEntity("Pronombre","Reciben este nombre por que presentan rasgos gramaticales de persona, concordando en género y número con el verbo que es el sujeto del predicado. Además, designan a los participantes del enunciado. Son elementos definidos al igual que los artículos determinados y con los nombres propios. Al igual que estos últimos designan de modo unívoco a su referente. ");
                    dao.insert(nuevoTipo);
                    nuevoTipo = new SintaxisEntity("Adverbio","El adverbio es una clase de palabra invariable que se caracteriza por la ausencia de flexión y la capacidad de establecer una relación de modificación. Los adverbios modifican a los verbos, adjetivos y a otros adverbios. Existen cuatro criterios principales para clasificar los adverbios:\n" +
                            "1.\tSu estructura morfológica: los simples y los compuestos que son los acabados principalmente en -mente y -ante.\n" +
                            "2.\t2. Su significado: cantidad, lugar, tiempo, manera, afirmación, negación y duda. \n" +
                            "3.\tSu naturaleza gramatical: léxicos y gramaticales.\n" +
                            "4.\tSu incidencia sintáctica: argumentales, atributivos y adjuntos. \n");
                    dao.insert(nuevoTipo);
                    nuevoTipo = new SintaxisEntity("Verbo","El verbo es la clase de palabras que se caracteriza por tener variación de persona, número, tiempo, modo y aspecto. Los verbos actúan como sujeto del predicado de la oración y se presentan en formas personales o no personales.\n" +
                            "En su forma no personal encontramos: los verbos en infinitivo (-ar, -er, -ir), los verbos en gerundio (-ando, -iendo) y los verbos en participio (-do), que en algunos casos es variable (-da, -dos, -das). \n" +
                            "Las formas personales del verbo son las formas conjugadas en un tiempo verbal, esta concuerda en persona y número con el sujeto de la oración, están divididas en:\n" +
                            "•\tEl modo verbal: el indicativo (información real, conocida y cierta), el subjuntivo (información virtual, inespecífica o no verificada) y el imperativo (mandato o ruego).\n" +
                            "•\tEl tiempo verbal: presente, pasado o futuro, pudiendo ser simples (un solo verbo) o compuestos (donde hay un verbo auxiliar conjugado unido al principal en participio).\n" +
                            "\n");
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
                    nuevoTipo = new SintaxisEntity("Interjección","La interjección es una clase de palabra que esta especializada en la formación de enunciados exclamativos, esta comunica sentimientos e impresiones, relaciones afectivas o induce a la acción. Algunas de ellas codifican determinados comportamientos sociales (saludos, felicitaciones, despedidas…). A diferencia de otras palabras, las interjecciones no modifican ni determinan las demás palabras. \n" +
                            "Las interjecciones se dividen en dos grandes grupos:\n" +
                            "-\tLas apelativas o directivas orientadas ha que el oyente realice la acción o provoque una reacción emocional en él.\n" +
                            "-\tLas Expresivas o sintomáticas donde el hablante manifiesta sus sensaciones, sentimientos y estados de ánimo. \n ");
                    dao.insert(nuevoTipo);

                    nuevoTipo = new SintaxisEntity("Artículo","Es una clase de palabra que permite delimitar la denotación del grupo nominal del que pertenece, es decir, especifica si lo designado por ese enunciado constituye o no información consabida. En el latín clásico no existían los artículos, su uso en lenguas románicas se debe a procesos de gramaticalización. El artículo se caracteriza por preceder a todos los demás componentes del grupo nominal, además, de los cuantificadores que pueda haber. Existen dos clases principales de artículos.\n" +
                            "1.\tLos artículos determinados, que en la tradición gramatical anuncia el género y número del sustantivo, es átono y actúa como elemento nominalizador o sustantivador en los grupos nominales que carecen de sustantivo explícito.  \n" +
                            "2.\tLos artículos indeterminados pueden ser tónicos y tiene una gran restricción con los cuantificadores que acompaña. \n");
                    dao.insert(nuevoTipo);

                }
            });
        }

    };
}
