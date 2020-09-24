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
    private static final int NUMBER_OF_THREADS = 1000;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static PalabrasRoomDataBase getRoomDataBase(final Context context) {

        if (INSTANCE == null) {
            synchronized (PalabrasRoomDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PalabrasRoomDataBase.class, "Palabras_DataBase")
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

                    PalabrasDao dao = INSTANCE.DAO();
                    dao.deleteAll();


                    Sustantivos(dao);

                    Adjetivos(dao);
                    AdjetivosSutantivos(dao);
                    AdjetivoSutantatioAdverbio(dao);
                    AdjetivoInterjeccion(dao);
                    AdjetivoAdverbio(dao);

                    Conjuncion(dao);
                    ConjuncionSustantivo (dao);
                    conjuncionAdverbioInterjeccion(dao);
                    conjuncionPronombre(dao);



                    Pronombres(dao);
                    Articulos(dao);
                    PronombreArticulo(dao);
                    PronommbresAdjetivo(dao);
                    PronombreAdjetivoSustantivo(dao);
                    PronombreAdjetivoAdverbio(dao);
                    PalabrasConCuatroOMasFunciones(dao);
                    Verbo(dao);
                    Preposicion(dao);
                    Interjeccion(dao);
                    Adverbios(dao);
                    AdverbiosPronombre(dao);
                    AdverbioSustantivo(dao);
                    AdverbioInterjeccion(dao);
                    AdverbioPreposicion(dao);
                    AdverbioPronombreSustantivo(dao);
                }
            });
        }

    };

    private static void Sustantivos(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("Hierro", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/hierro");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Pato", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/pato#SAGNQ6l");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Pata", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/pato#SAGNQ6l");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Mesa", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/mesa");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Cafeína", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/cafe%C3%ADna");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Estrella", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/estrella?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Exploxión", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/explosi%C3%B3n?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Guitarra", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/guitarra?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Navaja", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/navaja#QIQMR8S");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Martillo", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/martillo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Libro", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/libro?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Embarcación", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/embarcaci%C3%B3n?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Rueda", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/rueda?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Pelo", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/pelo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Agujeta", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/agujeta?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Felicidad", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/felicidad?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Satélite", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/sat%C3%A9lite?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Templo", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/templo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Lentes", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/lente?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Bolígrafo", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/bol%C3%ADgrafo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Botella", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/botella?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Castillo", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/castillo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Casa", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/casa?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Persona", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/persona?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Metal", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/metal?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Teléfono", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/tel%C3%A9fono?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Mapa", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/mapa?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Mensaje", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/mensaje?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Masaje", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/masaje?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Cohete", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/cohete?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Rey", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/rey?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Reina", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/rey?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Edificio", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/edificio?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Césped", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/c%C3%A9sped?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Candidato", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/candidato?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Candidata", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/candidato?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Silla", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/silla?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Deporte", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/deporte?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Rúcula", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/r%C3%BAcula?m=form");
        dao.insert(palabra);
    }

    private static void Adjetivos(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("Actual", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/actual?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Aburrido", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/aburrido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Aburrida", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/aburrido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Ácida", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/%C3%A1cido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Alegre", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/alegre?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Débil", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/d%C3%A9bil");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Amarga", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/amargo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Atrevido ", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/atrevido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Delgada", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/delgado?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Atrevida", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/atrevido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Bonita", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/bonito#5rVKGkX");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Buen", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/buen?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Difícil", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/dif%C3%ADcil?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Divertido", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/divertido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Divertida", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/divertido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Enfermo", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/enfermo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Enferma", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/enfermo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Estrecha", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/estrecho?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Famoso", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/famoso");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Famosa", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/famoso");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Fea", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/feo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Guapa", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/guapo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Interesante", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/interesante?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Inútil", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/in%C3%BAtil?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Izquierdo", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/izquierdo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Listo", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/listo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Masico", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/masivo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Masiva", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/masivo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Muerta", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/muerto?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Nuevo", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/nuevo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Pequeño", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/peque%C3%B1o?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Pequeña", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/peque%C3%B1o?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Perfecta", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/perfecto?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Popular", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/popular?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Primer", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/primer?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Próxima", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/pr%C3%B3ximo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Próximo", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/pr%C3%B3ximo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Rápida", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/r%C3%A1pido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Raro", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/rara#VANhUcQ");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Roja", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/rojo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Sano", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/sano?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Sana", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/sano?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Social", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/social?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Sola", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/solo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Soso", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/soso#YRqpFp4");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Tímido", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/t%C3%ADmido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Tímida", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/t%C3%ADmido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Tonta", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/tonto?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Verdadero", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/verdadero?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Verdadera", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/verdadero?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Viva", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/vivo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Errado", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/errado?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Impreciso", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/impreciso?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Imprecisa", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/impreciso?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Preciso", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/preciso?m=form");
        dao.insert(palabra);
    }
    private static void AdjetivosSutantivos(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("Ácido", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/%C3%A1cido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Rara", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/rara#VANhUcQ");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Errada", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/errada#G3VGPCX");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Lista", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/listo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Recipiente", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/recipiente?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Estadística", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/estad%C3%ADstico#GjpDTiC");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Húmedo", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/h%C3%BAmedo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Húmeda", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/h%C3%BAmedo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Sosa", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/sosa#YRD7Qdu");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Adjetivo", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/adjetivo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Adjetiva", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/adjetivo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Diaria", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/diario?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Diario", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/diario?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Estadístico", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/estad%C3%ADstico#GjpDTiC");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Enano", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/enano?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Enana", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/enano?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Perra", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/perro?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Perro", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/perro?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Plástica", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/pl%C3%A1stico?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Plástico", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/pl%C3%A1stico?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Crema", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/crema#BDxo4Yt");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Café", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/caf%C3%A9?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("AMARGO", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/amargo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Tonto", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/tonto?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Vivo", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/vivo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Viejo", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/viejo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Vieja", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/viejo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Verde", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/verde?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Precisa", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/preciso?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Útil", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/%C3%BAtil#bCSFzkP");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Triste", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/triste?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Sinvergüenza", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/sinverg%C3%BCenza?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Segundo", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/segundo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Simple", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/simple?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Segunda", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/segundo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Seco", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/seco?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Seca", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/seco?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Salada", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/salado#X10c5KN");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Salado", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/salado#X10c5KN");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Rico", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/rico?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Rojo", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/rojo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Rica", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/rico?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Recto", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/recto?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Recta", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/recto?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Real", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/real#VGqyuLj");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Principal", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/principal?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Posible", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/posible?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Perfecto", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/perfecto?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Pobre", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/pobre?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Negro", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/negro?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Nueva", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/nuevo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Negra", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/negro?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Muerto", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/muerto?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Natural", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/natural?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Nacional", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/nacional?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Musical", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/musical?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Ancho", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/ancho?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Ancha", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/ancho?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Azul", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/azul?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Blanco", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/blanco?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Blanca", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/blanco?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Blanda", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/blando");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Bonito", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/bonito#5rVKGkX");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Bueno", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/bueno");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Buena", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/bueno");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Central", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/central?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Común", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/com%C3%BAn?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Conocido", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/conocido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Conocida", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/conocido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Contento", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/contento#AUokAR9");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Contenta", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/contento#AUokAR9");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Corto", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/corto#B356ILe");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Corta", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/corto#B356ILe");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Delgado", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/delgado?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Derecho", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/derecho?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Derecha", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/derecho?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Dulce", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/dulce?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Dura", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/duro#EIKtetO");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Estrecho", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/estrecho?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Exterior", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/exterior?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Falso", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/falso?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Falsa", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/falso?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Feo", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/feo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Final", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/final?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Fresca", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/fresco#IT35u3d");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Fresco", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/fresco#IT35u3d");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Fría", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/fr%C3%ADo#IUteOxn");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Frío", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/fr%C3%ADo#IUteOxn");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Gordo", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/gordo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Gorda", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/gordo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Grande", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/grande?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Guapo", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/guapo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Imposible", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/imposible?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Interior", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/interior?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Izquierda", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/izquierdo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Joven", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/joven?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Malo", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/malo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Mala", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/malo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Mayor", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/mayor?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Menor", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/menor?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Clara", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/claro#9PhBhLd");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Seguro", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/seguro?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Larga", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/largo?m=form");
        dao.insert(palabra);
    }
    private static void AdjetivoSutantatioAdverbio(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("Alto", true, false, true, false, false, true, false, false, false,"https://dle.rae.es/alto#27ieUu8");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Alta", true, false, true, false, false, true, false, false, false,"https://dle.rae.es/alto#27ieUu8");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Solo", true, false, true, false, false, true, false, false, false, "https://dle.rae.es/solo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Duro", true, false, true, false, false, true, false, false, false, "https://dle.rae.es/duro#EIKtetO");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Fuerte", true, false, true, false, false, true, false, false, false,"https://dle.rae.es/fuerte?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Igual", true, false, true, false, false, true, false, false, false,"https://dle.rae.es/igual?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Largo", true, false, true, false, false, true, false, false, false,"https://dle.rae.es/largo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Lento", true, false, true, false, false, true, false, false, false,"https://dle.rae.es/lento?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Rápido", true, false, true, false, false, true, false, false, false, "https://dle.rae.es/r%C3%A1pido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Temprano", true, false, true, false, false, true, false, false, false,"https://dle.rae.es/temprano?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Mal", true, false, true, false, false, true, false, false, false,"https://dle.rae.es/mal#NyEUGaa");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Claro", true, false, true, false, false, true, false, false, false,"https://dle.rae.es/claro#9PhBhLd");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Segura", true, false, true, false, false, true, false, false, false,"https://dle.rae.es/seguro?m=form");
        dao.insert(palabra);


    }
    private static void AdjetivoInterjeccion(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("Caliente", true, false, true, false, false, true, false, false, false,"https://dle.rae.es/caliente?m=form");
        dao.insert(palabra);
    }
    private static void AdjetivoAdverbio(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("Capaz", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/capaz?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Blando", false, false, true, false, false, true, false, false, false, "https://dle.rae.es/blando?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Caro", false, false, true, false, false, true, false, false, false, "https://dle.rae.es/caro#7d34R35");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Baja", false, false, true, false, false, true, false, false, false,"https://dle.rae.es/bajo#4oyrC6G");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Diferente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/diferente?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Lenta", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/lento?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Mejor", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/mejor?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Peor", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/peor?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("PRIMERO", false, false, false, false, false, true, false, false, false, "https://dle.rae.es/primero#UAztjAx");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Temprana", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/temprano?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Antes", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/antes?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Después", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/despu%C3%A9s?m=form");
        dao.insert(palabra);

    }

    private static void Conjuncion(PalabrasDao dao) {

        PalabrasEntity palabra = new PalabrasEntity("SINO", false, false, false, false, false, false, false, true, false,"https://dle.rae.es/sino#XywVM1a");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Mas", false, false, false, false, false, false, false, true, false,"https://dle.rae.es/mas?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Porque", false, false, false, false, false, false, false, true, false,"https://dle.rae.es/porque?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Aunque", false, false, false, false, false, false, false, true, false,"https://dle.rae.es/aunque?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Ergo", false, false, false, false, false, false, false, true, false,"https://dle.rae.es/ergo?m=form");
        dao.insert(palabra);
    }
    private static void ConjuncionSustantivo(PalabrasDao dao){
        PalabrasEntity palabra = new PalabrasEntity("Pero", true, false, false, false, false, false, false, true, false,"https://dle.rae.es/pero#SgxG2YC");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Ni", true, false, false, false, false, false, false, true, false,"https://dle.rae.es/ni?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Y", true, false, false, false, false, false, false, true, false,"https://dle.rae.es/y#c8HoARq");
        dao.insert(palabra);
        palabra = new PalabrasEntity("O", true, false, false, false, false, false, false, true, false,"https://dle.rae.es/o#QlqTEX0");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Si", true, false, false, false, false, false, false, true, false,"https://dle.rae.es/si#XmM8PPL");
        dao.insert(palabra);
    }
    private static void conjuncionAdverbioInterjeccion(PalabrasDao dao){
        PalabrasEntity  palabra = new PalabrasEntity("Pues", false, false, false, false, false, true, false, true, true,"https://dle.rae.es/pues?m=form");
        dao.insert(palabra);
    }
    private static void conjuncionPronombre(PalabrasDao dao){
        PalabrasEntity    palabra = new PalabrasEntity("Que", false, false, false, true, false, false, false, true, false,"https://dle.rae.es/que?m=form");
        dao.insert(palabra);
    }

    private static void Preposicion(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("CABE", false, false, false, false, false, false, true, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CON", false, false, false, false, false, false, true, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CONTRA", false, false, false, false, false, false, true, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("DE", false, false, false, false, false, false, true, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("DESDE", false, false, false, false, false, false, true, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("EN", false, false, false, false, false, false, true, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ENTRE", false, false, false, false, false, false, true, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("HACIA", false, false, false, false, false, false, true, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("PARA", false, false, false, false, false, false, true, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("POR", false, false, false, false, false, false, true, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("SIN", false, false, false, false, false, false, true, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("SO", false, false, false, false, false, false, true, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("TRAS", false, false, false, false, false, false, true, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("DURANTE", false, false, false, false, false, false, true, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("MEDIANTE", false, false, false, false, false, false, true, false, false);
        dao.insert(palabra);
    }
    private static void PreposicionSustantivo(PalabrasDao dao){
        PalabrasEntity palabra = new PalabrasEntity("A", true, false, false, false, false, false, true, false, false,"https://dle.rae.es/a#002rZ9U");
        dao.insert(palabra);
    }
    private static void PreposicionSustantivoAdverbio(PalabrasDao dao){
         PalabrasEntity palabra = new PalabrasEntity("Ante", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/ante#2lpFbYn");
        dao.insert(palabra);
    }

    private static void Verbo(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("VIVIR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("HABLAR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("COMER", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ESTAR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("SER", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("TENER", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("PONER", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("TOMAR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("DAR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("IR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("DECIR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("HACER", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("PODER", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("NECESITAR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("QUERER", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ABRIR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("AGARRAR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ANDAR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CAMINAR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("BEBER", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("BUSCAR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CAERSE", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CERRAR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("COMENZAR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("COMPRAR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CONDUCIR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CONOCER", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("PEDIR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("DORMIR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ENCONTRAR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ENTENDER", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("LEER", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ESCRIBIR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ESCUCHAR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("PENSAR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("MIRAR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("LLEGAR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("SABER", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("PERDER", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("OIR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("JUGAR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("SALIR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("SALTAR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("VER", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("TRABAJAR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("TRAER", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("TOCAR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("SENTARSE", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("SENTIRSE", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("PAGAR", false, false, false, false, true, false, false, false, false);
        dao.insert(palabra);
    }

    private static void Articulos(PalabrasDao dao) {
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
    private static void ArticuloSustantivo (PalabrasDao dao){
        PalabrasEntity palabra = new PalabrasEntity("La", true, true, false, false, false, false, false, false, false,"https://dle.rae.es/el#ESraxkH");
        dao.insert(palabra);
    }

    private static void Pronombres(PalabrasDao dao) {
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
        palabra = new PalabrasEntity("QUIENQUIERA", false, false, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("QUIENESQUIERA", false, false, false, true, false, false, false, false, false);
        dao.insert(palabra);
    }
    private static void PronombreArticulo(PalabrasDao dao) {

        PalabrasEntity
        palabra = new PalabrasEntity("LO", false, true, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("LOS", false, true, false, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("LAS", false, true, false, true, false, false, false, false, false);
        dao.insert(palabra);

    }
    private static void PronommbresAdjetivo(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("ALGUNA", false, false, true, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ALGUNO", false, false, true, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("OTRO", false, false, true, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("OTRA", false, false, true, true, false, false, false, false, false);
        dao.insert(palabra);

    }
    private static void PronombreAdjetivoSustantivo(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("CUALQUIERA", true, false, true, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ALGO", true, false, true, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ALGUIEN", true, false, true, true, false, false, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("PRONTO", true, false, true, true, false, false, false, false, false);
        dao.insert(palabra);
    }
    private static void PronombreAdjetivoAdverbio(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("MUCHO", false, false, true, true, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("MUCHA", false, false, true, true, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("POCO", false, false, true, true, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("POCA", false, false, true, true, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("BASTANTE", false, false, true, true, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("DEMASIADO", false, false, true, true, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("DEMASIADA", false, false, true, true, false, true, false, false, false);
        dao.insert(palabra);
    }

    private static void PalabrasConCuatroOMasFunciones(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("TODO", true, false, true, true, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("TODA", true, false, true, true, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("MÁS", true, false, true, true, false, true, false, true, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("BAJO", true, false, true, false, false, true, true, false, false,"https://dle.rae.es/bajo#4oyrC6G");
        dao.insert(palabra);
        palabra = new PalabrasEntity("ASÍ", false, false, true, false, false, true, false, true, true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("COMO", true, false, false, false, false, true, true, true, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("JAMÁS", true, false, false, false, false, true, true, true, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("Cara", true, false, true, false, false, true, true, false, false, "https://dle.rae.es/cara#7NOG7x2");
        dao.insert(palabra);

    }

    private static void Adverbios(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("ALLÍ", false, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("TAMPOCO", false, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("QUIZÁ", false, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ALLÁ", false, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ENFRENTE", false, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("DENTRO", false, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ABAJO", false, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ENSEGUIDA", false, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("TODAVÍA", false, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ANOCHE", false, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("RECIÉN", false, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACTUALMENTE", false, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ANTIGUAMENTE", false, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ÚLTIMAMENTE", false, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("RECIENTEMENTE", false, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ANTEANOCHE", false, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("DEPRISA", false, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("GRATIS", false, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("APOSTA", false, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ADREDE", false, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("TRANQUILAMENTE", false, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("PENOSAMENTE", false, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("LIGERAMENTE", false, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("INDISTINTAMENTE", false, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CUIDADOSAMENTE", false, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("VELOZMENTE", false, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
    }
    private static void AdverbiosPronombre(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("Acá", false, false, false, true, false, true, false, false, false,"https://dle.rae.es/ac%C3%A1?m=form");
        dao.insert(palabra);
    }
    private static void AdverbioSustantivo(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("AFUERA", true, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("NUNCA", true, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("SIEMPRE", true, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("CERCA", true, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("LEJOS", true, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ALREDEDOR", true, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("HOY", true, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("MAÑANA", true, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("Ayer", true, false, false, false, false, true, false, false, false,"https://dle.rae.es/ayer?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("TARDE", true, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("NO", true, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ACASO", true, false, false, false, false, true, false, false, false);
        dao.insert(palabra);
    }
    private static void AdverbioInterjeccion(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("AQUÍ", false, false, false, false, false, true, false, false, true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("ARRIBA", false, false, false, false, false, true, false, false, true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("FUERA", false, false, false, false, false, true, false, false, true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("YA", false, false, false, false, false, true, false, false, true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("DESPACIO", false, false, false, false, false, true, false, false, true);
        dao.insert(palabra);
    }
    private static void AdverbioPreposicion(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("Según", false, false, false, false, false, true, true, false, false,"https://dle.rae.es/seg%C3%BAn?m=form");
        dao.insert(palabra);
    }
    private static void AdverbioPronombreSustantivo(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("Nada", true, false, false, true, false, true, false, false, false,"https://dle.rae.es/nada?m=form");
        dao.insert(palabra);
    }

    private static void Interjeccion(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("AY", false, false, false, false, false, false, false, false, true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("EPA", false, false, false, false, false, false, false, false, true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("OLÉ", false, false, false, false, false, false, false, false, true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("AH", false, false, false, false, false, false, false, false, true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("OH", false, false, false, false, false, false, false, false, true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("GUAY", false, false, false, false, false, false, false, false, true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("EH", false, false, false, false, false, false, false, false, true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("HEY", false, false, false, false, false, false, false, false, true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("UY", false, false, false, false, false, false, false, false, true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("PUAJ", false, false, false, false, false, false, false, false, true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("HOLA", false, false, false, false, false, false, false, false, true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("OJALÁ", false, false, false, false, false, false, false, false, true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("UF", false, false, false, false, false, false, false, false, true);
        dao.insert(palabra);
        palabra = new PalabrasEntity("BAH", false, false, false, false, false, false, false, false, true);
        dao.insert(palabra);
    }
//Añadir en el layout un aviso que diga que aquellas formas que admiten plural deben ser tenidas en cuentas a la hora de elegir que tipo de función puede desempeñar una palbra
    //Ejemplo: aparece por pantalla la palabra demasiado, pero debe entenderse como demasiado/demasiados
}

