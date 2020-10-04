package com.GF.verbum.DB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.GF.verbum.DB.DAO.SintaxisDao;
import com.GF.verbum.DB.Entities.SintaxisEntity;

import java.util.Locale;
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
                if(Locale.getDefault().getLanguage()=="es") {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                SintaxisRoomDataBase.class, "Sintaxis_1_DataBase").addCallback(llamada).build();
                    }
                }else if(Locale.getDefault().getLanguage()=="en"){
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                SintaxisRoomDataBase.class, "Sintaxis_1_DataBase_en").addCallback(call).build();
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

                    SintaxisDao dao = INSTANCE.DAO();
                    dao.deleteAll();
                SintaxisEntity nuevoTipo = new SintaxisEntity("Sustantivo","Los sustantivos denotan entidades, materiales o inmateriales, de toda naturaleza y condición: personas, animales, cosas reales… Esta diversidad de funciones les permite agruparlos en varias clases gramaticales. Los sustantivos se dividen en dos grandes grupos tradicionalmente en común y propios.\n" +
                        "1.\tEl nombre común o apelativo, es para todos los individuos de una clase. Se caracteriza por clasificar los sustantivos según una serie de rasgos comunes que los distinguen. \n" +
                        "2.\tEl nombre propio identifica a un ser entre los demás que forman el grupo, pero sin informar de sus rasgos característicos. \n" +
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
                            "1. Morfología: los adjetivos son palabras variables, que posee género, número y grado. \n" +
                            " \n" +
                            "2. Semántica y sintáctica. Los Adjetivos a su vez se pueden dividir entres clases en función de sus distintos significados y sus propiedades sintácticas: \n" +
                            " \n" +
                            "1. Adjetivos calificativos. Son aquellos que expresan propiedad, pueden ir antes del nombre (con una interpretación no restrictiva) o pospuestos (con una interpretación restrictiva).\n" +
                            "\n" +
                            "2. Adjetivos relacionales. Que relacionan el sustantivo que acompañan y el sustantivo que el propio adjetivo tiene como lexema. Podemos encontrar en esta clasificación adjetivos de tipo clarificativos o temáticos. \n" +
                            " \n" +
                            "3. Adjetivos adverbiales. Son aquellos que su significado es cercano al de los adverbios. En este grupo estarían los modales, los marcadores de la intensidad o referencia, los circunstanciales o los aspectuales.  \n");
                    dao.insert(nuevoTipo);
                    nuevoTipo = new SintaxisEntity("Pronombre","Reciben este nombre por que presentan rasgos gramaticales de persona, concordando en género y número con el verbo que es el sujeto del predicado. Además, designan a los participantes del enunciado. Son elementos definidos al igual que los artículos determinados y con los nombres propios. Al igual que estos últimos designan de modo unívoco a su referente. ");
                    dao.insert(nuevoTipo);
                    nuevoTipo = new SintaxisEntity("Adverbio","El adverbio es una clase de palabra invariable que se caracteriza por la ausencia de flexión y la capacidad de establecer una relación de modificación. Los adverbios modifican a los verbos, adjetivos y a otros adverbios. Existen cuatro criterios principales para clasificar los adverbios:\n" +
                            "1.\tSu estructura morfológica: los simples y los compuestos que son los acabados principalmente en -mente y -ante.\n" +
                            "2.\t Su significado: cantidad, lugar, tiempo, manera, afirmación, negación y duda. \n" +
                            "3.\tSu naturaleza gramatical: léxicos y gramaticales.\n" +
                            "4.\tSu incidencia sintáctica: argumentales, atributivos y adjuntos. \n");
                    dao.insert(nuevoTipo);
                    nuevoTipo = new SintaxisEntity("Verbo","El verbo es la clase de palabras que se caracteriza por tener variación de persona, número, tiempo, modo y aspecto. Los verbos actúan como sujeto del predicado de la oración y se presentan en formas personales o no personales.\n" +
                            "En su forma no personal encontramos: los verbos en infinitivo (-ar, -er, -ir), los verbos en gerundio (-ando, -iendo) y los verbos en participio (-do), que en algunos casos es variable (-da, -dos, -das). \n" +
                            "Las formas personales del verbo son las formas conjugadas en un tiempo verbal, esta concuerda en persona y número con el sujeto de la oración, están divididas en:\n" +
                            "1.\tEl modo verbal: el indicativo (información real, conocida y cierta), el subjuntivo (información virtual, inespecífica o no verificada) y el imperativo (mandato o ruego).\n" +
                            "2.\tEl tiempo verbal: presente, pasado o futuro, pudiendo ser simples (un solo verbo) o compuestos (donde hay un verbo auxiliar conjugado unido al principal en participio).\n" +
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
                            "1. Conjunciones subordinadas, que introducen oraciones que se incluyen dentro de otras más amplias. A diferencia de los pronombres relativos las conjunciones subordinadas no cumplen ninguna función en la oración que están presentes. \n" +
                            "2. Establecen relaciones entre dos o más segmentos donde por lo general pertenecen a la misma categoría y nivel de complejidad. Si hay más de dos elementos, en general solo preceden al último. A su vez se dividen en copulativas (unen dos segmentos), disyuntivas (que dan a elegir) o adversativas (oposición entre alternativas). \n" +
                            "\n ");
                    dao.insert(nuevoTipo);
                    nuevoTipo = new SintaxisEntity("Interjección","La interjección es una clase de palabra que esta especializada en la formación de enunciados exclamativos, esta comunica sentimientos e impresiones, relaciones afectivas o induce a la acción. Algunas de ellas codifican determinados comportamientos sociales (saludos, felicitaciones, despedidas…). A diferencia de otras palabras, las interjecciones no modifican ni determinan las demás palabras. \n" +
                            "Las interjecciones se dividen en dos grandes grupos:\n" +
                            "1.\tLas apelativas o directivas orientadas ha que el oyente realice la acción o provoque una reacción emocional en él.\n" +
                            "2.\tLas Expresivas o sintomáticas donde el hablante manifiesta sus sensaciones, sentimientos y estados de ánimo. \n ");
                    dao.insert(nuevoTipo);

                    nuevoTipo = new SintaxisEntity("Artículo","Es una clase de palabra que permite delimitar la denotación del grupo nominal del que pertenece, es decir, especifica si lo designado por ese enunciado constituye o no información consabida. En el latín clásico no existían los artículos, su uso en lenguas románicas se debe a procesos de gramaticalización. El artículo se caracteriza por preceder a todos los demás componentes del grupo nominal, además, de los cuantificadores que pueda haber. Existen dos clases principales de artículos.\n" +
                            "1.\tLos artículos determinados, que en la tradición gramatical anuncia el género y número del sustantivo, es átono y actúa como elemento nominalizador o sustantivador en los grupos nominales que carecen de sustantivo explícito.  \n" +
                            "2.\tLos artículos indeterminados pueden ser tónicos y tiene una gran restricción con los cuantificadores que acompaña. \n");
                    dao.insert(nuevoTipo);

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

                    SintaxisDao dao = INSTANCE.DAO();
                    dao.deleteAll();
                    SintaxisEntity nuevoTipo = new SintaxisEntity("Noun","Nouns denote entities, material or immaterial, of all nature and condition: people, animals, real things ... This diversity of functions allows them to group them into various grammatical classes. Nouns are divided into two large groups traditionally in common and proper name. \n "+
                            "1. The common or appellative name is for all individuals in a class. It is characterized by classifying nouns according to a series of common features that distinguish them. \n" +
                            "2. The proper name identifies a being among the others that make up the group, but without reporting its characteristic features. \n" +
                            "In turn, common names can be divided into other subgroups. \n" +
                            "1. Countables, are nouns that can be counted or enumerated. \n" +
                            "2. Not countable, they are nouns that designate quantities that are interpreted as substances or matters. \n" +
                            "3.  Individuals, denoting people, animals or things that are perceived as a single entity. \n" +
                            "4.  Collectives, which refer to sets of people or things in their singular form.\n" +
                            "5.  Abstracts, which refer to what is not material. \n" +
                            "6. Concrete, which refer to beings that are attributed abstract nouns.");
                    dao.insert(nuevoTipo);
                    nuevoTipo = new SintaxisEntity("Adjective","It is responsible for modifying or reaffirming the accompanying noun. It performs the function of modifier, adjacent, complement of the name, attribute or predicative complement. \n" +
                            "\n" +
                            "We could include within this grammatical category all the words that modify the noun and agree with it (adjectives, demonstratives, possessives ...). All these groups could be included within the determiners at first sight, but the adjectives unlike these they do not have the ability to allow a name to function as a subject. \n "+
                            "\n" +
                            "When defining adjectives we can make two distinctions: \n" +
                            "\n" +
                            "1. Morphology: adjectives are variable words, which have gender, number and degree. \n" +
                            "\n" +
                            "2. Semantics and syntactics. Adjectives in turn can be divided into classes based on their different meanings and syntactic properties: \n" +
                            "\n" +
                            "1. Qualifying adjectives. They are those that express ownership, they can come before the name (with a non-restrictive interpretation) or postponed (with a restrictive interpretation). \n" +
                            "\n" +
                            "2. Relational adjectives. That relate the noun they accompany and the noun that the adjective itself has as its lexeme. We can find in this classification clarifying or thematic adjectives. \n" +
                            "\n" +
                            "3. Adverbial adjectives. They are those whose meaning is close to that of adverbs. In this group would be the modals, the markers of intensity or reference, the circumstantial or the aspectual. \n");
                    dao.insert(nuevoTipo);
                    nuevoTipo = new SintaxisEntity("Pronoun","They receive this name because they present grammatical characteristics of a person, agreeing in gender and number with the verb that is the subject of the predicate. In addition, they designate the participants of the statement. They are defined elements like the specific articles and with the proper names. Like the latter, they uniquely designate their referent.");
                    dao.insert(nuevoTipo);
                    nuevoTipo = new SintaxisEntity("Adverb","The adverb is an invariable word class characterized by the absence of inflection and the ability to establish a relationship of modification. Adverbs modify verbs, adjectives, and other adverbs. There are four main criteria for classifying adverbs: \n"+
                            "1. Its morphological structure: the simple ones and the compounds that are mainly finished in -mente and -ante."+"\n"+
                            "2. Its meaning: quantity, place, time, manner, affirmation, negation and doubt.\n" +
                            "3. Its grammatical nature: lexical and grammatical.\n" +
                            "4. Its syntactic incidence: arguments, attributives and attachments.");
                    dao.insert(nuevoTipo);
                    nuevoTipo = new SintaxisEntity("Verb","The verb is the class of words that is characterized by having variation of person, number, time, mood and aspect. Verbs act as the subject of the predicate of the sentence and are presented in personal or non-personal forms. \n "+
                            "In its non-personal form we find: verbs in the infinitive (-ar, -er, -ir), verbs in gerund (-ando, -iendo) and verbs in participle (-do), which in some cases is variable (-da, -dos, -das). \n "+
                            "The personal forms of the verb are the conjugated forms in a verb tense, this agrees in person and number with the subject of the sentence, they are divided into: \n" +
                            "1.The verb mood: the indicative (real, known and certain information), the subjunctive (virtual, unspecific or unverified information) and the imperative (command or request). \n" +
                            "2. The verb tense: present, past or future, which can be simple (a single verb) or compound (where there is a conjugated auxiliary verb joined to the main participle).");
                    dao.insert(nuevoTipo);
                    nuevoTipo = new SintaxisEntity("Preposition","Prepositions are invariable words, for the most part unstressed, characterized by introducing a complement. They usually introduce noun phrases, but they can also introduce others such as adverbial, prepositional, substantive subordinate phrases and relative sentences without an express antecedent. The preposition plus its complement can have an argument function, a regime complement or indirect complements. \n "+
                         "The list of prepositions provided by the RAE (2009) is as follows: \n" +
                            "\n" +
                            "A, ante, bajo, cabe, con, contra, de, desde, en, entre, hacia, hasta, para, por, según, sin, so, sobre, tras, durante, mediante, versus, vía.");
                    dao.insert(nuevoTipo);
                    nuevoTipo = new SintaxisEntity("Conjunction","They are responsible for establishing relationships between different sentences. Within conjunctions we can find two types: \n" +
                            "\n" +
                            "1. Subordinate conjunctions, which introduce sentences that are included within broader ones. Unlike relative pronouns, subordinate conjunctions do not fulfill any function in the sentence they are present. \n" +
                            "2. They establish relationships between two or more segments where they generally belong to the same category and level of complexity. If there are more than two elements, in general they only precede the last one. In turn, they are divided into copulatives (they join two segments) , disjunctive (giving a choice) or adversative (opposition between alternatives). "
                            );
                    dao.insert(nuevoTipo);
                    nuevoTipo = new SintaxisEntity("Interjection","Interjection is a kind of word that is specialized in the formation of exclamatory sentences, it communicates feelings and impressions, affective relationships or induces action. Some of them encode certain social behaviors (greetings, congratulations, farewells ...). Unlike other words, interjections do not modify or determine the other words. \n "+
                            "Interjections are divided into two large groups: \n" +
                            "1. The appeals or directed directives have the listener perform the action or provoke an emotional reaction in him. \n" +
                            "2. The expressive or symptomatic ones where the speaker expresses his sensations, feelings and moods.");
                    dao.insert(nuevoTipo);

                    nuevoTipo = new SintaxisEntity("Article","It is a kind of word that allows delimiting the denotation of the nominal group to which it belongs, that is, it specifies whether or not what is designated by that statement constitutes known information. In classical Latin there were no articles, their use in Romance languages is due to to grammaticalization processes. The article is characterized by preceding all the other components of the nominal group, in addition to any quantifiers that may exist. There are two main classes of articles. \n "+
                            "1. Determined articles, which in the grammatical tradition announce the gender and number of the noun, is unstressed and acts as a nominalizing or substantivating element in nominal groups that lack an explicit noun. \n" +
                            "2. Indeterminate articles can be tonic and you have a great restriction with accompanying quantifiers.");
                    dao.insert(nuevoTipo);

                }
            });
        }

    };
}
