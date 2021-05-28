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


@Database(entities = PalabrasEntity.class,version = 3 ,exportSchema = false)

public abstract class PalabrasRoomDataBase extends RoomDatabase {

    public abstract PalabrasDao DAO();

    private static volatile PalabrasRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 10000;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static PalabrasRoomDataBase getRoomDataBase(final Context context) {

        if (INSTANCE == null) {
            synchronized (PalabrasRoomDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PalabrasRoomDataBase.class, "Palabra_v2_DataBase")
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

                    Preposicion(dao);
                    PreposicionSustantivo(dao);
                    PreposicionSustantivoAdverbio(dao);

                    Verbo(dao);
                    VerboSustantivo(dao);

                    Articulos(dao);
                    ArticuloSustantivo(dao);
                    ArticuloSustantivoPronombre(dao);

                    Pronombres(dao);
                    PronommbresAdjetivo(dao);
                    PronombreAdjetivoSustantivo(dao);
                    PronombreAdjetivoAdverbio(dao);
                    PronombreSustantivo(dao);
                    PronombreAdverbioSustantivo(dao);

                    PalabrasConCuatroOMasFunciones(dao);

                    Adverbios(dao);
                    AdverbiosPronombre(dao);
                    AdverbioSustantivo(dao);
                    AdverbioInterjeccion(dao);
                    AdverbioPreposicion(dao);
                    AdverbioPronombreSustantivo(dao);
                    AdverbioSustativoInterjeccion(dao);

                    Interjeccion(dao);
                    InterjeccionSustantivo(dao);
                    InterjeccionSustantivoAdverbio(dao);
                    palabrasFrases(dao);
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
        palabra = new PalabrasEntity("Explosión", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/explosi%C3%B3n?m=form");
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
        palabra = new PalabrasEntity("Masivo", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/masivo?m=form");
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
        PalabrasEntity palabra = new PalabrasEntity("ácido", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/%C3%A1cido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("rara", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/rara#VANhUcQ");
        dao.insert(palabra);
        palabra = new PalabrasEntity("errada", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/errada#G3VGPCX");
        dao.insert(palabra);
        palabra = new PalabrasEntity("lista", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/listo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("recipiente", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/recipiente?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("estadística", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/estad%C3%ADstico#GjpDTiC");
        dao.insert(palabra);
        palabra = new PalabrasEntity("húmedo", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/h%C3%BAmedo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Húmeda", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/h%C3%BAmedo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Sosa", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/sosa#YRD7Qdu");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Adjetivo", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/adjetivo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Adjetiva", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/adjetivo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Diaria", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/diario?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Diario", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/diario?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Estadístico", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/estad%C3%ADstico#GjpDTiC");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Enano", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/enano?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Enana", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/enano?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Perra", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/perro?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Perro", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/perro?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Plástica", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/pl%C3%A1stico?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Plástico", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/pl%C3%A1stico?m=form");
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
        PalabrasEntity palabra = new PalabrasEntity("Capaz", false, false, true, false, false, true, false, false, false,"https://dle.rae.es/capaz?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Blando", false, false, true, false, false, true, false, false, false, "https://dle.rae.es/blando?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Caro", false, false, true, false, false, true, false, false, false, "https://dle.rae.es/caro#7d34R35");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Baja", false, false, true, false, false, true, false, false, false,"https://dle.rae.es/bajo#4oyrC6G");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Diferente", false, false, true, false, false, true, false, false, false,"https://dle.rae.es/diferente?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Lenta", false, false, true, false, false, true, false, false, false,"https://dle.rae.es/lento?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Mejor", false, false, true, false, false, true, false, false, false,"https://dle.rae.es/mejor?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Peor", false, false, true, false, false, true, false, false, false,"https://dle.rae.es/peor?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("PRIMERO", false, false, true, false, false, true, false, false, false, "https://dle.rae.es/primero#UAztjAx");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Temprana", false, false, true, false, false, true, false, false, false,"https://dle.rae.es/temprano?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Antes", false, false, true, false, false, true, false, false, false,"https://dle.rae.es/antes?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Después", false, false, true, false, false, true, false, false, false,"https://dle.rae.es/despu%C3%A9s?m=form");
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
        PalabrasEntity
        palabra = new PalabrasEntity("Con", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/con?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("De", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/de#BtDkacL");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Desde", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/desde?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("En", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/en?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Entre", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/entre?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Hacia", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/hacia?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Para", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/para#Rp1CuT2");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Por", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/por?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Sin", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/sin?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Durante", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/durante?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Mediante", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/mediante?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Versus", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/versus");
        dao.insert(palabra);
    }
    private static void PreposicionSustantivo(PalabrasDao dao){
        PalabrasEntity palabra = new PalabrasEntity("A", true, false, false, false, false, false, true, false, false,"https://dle.rae.es/a#002rZ9U");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Cabe", true,false, false, false, false, false, true, false, false,"https://dle.rae.es/cabe#6PLJnT8");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Contra", true, false, false, false, false, false, true, false, false,"https://dle.rae.es/contra#AWCzXfA");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Tras", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/tras#aOnVELH");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Vía", true, false, false, false, false, false, true, false, false,"https://dle.rae.es/v%C3%ADa?m=form");
        dao.insert(palabra);
    }
    private static void PreposicionSustantivoAdverbio(PalabrasDao dao){
         PalabrasEntity palabra = new PalabrasEntity("Ante", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/ante#2lpFbYn");
        dao.insert(palabra);
    }

  private static void Verbo(PalabrasDao dao) {
        PalabrasEntity
        palabra = new PalabrasEntity("Hablar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/hablar");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Comer", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/comer#9vPNMxG");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Tener", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/tener?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Poner", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/poner?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Tomar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/tomar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Dar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/dar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Ir", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/ir?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Hacer", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/hacer?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Necesitar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/necesitar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Abrir", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/abrir?formList=form&w=#");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Agarrar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/agarrar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Buscar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/buscar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Caer", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/caer?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Cerrar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/cerrar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Comenzar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/comenzar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Comprar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/comprar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Conducir", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/conducir?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Conocer", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/conocer?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Pedir", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/pedir?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Dormir", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/dormir?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Encontrar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/encontrar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Entender", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/entender#FgqY9Xy");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Leer", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/leer?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Escribir", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/escribir?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Escuchar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/escuchar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Pensar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/pensar#STY14i0");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Mirar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/mirar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Llegar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/llegar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Perder", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/perder?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Oír", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/o%C3%ADr");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Jugar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/jugar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Salir", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/salir?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Saltar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/saltar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Trabajar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/trabajar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Traer", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/traer#aE2JYRT");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Tocar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/tocar#Zw9vb5a");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Sentar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/sentar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Pagar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/pagar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Estar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/estar?m=form");
        dao.insert(palabra);
    }
    private static void VerboSustantivo(PalabrasDao dao){
        PalabrasEntity  palabra = new PalabrasEntity("Vivir", true, false, false, false, true, false, false, false, false,"https://dle.rae.es/vivir#bycwmXJ");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Ser", true, false, false, false, true, false, false, false, false,"https://dle.rae.es/ser#Xe5NPsQ");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Beber", true, false, false, false, true, false, false, false, false,"https://dle.rae.es/beber#5GjKfxr");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Decir", true, false, false, false, true, false, false, false, false,"https://dle.rae.es/decir#BxMOE45");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Poder", true, false, false, false, true, false, false, false, false,"https://dle.rae.es/poder#TU2nLT0");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Querer", true, false, false, false, true, false, false, false, false,"https://dle.rae.es/querer#Unz1d3h");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Sentir", true, false, false, false, true, false, false, false, false,"https://dle.rae.es/sentir?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Ver", true, false, false, false, true, false, false, false, false,"https://dle.rae.es/ver#baR8qnC");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Saber", true, false, false, false, true, false, false, false, false,"https://dle.rae.es/saber#WswcTXr");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Andar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/andar#2ZIziL7");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Caminar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/caminar#6xqfYHy");
        dao.insert(palabra);
    }

    private static void Articulos(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("El", false, true, false, false, false, false, false, false, false,"https://dle.rae.es/el#ESraxkH");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Un", false, true, false, false, false, false, false, false, false,"https://dle.rae.es/un#b3uuZZA");
        dao.insert(palabra);
        palabra = new PalabrasEntity("AL", false, true, false, false, false, false, false, false, false,"https://dle.rae.es/al?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("DEL", false, true, false, false, false, false, false, false, false,"https://dle.rae.es/del?m=form");
        dao.insert(palabra);
    }
    private static void ArticuloSustantivo (PalabrasDao dao){
        PalabrasEntity palabra = new PalabrasEntity("La", true, true, false, false, false, false, false, false, false,"https://dle.rae.es/el#ESraxkH");
        dao.insert(palabra);
    }
    private static void ArticuloSustantivoPronombre(PalabrasDao dao){
         PalabrasEntity palabra = new PalabrasEntity("Uno", true, true, false, true, false, false, false, false, false,"https://dle.rae.es/uno");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Una", true, true, false, true, false, false, false, false, false,"https://dle.rae.es/uno");
        dao.insert(palabra);
    }

   private static void Pronombres(PalabrasDao dao) {
        PalabrasEntity
        palabra = new PalabrasEntity("Alguien", false, false, true, true, false, false, false, false, false,"https://dle.rae.es/alguien?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Me", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/me?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Mí", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/m%C3%AD?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Nosotros", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/nosotras?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Nosotras", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/nosotras?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Nos", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/nos?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Tú", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/t%C3%BA?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Ti", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/ti?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Usted", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/usted?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Vos", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/vos?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Él", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/%C3%A9l");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Ella", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/%C3%A9l");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Le", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/le?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Se", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/se#XNVjCmd");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Nadie", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/nadie?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Quienquiera", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/quienquiera");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Quienesquiera", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/quienquiera");
        dao.insert(palabra);
    }
    private static void PronommbresAdjetivo(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("Alguno", false, false, true, true, false, false, false, false, false,"https://dle.rae.es/alguno?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Alguna", false, false, true, true, false, false, false, false, false,"https://dle.rae.es/alguno?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Otro", false, false, true, true, false, false, false, false, false,"https://dle.rae.es/otro?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Otra", false, false, true, true, false, false, false, false, false,"https://dle.rae.es/otro?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Toda", true, false, true, true, false, true, false, false, false,"https://dle.rae.es/todo?m=form");
        dao.insert(palabra);
    }
    private static void PronombreAdjetivoSustantivo(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("Cualquiera", true, false, true, true, false, false, false, false, false,"https://dle.rae.es/cualquiera?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Algo", true, false, true, true, false, false, false, false, false,"https://dle.rae.es/algo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Pronto", true, false, true, true, false, false, false, false, false,"https://dle.rae.es/pronto?m=form");
        dao.insert(palabra);

    }
    private static void PronombreAdjetivoAdverbio(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("Mucho", false, false, true, true, false, true, false, false, false,"https://dle.rae.es/mucho?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Mucha", false, false, true, true, false, true, false, false, false,"https://dle.rae.es/mucho?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Poco", false, false, true, true, false, true, false, false, false,"https://dle.rae.es/poco?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Poca", false, false, true, true, false, true, false, false, false,"https://dle.rae.es/poco?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Bastante", false, false, true, true, false, true, false, false, false,"https://dle.rae.es/bastante?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Demasiado", false, false, true, true, false, true, false, false, false,"https://dle.rae.es/bastante?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Demasiada", false, false, true, true, false, true, false, false, false,"https://dle.rae.es/bastante?m=form");
        dao.insert(palabra);
    }
    private static void PronombreSustantivo(PalabrasDao dao){
        PalabrasEntity   palabra = new PalabrasEntity("Lo", true, false, false, true, false, false, false, false, false,"https://dle.rae.es/lo#NWnLPAn");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Yo", true, false, false, true, false, false, false, false, false,"https://dle.rae.es/yo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Ello", true, false, false, true, false, false, false, false, false,"https://dle.rae.es/%C3%A9l");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Te", true, false, false, true, false, false, false, false, false,"https://dle.rae.es/te#ZHbpNE9");
        dao.insert(palabra);
    }
    private static void PronombreAdverbioSustantivo(PalabrasDao dao){
        PalabrasEntity     palabra = new PalabrasEntity("Sí", true, false, false, true, false, true, false, false, false,"https://dle.rae.es/s%C3%AD#XmOP8Ia");
        dao.insert(palabra);
    }

    private static void PalabrasConCuatroOMasFunciones(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("Todo", true, false, true, true, false, true, false, false, false,"https://dle.rae.es/todo?m=form");
        dao.insert(palabra);

        palabra = new PalabrasEntity("Más", true, false, true, true, false, true, false, true, false,"https://dle.rae.es/m%C3%A1s?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("BAJO", true, false, true, false, false, true, true, false, false,"https://dle.rae.es/bajo#4oyrC6G");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Así", false, false, true, false, false, true, false, true, true,"https://dle.rae.es/as%C3%AD?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Como", true, false, false, false, false, true, true, true, false,"https://dle.rae.es/como#9xWAAKF");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Cara", true, false, true, false, false, true, true, false, false, "https://dle.rae.es/cara#7NOG7x2");
        dao.insert(palabra);
        palabra = new PalabrasEntity("So", false, false, true, false, false, true, true, false, true,"https://dle.rae.es/so#Y38uhep");
        dao.insert(palabra);

    }

    private static void Adverbios(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("Allí", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/all%C3%AD?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Jamás", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/jam%C3%A1s?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Siempre", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/siempre?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Nunca", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/nunca?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Tampoco", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/tampoco?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Quizá", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/quiz%C3%A1?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Allá", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/all%C3%A1?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Enfrente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/enfrente?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Dentro", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/dentro?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Abajo", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/abajo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Enseguida", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/enseguida?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Todavía", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/todav%C3%ADa?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Anoche", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/anoche?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Recién", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/reci%C3%A9n?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Actualmente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/actualmente?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Antiguamente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/antiguamente?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Últimamente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/%C3%BAltimamente?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Recientemente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/recientemente?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Anteanoche", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/anteanoche?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Deprisa", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/deprisa?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Gratis", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/gratis?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Aposta", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/aposta?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Adrede", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/adrede?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Tranquilamente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/tranquilamente?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Ligeramente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/ligeramente?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Indistintamente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/indistintamente?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Cuidadosamente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/cuidadosamente?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Velozmente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/velozmente?m=form");
        dao.insert(palabra);
    }
    private static void AdverbiosPronombre(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("Acá", false, false, false, true, false, true, false, false, false,"https://dle.rae.es/ac%C3%A1?m=form");
        dao.insert(palabra);
    }
    private static void AdverbioSustantivo(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("CERCA", true, false, false, false, false, true, false, false, false,"https://dle.rae.es/cerca#8J0QC7a");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Lejos", true, false, false, false, false, true, false, false, false,"https://dle.rae.es/lejos?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Alrededor", true, false, false, false, false, true, false, false, false,"https://dle.rae.es/alrededor?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Hoy", true, false, false, false, false, true, false, false, false,"https://dle.rae.es/hoy?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Mañana", true, false, false, false, false, true, false, false, false,"https://dle.rae.es/ma%C3%B1ana?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Ayer", true, false, false, false, false, true, false, false, false,"https://dle.rae.es/ayer?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Tarde", true, false, false, false, false, true, false, false, false,"https://dle.rae.es/tarde?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("No", true, false, false, false, false, true, false, false, false,"https://dle.rae.es/no?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Acaso", true, false, false, false, false, true, false, false, false,"https://dle.rae.es/acaso?m=form");
        dao.insert(palabra);
    }
    private static void AdverbioSustativoInterjeccion(PalabrasDao dao){
        PalabrasEntity palabra = new PalabrasEntity("Afuera", true, false, false, false, false, true, false, false, true,"https://dle.rae.es/afuera?m=form");
        dao.insert(palabra);
    }
    private static void AdverbioInterjeccion(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("Aquí", false, false, false, false, false, true, false, false, true,"https://dle.rae.es/aqu%C3%AD?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Arriba", false, false, false, false, false, true, false, false, true,"https://dle.rae.es/arriba?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Fuera", false, false, false, false, false, true, false, false, true,"https://dle.rae.es/fuera?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Ya", false, false, false, false, false, true, false, false, true,"https://dle.rae.es/ya?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Despacio", false, false, false, false, false, true, false, false, true,"https://dle.rae.es/despacio?m=form");
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
        PalabrasEntity
        palabra = new PalabrasEntity("EPA", false, false, false, false, false, false, false, false, true,"https://dle.rae.es/epa?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Olé", false, false, false, false, false, false, false, false, true,"https://dle.rae.es/ol%C3%A9?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Ah", false, false, false, false, false, false, false, false, true,"https://dle.rae.es/ah?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Oh", false, false, false, false, false, false, false, false, true,"https://dle.rae.es/oh?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Eh", false, false, false, false, false, false, false, false, true,"https://dle.rae.es/eh?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Uy", false, false, false, false, false, false, false, false, true,"https://dle.rae.es/uy?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Hola", false, false, false, false, false, false, false, false, true,"https://dle.rae.es/hola?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Ojalá", false, false, false, false, false, false, false, false, true,"https://dle.rae.es/ojal%C3%A1?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Uf", false, false, false, false, false, false, false, false, true,"https://dle.rae.es/uf?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Bah", false, false, false, false, false, false, false, false, true,"https://dle.rae.es/bah?m=form");
        dao.insert(palabra);
    }
    private static void InterjeccionSustantivo(PalabrasDao dao){
        PalabrasEntity palabra = new PalabrasEntity("Ay", true, false, false, false, false, false, false, false, true,"https://dle.rae.es/ay?m=form");
        dao.insert(palabra);
    }
    private static void InterjeccionSustantivoAdverbio(PalabrasDao dao){
        PalabrasEntity    palabra = new PalabrasEntity("Guay", true, false, false, false, false, true, false, false, true,"https://dle.rae.es/guay#JmihBh3");
        dao.insert(palabra);

    }

    private static void palabrasFrases(PalabrasDao dao){
        dao.insert(new PalabrasEntity("es", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/es"));
        dao.insert(new PalabrasEntity("niño", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/ni%C3%B1o?m=form"));
        dao.insert(new PalabrasEntity("muy", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/muy?m=form"));


    }
//Añadir en el layout un aviso que diga que aquellas formas que admiten plural deben ser tenidas en cuentas a la hora de elegir que tipo de función puede desempeñar una palbra
    //Ejemplo: aparece por pantalla la palabra demasiado, pero debe entenderse como demasiado/demasiados
}

