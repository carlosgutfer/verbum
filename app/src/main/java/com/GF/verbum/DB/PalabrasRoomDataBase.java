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
                    Sustantivos(dao);

                    PalabrasEntity palabra = new PalabrasEntity("ESTRECHO",true,false,true,false,false,false,false,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("ROJO",true,false,true,false,false,false,false,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("TRES",true,false,true,false,false,false,false,false,false);
                    dao.insert(palabra);

                    Pronombres(dao);
                    Articulos(dao);
                    Adjetivos(dao);
                    PronombreArticulo(dao);
                    PronommbresAdjetivo(dao);
                    PronombreAdjetivoSustantivo(dao);
                    PronombreAdjetivoAdverbio(dao);
                    PalabrasConCuatroOMasFunciones(dao);
                    Verbo(dao);


                    palabra = new PalabrasEntity("TRANQUILAMENTE",false,false,false,false,false,true,false,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("SUMAMENTE",false,false,false,false,false,true,false,false,false);
                    dao.insert(palabra);
                    palabra = new PalabrasEntity("INCLUSO",false,false,false,false,false,true,false,false,false);
                    dao.insert(palabra);

                    Conjuncion(dao);
                    Preposicion(dao);
                    Interjeccion(dao);






                }
            });
        }

    };

    private static void Adjetivos(PalabrasDao dao) {
         PalabrasEntity palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ABURRIDO",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ÁCIDA",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ALEGRE",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("AMARGA",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("BLANDO",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ATREVIDO ",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ATREVIDA",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("BONITA",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("BUEN",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);

        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUAL",false,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
    }
    private static void AdjetivosSutantivos(PalabrasDao dao){
       PalabrasEntity palabra = new PalabrasEntity("ÁCIDO",true,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ANCHO",true,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ANCHA",true,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("AZUL",true,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("BLANCO",true,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("BLANCA",true,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("BLANDA",true,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("BONITO",true,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("BUENO",true,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("BUENA",true,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CENTRAL",true,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("COMÚN",true,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CONOCIDO",true,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CONOCIDA",true,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CONTENTO",true,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CONTENTA",true,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CORTO",true,false,true,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CORTA",true,false,true,false,false,false,false,false,false);
        dao.insert(palabra);

    }
    private static void AdjetivoSutantatioAdverbio(PalabrasDao dao){
        PalabrasEntity palabra = new PalabrasEntity("ALTO",true,false,true,false,false,true,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ALTA",true,false,true,false,false,true,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("AMARGO",true,false,true,false,false,true,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("BAJA",true,false,true,false,false,true,false,false,false);
        dao.insert(palabra);
    }
    private static void AdjetivoInterjeccion(PalabrasDao dao){
        PalabrasEntity palabra = new PalabrasEntity("CALIENTE",true,false,true,false,false,true,false,false,false);
        dao.insert(palabra);
    }
    private static void AdjetivoAdverbio(PalabrasDao dao){
      PalabrasEntity  palabra = new PalabrasEntity("CAPAZ",false,false,false,false,false,true,false,false,false);
        dao.insert(palabra);
    }

    private static void Sustantivos(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("HIERRO",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("PATO",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("MESA",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
         palabra = new PalabrasEntity("CREMA",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CAFÉ",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ESTRELLA",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("EXPLOSIÓN",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("GUITARRA",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("PLÁSTICO",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("NAVAJA",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("MARTILLO",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("LIBROS",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("EMBARCACIÓN",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("RUEDA",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("PELO",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("AGUJETA",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("LIBROS",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("PERRO",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("FELICIDAD",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("SATÉLITE",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("TEMPLO",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("LENTES",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("BOLÍGRAFO",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("BOTELLA",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CASTILLO",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ENANO",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CASA",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("LIBRO",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("PERSONA",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("METAL",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("TELÉFONO",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ESTADÍSTICA",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("MAPA",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("MENSAJE",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("COHETE",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("REY",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("EDIFICIO",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CÉSPED",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CANDIDTAO",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("RECIPIENTE",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("DIARIOS",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("SILLA",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("DEPORTE",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("RÚCULA",true,false,false,false,false,false,false,false,false);
        dao.insert(palabra);
    }
    private static void Conjuncion(PalabrasDao dao) {
         PalabrasEntity palabra = new PalabrasEntity("PERO",false,false,false,false,false,false,false,true,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("NI",false,false,false,false,false,false,false,true,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("SINO",false,false,false,false,false,false,false,true,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("Y",false,false,false,false,false,false,false,true,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("MAS",false,false,false,false,false,false,false,true,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("O",false,false,false,false,false,false,false,true,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("PORQUE",false,false,false,false,false,false,false,true,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("COMO",false,false,false,false,false,false,false,true,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("PUES",false,false,false,false,false,false,false,true,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("QUE",false,false,false,false,false,false,false,true,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("SI",false,false,false,false,false,false,false,true,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("AUNQUE",false,false,false,false,false,false,false,true,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ERGO",false,false,false,false,false,false,false,true,false);
        dao.insert(palabra);
    }
    private static void Preposicion(PalabrasDao dao) {
         PalabrasEntity palabra = new PalabrasEntity("A",false,false,false,false,false,false,true,false,false);
            dao.insert(palabra);
        palabra = new PalabrasEntity("ANTE",false,false,false,false,false,false,true,false,false);
        dao.insert(palabra);

        palabra = new PalabrasEntity("CABE",false,false,false,false,false,false,true,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CON",false,false,false,false,false,false,true,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CONTRA",false,false,false,false,false,false,true,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("DE",false,false,false,false,false,false,true,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("DESDE",false,false,false,false,false,false,true,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("EN",false,false,false,false,false,false,true,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ENTRE",false,false,false,false,false,false,true,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("HACIA",false,false,false,false,false,false,true,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("PARA",false,false,false,false,false,false,true,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("POR",false,false,false,false,false,false,true,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("SEGÚN",false,false,false,false,false,false,true,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("SIN",false,false,false,false,false,false,true,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("SO",false,false,false,false,false,false,true,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("TRAS",false,false,false,false,false,false,true,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("DURANTE",false,false,false,false,false,false,true,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("MEDIANTE",false,false,false,false,false,false,true,false,false);
        dao.insert(palabra);
    }
    private static void Verbo(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("VIVIR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("HABLAR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("COMER",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ESTAR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("SER",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("TENER",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("PONER",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("TOMAR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("DAR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("IR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("DECIR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("HACER",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("PODER",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("NECESITAR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("QUERER",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ABRIR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("AGARRAR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ANDAR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CAMINAR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("BEBER",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("BUSCAR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CAERSE",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CERRAR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("COMENZAR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("COMPRAR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CONDUCIR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CONOCER",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("PEDIR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("DORMIR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ENCONTRAR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ENTENDER",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("LEER",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ESCRIBIR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ESCUCHAR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("PENSAR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("MIRAR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("LLEGAR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("SABER",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("PERDER",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("OÍR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("JUGAR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("SALIR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("SALTAR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("VER",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("TRABAJAR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("TRAER",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("TOCAR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("SENTARSE",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("SENTIRSE",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("PAGAR",false,false,false,false,true,false,false,false,false);
        dao.insert(palabra);
    }
    private static void Articulos(PalabrasDao dao){
        PalabrasEntity palabra = new PalabrasEntity("EL", false, true, false, false, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("UN", false, true, false, false, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("UNO", false, true, false, false, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("UNA", false, true, false, false, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("UNAS", false, true, false, false, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("UNOS", false, true, false, false, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("AL", false, true, false, false, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("DEL", false, true, false, false, false, false, false, false, false);
        dao.insert(palabra);
    }
    private static void Pronombres(PalabrasDao dao){
        PalabrasEntity palabra = new PalabrasEntity("YO", false, false, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ME", false, false, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("MÍ", false, false, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("NOSOTROS", false, false, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("NOSOTRAS", false, false, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("NOS", false, false, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("TÚ", false, false, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("TE", false, false, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("TI", false, false, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("USTED", false, false, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("VOS", false, false, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ÉL", false, false, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ELLA", false, false, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ELLO", false, false, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("LE", false, false, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("LES", false, false, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("SE", false, false, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("SÍ", false, false, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("NADIE", false, false, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ALGUIEN", false, false, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("NADA", false, false, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ALGO", false, false, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("QUIENQUIERA", false, false, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("QUIENESQUIERA", false, false, false, true, false, false, false, false, false);
        dao.insert(palabra);
    }
    private static void PronombreArticulo(PalabrasDao dao) {

       PalabrasEntity palabra = new PalabrasEntity("LA", false, true, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("LO", false, true, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("LOS", false, true, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("LAS", false, true, false, true, false, false, false, false, false);
        dao.insert(palabra);

    }
    private static void PronommbresAdjetivo(PalabrasDao dao){
        PalabrasEntity palabra = new PalabrasEntity("ALGUNA",false,false,true,true,false,false,false,false,false);
        dao.insert(palabra);
         palabra = new PalabrasEntity("ALGUNO",false,false,true,true,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("OTRO",false,false,true,true,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("OTRA",false,false,true,true,false,false,false,false,false);
        dao.insert(palabra);

    }
    private static void PronombreAdjetivoSustantivo(PalabrasDao dao){
        PalabrasEntity palabra = new PalabrasEntity("CUALQUIERA",true,false,true,true,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("NADA",true,false,true,true,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ALGO",true,false,true,true,false,false,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ALGUIEN",true,false,true,true,false,false,false,false,false);
        dao.insert(palabra);
    }
    private static void PronombreAdjetivoAdverbio(PalabrasDao dao){
        PalabrasEntity palabra = new PalabrasEntity("MUCHO",false,false,true,true,false,true,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("MUCHA",false,false,true,true,false,true,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("POCO",false,false,true,true,false,true,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("POCA",false,false,true,true,false,true,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("BASTANTE",false,false,true,true,false,true,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("DEMASIADO",false,false,true,true,false,true,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("DEMASIADA",false,false,true,true,false,true,false,false,false);
        dao.insert(palabra);
    }
    private static void PalabrasConCuatroOMasFunciones(PalabrasDao dao){
        PalabrasEntity palabra = new PalabrasEntity("TODO",true,false,true,true,false,true,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("TODA",true,false,true,true,false,true,false,false,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("MÁS",true,false,true,true,false,true,false,true,false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("BAJO",true,false,true,false,false,true,true,false,false);
        dao.insert(palabra);
    }
    private static void Interjeccion(PalabrasDao dao){
        PalabrasEntity palabra = new PalabrasEntity("AY",false,false,false,false,false,false,false,false,true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("EPA",false,false,false,false,false,false,false,false,true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("OLÉ",false,false,false,false,false,false,false,false,true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("AH",false,false,false,false,false,false,false,false,true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("OH",false,false,false,false,false,false,false,false,true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("GUAY",false,false,false,false,false,false,false,false,true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("EH",false,false,false,false,false,false,false,false,true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("HEY",false,false,false,false,false,false,false,false,true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("UY",false,false,false,false,false,false,false,false,true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("PUAJ",false,false,false,false,false,false,false,false,true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("HOLA",false,false,false,false,false,false,false,false,true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("OJALÁ",false,false,false,false,false,false,false,false,true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("UF",false,false,false,false,false,false,false,false,true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("BAH",false,false,false,false,false,false,false,false,true);
        dao.insert(palabra);








    }
//Añadir en el layout un aviso que diga que aquellas formas que admiten plural deben ser tenidas en cuentas a la hora de elegir que tipo de función puede desempeñar una palbra
    //Ejemplo: aparece por pantalla la palabra demasiado, pero debe entenderse como demasiado/demasiados

}
