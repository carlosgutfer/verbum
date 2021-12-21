package com.GF.verbum.DB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.GF.verbum.DB.DAO.FrasesDao;
import com.GF.verbum.DB.DAO.FratipDao;
import com.GF.verbum.DB.DAO.PalabrasDao;
import com.GF.verbum.DB.DAO.PalfraDao;
import com.GF.verbum.DB.DAO.TiposDao;
import com.GF.verbum.DB.Entities.PalabrasEntity;
import com.GF.verbum.DB.Entities.frasesEntity;
import com.GF.verbum.DB.Entities.fratipEntity;
import com.GF.verbum.DB.Entities.palfraEntity;
import com.GF.verbum.DB.Entities.tiposEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {PalabrasEntity.class, palfraEntity.class, tiposEntity.class,fratipEntity.class,frasesEntity.class},version = 5 ,exportSchema = false)

public abstract class PalabrasRoomDataBase extends RoomDatabase {

    public abstract PalabrasDao DAOPALABRAS();
    public abstract PalfraDao DAOPALFRA();
    public abstract TiposDao DAOTIPOS();
    public abstract  FratipDao DAOFRATIP();
    public abstract FrasesDao DAOFRASES();



    private static volatile PalabrasRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 10000;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static PalabrasRoomDataBase getRoomDataBase(final Context context) {

        if (INSTANCE == null) {
            synchronized (PalabrasRoomDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PalabrasRoomDataBase.class, "Palabra_v3_DataBase")
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

                    PalabrasDao dao = INSTANCE.DAOPALABRAS();
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

                    FrasesDao daoFrases = INSTANCE.DAOFRASES();
                    daoFrases.deleteAll();
                    addItemFra(daoFrases);

                    TiposDao dao3 = INSTANCE.DAOTIPOS();
                    dao3.deleteAll();
                    addItemTipos(dao3);

                    FratipDao daotipos = INSTANCE.DAOFRATIP();
                    daotipos.deleteAll();
                    addItemFraTip(daotipos);

                    PalfraDao dao2 = INSTANCE.DAOPALFRA();
                    dao2.deleteAll();
                    addItemspalfra(dao2);








                }
            });
        }

    };
    private static void addItemTipos(TiposDao dao) {
        dao.insert(new tiposEntity("Simple"));
        dao.insert(new tiposEntity("predicativa",1));
        dao.insert(new tiposEntity("activa",2));
        dao.insert(new tiposEntity("transitiva", 3));
        dao.insert(new tiposEntity("reflexiva",4));
        dao.insert(new tiposEntity("recíproca",4));
        dao.insert(new tiposEntity("intransitiva",3));
        dao.insert(new tiposEntity("pasiva",2));
        dao.insert(new tiposEntity("pasiva refleja",8));
        dao.insert(new tiposEntity("copulativa",1));
        //actitud del hablante
        //enunciativa
        dao.insert(new tiposEntity("enunciativa"));
        dao.insert(new tiposEntity("afirmativa",11));
        dao.insert( new tiposEntity("negativa",11));
        //interrogativa
        dao.insert(new tiposEntity("interrogativa"));
        dao.insert( new tiposEntity("directa",14));
        dao.insert(new tiposEntity("indirecta",14));
        dao.insert(new tiposEntity("total",15));
        dao.insert( new tiposEntity("parcial",15));
        dao.insert(new tiposEntity("total",14));
        dao.insert(new tiposEntity("parcial",14));
        dao.insert(new tiposEntity("exclamativa"));
        dao.insert(new tiposEntity("dubitativa"));
        dao.insert( new tiposEntity("desiderativa"));
        dao.insert(new tiposEntity("imperativa"));
        dao.insert(new tiposEntity("impersonal"));
        //Compuestas
        dao.insert(new tiposEntity("compuesta"));
        //coordinadas
        dao.insert(new tiposEntity("coordinada",26));
        dao.insert(new tiposEntity("copulativa",27));
        dao.insert( new tiposEntity("disyuntiva",27));
        dao.insert( new tiposEntity("distributiva",27));
        dao.insert(new tiposEntity("adversativa",27));
        dao.insert(new tiposEntity("explicativa",27));
        //subordinadas
        dao.insert(new tiposEntity("subordinada",26));
        dao.insert(new tiposEntity("sustantiva",33));
        dao.insert( new tiposEntity("adjetivas",33));
        dao.insert(new tiposEntity("adverbiale",33));
        //yuxtapuestas
        dao.insert(new tiposEntity("yuxtapuesta",26));
    }

    private static void addItemFra(FrasesDao daoFrases) {




        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(false));
        daoFrases.insert(new frasesEntity(true));//101

    }

    private static void addItemFraTip(FratipDao dao) {
        dao.insert(new fratipEntity(1,1,1));
        dao.insert(new fratipEntity(1,10,2));
        dao.insert(new fratipEntity(1,11,3));
        dao.insert(new fratipEntity(1,12,4));

        dao.insert(new fratipEntity(2,1,1));
        dao.insert(new fratipEntity(2,2,2));
        dao.insert(new fratipEntity(2,3,3));
        dao.insert(new fratipEntity(2,7,4));
        dao.insert(new fratipEntity(2,14,5));
        dao.insert(new fratipEntity(2,15,6));
        dao.insert(new fratipEntity(2,18,7));

        dao.insert(new fratipEntity(3,1,1));
        dao.insert(new fratipEntity(3,2,2));
        dao.insert(new fratipEntity(3,3,3));
        dao.insert(new fratipEntity(3,4,4));
        dao.insert(new fratipEntity(3,11,5));
        dao.insert(new fratipEntity(3,12,6));

        dao.insert(new fratipEntity(4,1,1));
        dao.insert(new fratipEntity(4,2,2));
        dao.insert(new fratipEntity(4,3,3));
        dao.insert(new fratipEntity(4,4,4));
        dao.insert(new fratipEntity(4,11,5));
        dao.insert(new fratipEntity(4,12,6));

        dao.insert(new fratipEntity(5,1,1));
        dao.insert(new fratipEntity(5,2,2));
        dao.insert(new fratipEntity(5,3,3));
        dao.insert(new fratipEntity(5,4,4));
        dao.insert(new fratipEntity(5,5,5));
        dao.insert(new fratipEntity(5,11,6));
        dao.insert(new fratipEntity(5,12,7));

        dao.insert(new fratipEntity(6,1,1));
        dao.insert(new fratipEntity(6,2,2));
        dao.insert(new fratipEntity(6,3,3));
        dao.insert(new fratipEntity(6,4,4));
        dao.insert(new fratipEntity(6,5,5));
        dao.insert(new fratipEntity(6,11,6));
        dao.insert(new fratipEntity(6,12,7));

        dao.insert(new fratipEntity(7,1,1));
        dao.insert(new fratipEntity(7,2,2));
        dao.insert(new fratipEntity(7,3,3));
        dao.insert(new fratipEntity(7,7,4));
        dao.insert(new fratipEntity(7,11,5));
        dao.insert(new fratipEntity(7,12,6));

        dao.insert(new fratipEntity(8,1,1));
        dao.insert(new fratipEntity(8,2,2));
        dao.insert(new fratipEntity(8,3,3));
        dao.insert(new fratipEntity(8,7,4));
        dao.insert(new fratipEntity(8,11,5));
        dao.insert(new fratipEntity(8,12,6));

        dao.insert(new fratipEntity(9,1,1));
        dao.insert(new fratipEntity(9,2,2));
        dao.insert(new fratipEntity(9,3,3));
        dao.insert(new fratipEntity(9,7,4));
        dao.insert(new fratipEntity(9,11,5));
        dao.insert(new fratipEntity(9,12,6));

        dao.insert(new fratipEntity(10,1,1));
        dao.insert(new fratipEntity(10,2,2));
        dao.insert(new fratipEntity(10,3,3));
        dao.insert(new fratipEntity(10,7,4));
        dao.insert(new fratipEntity(10,11,5));
        dao.insert(new fratipEntity(10,12,6));


        dao.insert(new fratipEntity(11,1,1));
        dao.insert(new fratipEntity(11,2,2));
        dao.insert(new fratipEntity(11,3,3));
        dao.insert(new fratipEntity(11,7,4));
        dao.insert(new fratipEntity(11,11,5));
        dao.insert(new fratipEntity(11,12,6));

        dao.insert(new fratipEntity(12,1,1));
        dao.insert(new fratipEntity(12,2,2));
        dao.insert(new fratipEntity(12,3,3));
        dao.insert(new fratipEntity(12,7,4));
        dao.insert(new fratipEntity(12,11,5));
        dao.insert(new fratipEntity(12,12,6));

        dao.insert(new fratipEntity(13,1,1));
        dao.insert(new fratipEntity(13,2,2));
        dao.insert(new fratipEntity(13,3,3));
        dao.insert(new fratipEntity(13,7,4));
        dao.insert(new fratipEntity(13,11,5));
        dao.insert(new fratipEntity(13,12,6));

        dao.insert(new fratipEntity(14,1,1));
        dao.insert(new fratipEntity(14,2,2));
        dao.insert(new fratipEntity(14,3,3));
        dao.insert(new fratipEntity(14,7,4));
        dao.insert(new fratipEntity(14,11,5));
        dao.insert(new fratipEntity(14,12,6));

        dao.insert(new fratipEntity(15,1,1));
        dao.insert(new fratipEntity(15,2,2));
        dao.insert(new fratipEntity(15,3,3));
        dao.insert(new fratipEntity(15,7,4));
        dao.insert(new fratipEntity(15,11,5));
        dao.insert(new fratipEntity(15,12,6));


        dao.insert(new fratipEntity(16,1,1));
        dao.insert(new fratipEntity(16,2,2));
        dao.insert(new fratipEntity(16,3,3));
        dao.insert(new fratipEntity(16,7,4));
        dao.insert(new fratipEntity(16,11,5));
        dao.insert(new fratipEntity(16,12,6));

        dao.insert(new fratipEntity(17,1,1));
        dao.insert(new fratipEntity(17,2,2));
        dao.insert(new fratipEntity(17,3,3));
        dao.insert(new fratipEntity(17,7,4));
        dao.insert(new fratipEntity(17,11,5));
        dao.insert(new fratipEntity(17,12,6));

        dao.insert(new fratipEntity(18,1,1));
        dao.insert(new fratipEntity(18,2,2));
        dao.insert(new fratipEntity(18,3,3));
        dao.insert(new fratipEntity(18,7,4));
        dao.insert(new fratipEntity(18,11,5));
        dao.insert(new fratipEntity(18,12,6));

        dao.insert(new fratipEntity(19,1,1));
        dao.insert(new fratipEntity(19,2,2));
        dao.insert(new fratipEntity(19,3,3));
        dao.insert(new fratipEntity(19,7,4));
        dao.insert(new fratipEntity(19,11,5));
        dao.insert(new fratipEntity(19,12,6));


        dao.insert(new fratipEntity(20,1,1));
        dao.insert(new fratipEntity(20,2,2));
        dao.insert(new fratipEntity(20,3,3));
        dao.insert(new fratipEntity(20,7,4));
        dao.insert(new fratipEntity(20,11,5));
        dao.insert(new fratipEntity(20,12,6));

        dao.insert(new fratipEntity(21,1,1));
        dao.insert(new fratipEntity(21,2,2));
        dao.insert(new fratipEntity(21,3,3));
        dao.insert(new fratipEntity(21,7,4));
        dao.insert(new fratipEntity(21,6,5));
        dao.insert(new fratipEntity(21,11,6));
        dao.insert(new fratipEntity(21,12,7));

        dao.insert(new fratipEntity(22,1,1));
        dao.insert(new fratipEntity(22,2,2));
        dao.insert(new fratipEntity(22,3,3));
        dao.insert(new fratipEntity(22,4,4));
        dao.insert(new fratipEntity(22,6,5));
        dao.insert(new fratipEntity(22,11,6));
        dao.insert(new fratipEntity(22,12,7));

        dao.insert(new fratipEntity(23,1,1));
        dao.insert(new fratipEntity(23,2,2));
        dao.insert(new fratipEntity(23,3,3));
        dao.insert(new fratipEntity(23,4,4));
        dao.insert(new fratipEntity(23,6,5));
        dao.insert(new fratipEntity(23,11,6));
        dao.insert(new fratipEntity(23,12,7));


        dao.insert(new fratipEntity(24,1,1));
        dao.insert(new fratipEntity(24,2,2));
        dao.insert(new fratipEntity(24,3,3));
        dao.insert(new fratipEntity(24,4,4));
        dao.insert(new fratipEntity(24,6,5));
        dao.insert(new fratipEntity(24,11,6));
        dao.insert(new fratipEntity(24,12,7));

        dao.insert(new fratipEntity(25,1,1));
        dao.insert(new fratipEntity(25,2,2));
        dao.insert(new fratipEntity(25,3,3));
        dao.insert(new fratipEntity(25,4,4));
        dao.insert(new fratipEntity(25,6,5));
        dao.insert(new fratipEntity(25,11,6));
        dao.insert(new fratipEntity(25,12,7));

        dao.insert(new fratipEntity(26,1,1));
        dao.insert(new fratipEntity(26,2,2));
        dao.insert(new fratipEntity(26,3,3));
        dao.insert(new fratipEntity(26,4,4));
        dao.insert(new fratipEntity(26,6,5));
        dao.insert(new fratipEntity(26,11,6));
        dao.insert(new fratipEntity(26,12,7));

        dao.insert(new fratipEntity(27,1,1));
        dao.insert(new fratipEntity(27,2,2));
        dao.insert(new fratipEntity(27,3,3));
        dao.insert(new fratipEntity(27,4,4));
        dao.insert(new fratipEntity(27,6,5));
        dao.insert(new fratipEntity(27,11,6));
        dao.insert(new fratipEntity(27,12,7));

        dao.insert(new fratipEntity(28,1,1));
        dao.insert(new fratipEntity(28,2,2));
        dao.insert(new fratipEntity(28,3,3));
        dao.insert(new fratipEntity(28,4,4));
        dao.insert(new fratipEntity(28,6,5));
        dao.insert(new fratipEntity(28,11,6));
        dao.insert(new fratipEntity(28,12,7));

        dao.insert(new fratipEntity(29,1,1));
        dao.insert(new fratipEntity(29,2,2));
        dao.insert(new fratipEntity(29,3,3));
        dao.insert(new fratipEntity(29,4,4));
        dao.insert(new fratipEntity(29,6,5));
        dao.insert(new fratipEntity(29,11,6));
        dao.insert(new fratipEntity(29,12,7));

        dao.insert(new fratipEntity(30,1,1));
        dao.insert(new fratipEntity(30,2,2));
        dao.insert(new fratipEntity(30,3,3));
        dao.insert(new fratipEntity(30,4,4));
        dao.insert(new fratipEntity(30,6,5));
        dao.insert(new fratipEntity(30,11,6));
        dao.insert(new fratipEntity(30,12,7));

        dao.insert(new fratipEntity(31,1,1));
        dao.insert(new fratipEntity(31,2,2));
        dao.insert(new fratipEntity(31,3,3));
        dao.insert(new fratipEntity(31,4,4));
        dao.insert(new fratipEntity(31,6,5));
        dao.insert(new fratipEntity(31,11,6));
        dao.insert(new fratipEntity(31,12,7));

        dao.insert(new fratipEntity(32,1,1));
        dao.insert(new fratipEntity(32,2,2));
        dao.insert(new fratipEntity(32,3,3));
        dao.insert(new fratipEntity(32,4,4));
        dao.insert(new fratipEntity(32,6,5));
        dao.insert(new fratipEntity(32,11,6));
        dao.insert(new fratipEntity(32,12,7));

        dao.insert(new fratipEntity(33,1,1));
        dao.insert(new fratipEntity(33,2,2));
        dao.insert(new fratipEntity(33,8,3));
        dao.insert(new fratipEntity(33,9,4));
        dao.insert(new fratipEntity(33,11,5));
        dao.insert(new fratipEntity(33,12,6));

        dao.insert(new fratipEntity(34,1,1));
        dao.insert(new fratipEntity(34,2,2));
        dao.insert(new fratipEntity(34,8,3));
        dao.insert(new fratipEntity(34,9,4));
        dao.insert(new fratipEntity(34,11,5));
        dao.insert(new fratipEntity(34,12,6));

        dao.insert(new fratipEntity(35,1,1));
        dao.insert(new fratipEntity(35,2,2));
        dao.insert(new fratipEntity(35,8,3));
        dao.insert(new fratipEntity(35,9,4));
        dao.insert(new fratipEntity(35,11,5));
        dao.insert(new fratipEntity(35,12,6));

        dao.insert(new fratipEntity(36,1,1));
        dao.insert(new fratipEntity(36,2,2));
        dao.insert(new fratipEntity(36,8,3));
        dao.insert(new fratipEntity(36,9,4));
        dao.insert(new fratipEntity(36,11,5));
        dao.insert(new fratipEntity(36,12,6));

        dao.insert(new fratipEntity(37,1,1));
        dao.insert(new fratipEntity(37,2,2));
        dao.insert(new fratipEntity(37,8,3));
        dao.insert(new fratipEntity(37,9,4));
        dao.insert(new fratipEntity(37,11,5));
        dao.insert(new fratipEntity(37,12,6));

        dao.insert(new fratipEntity(38,1,1));
        dao.insert(new fratipEntity(38,2,2));
        dao.insert(new fratipEntity(38,8,3));
        dao.insert(new fratipEntity(38,9,4));
        dao.insert(new fratipEntity(38,11,5));
        dao.insert(new fratipEntity(38,12,6));

        dao.insert(new fratipEntity(39,1,1));
        dao.insert(new fratipEntity(39,2,2));
        dao.insert(new fratipEntity(39,8,3));
        dao.insert(new fratipEntity(39,9,4));
        dao.insert(new fratipEntity(39,11,5));
        dao.insert(new fratipEntity(39,12,6));

        dao.insert(new fratipEntity(40,1,1));
        dao.insert(new fratipEntity(40,2,2));
        dao.insert(new fratipEntity(40,8,3));
        dao.insert(new fratipEntity(40,9,4));
        dao.insert(new fratipEntity(40,11,5));
        dao.insert(new fratipEntity(40,12,6));

        dao.insert(new fratipEntity(41,1,1));
        dao.insert(new fratipEntity(41,2,2));
        dao.insert(new fratipEntity(41,8,3));
        dao.insert(new fratipEntity(41,9,4));
        dao.insert(new fratipEntity(41,11,5));
        dao.insert(new fratipEntity(41,12,6));

        dao.insert(new fratipEntity(42,1,1));
        dao.insert(new fratipEntity(42,2,2));
        dao.insert(new fratipEntity(42,8,3));
        dao.insert(new fratipEntity(42,9,4));
        dao.insert(new fratipEntity(42,11,5));
        dao.insert(new fratipEntity(42,12,6));

        dao.insert(new fratipEntity(43,1,1));
        dao.insert(new fratipEntity(43,2,2));
        dao.insert(new fratipEntity(43,8,3));
        dao.insert(new fratipEntity(43,9,4));
        dao.insert(new fratipEntity(43,11,5));
        dao.insert(new fratipEntity(43,13,6));

        dao.insert(new fratipEntity(44,1,1));
        dao.insert(new fratipEntity(44,2,2));
        dao.insert(new fratipEntity(44,8,3));
        dao.insert(new fratipEntity(44,9,4));
        dao.insert(new fratipEntity(44,11,5));
        dao.insert(new fratipEntity(44,12,6));

        dao.insert(new fratipEntity(45,1,1));
        dao.insert(new fratipEntity(45,2,2));
        dao.insert(new fratipEntity(45,8,3));
        dao.insert(new fratipEntity(45,9,4));
        dao.insert(new fratipEntity(45,11,5));
        dao.insert(new fratipEntity(45,12,6));

        dao.insert(new fratipEntity(46,1,1));
        dao.insert(new fratipEntity(46,2,2));
        dao.insert(new fratipEntity(46,8,3));
        dao.insert(new fratipEntity(46,9,4));
        dao.insert(new fratipEntity(46,11,5));
        dao.insert(new fratipEntity(46,12,6));

        dao.insert(new fratipEntity(47,1,1));
        dao.insert(new fratipEntity(47,2,2));
        dao.insert(new fratipEntity(47,3,3));
        dao.insert(new fratipEntity(47,4,4));
        dao.insert(new fratipEntity(47,11,5));
        dao.insert(new fratipEntity(47,5,6));
        dao.insert(new fratipEntity(47,12,7));

        dao.insert(new fratipEntity(48,1,1));
        dao.insert(new fratipEntity(48,2,2));
        dao.insert(new fratipEntity(48,3,3));
        dao.insert(new fratipEntity(48,4,4));
        dao.insert(new fratipEntity(48,11,5));
        dao.insert(new fratipEntity(48,5,6));
        dao.insert(new fratipEntity(48,12,7));

        dao.insert(new fratipEntity(49,1,1));
        dao.insert(new fratipEntity(49,2,2));
        dao.insert(new fratipEntity(49,3,3));
        dao.insert(new fratipEntity(49,4,4));
        dao.insert(new fratipEntity(49,11,5));
        dao.insert(new fratipEntity(49,5,6));
        dao.insert(new fratipEntity(49,12,7));

        dao.insert(new fratipEntity(50,1,1));
        dao.insert(new fratipEntity(50,2,2));
        dao.insert(new fratipEntity(50,3,3));
        dao.insert(new fratipEntity(50,4,4));
        dao.insert(new fratipEntity(50,11,5));
        dao.insert(new fratipEntity(50,5,6));
        dao.insert(new fratipEntity(50,12,7));

        dao.insert(new fratipEntity(51,1,1));
        dao.insert(new fratipEntity(51,2,2));
        dao.insert(new fratipEntity(51,3,3));
        dao.insert(new fratipEntity(51,4,4));
        dao.insert(new fratipEntity(51,11,5));
        dao.insert(new fratipEntity(51,5,6));
        dao.insert(new fratipEntity(51,12,7));

        dao.insert(new fratipEntity(52,1,1));
        dao.insert(new fratipEntity(52,2,2));
        dao.insert(new fratipEntity(52,3,3));
        dao.insert(new fratipEntity(52,4,4));
        dao.insert(new fratipEntity(52,11,5));
        dao.insert(new fratipEntity(52,5,6));
        dao.insert(new fratipEntity(52,12,7));

        dao.insert(new fratipEntity(53,1,1));
        dao.insert(new fratipEntity(53,2,2));
        dao.insert(new fratipEntity(53,3,3));
        dao.insert(new fratipEntity(53,4,4));
        dao.insert(new fratipEntity(53,11,5));
        dao.insert(new fratipEntity(53,5,6));
        dao.insert(new fratipEntity(53,12,7));

        dao.insert(new fratipEntity(54,1,1));
        dao.insert(new fratipEntity(54,2,2));
        dao.insert(new fratipEntity(54,3,3));
        dao.insert(new fratipEntity(54,4,4));
        dao.insert(new fratipEntity(54,11,5));
        dao.insert(new fratipEntity(54,5,6));
        dao.insert(new fratipEntity(54,12,7));

        dao.insert(new fratipEntity(55,1,1));
        dao.insert(new fratipEntity(55,2,2));
        dao.insert(new fratipEntity(55,3,3));
        dao.insert(new fratipEntity(55,4,4));
        dao.insert(new fratipEntity(55,11,5));
        dao.insert(new fratipEntity(55,5,6));
        dao.insert(new fratipEntity(55,12,7));

        dao.insert(new fratipEntity(56,1,1));
        dao.insert(new fratipEntity(56,2,2));
        dao.insert(new fratipEntity(56,3,3));
        dao.insert(new fratipEntity(56,4,4));
        dao.insert(new fratipEntity(56,11,5));
        dao.insert(new fratipEntity(56,5,6));
        dao.insert(new fratipEntity(56,12,7));

        dao.insert(new fratipEntity(57,1,1));
        dao.insert(new fratipEntity(57,2,2));
        dao.insert(new fratipEntity(57,3,3));
        dao.insert(new fratipEntity(57,4,4));
        dao.insert(new fratipEntity(57,11,5));
        dao.insert(new fratipEntity(57,5,6));
        dao.insert(new fratipEntity(57,12,7));

        dao.insert(new fratipEntity(58,1,1));
        dao.insert(new fratipEntity(58,2,2));
        dao.insert(new fratipEntity(58,3,3));
        dao.insert(new fratipEntity(58,7,4));
        dao.insert(new fratipEntity(58,11,5));
        dao.insert(new fratipEntity(58,12,6));

        dao.insert(new fratipEntity(59,1,1));
        dao.insert(new fratipEntity(59,2,2));
        dao.insert(new fratipEntity(59,3,3));
        dao.insert(new fratipEntity(59,7,4));
        dao.insert(new fratipEntity(59,11,5));
        dao.insert(new fratipEntity(59,12,6));

        dao.insert(new fratipEntity(60,1,1));
        dao.insert(new fratipEntity(60,2,2));
        dao.insert(new fratipEntity(60,3,3));
        dao.insert(new fratipEntity(60,7,4));
        dao.insert(new fratipEntity(60,11,5));
        dao.insert(new fratipEntity(60,12,6));

        dao.insert(new fratipEntity(61,1,1));
        dao.insert(new fratipEntity(61,2,2));
        dao.insert(new fratipEntity(61,3,3));
        dao.insert(new fratipEntity(61,7,4));
        dao.insert(new fratipEntity(61,11,5));
        dao.insert(new fratipEntity(61,12,6));

        dao.insert(new fratipEntity(62,1,1));
        dao.insert(new fratipEntity(62,2,2));
        dao.insert(new fratipEntity(62,3,3));
        dao.insert(new fratipEntity(62,7,4));
        dao.insert(new fratipEntity(62,11,5));
        dao.insert(new fratipEntity(62,12,6));

        dao.insert(new fratipEntity(63,1,1));
        dao.insert(new fratipEntity(63,2,2));
        dao.insert(new fratipEntity(63,3,3));
        dao.insert(new fratipEntity(63,7,4));
        dao.insert(new fratipEntity(63,11,5));
        dao.insert(new fratipEntity(63,12,6));

        dao.insert(new fratipEntity(64,1,1));
        dao.insert(new fratipEntity(64,2,2));
        dao.insert(new fratipEntity(64,3,3));
        dao.insert(new fratipEntity(64,7,4));
        dao.insert(new fratipEntity(64,11,5));
        dao.insert(new fratipEntity(64,12,6));

        dao.insert(new fratipEntity(65,1,1));
        dao.insert(new fratipEntity(65,10,2));
        dao.insert(new fratipEntity(65,11,3));
        dao.insert(new fratipEntity(65,12,4));

        dao.insert(new fratipEntity(66,1,1));
        dao.insert(new fratipEntity(66,10,2));
        dao.insert(new fratipEntity(66,11,3));
        dao.insert(new fratipEntity(66,12,4));

        dao.insert(new fratipEntity(67,1,1));
        dao.insert(new fratipEntity(67,10,2));
        dao.insert(new fratipEntity(67,11,3));
        dao.insert(new fratipEntity(67,12,4));

        dao.insert(new fratipEntity(68,1,1));
        dao.insert(new fratipEntity(68,10,2));
        dao.insert(new fratipEntity(68,11,3));
        dao.insert(new fratipEntity(68,12,4));

        dao.insert(new fratipEntity(69,1,1));
        dao.insert(new fratipEntity(69,10,2));
        dao.insert(new fratipEntity(69,11,3));
        dao.insert(new fratipEntity(69,12,4));

        dao.insert(new fratipEntity(70,1,1));
        dao.insert(new fratipEntity(70,10,2));
        dao.insert(new fratipEntity(70,11,3));
        dao.insert(new fratipEntity(70,12,4));

        dao.insert(new fratipEntity(71,1,1));
        dao.insert(new fratipEntity(71,10,2));
        dao.insert(new fratipEntity(71,11,3));
        dao.insert(new fratipEntity(71,12,4));

        dao.insert(new fratipEntity(72,1,1));
        dao.insert(new fratipEntity(72,10,2));
        dao.insert(new fratipEntity(72,11,3));
        dao.insert(new fratipEntity(72,12,4));

        dao.insert(new fratipEntity(73,1,1));
        dao.insert(new fratipEntity(73,10,2));
        dao.insert(new fratipEntity(73,11,3));
        dao.insert(new fratipEntity(73,12,4));

        dao.insert(new fratipEntity(74,1,1));
        dao.insert(new fratipEntity(74,10,2));
        dao.insert(new fratipEntity(74,11,3));
        dao.insert(new fratipEntity(74,12,4));

        dao.insert(new fratipEntity(75,1,1));
        dao.insert(new fratipEntity(75,10,2));
        dao.insert(new fratipEntity(75,11,3));
        dao.insert(new fratipEntity(75,12,4));

        dao.insert(new fratipEntity(76,1,1));
        dao.insert(new fratipEntity(76,10,2));
        dao.insert(new fratipEntity(76,11,3));
        dao.insert(new fratipEntity(76,12,4));

        dao.insert(new fratipEntity(77,1,1));
        dao.insert(new fratipEntity(77,2,2));
        dao.insert(new fratipEntity(77,3,3));
        dao.insert(new fratipEntity(77,4,4));
        dao.insert(new fratipEntity(77,6,5));
        dao.insert(new fratipEntity(77,11,6));
        dao.insert(new fratipEntity(77,12,7));

        dao.insert(new fratipEntity(78,1,1));
        dao.insert(new fratipEntity(78,2,2));
        dao.insert(new fratipEntity(78,3,3));
        dao.insert(new fratipEntity(78,4,4));
        dao.insert(new fratipEntity(78,6,5));
        dao.insert(new fratipEntity(78,11,6));
        dao.insert(new fratipEntity(78,12,7));

        dao.insert(new fratipEntity(79,1,1));
        dao.insert(new fratipEntity(79,2,2));
        dao.insert(new fratipEntity(79,3,3));
        dao.insert(new fratipEntity(79,4,4));
        dao.insert(new fratipEntity(79,6,5));
        dao.insert(new fratipEntity(79,11,6));
        dao.insert(new fratipEntity(79,12,7));

        dao.insert(new fratipEntity(80,1,1));
        dao.insert(new fratipEntity(80,2,2));
        dao.insert(new fratipEntity(80,3,3));
        dao.insert(new fratipEntity(80,4,4));
        dao.insert(new fratipEntity(80,6,5));
        dao.insert(new fratipEntity(80,11,6));
        dao.insert(new fratipEntity(80,12,7));

        dao.insert(new fratipEntity(81,1,1));
        dao.insert(new fratipEntity(81,2,2));
        dao.insert(new fratipEntity(81,3,3));
        dao.insert(new fratipEntity(81,4,4));
        dao.insert(new fratipEntity(81,6,5));
        dao.insert(new fratipEntity(81,11,6));
        dao.insert(new fratipEntity(81,12,7));

        dao.insert(new fratipEntity(82,1,1));
        dao.insert(new fratipEntity(82,2,2));
        dao.insert(new fratipEntity(82,3,3));
        dao.insert(new fratipEntity(82,4,4));
        dao.insert(new fratipEntity(82,6,5));
        dao.insert(new fratipEntity(82,11,6));
        dao.insert(new fratipEntity(82,12,7));

        dao.insert(new fratipEntity(83,1,1));
        dao.insert(new fratipEntity(83,2,2));
        dao.insert(new fratipEntity(83,3,3));
        dao.insert(new fratipEntity(83,4,4));
        dao.insert(new fratipEntity(83,6,5));
        dao.insert(new fratipEntity(83,11,6));
        dao.insert(new fratipEntity(83,12,7));

        dao.insert(new fratipEntity(84,1,1));
        dao.insert(new fratipEntity(84,2,2));
        dao.insert(new fratipEntity(84,3,3));
        dao.insert(new fratipEntity(84,4,4));
        dao.insert(new fratipEntity(84,6,5));
        dao.insert(new fratipEntity(84,11,6));
        dao.insert(new fratipEntity(84,12,7));

        dao.insert(new fratipEntity(85,1,1));
        dao.insert(new fratipEntity(85,2,2));
        dao.insert(new fratipEntity(85,3,3));
        dao.insert(new fratipEntity(85,4,4));
        dao.insert(new fratipEntity(85,6,5));
        dao.insert(new fratipEntity(85,11,6));
        dao.insert(new fratipEntity(85,12,7));

        dao.insert(new fratipEntity(86,1,1));
        dao.insert(new fratipEntity(86,2,2));
        dao.insert(new fratipEntity(86,3,3));
        dao.insert(new fratipEntity(86,4,4));
        dao.insert(new fratipEntity(86,6,5));
        dao.insert(new fratipEntity(86,11,6));
        dao.insert(new fratipEntity(86,12,7));

        dao.insert(new fratipEntity(87,1,1));
        dao.insert(new fratipEntity(87,2,2));
        dao.insert(new fratipEntity(87,3,3));
        dao.insert(new fratipEntity(87,4,4));
        dao.insert(new fratipEntity(87,6,5));
        dao.insert(new fratipEntity(87,11,6));
        dao.insert(new fratipEntity(87,12,7));

        dao.insert(new fratipEntity(88,1,1));
        dao.insert(new fratipEntity(88,2,2));
        dao.insert(new fratipEntity(88,3,3));
        dao.insert(new fratipEntity(88,4,4));
        dao.insert(new fratipEntity(88,6,5));
        dao.insert(new fratipEntity(88,11,6));
        dao.insert(new fratipEntity(88,12,7));


        dao.insert(new fratipEntity(89,1,1));
        dao.insert(new fratipEntity(89,2,2));
        dao.insert(new fratipEntity(89,8,3));
        dao.insert(new fratipEntity(89,11,4));
        dao.insert(new fratipEntity(89,12,5));


        dao.insert(new fratipEntity(90,1,1));
        dao.insert(new fratipEntity(90,2,2));
        dao.insert(new fratipEntity(90,8,3));
        dao.insert(new fratipEntity(90,11,4));
        dao.insert(new fratipEntity(90,12,5));

        dao.insert(new fratipEntity(91,1,1));
        dao.insert(new fratipEntity(91,2,2));
        dao.insert(new fratipEntity(91,8,3));
        dao.insert(new fratipEntity(91,11,4));
        dao.insert(new fratipEntity(91,12,5));

        dao.insert(new fratipEntity(92,1,1));
        dao.insert(new fratipEntity(92,2,2));
        dao.insert(new fratipEntity(92,8,3));
        dao.insert(new fratipEntity(92,11,4));
        dao.insert(new fratipEntity(92,12,5));

        dao.insert(new fratipEntity(93,1,1));
        dao.insert(new fratipEntity(93,2,2));
        dao.insert(new fratipEntity(93,8,3));
        dao.insert(new fratipEntity(93,11,4));
        dao.insert(new fratipEntity(93,12,5));

        dao.insert(new fratipEntity(94,1,1));
        dao.insert(new fratipEntity(94,2,2));
        dao.insert(new fratipEntity(94,8,3));
        dao.insert(new fratipEntity(94,11,4));
        dao.insert(new fratipEntity(94,12,5));

        dao.insert(new fratipEntity(95,1,1));
        dao.insert(new fratipEntity(95,2,2));
        dao.insert(new fratipEntity(95,8,3));
        dao.insert(new fratipEntity(95,11,4));
        dao.insert(new fratipEntity(95,12,5));

        dao.insert(new fratipEntity(96,1,1));
        dao.insert(new fratipEntity(96,2,2));
        dao.insert(new fratipEntity(96,8,3));
        dao.insert(new fratipEntity(96,11,4));
        dao.insert(new fratipEntity(96,12,5));

        dao.insert(new fratipEntity(97,1,1));
        dao.insert(new fratipEntity(97,2,2));
        dao.insert(new fratipEntity(97,8,3));
        dao.insert(new fratipEntity(97,11,4));
        dao.insert(new fratipEntity(97,12,5));

        dao.insert(new fratipEntity(98,1,1));
        dao.insert(new fratipEntity(98,2,2));
        dao.insert(new fratipEntity(98,8,3));
        dao.insert(new fratipEntity(98,11,4));
        dao.insert(new fratipEntity(98,12,5));

        dao.insert(new fratipEntity(99,1,1));
        dao.insert(new fratipEntity(99,2,2));
        dao.insert(new fratipEntity(99,8,3));
        dao.insert(new fratipEntity(99,11,4));
        dao.insert(new fratipEntity(99,12,5));

        dao.insert(new fratipEntity(100,1,1));
        dao.insert(new fratipEntity(100,2,2));
        dao.insert(new fratipEntity(100,8,3));
        dao.insert(new fratipEntity(100,11,4));
        dao.insert(new fratipEntity(100,12,5));

        dao.insert(new fratipEntity(101,26,1));
        dao.insert(new fratipEntity(101,27,2));
        dao.insert(new fratipEntity(101,28,3));
        dao.insert(new fratipEntity(101,1,4));
        dao.insert(new fratipEntity(101,2,5));
        dao.insert(new fratipEntity(101,3,6));
        dao.insert(new fratipEntity(101,7,7));
        dao.insert(new fratipEntity(101,11,8));
        dao.insert(new fratipEntity(101,12,9));
        dao.insert(new fratipEntity(101,1,10));
        dao.insert(new fratipEntity(101,2,11));
        dao.insert(new fratipEntity(101,3,12));
        dao.insert(new fratipEntity(101,4,13));
        dao.insert(new fratipEntity(101,11,14));
        dao.insert(new fratipEntity(101,12,15));



    }



    private static void Sustantivos(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("hierro", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/hierro");
        dao.insert(palabra);
        palabra = new PalabrasEntity("pato", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/pato#SAGNQ6l");
        dao.insert(palabra);
        palabra = new PalabrasEntity("pata", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/pato#SAGNQ6l");
        dao.insert(palabra);
        palabra = new PalabrasEntity("mesa", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/mesa");
        dao.insert(palabra);
        palabra = new PalabrasEntity("cafeína", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/cafe%C3%ADna");
        dao.insert(palabra);
        palabra = new PalabrasEntity("estrella", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/estrella?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("explosión", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/explosi%C3%B3n?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("guitarra", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/guitarra?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("navaja", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/navaja#QIQMR8S");
        dao.insert(palabra);
        palabra = new PalabrasEntity("martillo", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/martillo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("libro", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/libro?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("embarcación", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/embarcaci%C3%B3n?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("rueda", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/rueda?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("pelo", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/pelo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("agujeta", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/agujeta?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("felicidad", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/felicidad?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("satélite", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/sat%C3%A9lite?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("templo", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/templo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("lentes", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/lente?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("bolígrafo", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/bol%C3%ADgrafo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("botella", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/botella?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("castillo", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/castillo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("casa", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/casa?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("persona", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/persona?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("metal", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/metal?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("teléfono", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/tel%C3%A9fono?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("mapa", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/mapa?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("mensaje", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/mensaje?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("masaje", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/masaje?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("cohete", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/cohete?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("rey", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/rey?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("reina", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/rey?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("edificio", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/edificio?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("césped", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/c%C3%A9sped?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("candidato", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/candidato?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("candidata", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/candidato?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("silla", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/silla?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("deporte", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/deporte?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("rúcula", true, false, false, false, false, false, false, false, false, "https://dle.rae.es/r%C3%BAcula?m=form");
        dao.insert(palabra);
    }
    private static void Adjetivos(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("actual", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/actual?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("aburrido", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/aburrido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("aburrida", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/aburrido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("ácida", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/%C3%A1cido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("alegre", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/alegre?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("débil", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/d%C3%A9bil");
        dao.insert(palabra);
        palabra = new PalabrasEntity("amarga", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/amargo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("atrevido ", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/atrevido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("delgada", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/delgado?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("atrevida", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/atrevido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("bonita", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/bonito#5rVKGkX");
        dao.insert(palabra);
        palabra = new PalabrasEntity("buen", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/buen?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("difícil", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/dif%C3%ADcil?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("divertido", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/divertido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("divertida", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/divertido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("enfermo", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/enfermo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("enferma", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/enfermo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("estrecha", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/estrecho?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("famoso", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/famoso");
        dao.insert(palabra);
        palabra = new PalabrasEntity("famosa", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/famoso");
        dao.insert(palabra);
        palabra = new PalabrasEntity("fea", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/feo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("guapa", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/guapo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("interesante", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/interesante?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("inútil", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/in%C3%BAtil?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("izquierdo", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/izquierdo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("listo", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/listo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("masivo", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/masivo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("masiva", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/masivo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("muerta", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/muerto?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("nuevo", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/nuevo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("pequeño", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/peque%C3%B1o?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("pequeña", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/peque%C3%B1o?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("perfecta", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/perfecto?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("popular", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/popular?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("primer", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/primer?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("próxima", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/pr%C3%B3ximo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("próximo", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/pr%C3%B3ximo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("rápida", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/r%C3%A1pido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("raro", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/rara#VANhUcQ");
        dao.insert(palabra);
        palabra = new PalabrasEntity("roja", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/rojo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("sano", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/sano?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("sana", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/sano?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("social", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/social?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("sola", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/solo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("soso", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/soso#YRqpFp4");
        dao.insert(palabra);
        palabra = new PalabrasEntity("tímido", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/t%C3%ADmido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("tímida", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/t%C3%ADmido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("tonta", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/tonto?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("verdadero", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/verdadero?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("verdadera", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/verdadero?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("viva", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/vivo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("errado", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/errado?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("impreciso", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/impreciso?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("imprecisa", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/impreciso?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("preciso", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/preciso?m=form");
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
        palabra = new PalabrasEntity("adjetivo", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/adjetivo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("adjetiva", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/adjetivo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("diaria", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/diario?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("diario", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/diario?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("estadístico", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/estad%C3%ADstico#GjpDTiC");
        dao.insert(palabra);
        palabra = new PalabrasEntity("enano", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/enano?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("enana", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/enano?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("perra", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/perro?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("perro", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/perro?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("plástica", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/pl%C3%A1stico?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("plástico", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/pl%C3%A1stico?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("crema", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/crema#BDxo4Yt");
        dao.insert(palabra);
        palabra = new PalabrasEntity("café", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/caf%C3%A9?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("amargo", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/amargo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("tonto", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/tonto?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("vivo", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/vivo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Viejo", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/viejo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Vieja", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/viejo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("Verde", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/verde?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("precisa", true, false, true, false, true, false, false, false, false, "https://dle.rae.es/preciso?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("útil", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/%C3%BAtil#bCSFzkP");
        dao.insert(palabra);
        palabra = new PalabrasEntity("triste", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/triste?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("sinvergüenza", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/sinverg%C3%BCenza?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("segundo", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/segundo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("simple", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/simple?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("segunda", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/segundo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("seco", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/seco?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("seca", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/seco?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("salada", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/salado#X10c5KN");
        dao.insert(palabra);
        palabra = new PalabrasEntity("salado", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/salado#X10c5KN");
        dao.insert(palabra);
        palabra = new PalabrasEntity("rico", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/rico?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("rojo", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/rojo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("rica", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/rico?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("recto", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/recto?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("recta", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/recto?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("real", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/real#VGqyuLj");
        dao.insert(palabra);
        palabra = new PalabrasEntity("principal", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/principal?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("posible", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/posible?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("perfecto", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/perfecto?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("pobre", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/pobre?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("negro", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/negro?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("nueva", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/nuevo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("negra", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/negro?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("muerto", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/muerto?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("natural", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/natural?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("nacional", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/nacional?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("musical", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/musical?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("ancho", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/ancho?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("ancha", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/ancho?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("azul", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/azul?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("blanco", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/blanco?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("blanca", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/blanco?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("blanda", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/blando");
        dao.insert(palabra);
        palabra = new PalabrasEntity("bonito", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/bonito#5rVKGkX");
        dao.insert(palabra);
        palabra = new PalabrasEntity("bueno", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/bueno");
        dao.insert(palabra);
        palabra = new PalabrasEntity("buena", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/bueno");
        dao.insert(palabra);
        palabra = new PalabrasEntity("central", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/central?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("común", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/com%C3%BAn?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("conocido", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/conocido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("conocida", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/conocido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("contento", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/contento#AUokAR9");
        dao.insert(palabra);
        palabra = new PalabrasEntity("contenta", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/contento#AUokAR9");
        dao.insert(palabra);
        palabra = new PalabrasEntity("corto", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/corto#B356ILe");
        dao.insert(palabra);
        palabra = new PalabrasEntity("corta", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/corto#B356ILe");
        dao.insert(palabra);
        palabra = new PalabrasEntity("delgado", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/delgado?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("derecho", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/derecho?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("derecha", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/derecho?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("dulce", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/dulce?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("dura", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/duro#EIKtetO");
        dao.insert(palabra);
        palabra = new PalabrasEntity("estrecho", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/estrecho?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("exterior", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/exterior?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("falso", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/falso?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("falsa", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/falso?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("feo", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/feo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("final", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/final?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("fresca", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/fresco#IT35u3d");
        dao.insert(palabra);
        palabra = new PalabrasEntity("fresco", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/fresco#IT35u3d");
        dao.insert(palabra);
        palabra = new PalabrasEntity("fría", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/fr%C3%ADo#IUteOxn");
        dao.insert(palabra);
        palabra = new PalabrasEntity("frío", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/fr%C3%ADo#IUteOxn");
        dao.insert(palabra);
        palabra = new PalabrasEntity("gordo", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/gordo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("gorda", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/gordo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("grande", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/grande?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("guapo", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/guapo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("imposible", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/imposible?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("interior", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/interior?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("izquierda", true, false, true, false, false, false, false, false, false, "https://dle.rae.es/izquierdo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("joven", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/joven?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("malo", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/malo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("mala", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/malo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("mayor", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/mayor?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("menor", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/menor?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("clara", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/claro#9PhBhLd");
        dao.insert(palabra);
        palabra = new PalabrasEntity("seguro", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/seguro?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("larga", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/largo?m=form");
        dao.insert(palabra);
    }
    private static void AdjetivoSutantatioAdverbio(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("alto", true, false, true, false, false, true, false, false, false,"https://dle.rae.es/alto#27ieUu8");
        dao.insert(palabra);
        palabra = new PalabrasEntity("alta", true, false, true, false, false, true, false, false, false,"https://dle.rae.es/alto#27ieUu8");
        dao.insert(palabra);
        palabra = new PalabrasEntity("solo", true, false, true, false, false, true, false, false, false, "https://dle.rae.es/solo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("duro", true, false, true, false, false, true, false, false, false, "https://dle.rae.es/duro#EIKtetO");
        dao.insert(palabra);
        palabra = new PalabrasEntity("fuerte", true, false, true, false, false, true, false, false, false,"https://dle.rae.es/fuerte?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("igual", true, false, true, false, false, true, false, false, false,"https://dle.rae.es/igual?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("largo", true, false, true, false, false, true, false, false, false,"https://dle.rae.es/largo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("lento", true, false, true, false, false, true, false, false, false,"https://dle.rae.es/lento?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("rápido", true, false, true, false, false, true, false, false, false, "https://dle.rae.es/r%C3%A1pido?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("temprano", true, false, true, false, false, true, false, false, false,"https://dle.rae.es/temprano?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("mal", true, false, true, false, false, true, false, false, false,"https://dle.rae.es/mal#NyEUGaa");
        dao.insert(palabra);
        palabra = new PalabrasEntity("claro", true, false, true, false, false, true, false, false, false,"https://dle.rae.es/claro#9PhBhLd");
        dao.insert(palabra);
        palabra = new PalabrasEntity("segura", true, false, true, false, false, true, false, false, false,"https://dle.rae.es/seguro?m=form");
        dao.insert(palabra);


    }
    private static void AdjetivoInterjeccion(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("caliente", true, false, true, false, false, true, false, false, false,"https://dle.rae.es/caliente?m=form");
        dao.insert(palabra);
    }
    private static void AdjetivoAdverbio(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("capaz", false, false, true, false, false, true, false, false, false,"https://dle.rae.es/capaz?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("blando", false, false, true, false, false, true, false, false, false, "https://dle.rae.es/blando?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("caro", false, false, true, false, false, true, false, false, false, "https://dle.rae.es/caro#7d34R35");
        dao.insert(palabra);
        palabra = new PalabrasEntity("baja", false, false, true, false, false, true, false, false, false,"https://dle.rae.es/bajo#4oyrC6G");
        dao.insert(palabra);
        palabra = new PalabrasEntity("diferente", false, false, true, false, false, true, false, false, false,"https://dle.rae.es/diferente?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("lenta", false, false, true, false, false, true, false, false, false,"https://dle.rae.es/lento?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("mejor", false, false, true, false, false, true, false, false, false,"https://dle.rae.es/mejor?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("peor", false, false, true, false, false, true, false, false, false,"https://dle.rae.es/peor?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("primero", false, false, true, false, false, true, false, false, false, "https://dle.rae.es/primero#UAztjAx");
        dao.insert(palabra);
        palabra = new PalabrasEntity("temprana", false, false, true, false, false, true, false, false, false,"https://dle.rae.es/temprano?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("antes", false, false, true, false, false, true, false, false, false,"https://dle.rae.es/antes?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("después", false, false, true, false, false, true, false, false, false,"https://dle.rae.es/despu%C3%A9s?m=form");
        dao.insert(palabra);

    }
    private static void Conjuncion(PalabrasDao dao) {

        PalabrasEntity palabra = new PalabrasEntity("sino", false, false, false, false, false, false, false, true, false,"https://dle.rae.es/sino#XywVM1a");
        dao.insert(palabra);
        palabra = new PalabrasEntity("mas", false, false, false, false, false, false, false, true, false,"https://dle.rae.es/mas?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("porque", false, false, false, false, false, false, false, true, false,"https://dle.rae.es/porque?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("aunque", false, false, false, false, false, false, false, true, false,"https://dle.rae.es/aunque?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("ergo", false, false, false, false, false, false, false, true, false,"https://dle.rae.es/ergo?m=form");
        dao.insert(palabra);
    }
    private static void ConjuncionSustantivo(PalabrasDao dao){
        PalabrasEntity palabra = new PalabrasEntity("pero", true, false, false, false, false, false, false, true, false,"https://dle.rae.es/pero#SgxG2YC");
        dao.insert(palabra);
        palabra = new PalabrasEntity("ni", true, false, false, false, false, false, false, true, false,"https://dle.rae.es/ni?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("y", true, false, false, false, false, false, false, true, false,"https://dle.rae.es/y#c8HoARq");
        dao.insert(palabra);
        palabra = new PalabrasEntity("o", true, false, false, false, false, false, false, true, false,"https://dle.rae.es/o#QlqTEX0");
        dao.insert(palabra);
        palabra = new PalabrasEntity("si", true, false, false, false, false, false, false, true, false,"https://dle.rae.es/si#XmM8PPL");
        dao.insert(palabra);
    }
    private static void conjuncionAdverbioInterjeccion(PalabrasDao dao){
        PalabrasEntity  palabra = new PalabrasEntity("pues", false, false, false, false, false, true, false, true, true,"https://dle.rae.es/pues?m=form");
        dao.insert(palabra);
    }
    private static void conjuncionPronombre(PalabrasDao dao){
        PalabrasEntity    palabra = new PalabrasEntity("que", false, false, false, true, false, false, false, true, false,"https://dle.rae.es/que?m=form");
        dao.insert(palabra);
    }
    private static void Preposicion(PalabrasDao dao) {
        PalabrasEntity
        palabra = new PalabrasEntity("con", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/con?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("de", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/de#BtDkacL");
        dao.insert(palabra);
        palabra = new PalabrasEntity("desde", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/desde?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("en", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/en?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("entre", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/entre?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("hacia", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/hacia?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("para", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/para#Rp1CuT2");
        dao.insert(palabra);
        palabra = new PalabrasEntity("por", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/por?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("sin", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/sin?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("durante", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/durante?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("mediante", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/mediante?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("versus", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/versus");
        dao.insert(palabra);
    }
    private static void PreposicionSustantivo(PalabrasDao dao){
        PalabrasEntity palabra = new PalabrasEntity("a", true, false, false, false, false, false, true, false, false,"https://dle.rae.es/a#002rZ9U");
        dao.insert(palabra);
        palabra = new PalabrasEntity("cabe", true,false, false, false, false, false, true, false, false,"https://dle.rae.es/cabe#6PLJnT8");
        dao.insert(palabra);
        palabra = new PalabrasEntity("contra", true, false, false, false, false, false, true, false, false,"https://dle.rae.es/contra#AWCzXfA");
        dao.insert(palabra);
        palabra = new PalabrasEntity("tras", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/tras#aOnVELH");
        dao.insert(palabra);
        palabra = new PalabrasEntity("vía", true, false, false, false, false, false, true, false, false,"https://dle.rae.es/v%C3%ADa?m=form");
        dao.insert(palabra);
    }
    private static void PreposicionSustantivoAdverbio(PalabrasDao dao){
         PalabrasEntity palabra = new PalabrasEntity("ante", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/ante#2lpFbYn");
        dao.insert(palabra);
    }

    private static void Verbo(PalabrasDao dao) {
        PalabrasEntity
        palabra = new PalabrasEntity("hablar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/hablar");
        dao.insert(palabra);
        palabra = new PalabrasEntity("comer", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/comer#9vPNMxG");
        dao.insert(palabra);
        palabra = new PalabrasEntity("tener", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/tener?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("poner", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/poner?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("tomar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/tomar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("dar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/dar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("ir", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/ir?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("hacer", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/hacer?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("necesitar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/necesitar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("abrir", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/abrir?formList=form&w=#");
        dao.insert(palabra);
        palabra = new PalabrasEntity("agarrar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/agarrar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("buscar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/buscar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("caer", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/caer?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("cerrar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/cerrar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("comenzar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/comenzar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("comprar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/comprar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("conducir", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/conducir?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("conocer", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/conocer?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("pedir", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/pedir?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("dormir", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/dormir?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("encontrar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/encontrar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("entender", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/entender#FgqY9Xy");
        dao.insert(palabra);
        palabra = new PalabrasEntity("leer", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/leer?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("escribir", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/escribir?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("escuchar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/escuchar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("pensar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/pensar#STY14i0");
        dao.insert(palabra);
        palabra = new PalabrasEntity("mirar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/mirar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("llegar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/llegar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("perder", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/perder?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("oír", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/o%C3%ADr");
        dao.insert(palabra);
        palabra = new PalabrasEntity("jugar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/jugar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("salir", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/salir?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("saltar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/saltar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("trabajar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/trabajar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("traer", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/traer#aE2JYRT");
        dao.insert(palabra);
        palabra = new PalabrasEntity("tocar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/tocar#Zw9vb5a");
        dao.insert(palabra);
        palabra = new PalabrasEntity("sentar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/sentar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("pagar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/pagar?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("estar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/estar?m=form");
        dao.insert(palabra);
    }
    private static void VerboSustantivo(PalabrasDao dao){
        PalabrasEntity  palabra = new PalabrasEntity("vivir", true, false, false, false, true, false, false, false, false,"https://dle.rae.es/vivir#bycwmXJ");
        dao.insert(palabra);
        palabra = new PalabrasEntity("ser", true, false, false, false, true, false, false, false, false,"https://dle.rae.es/ser#Xe5NPsQ");
        dao.insert(palabra);
        palabra = new PalabrasEntity("beber", true, false, false, false, true, false, false, false, false,"https://dle.rae.es/beber#5GjKfxr");
        dao.insert(palabra);
        palabra = new PalabrasEntity("decir", true, false, false, false, true, false, false, false, false,"https://dle.rae.es/decir#BxMOE45");
        dao.insert(palabra);
        palabra = new PalabrasEntity("poder", true, false, false, false, true, false, false, false, false,"https://dle.rae.es/poder#TU2nLT0");
        dao.insert(palabra);
        palabra = new PalabrasEntity("querer", true, false, false, false, true, false, false, false, false,"https://dle.rae.es/querer#Unz1d3h");
        dao.insert(palabra);
        palabra = new PalabrasEntity("sentir", true, false, false, false, true, false, false, false, false,"https://dle.rae.es/sentir?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("ver", true, false, false, false, true, false, false, false, false,"https://dle.rae.es/ver#baR8qnC");
        dao.insert(palabra);
        palabra = new PalabrasEntity("saber", true, false, false, false, true, false, false, false, false,"https://dle.rae.es/saber#WswcTXr");
        dao.insert(palabra);
        palabra = new PalabrasEntity("andar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/andar#2ZIziL7");
        dao.insert(palabra);
        palabra = new PalabrasEntity("caminar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/caminar#6xqfYHy");
        dao.insert(palabra);
    }
    private static void Articulos(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("el", false, true, false, false, false, false, false, false, false,"https://dle.rae.es/el#ESraxkH");
        dao.insert(palabra);
        palabra = new PalabrasEntity("un", false, true, false, false, false, false, false, false, false,"https://dle.rae.es/un#b3uuZZA");
        dao.insert(palabra);
        palabra = new PalabrasEntity("al", false, true, false, false, false, false, false, false, false,"https://dle.rae.es/al?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("del", false, true, false, false, false, false, false, false, false,"https://dle.rae.es/del?m=form");
        dao.insert(palabra);
    }
    private static void ArticuloSustantivo (PalabrasDao dao){
        PalabrasEntity palabra = new PalabrasEntity("la", true, true, false, false, false, false, false, false, false,"https://dle.rae.es/el#ESraxkH");
        dao.insert(palabra);
    }
    private static void ArticuloSustantivoPronombre(PalabrasDao dao){
         PalabrasEntity palabra = new PalabrasEntity("uno", true, true, false, true, false, false, false, false, false,"https://dle.rae.es/uno");
        dao.insert(palabra);
        palabra = new PalabrasEntity("una", true, true, false, true, false, false, false, false, false,"https://dle.rae.es/uno");
        dao.insert(palabra);
    }

   private static void Pronombres(PalabrasDao dao) {
        PalabrasEntity
        palabra = new PalabrasEntity("alguien", false, false, true, true, false, false, false, false, false,"https://dle.rae.es/alguien?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("me", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/me?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("mí", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/m%C3%AD?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("nosotros", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/nosotras?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("nosotras", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/nosotras?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("nos", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/nos?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("tú", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/t%C3%BA?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("ti", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/ti?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("usted", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/usted?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("vos", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/vos?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("él", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/%C3%A9l");
        dao.insert(palabra);
        palabra = new PalabrasEntity("ella", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/%C3%A9l");
        dao.insert(palabra);
        palabra = new PalabrasEntity("se", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/le?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("se", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/se#XNVjCmd");
        dao.insert(palabra);
        palabra = new PalabrasEntity("nadie", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/nadie?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("quienquiera", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/quienquiera");
        dao.insert(palabra);
        palabra = new PalabrasEntity("quienesquiera", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/quienquiera");
        dao.insert(palabra);
    }
    private static void PronommbresAdjetivo(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("alguno", false, false, true, true, false, false, false, false, false,"https://dle.rae.es/alguno?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("alguna", false, false, true, true, false, false, false, false, false,"https://dle.rae.es/alguno?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("otro", false, false, true, true, false, false, false, false, false,"https://dle.rae.es/otro?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("otra", false, false, true, true, false, false, false, false, false,"https://dle.rae.es/otro?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("toda", true, false, true, true, false, true, false, false, false,"https://dle.rae.es/todo?m=form");
        dao.insert(palabra);
    }
    private static void PronombreAdjetivoSustantivo(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("cualquiera", true, false, true, true, false, false, false, false, false,"https://dle.rae.es/cualquiera?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("algo", true, false, true, true, false, false, false, false, false,"https://dle.rae.es/algo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("pronto", true, false, true, true, false, false, false, false, false,"https://dle.rae.es/pronto?m=form");
        dao.insert(palabra);

    }
    private static void PronombreAdjetivoAdverbio(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("mucho", false, false, true, true, false, true, false, false, false,"https://dle.rae.es/mucho?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("mucha", false, false, true, true, false, true, false, false, false,"https://dle.rae.es/mucho?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("poco", false, false, true, true, false, true, false, false, false,"https://dle.rae.es/poco?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("poca", false, false, true, true, false, true, false, false, false,"https://dle.rae.es/poco?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("bastante", false, false, true, true, false, true, false, false, false,"https://dle.rae.es/bastante?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("demasiado", false, false, true, true, false, true, false, false, false,"https://dle.rae.es/bastante?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("demasiada", false, false, true, true, false, true, false, false, false,"https://dle.rae.es/bastante?m=form");
        dao.insert(palabra);
    }
    private static void PronombreSustantivo(PalabrasDao dao){
        PalabrasEntity   palabra = new PalabrasEntity("lo", true, false, false, true, false, false, false, false, false,"https://dle.rae.es/lo#NWnLPAn");
        dao.insert(palabra);
        palabra = new PalabrasEntity("yo", true, false, false, true, false, false, false, false, false,"https://dle.rae.es/yo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("ello", true, false, false, true, false, false, false, false, false,"https://dle.rae.es/%C3%A9l");
        dao.insert(palabra);
        palabra = new PalabrasEntity("te", true, false, false, true, false, false, false, false, false,"https://dle.rae.es/te#ZHbpNE9");
        dao.insert(palabra);
    }
    private static void PronombreAdverbioSustantivo(PalabrasDao dao){
        PalabrasEntity     palabra = new PalabrasEntity("sí", true, false, false, true, false, true, false, false, false,"https://dle.rae.es/s%C3%AD#XmOP8Ia");
        dao.insert(palabra);
    }

    private static void PalabrasConCuatroOMasFunciones(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("todo", true, false, true, true, false, true, false, false, false,"https://dle.rae.es/todo?m=form");
        dao.insert(palabra);

        palabra = new PalabrasEntity("más", true, false, true, true, false, true, false, true, false,"https://dle.rae.es/m%C3%A1s?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("bajo", true, false, true, false, false, true, true, false, false,"https://dle.rae.es/bajo#4oyrC6G");
        dao.insert(palabra);
        palabra = new PalabrasEntity("así", false, false, true, false, false, true, false, true, true,"https://dle.rae.es/as%C3%AD?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("como", true, false, false, false, false, true, true, true, false,"https://dle.rae.es/como#9xWAAKF");
        dao.insert(palabra);
        palabra = new PalabrasEntity("cara", true, false, true, false, false, true, true, false, false, "https://dle.rae.es/cara#7NOG7x2");
        dao.insert(palabra);
        palabra = new PalabrasEntity("so", false, false, true, false, false, true, true, false, true,"https://dle.rae.es/so#Y38uhep");
        dao.insert(palabra);

    }

    private static void Adverbios(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("allí", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/all%C3%AD?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("jamás", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/jam%C3%A1s?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("siempre", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/siempre?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("nunca", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/nunca?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("tampoco", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/tampoco?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("quizá", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/quiz%C3%A1?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("allá", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/all%C3%A1?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("enfrente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/enfrente?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("dentro", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/dentro?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("abajo", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/abajo?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("enseguida", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/enseguida?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("todavía", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/todav%C3%ADa?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("anoche", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/anoche?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("recién", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/reci%C3%A9n?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("actualmente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/actualmente?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("antiguamente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/antiguamente?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("últimamente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/%C3%BAltimamente?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("recientemente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/recientemente?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("anteanoche", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/anteanoche?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("deprisa", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/deprisa?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("gratis", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/gratis?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("aposta", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/aposta?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("adrede", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/adrede?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("tranquilamente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/tranquilamente?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("ligeramente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/ligeramente?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("indistintamente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/indistintamente?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("cuidadosamente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/cuidadosamente?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("velozmente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/velozmente?m=form");
        dao.insert(palabra);
    }
    private static void AdverbiosPronombre(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("acá", false, false, false, true, false, true, false, false, false,"https://dle.rae.es/ac%C3%A1?m=form");
        dao.insert(palabra);
    }
    private static void AdverbioSustantivo(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("cerca", true, false, false, false, false, true, false, false, false,"https://dle.rae.es/cerca#8J0QC7a");
        dao.insert(palabra);
        palabra = new PalabrasEntity("lejos", true, false, false, false, false, true, false, false, false,"https://dle.rae.es/lejos?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("alrededor", true, false, false, false, false, true, false, false, false,"https://dle.rae.es/alrededor?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("hoy", true, false, false, false, false, true, false, false, false,"https://dle.rae.es/hoy?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("mañana", true, false, false, false, false, true, false, false, false,"https://dle.rae.es/ma%C3%B1ana?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("ayer", true, false, false, false, false, true, false, false, false,"https://dle.rae.es/ayer?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("tarde", true, false, false, false, false, true, false, false, false,"https://dle.rae.es/tarde?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("no", true, false, false, false, false, true, false, false, false,"https://dle.rae.es/no?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("acaso", true, false, false, false, false, true, false, false, false,"https://dle.rae.es/acaso?m=form");
        dao.insert(palabra);
    }
    private static void AdverbioSustativoInterjeccion(PalabrasDao dao){
        PalabrasEntity palabra = new PalabrasEntity("afuera", true, false, false, false, false, true, false, false, true,"https://dle.rae.es/afuera?m=form");
        dao.insert(palabra);
    }
    private static void AdverbioInterjeccion(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("aquí", false, false, false, false, false, true, false, false, true,"https://dle.rae.es/aqu%C3%AD?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("arriba", false, false, false, false, false, true, false, false, true,"https://dle.rae.es/arriba?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("fuera", false, false, false, false, false, true, false, false, true,"https://dle.rae.es/fuera?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("ya", false, false, false, false, false, true, false, false, true,"https://dle.rae.es/ya?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("despacio", false, false, false, false, false, true, false, false, true,"https://dle.rae.es/despacio?m=form");
        dao.insert(palabra);
    }
    private static void AdverbioPreposicion(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("según", false, false, false, false, false, true, true, false, false,"https://dle.rae.es/seg%C3%BAn?m=form");
        dao.insert(palabra);
    }
    private static void AdverbioPronombreSustantivo(PalabrasDao dao) {
        PalabrasEntity palabra = new PalabrasEntity("nada", true, false, false, true, false, true, false, false, false,"https://dle.rae.es/nada?m=form");
        dao.insert(palabra);
    }

    private static void Interjeccion(PalabrasDao dao) {
        PalabrasEntity
        palabra = new PalabrasEntity("EPA", false, false, false, false, false, false, false, false, true,"https://dle.rae.es/epa?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("olé", false, false, false, false, false, false, false, false, true,"https://dle.rae.es/ol%C3%A9?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("ah", false, false, false, false, false, false, false, false, true,"https://dle.rae.es/ah?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("oh", false, false, false, false, false, false, false, false, true,"https://dle.rae.es/oh?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("eh", false, false, false, false, false, false, false, false, true,"https://dle.rae.es/eh?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("uy", false, false, false, false, false, false, false, false, true,"https://dle.rae.es/uy?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("hola", false, false, false, false, false, false, false, false, true,"https://dle.rae.es/hola?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("ojalá", false, false, false, false, false, false, false, false, true,"https://dle.rae.es/ojal%C3%A1?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("uf", false, false, false, false, false, false, false, false, true,"https://dle.rae.es/uf?m=form");
        dao.insert(palabra);
        palabra = new PalabrasEntity("bah", false, false, false, false, false, false, false, false, true,"https://dle.rae.es/bah?m=form");
        dao.insert(palabra);
    }
    private static void InterjeccionSustantivo(PalabrasDao dao){
        PalabrasEntity palabra = new PalabrasEntity("ay", true, false, false, false, false, false, false, false, true,"https://dle.rae.es/ay?m=form");
        dao.insert(palabra);
    }
    private static void InterjeccionSustantivoAdverbio(PalabrasDao dao){
        PalabrasEntity    palabra = new PalabrasEntity("guay", true, false, false, false, false, true, false, false, true,"https://dle.rae.es/guay#JmihBh3");
        dao.insert(palabra);

    }

    private static void palabrasFrases(PalabrasDao dao){
        dao.insert(new PalabrasEntity("es", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/es"));
        dao.insert(new PalabrasEntity("niño", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/ni%C3%B1o?m=form"));
        dao.insert(new PalabrasEntity("muy", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/muy?m=form"));
        dao.insert(new PalabrasEntity("cuánto", false, false, true, true, false, true, false, false, false,"https://dle.rae.es/cu%C3%A1nto"));
        dao.insert(new PalabrasEntity("cuesta", true, false, false, false, true, false, false, false, false,"https://dle.rae.es/cuesta?m=form"));
        dao.insert(new PalabrasEntity("esa", false, false, true, true, false, false, false, false, false,"https://dle.rae.es/ese"));
        dao.insert(new PalabrasEntity("raquetas", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/raqueta?m=form"));
        dao.insert(new PalabrasEntity("compró", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/comprar?m=form"));
        dao.insert(new PalabrasEntity("a", false, false, false, false, false, false, true, false, false,"https://dle.rae.es/a?m=form"));
        dao.insert(new PalabrasEntity("su", false, false, true, false, false, false, false, false, false,"https://dle.rae.es/su?m=form"));
        dao.insert(new PalabrasEntity("madre", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/madre?m=form"));
        dao.insert(new PalabrasEntity("mujer", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/mujer?m=form"));
        dao.insert(new PalabrasEntity("comió", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/comer?m=form"));
        dao.insert(new PalabrasEntity("plátano", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/pl%C3%A1tano?m=form"));
        dao.insert(new PalabrasEntity("desayunar", true, false, false, false, true, false, false, false, false,"https://dle.rae.es/desayuno?m=form"));
        dao.insert(new PalabrasEntity("peina", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/peinar#SLj3fSm"));
        dao.insert(new PalabrasEntity("rizado", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/rizado?m=form"));
        dao.insert(new PalabrasEntity("cabello", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/cabello?m=form"));
        dao.insert(new PalabrasEntity("bombero", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/bombero?m=form"));
        dao.insert(new PalabrasEntity("apagó", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/apagar?m=form"));
        dao.insert(new PalabrasEntity("fuego", true, false, false, false, false, false, false, false, true,"https://dle.rae.es/fuego?m=form"));
        dao.insert(new PalabrasEntity("rapidamente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/r%C3%A1pido"));
        dao.insert(new PalabrasEntity("huyó", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/huir"));
        dao.insert(new PalabrasEntity("las", false, true, false, false, false, false, false, false, false,"https://dle.rae.es/el#ESraxkH"));
        dao.insert(new PalabrasEntity("alcantarillas", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/alcantarilla#1cDewL8"));
        dao.insert(new PalabrasEntity("ciudad", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/ciudad?m=form"));
        dao.insert(new PalabrasEntity("fui", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/ir#M5ucdgy"));
        dao.insert(new PalabrasEntity("despacho", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/despacho?m=form"));
        dao.insert(new PalabrasEntity("profesor", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/profesor?m=form"));
        dao.insert(new PalabrasEntity("juego", true, false, false, false, true, false, false, false, false,"https://dle.rae.es/juego?m=form"));
        dao.insert(new PalabrasEntity("consola", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/consola?m=form"));
        dao.insert(new PalabrasEntity("todos", true, false, true, true, false, true, false, false, false,"https://dle.rae.es/todo?m=form"));
        dao.insert(new PalabrasEntity("los", false, true, false, false, false, false, false, false, false,"https://dle.rae.es/el#ESraxkH"));
        dao.insert(new PalabrasEntity("días", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/d%C3%ADa?m=form"));
        dao.insert(new PalabrasEntity("comimos", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/comer?m=form"));
        dao.insert(new PalabrasEntity("aquel", false, false, true, true, false, false, false, false, false,"https://dle.rae.es/aquel?m=form"));
        dao.insert(new PalabrasEntity("restaurante", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/restaurante?m=form"));
        dao.insert(new PalabrasEntity("señor", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/se%C3%B1or?m=form"));
        dao.insert(new PalabrasEntity("paseaba", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/pasear?m=form"));
        dao.insert(new PalabrasEntity("tranquilamente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/tranquilo"));
        dao.insert(new PalabrasEntity("tejió", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/tejer"));
        dao.insert(new PalabrasEntity("chaleco", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/chaleco?m=form"));
        dao.insert(new PalabrasEntity("noche", true, false, false, false, false, true, false, false, false,"https://dle.rae.es/noche?m=form"));
        dao.insert(new PalabrasEntity("abuelo", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/abuelo?m=form"));
        dao.insert(new PalabrasEntity("tiró", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/tirar?m=form"));
        dao.insert(new PalabrasEntity("dados", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/dado#BpBhRJZ"));
        dao.insert(new PalabrasEntity("parchís", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/parch%C3%ADs?m=form"));
        dao.insert(new PalabrasEntity("coche", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/coche?m=form"));
        dao.insert(new PalabrasEntity("estrelló", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/estrellar?m=form"));
        dao.insert(new PalabrasEntity("ese", false, false, true, false, false, false, true, false, false,"https://dle.rae.es/ese?m=form"));
        dao.insert(new PalabrasEntity("árbol", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/%C3%A1rbol?m=form"));
        dao.insert(new PalabrasEntity("hemos escrito", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/escribir?m=form"));
        dao.insert(new PalabrasEntity("siguiente", false, false, true, false, false, false, false, false, false,"https://dle.rae.es/siguiente?m=form"));
        dao.insert(new PalabrasEntity("número", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/n%C3%BAmero?m=form"));
        dao.insert(new PalabrasEntity("carrera", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/carrera?m=form"));
        dao.insert(new PalabrasEntity("ganó", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/ganar?m=form"));
        dao.insert(new PalabrasEntity("he librado", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/librar?m=form"));
        dao.insert(new PalabrasEntity("tres", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/tres?m=form"));
        dao.insert(new PalabrasEntity("esta", false, false, true, true, false, false, false, false, false,"https://dle.rae.es/este"));
        dao.insert(new PalabrasEntity("semana", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/semana?m=form"));
        dao.insert(new PalabrasEntity("directora", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/director?m=form"));
        dao.insert(new PalabrasEntity("reprendió", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/reprender?m=form"));
        dao.insert(new PalabrasEntity("alumnos", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/alumno?m=form"));
        dao.insert(new PalabrasEntity("condujo", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/conducir?m=form"));
        dao.insert(new PalabrasEntity("autopista", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/autopista?m=form"));
        dao.insert(new PalabrasEntity("sentido", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/sentido?m=form"));
        dao.insert(new PalabrasEntity("contrario", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/contrario?m=form"));
        dao.insert(new PalabrasEntity("encontraron", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/encontrar"));
        dao.insert(new PalabrasEntity("cartera", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/cartera?m=form"));
        dao.insert(new PalabrasEntity("basura", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/basura?m=form"));
        dao.insert(new PalabrasEntity("ellos", false, false, false, true, false, false, false, false, false,"https://dle.rae.es/%C3%A9l#ESsilCu"));
        dao.insert(new PalabrasEntity("duchan", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/duchar?m=form"));
        dao.insert(new PalabrasEntity("todas", true, false, true, true, false, true, false, false, false,"https://dle.rae.es/todo?m=form"));
        dao.insert(new PalabrasEntity("mañanas", true, false, false, false, false, true, false, false, false,"https://dle.rae.es/ma%C3%B1ana?m=form"));
        dao.insert(new PalabrasEntity("trabajadores", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/trabajador?m=form"));
        dao.insert(new PalabrasEntity("saludaron", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/saludar?m=form"));
        dao.insert(new PalabrasEntity("entrada", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/entrada?m=form"));
        dao.insert(new PalabrasEntity("luchadores", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/luchador?m=form"));
        dao.insert(new PalabrasEntity("golpearon", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/golpear?m=form"));
        dao.insert(new PalabrasEntity("piedad", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/piedad?m=form"));
        dao.insert(new PalabrasEntity("amigas", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/amigo#2LsqDPa"));
        dao.insert(new PalabrasEntity("contaron", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/contar?m=form"));
        dao.insert(new PalabrasEntity("sus", false, false, true, false, false, false, false, false, false,"https://dle.rae.es/su#YUFeT07"));
        dao.insert(new PalabrasEntity("secretos", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/secreto#XPKxnKN"));
        dao.insert(new PalabrasEntity("muchachos", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/muchacho?m=form"));
        dao.insert(new PalabrasEntity("vieron", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/ver?m=form"));
        dao.insert(new PalabrasEntity("parada", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/parada?m=form"));
        dao.insert(new PalabrasEntity("amantes", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/amante#2Dm6VRP"));
        dao.insert(new PalabrasEntity("extrañan", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/extra%C3%B1ar?m=form"));
        dao.insert(new PalabrasEntity("dieron", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/dar?m=form"));
        dao.insert(new PalabrasEntity("abrazo", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/abrazo?m=form"));
        dao.insert(new PalabrasEntity("besaron", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/besar?m=form"));
        dao.insert(new PalabrasEntity("este", false, false, true, true, false, false, false, false, false,"https://dle.rae.es/este"));
        dao.insert(new PalabrasEntity("entienden", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/entender?m=form"));
        dao.insert(new PalabrasEntity("perfectamente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/perfectamente?m=form"));
        dao.insert(new PalabrasEntity("conocen", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/conocer?m=form"));
        dao.insert(new PalabrasEntity("bien", true, false, true, false, false, true, false, false, false,"https://dle.rae.es/bien?m=form"));
        dao.insert(new PalabrasEntity("niños", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/ni%C3%B1o?m=form"));
        dao.insert(new PalabrasEntity("acompañan", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/acompa%C3%B1ar?m=form"));
        dao.insert(new PalabrasEntity("colegio", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/colegio?m=form"));
        dao.insert(new PalabrasEntity("mandaron", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/mandar?m=form"));
        dao.insert(new PalabrasEntity("cartas", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/carta?m=form"));
        dao.insert(new PalabrasEntity("accidente", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/accidente?m=form"));
        dao.insert(new PalabrasEntity("necesita", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/necesitar"));
        dao.insert(new PalabrasEntity("conductor", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/conductor?m=form"));
        dao.insert(new PalabrasEntity("camión", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/cami%C3%B3n?m=form"));
        dao.insert(new PalabrasEntity("vendedor", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/vendedor?m=form"));
        dao.insert(new PalabrasEntity("urgentemente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/urgentemente?m=form"));
        dao.insert(new PalabrasEntity("ofrece", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/ofrecer?m=form"));
        dao.insert(new PalabrasEntity("limonada", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/limonado#NKfhflu"));
        dao.insert(new PalabrasEntity("compra", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/compra?m=form"));
        dao.insert(new PalabrasEntity("alquilan", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/alquilar?m=form"));
        dao.insert(new PalabrasEntity("casas", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/casa#7lsKMtR"));
        dao.insert(new PalabrasEntity("barrio", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/barrio?m=form"));
        dao.insert(new PalabrasEntity("publicó", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/publicar?m=form"));
        dao.insert(new PalabrasEntity("nuestro", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/nuestro?m=form"));
        dao.insert(new PalabrasEntity("anuncio", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/anuncio?m=form"));
        dao.insert(new PalabrasEntity("periódico", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/peri%C3%B3dico?m=form"));
        dao.insert(new PalabrasEntity("fuerza", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/fuerza?m=form"));
        dao.insert(new PalabrasEntity("eso", false, false, true, false, false, false, true, false, false,"https://dle.rae.es/ese?m=form"));
        dao.insert(new PalabrasEntity("regalan", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/regalar#Vgmi2Af"));
        dao.insert(new PalabrasEntity("chocolatinas", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/chocolatina?m=form"));
        dao.insert(new PalabrasEntity("aquella", false, false, true, true, false, false, false, false, false,"https://dle.rae.es/aquel?m=form"));
        dao.insert(new PalabrasEntity("tienda", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/tienda?m=form"));
        dao.insert(new PalabrasEntity("realizan", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/realizar?m=form"));
        dao.insert(new PalabrasEntity("tartas", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/tarta?m=form"));
        dao.insert(new PalabrasEntity("encargo", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/encargo?m=form"));
        dao.insert(new PalabrasEntity("escuchó", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/escuchar?m=form"));
        dao.insert(new PalabrasEntity("claramente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/claramente?m=form"));
        dao.insert(new PalabrasEntity("confesión", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/confesi%C3%B3n?m=form"));
        dao.insert(new PalabrasEntity("valora", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/valorar?m=form"));
        dao.insert(new PalabrasEntity("esfuerzo", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/esfuerzo?m=form"));
        dao.insert(new PalabrasEntity("reclamó", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/reclamar?m=form"));
        dao.insert(new PalabrasEntity("justicia", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/justicia?m=form"));
        dao.insert(new PalabrasEntity("maquilló", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/maquillar"));
        dao.insert(new PalabrasEntity("duché", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/duchar?m=form"));
        dao.insert(new PalabrasEntity("entrenar", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/entrenar?m=form"));
        dao.insert(new PalabrasEntity("miro", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/mirar?m=form"));
        dao.insert(new PalabrasEntity("espejo", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/espejo?m=form"));
        dao.insert(new PalabrasEntity("corté", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/cortar?m=form"));
        dao.insert(new PalabrasEntity("dedo", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/dedo?m=form"));
        dao.insert(new PalabrasEntity("cuchillo", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/dedo?m=form"));
        dao.insert(new PalabrasEntity("lavo", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/lavar?m=form"));
        dao.insert(new PalabrasEntity("toalla", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/toalla?m=form"));
        dao.insert(new PalabrasEntity("visto", true, false, true, false, true, false, false, false, false,"https://dle.rae.es/visto?m=form"));
        dao.insert(new PalabrasEntity("cansó", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/cansar?m=form"));
        dao.insert(new PalabrasEntity("excusas", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/excusa#HDaaQhK"));
        dao.insert(new PalabrasEntity("disfracé", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/disfrazar?m=form"));
        dao.insert(new PalabrasEntity("mosca", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/mosca?m=form"));
        dao.insert(new PalabrasEntity("carnavales", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/carnaval?m=form"));
        dao.insert(new PalabrasEntity("fuimos", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/ir#M5ucdgy"));
        dao.insert(new PalabrasEntity("feria", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/feria?m=form"));
        dao.insert(new PalabrasEntity("manualidades", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/manualidad?m=form"));
        dao.insert(new PalabrasEntity("cayó", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/caer?m=form"));
        dao.insert(new PalabrasEntity("diente", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/diente?m=form"));
        dao.insert(new PalabrasEntity("golpe", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/golpe?m=form"));
        dao.insert(new PalabrasEntity("película", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/pel%C3%ADcula?m=form"));
        dao.insert(new PalabrasEntity("aburrió", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/aburrir?m=form"));
        dao.insert(new PalabrasEntity("enormemente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/enormemente?m=form"));
        dao.insert(new PalabrasEntity("chico", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/chico?m=form"));
        dao.insert(new PalabrasEntity("ocupó", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/ocupar?m=form"));
        dao.insert(new PalabrasEntity("problemas", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/problema?m=form"));
        dao.insert(new PalabrasEntity("mi", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/mi?m=form"));
        dao.insert(new PalabrasEntity("padre", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/padre?m=form"));
        dao.insert(new PalabrasEntity("duerme", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/dormir?m=form"));
        dao.insert(new PalabrasEntity("divorció", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/divorciar?m=form"));
        dao.insert(new PalabrasEntity("infidelidad", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/infidelidad?m=form"));
        dao.insert(new PalabrasEntity("defendió", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/defender?m=form"));
        dao.insert(new PalabrasEntity("acusaciones", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/acusaci%C3%B3n?m=form"));
        dao.insert(new PalabrasEntity("encendió", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/encender?m=form"));
        dao.insert(new PalabrasEntity("estoy", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/estar"));
        dao.insert(new PalabrasEntity("parecían", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/parecer?m=form"));
        dao.insert(new PalabrasEntity("está", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/estar"));
        dao.insert(new PalabrasEntity("parecía", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/parecer?m=form"));
        dao.insert(new PalabrasEntity("es", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/ser#Xe5Brrm"));
        dao.insert(new PalabrasEntity("parece", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/parecer?m=form"));
        dao.insert(new PalabrasEntity("estamos", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/ser#Xe5Brrm"));
        dao.insert(new PalabrasEntity("fuimos", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/ser#Xe5Brrm"));
        dao.insert(new PalabrasEntity("pareció", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/parecer?m=form"));
        dao.insert(new PalabrasEntity("harto", false, false, true, false, false, true, false, false, false,"https://dle.rae.es/harto"));
        dao.insert(new PalabrasEntity("tu", false, false, true, false, false, false, false, false, false,"https://dle.rae.es/tu?m=form"));
        dao.insert(new PalabrasEntity("actitud", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/actitud?m=form"));
        dao.insert(new PalabrasEntity("esos", false, false, true, true, false, false, false, false, false,"https://dle.rae.es/ese?m=form"));
        dao.insert(new PalabrasEntity("chicos", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/chico?m=form"));
        dao.insert(new PalabrasEntity("desanimados", false, false, true, false, false, false, false, false, false,"https://dle.rae.es/desanimado#CR653TF"));
        dao.insert(new PalabrasEntity("partido", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/partido?m=form"));
        dao.insert(new PalabrasEntity("feliz", false, false, true, false, false, false, false, false, false,"https://dle.rae.es/feliz?m=form"));
        dao.insert(new PalabrasEntity("mis", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/mi?m=form"));
        dao.insert(new PalabrasEntity("notas", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/nota#QdrVoo1"));
        dao.insert(new PalabrasEntity("hombre", true, false, false, false, false, false, false, false, true,"https://dle.rae.es/hombre?m=form"));
        dao.insert(new PalabrasEntity("sospechoso", false, false, true, false, false, false, false, false, false,"https://dle.rae.es/sospechoso?m=form"));
        dao.insert(new PalabrasEntity("color", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/color?m=form"));
        dao.insert(new PalabrasEntity("prestigioso", false, false, true, false, false, false, false, false, false,"https://dle.rae.es/prestigioso?m=form"));
        dao.insert(new PalabrasEntity("abogado", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/abogado?m=form"));
        dao.insert(new PalabrasEntity("hermano", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/hermano?m=form"));
        dao.insert( new PalabrasEntity("alegres", false, false, true, false, false, false, false, false, false, "https://dle.rae.es/alegre?m=form"));
        dao.insert(new PalabrasEntity("resultado", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/resultado?m=form"));
        dao.insert(new PalabrasEntity("árbitro", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/%C3%A1rbitro"));
        dao.insert(new PalabrasEntity("nerviosos", false, false, true, false, false, false, false, false, false,"https://dle.rae.es/nervioso?m=form"));
        dao.insert(new PalabrasEntity("ocurrido", false, false, true, false, false, false, false, false, false,"https://dle.rae.es/ocurrido?m=form"));
        dao.insert(new PalabrasEntity("periodista", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/periodista?m=form"));
        dao.insert(new PalabrasEntity("asustado", false, false, true, false, false, false, false, false, false,"https://dle.rae.es/asustado?m=form"));
        dao.insert(new PalabrasEntity("secuestro", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/secuestro?m=form"));
        dao.insert(new PalabrasEntity("ordenado", false, false, true, false, false, false, false, false, false,"https://dle.rae.es/ordenado?m=form"));
        dao.insert(new PalabrasEntity("trabajo", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/trabajo?m=form"));
        dao.insert(new PalabrasEntity("disfrazamos", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/disfrazar?m=form"));
        dao.insert(new PalabrasEntity("superhéroes", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/superh%C3%A9roe?m=form"));
        dao.insert(new PalabrasEntity("divorciaron", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/divorciar?m=form"));
        dao.insert(new PalabrasEntity("económicos", false, false, true, false, false, false, false, false, false,"https://dle.rae.es/econ%C3%B3mico?m=form"));
        dao.insert( new PalabrasEntity("miramos", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/mirar?m=form"));
        dao.insert(new PalabrasEntity("ternura", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/ternura?m=form"));
        dao.insert(new PalabrasEntity("amigos", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/amigo#2LsqDPa"));
        dao.insert(new PalabrasEntity("compraron", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/comprar?m=form"));
        dao.insert(new PalabrasEntity("deportistas", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/deportista?m=form"));
        dao.insert(new PalabrasEntity("secan", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/secar?m=form"));
        dao.insert(new PalabrasEntity("piscina", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/piscina?m=form"));
        dao.insert(new PalabrasEntity("duchamos", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/duchar?m=form"));
        dao.insert(new PalabrasEntity("noches", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/noche?m=form"));
        dao.insert(new PalabrasEntity("lavaron", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/lavar?m=form"));
        dao.insert(new PalabrasEntity("manos", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/mano#OF9CzGo"));
        dao.insert(new PalabrasEntity("peluqueros", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/peluquero?m=form"));
        dao.insert(new PalabrasEntity("cortaron", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/cortar?m=form"));
        dao.insert(new PalabrasEntity("mutuamente", false, false, false, false, false, true, false, false, false,"https://dle.rae.es/mutuamente?m=form"));
        dao.insert( new PalabrasEntity("miran", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/mirar?m=form"));
        dao.insert(new PalabrasEntity("distancia", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/distancia?m=form"));
        dao.insert(new PalabrasEntity("borrachos", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/borracho?m=form"));
        dao.insert( new PalabrasEntity("insultaron", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/insultar?m=form"));
        dao.insert(new PalabrasEntity("aparcamiento", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/aparcamiento?m=form"));
        dao.insert(new PalabrasEntity("abuelos", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/abuelo?m=form"));
        dao.insert( new PalabrasEntity("abanican", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/abanicar?m=form"));
        dao.insert(new PalabrasEntity("calor", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/calor?m=form"));
        dao.insert(new PalabrasEntity("maquillan", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/maquillar"));
        dao.insert(new PalabrasEntity("pinturas", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/pintura?m=form"));
        dao.insert(new PalabrasEntity("alumno", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/alumno?m=form"));
        dao.insert(new PalabrasEntity("fue", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/ir#M5ucdgy"));
        dao.insert(new PalabrasEntity("suspendido", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/suspender?m=form"));
        dao.insert(new PalabrasEntity("pelota", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/pelota?m=form"));
        dao.insert(new PalabrasEntity("pinchada", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/pinchar?m=form"));
        dao.insert(new PalabrasEntity("ha sido", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/ser?m=form"));
        dao.insert(new PalabrasEntity("construida", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/construir?m=form"));
        dao.insert(new PalabrasEntity("aquellos", false, false, true, true, false, false, false, false, false,"https://dle.rae.es/aquel?m=form"));
        dao.insert(new PalabrasEntity("albañiles", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/alba%C3%B1il?m=form"));
        dao.insert(new PalabrasEntity("árbitro", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/%C3%A1rbitro?m=form"));
        dao.insert(new PalabrasEntity("rosas", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/rosa#WiDY32j"));
        dao.insert(new PalabrasEntity("están", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/estar?m=form"));
        dao.insert(new PalabrasEntity("siendo", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/ser?m=form"));
        dao.insert(new PalabrasEntity("cortadas", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/cortar#B1wW3tP"));
        dao.insert(new PalabrasEntity("jardinera", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/jardinero"));
        dao.insert(new PalabrasEntity("bailarín", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/bailar%C3%ADn?m=form"));
        dao.insert(new PalabrasEntity("invitado", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/invitar#M4Ywbmr"));
        dao.insert(new PalabrasEntity("fiesta", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/fiesta?m=form"));
        dao.insert(new PalabrasEntity("novia", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/novio?m=form"));
        dao.insert(new PalabrasEntity("cantantes", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/cantante?m=form"));
        dao.insert(new PalabrasEntity("fueron", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/ser#Xe5Brrm"));
        dao.insert(new PalabrasEntity("aplaudidos", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/aplaudir?m=form"));
        dao.insert(new PalabrasEntity("público", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/p%C3%BAblico?m=form"));
        dao.insert(new PalabrasEntity("pollo", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/pollo?m=form"));
        dao.insert(new PalabrasEntity("cocinado", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/cocinar#9aukAUD"));
        dao.insert(new PalabrasEntity("chef", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/chef?m=form"));
        dao.insert(new PalabrasEntity("chicas", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/chico?m=form"));
        dao.insert(new PalabrasEntity("han sido", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/ser?m=form"));
        dao.insert(new PalabrasEntity("recibidas", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/recibir?m=form"));
        dao.insert(new PalabrasEntity("decana", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/decano?m=form"));
        dao.insert(new PalabrasEntity("llevado", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/ir#M5ucdgy"));
        dao.insert(new PalabrasEntity("veterinario", true, false, true, false, false, false, false, false, false,"https://dle.rae.es/veterinario?m=form"));
        dao.insert(new PalabrasEntity("dueño", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/due%C3%B1o?m=form"));
        dao.insert(new PalabrasEntity("carretera", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/carretera?m=form"));
        dao.insert(new PalabrasEntity("diseñada", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/dise%C3%B1ar?m=form"));
        dao.insert(new PalabrasEntity("multa", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/multa?m=form"));
        dao.insert(new PalabrasEntity("redactada", false, false, false, false, true, false, false, false, false,"https://dle.rae.es/redactar?m=form"));
        dao.insert(new PalabrasEntity("policía", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/polic%C3%ADa?m=form"));
        dao.insert(new PalabrasEntity("árbitro", true, false, false, false, false, false, false, false, false,"https://dle.rae.es/polic%C3%ADa?m=form"));

    }
//Añadir en el layout un aviso que diga que aquellas formas que admiten plural deben ser tenidas en cuentas a la hora de elegir que tipo de función puede desempeñar una palbra
    //Ejemplo: aparece por pantalla la palabra demasiado, pero debe entenderse como demasiado/demasiados
private static void addItemspalfra(PalfraDao dao2) {


    dao2.insert(new palfraEntity(101,304,1));
    dao2.insert(new palfraEntity(101,414,2));
    dao2.insert(new palfraEntity(101,523,3));
    dao2.insert(new palfraEntity(101,231,4));
    dao2.insert(new palfraEntity(101,308,5));
    dao2.insert(new palfraEntity(101,423,6));
    dao2.insert(new palfraEntity(101,480,7));

    dao2.insert(new palfraEntity(100,471,1));
    dao2.insert(new palfraEntity(100,681,2));
    dao2.insert(new palfraEntity(100,682,3));
    dao2.insert(new palfraEntity(100,243,4));
    dao2.insert(new palfraEntity(100,448,5));
    dao2.insert(new palfraEntity(100,683,6));

    dao2.insert(new palfraEntity(99,418,1));
    dao2.insert(new palfraEntity(99,679,2));
    dao2.insert(new palfraEntity(99,647,3));
    dao2.insert(new palfraEntity(99,680,4));
    dao2.insert(new palfraEntity(99,243,5));
    dao2.insert(new palfraEntity(99,575,6));
    dao2.insert(new palfraEntity(99,576,7));

    dao2.insert(new palfraEntity(98,304,1));
    dao2.insert(new palfraEntity(98,112,2));
    dao2.insert(new palfraEntity(98,651,3));
    dao2.insert(new palfraEntity(98,676,4));
    dao2.insert(new palfraEntity(98,306,5));
    dao2.insert(new palfraEntity(98,677,6));
    dao2.insert(new palfraEntity(98,243,7));
    dao2.insert(new palfraEntity(98,422,8));
    dao2.insert(new palfraEntity(98,678,9));


    dao2.insert(new palfraEntity(97,436,1));
    dao2.insert(new palfraEntity(97,672,2));
    dao2.insert(new palfraEntity(97,673,3));
    dao2.insert(new palfraEntity(97,674,4));
    dao2.insert(new palfraEntity(97,239,5));
    dao2.insert(new palfraEntity(97,304,6));
    dao2.insert(new palfraEntity(97,440,7));
    dao2.insert(new palfraEntity(97,243,8));
    dao2.insert(new palfraEntity(97,308,9));
    dao2.insert(new palfraEntity(97,675,10));

    dao2.insert(new palfraEntity(96,304,1));
    dao2.insert(new palfraEntity(96,669,2));
    dao2.insert(new palfraEntity(96,647,3));
    dao2.insert(new palfraEntity(96,670,4));
    dao2.insert(new palfraEntity(96,243,5));
    dao2.insert(new palfraEntity(96,304,6));
    dao2.insert(new palfraEntity(96,671,7));

    dao2.insert(new palfraEntity(95,445,1));
    dao2.insert(new palfraEntity(95,665,2));
    dao2.insert(new palfraEntity(95,666,3));
    dao2.insert(new palfraEntity(95,667,4));
    dao2.insert(new palfraEntity(95,243,5));
    dao2.insert(new palfraEntity(95,304,6));
    dao2.insert(new palfraEntity(95,668,7));

    dao2.insert(new palfraEntity(94,304,1));
    dao2.insert(new palfraEntity(94,661,2));
    dao2.insert(new palfraEntity(94,651,3));
    dao2.insert(new palfraEntity(94,662,4));
    dao2.insert(new palfraEntity(94,248,5));
    dao2.insert(new palfraEntity(94,308,6));
    dao2.insert(new palfraEntity(94,663,7));
    dao2.insert(new palfraEntity(94,243,8));
    dao2.insert(new palfraEntity(94,422,9));
    dao2.insert(new palfraEntity(94,664,10));

    dao2.insert(new palfraEntity(93,436,1));
    dao2.insert(new palfraEntity(93,656,2));
    dao2.insert(new palfraEntity(93,657,3));
    dao2.insert(new palfraEntity(93,658,4));
    dao2.insert(new palfraEntity(93,659,5));
    dao2.insert(new palfraEntity(93,243,6));
    dao2.insert(new palfraEntity(93,308,7));
    dao2.insert(new palfraEntity(93,660,8));


    dao2.insert(new palfraEntity(92,304,1));
    dao2.insert(new palfraEntity(92,598,2));
    dao2.insert(new palfraEntity(92,647,3));
    dao2.insert(new palfraEntity(92,648,4));
    dao2.insert(new palfraEntity(92,243,5));
    dao2.insert(new palfraEntity(92,304,6));
    dao2.insert(new palfraEntity(92,610,7));

    dao2.insert(new palfraEntity(91,308,1));
    dao2.insert(new palfraEntity(91,23,2));
    dao2.insert(new palfraEntity(91,651,3));
    dao2.insert(new palfraEntity(91,652,4));
    dao2.insert(new palfraEntity(91,243,5));
    dao2.insert(new palfraEntity(91,653,6));
    dao2.insert(new palfraEntity(91,654,7));

    dao2.insert(new palfraEntity(90,308,1));
    dao2.insert(new palfraEntity(90,649,2));
    dao2.insert(new palfraEntity(90,647,3));
    dao2.insert(new palfraEntity(90,650,4));
    dao2.insert(new palfraEntity(90,243,5));
    dao2.insert(new palfraEntity(90,445,6));
    dao2.insert(new palfraEntity(90,510,7));

    dao2.insert(new palfraEntity(89,304,1));
    dao2.insert(new palfraEntity(89,646,2));
    dao2.insert(new palfraEntity(89,647,3));
    dao2.insert(new palfraEntity(89,648,4));
    dao2.insert(new palfraEntity(89,243,5));
    dao2.insert(new palfraEntity(89,304,6));
    dao2.insert(new palfraEntity(89,441,7));


    dao2.insert(new palfraEntity(88,483,1));
    dao2.insert(new palfraEntity(88,323,2));
    dao2.insert(new palfraEntity(88,644,3));
    dao2.insert(new palfraEntity(88,304,4));
    dao2.insert(new palfraEntity(88,309,5));
    dao2.insert(new palfraEntity(88,306,6));
    dao2.insert(new palfraEntity(88,330,7));
    dao2.insert(new palfraEntity(88,236,8));
    dao2.insert(new palfraEntity(88,436,9));
    dao2.insert(new palfraEntity(88,645,10));

    dao2.insert(new palfraEntity(87,445,1));
    dao2.insert(new palfraEntity(87,641,2));
    dao2.insert(new palfraEntity(87,323,3));
    dao2.insert(new palfraEntity(87,642,4));
    dao2.insert(new palfraEntity(87,304,5));
    dao2.insert(new palfraEntity(87,309,6));
    dao2.insert(new palfraEntity(87,306,7));
    dao2.insert(new palfraEntity(87,330,8));
    dao2.insert(new palfraEntity(87,243,9));
    dao2.insert(new palfraEntity(87,304,10));
    dao2.insert(new palfraEntity(87,643,11));

    dao2.insert(new palfraEntity(86,445,1));
    dao2.insert(new palfraEntity(86,638,2));
    dao2.insert(new palfraEntity(86,323,3));
    dao2.insert(new palfraEntity(86,639,4));
    dao2.insert(new palfraEntity(86,239,5));
    dao2.insert(new palfraEntity(86,304,6));
    dao2.insert(new palfraEntity(86,640,7));

    dao2.insert(new palfraEntity(85,483,1));
    dao2.insert(new palfraEntity(85,323,2));
    dao2.insert(new palfraEntity(85,636,3));
    dao2.insert(new palfraEntity(85,238,4));
    dao2.insert(new palfraEntity(85,308,5));
    dao2.insert(new palfraEntity(85,637,6));

    dao2.insert(new palfraEntity(84,445,1));
    dao2.insert(new palfraEntity(84,633,2));
    dao2.insert(new palfraEntity(84,323,3));
    dao2.insert(new palfraEntity(84,634,4));
    dao2.insert(new palfraEntity(84,304,5));
    dao2.insert(new palfraEntity(84,14,6));
    dao2.insert(new palfraEntity(84,635,7));

    dao2.insert(new palfraEntity(83,324,1));
    dao2.insert(new palfraEntity(83,613,2));
    dao2.insert(new palfraEntity(83,436,3));
    dao2.insert(new palfraEntity(83,632,4));
    dao2.insert(new palfraEntity(83,236,5));
    dao2.insert(new palfraEntity(83,343,6));
    dao2.insert(new palfraEntity(83,612,7));


    dao2.insert(new palfraEntity(82,316,1));
    dao2.insert(new palfraEntity(82,629,2));
    dao2.insert(new palfraEntity(82,485,3));
    dao2.insert(new palfraEntity(82,436,4));
    dao2.insert(new palfraEntity(82,630,5));

    dao2.insert(new palfraEntity(81,445,1));
    dao2.insert(new palfraEntity(81,626,2));
    dao2.insert(new palfraEntity(81,323,3));
    dao2.insert(new palfraEntity(81,627,4));
    dao2.insert(new palfraEntity(81,308,5));
    dao2.insert(new palfraEntity(81,628,6));

    dao2.insert(new palfraEntity(80,445,1));
    dao2.insert(new palfraEntity(80,624,2));
    dao2.insert(new palfraEntity(80,625,3));
    dao2.insert(new palfraEntity(80,305,4));
    dao2.insert(new palfraEntity(80,69,5));
    dao2.insert(new palfraEntity(80,460,6));

    dao2.insert(new palfraEntity(79,316,1));
    dao2.insert(new palfraEntity(79,622,2));
    dao2.insert(new palfraEntity(79,238,3));
    dao2.insert(new palfraEntity(79,385,4));
    dao2.insert(new palfraEntity(79,236,5));
    dao2.insert(new palfraEntity(79,337,6));
    dao2.insert(new palfraEntity(79,623,7));

    dao2.insert(new palfraEntity(78,483,1));
    dao2.insert(new palfraEntity(78,323,2));
    dao2.insert(new palfraEntity(78,620,3));
    dao2.insert(new palfraEntity(78,243,4));
    dao2.insert(new palfraEntity(78,445,5));
    dao2.insert(new palfraEntity(78,574,6));
    dao2.insert(new palfraEntity(78,621,7));

    dao2.insert(new palfraEntity(77,316,1));
    dao2.insert(new palfraEntity(77,618,2));
    dao2.insert(new palfraEntity(77,237,3));
    dao2.insert(new palfraEntity(77,619,4));

    dao2.insert(new palfraEntity(76,304,1));
    dao2.insert(new palfraEntity(76,602,2));
    dao2.insert(new palfraEntity(76,413,3));
    dao2.insert(new palfraEntity(76,415,4));
    dao2.insert(new palfraEntity(76,616,5));
    dao2.insert(new palfraEntity(76,239,6));
    dao2.insert(new palfraEntity(76,304,7));
    dao2.insert(new palfraEntity(76,617,8));

    dao2.insert(new palfraEntity(75,304,1));
    dao2.insert(new palfraEntity(75,613,2));
    dao2.insert(new palfraEntity(75,586,3));
    dao2.insert(new palfraEntity(75,614,4));
    dao2.insert(new palfraEntity(75,223,5));
    dao2.insert(new palfraEntity(75,237,6));
    dao2.insert(new palfraEntity(75,422,7));
    dao2.insert(new palfraEntity(75,615,8));

    dao2.insert(new palfraEntity(74,316,1));
    dao2.insert(new palfraEntity(74,590,2));
    dao2.insert(new palfraEntity(74,611,3));
    dao2.insert(new palfraEntity(74,223,4));
    dao2.insert(new palfraEntity(74,237,5));
    dao2.insert(new palfraEntity(74,343,6));
    dao2.insert(new palfraEntity(74,612,7));

    dao2.insert(new palfraEntity(73,589,1));
    dao2.insert(new palfraEntity(73,608,2));
    dao2.insert(new palfraEntity(73,243,3));
    dao2.insert(new palfraEntity(73,304,4));
    dao2.insert(new palfraEntity(73,609,5));
    dao2.insert(new palfraEntity(73,307,6));
    dao2.insert(new palfraEntity(73,610,7));

    dao2.insert(new palfraEntity(72,304,1));
    dao2.insert(new palfraEntity(72,588,2));
    dao2.insert(new palfraEntity(72,336,3));
    dao2.insert(new palfraEntity(72,349,4));
    dao2.insert(new palfraEntity(72,198,5));

    dao2.insert(new palfraEntity(71,422,1));
    dao2.insert(new palfraEntity(71,607,2));
    dao2.insert(new palfraEntity(71,413,3));
    dao2.insert(new palfraEntity(71,305,4));
    dao2.insert(new palfraEntity(71,602,5));
    dao2.insert(new palfraEntity(71,415,6));
    dao2.insert(new palfraEntity(71,198,7));


    dao2.insert(new palfraEntity(70,575,1));
    dao2.insert(new palfraEntity(70,576,2));
    dao2.insert(new palfraEntity(70,413,3));
    dao2.insert(new palfraEntity(70,305,4));
    dao2.insert(new palfraEntity(70,605,5));
    dao2.insert(new palfraEntity(70,606,6));


    dao2.insert(new palfraEntity(69,308,1));
    dao2.insert(new palfraEntity(69,23,2));
    dao2.insert(new palfraEntity(69,413,3));
    dao2.insert(new palfraEntity(69,237,4));
    dao2.insert(new palfraEntity(69,604,5));
    dao2.insert(new palfraEntity(69,153,6));
    dao2.insert(new palfraEntity(69,231,7));
    dao2.insert(new palfraEntity(69,122,8));


    dao2.insert(new palfraEntity(68,462,1));
    dao2.insert(new palfraEntity(68,602,2));
    dao2.insert(new palfraEntity(68,586,3));
    dao2.insert(new palfraEntity(68,603,4));


    dao2.insert(new palfraEntity(67,575,1));
    dao2.insert(new palfraEntity(67,423,2));
    dao2.insert(new palfraEntity(67,585,3));
    dao2.insert(new palfraEntity(67,599,4));
    dao2.insert(new palfraEntity(67,243,5));
    dao2.insert(new palfraEntity(67,600,6));
    dao2.insert(new palfraEntity(67,601,7));

    dao2.insert(new palfraEntity(66,595,1));
    dao2.insert(new palfraEntity(66,596,2));
    dao2.insert(new palfraEntity(66,584,3));
    dao2.insert(new palfraEntity(66,597,4));
    dao2.insert(new palfraEntity(66,223,5));
    dao2.insert(new palfraEntity(66,307,6));
    dao2.insert(new palfraEntity(66,610,7));


    dao2.insert(new palfraEntity(65,344,1));
    dao2.insert(new palfraEntity(65,583,2));
    dao2.insert(new palfraEntity(65,592,3));
    dao2.insert(new palfraEntity(65,237,4));
    dao2.insert(new palfraEntity(65,593,5));
    dao2.insert(new palfraEntity(65,594,6));

    dao2.insert(new palfraEntity(64,321,1));
    dao2.insert(new palfraEntity(64,323,2));
    dao2.insert(new palfraEntity(64,580,3));
    dao2.insert(new palfraEntity(64,237,4));
    dao2.insert(new palfraEntity(64,436,5));
    dao2.insert(new palfraEntity(64,581,6));

    dao2.insert(new palfraEntity(63,321,1));
    dao2.insert(new palfraEntity(63,323,2));
    dao2.insert(new palfraEntity(63,578,3));
    dao2.insert(new palfraEntity(63,243,4));
    dao2.insert(new palfraEntity(63,308,5));
    dao2.insert(new palfraEntity(63,579,6));

    dao2.insert(new palfraEntity(62,575,1));
    dao2.insert(new palfraEntity(62,576,2));
    dao2.insert(new palfraEntity(62,323,3));
    dao2.insert(new palfraEntity(62,577,4));
    dao2.insert(new palfraEntity(62,335,5));
    dao2.insert(new palfraEntity(62,444,6));
    dao2.insert(new palfraEntity(62,445,7));
    dao2.insert(new palfraEntity(62,446,8));


    dao2.insert(new palfraEntity(61,304,1));
    dao2.insert(new palfraEntity(61,572,2));
    dao2.insert(new palfraEntity(61,323,3));
    dao2.insert(new palfraEntity(61,573,4));
    dao2.insert(new palfraEntity(61,237,5));
    dao2.insert(new palfraEntity(61,445,6));
    dao2.insert(new palfraEntity(61,574,7));

    dao2.insert(new palfraEntity(60,308,1));
    dao2.insert(new palfraEntity(60,569,2));
    dao2.insert(new palfraEntity(60,312,3));
    dao2.insert(new palfraEntity(60,570,4));
    dao2.insert(new palfraEntity(60,571,5));

    dao2.insert(new palfraEntity(59,323,1));
    dao2.insert(new palfraEntity(59,312,2));
    dao2.insert(new palfraEntity(59,566,3));
    dao2.insert(new palfraEntity(59,305,4));
    dao2.insert(new palfraEntity(59,567,5));
    dao2.insert(new palfraEntity(59,243,6));
    dao2.insert(new palfraEntity(59,304,7));
    dao2.insert(new palfraEntity(59,568,8));

    dao2.insert(new palfraEntity(58,314,1));
    dao2.insert(new palfraEntity(58,563,2));
    dao2.insert(new palfraEntity(58,421,3));
    dao2.insert(new palfraEntity(58,308,4));
    dao2.insert(new palfraEntity(58,564,5));
    dao2.insert(new palfraEntity(58,237,6));
    dao2.insert(new palfraEntity(58,565,7));

    dao2.insert(new palfraEntity(57,312,1));
    dao2.insert(new palfraEntity(57,560,2));
    dao2.insert(new palfraEntity(57,237,3));
    dao2.insert(new palfraEntity(57,561,4));
    dao2.insert(new palfraEntity(57,242,5));
    dao2.insert(new palfraEntity(57,445,6));
    dao2.insert(new palfraEntity(57,562,7));

    dao2.insert(new palfraEntity(56,321,1));
    dao2.insert(new palfraEntity(56,323,2));
    dao2.insert(new palfraEntity(56,558,3));
    dao2.insert(new palfraEntity(56,237,4));
    dao2.insert(new palfraEntity(56,495,5));
    dao2.insert(new palfraEntity(56,559,6));

    dao2.insert(new palfraEntity(55,321,1));
    dao2.insert(new palfraEntity(55,323,2));
    dao2.insert(new palfraEntity(55,420,3));
    dao2.insert(new palfraEntity(55,310,4));
    dao2.insert(new palfraEntity(55,23,5));
    dao2.insert(new palfraEntity(55,145,6));

    dao2.insert(new palfraEntity(54,312,1));
    dao2.insert(new palfraEntity(54,557,2));
    dao2.insert(new palfraEntity(54,243,3));
    dao2.insert(new palfraEntity(54,436,4));
    dao2.insert(new palfraEntity(54,486,5));
    dao2.insert(new palfraEntity(54,222,6));
    dao2.insert(new palfraEntity(54,237,7));
    dao2.insert(new palfraEntity(54,260,8));
    dao2.insert(new palfraEntity(54,306,9));
    dao2.insert(new palfraEntity(54,512,9));


    dao2.insert(new palfraEntity(53,322,1));
    dao2.insert(new palfraEntity(53,323,2));
    dao2.insert(new palfraEntity(53,582,3));
    dao2.insert(new palfraEntity(53,236,4));
    dao2.insert(new palfraEntity(53,495,5));
    dao2.insert(new palfraEntity(53,493,6));


    dao2.insert(new palfraEntity(52,304,1));
    dao2.insert(new palfraEntity(52,414,2));
    dao2.insert(new palfraEntity(52,323,3));
    dao2.insert(new palfraEntity(52,131,4));
    dao2.insert(new palfraEntity(52,308,5));
    dao2.insert(new palfraEntity(52,353,6));
    dao2.insert(new palfraEntity(52,236,7));
    dao2.insert(new palfraEntity(52,308,8));
    dao2.insert(new palfraEntity(52,556,9));



    dao2.insert(new palfraEntity(51,312,1));
    dao2.insert(new palfraEntity(51,555,2));
    dao2.insert(new palfraEntity(51,308,3));
    dao2.insert(new palfraEntity(51,353,4));
    dao2.insert(new palfraEntity(51,236,5));
    dao2.insert(new palfraEntity(51,308,6));
    dao2.insert(new palfraEntity(51,556,7));

    dao2.insert(new palfraEntity(50,312,1));
    dao2.insert(new palfraEntity(50,552,2));
    dao2.insert(new palfraEntity(50,304,3));
    dao2.insert(new palfraEntity(50,553,4));
    dao2.insert(new palfraEntity(50,236,5));
    dao2.insert(new palfraEntity(50,304,6));
    dao2.insert(new palfraEntity(50,554,7));

    dao2.insert(new palfraEntity(49,312,1));
    dao2.insert(new palfraEntity(49,550,2));
    dao2.insert(new palfraEntity(49,239,3));
    dao2.insert(new palfraEntity(49,304,4));
    dao2.insert(new palfraEntity(49,551,5));
    dao2.insert(new palfraEntity(49,222,6));
    dao2.insert(new palfraEntity(49,237,7));
    dao2.insert(new palfraEntity(49,285,8));

    dao2.insert(new palfraEntity(48,312,1));
    dao2.insert(new palfraEntity(48,548,2));
    dao2.insert(new palfraEntity(48,223,3));
    dao2.insert(new palfraEntity(48,237,4));
    dao2.insert(new palfraEntity(48,549,5));

    dao2.insert(new palfraEntity(47,323,1));
    dao2.insert(new palfraEntity(47,547,2));
    dao2.insert(new palfraEntity(47,222,3));
    dao2.insert(new palfraEntity(47,237,4));
    dao2.insert(new palfraEntity(47,285,5));

    dao2.insert(new palfraEntity(46,393,1));
    dao2.insert(new palfraEntity(46,323,2));
    dao2.insert(new palfraEntity(46,543,3));
    dao2.insert(new palfraEntity(46,304,4));
    dao2.insert(new palfraEntity(46,544,5));

    dao2.insert(new palfraEntity(45,323,1));
    dao2.insert(new palfraEntity(45,545,2));
    dao2.insert(new palfraEntity(45,546,3));
    dao2.insert(new palfraEntity(45,242,4));
    dao2.insert(new palfraEntity(45,304,5));
    dao2.insert(new palfraEntity(45,147,6));

    dao2.insert(new palfraEntity(44,323,1));
    dao2.insert(new palfraEntity(44,540,2));
    dao2.insert(new palfraEntity(44,541,3));
    dao2.insert(new palfraEntity(44,308,4));
    dao2.insert(new palfraEntity(44,542,5));

    dao2.insert(new palfraEntity(43,393,2));
    dao2.insert(new palfraEntity(43,391,3));
    dao2.insert(new palfraEntity(43,323,4));
    dao2.insert(new palfraEntity(43,543,5));
    dao2.insert(new palfraEntity(43,304,6));
    dao2.insert(new palfraEntity(43,544,7));


    dao2.insert(new palfraEntity(42,323,1));
    dao2.insert(new palfraEntity(42,537,2));
    dao2.insert(new palfraEntity(42,538,3));
    dao2.insert(new palfraEntity(42,243,4));
    dao2.insert(new palfraEntity(42,539,5));

    dao2.insert(new palfraEntity(41,323,1));
    dao2.insert(new palfraEntity(41,533,2));
    dao2.insert(new palfraEntity(41,534,3));
    dao2.insert(new palfraEntity(41,239,4));
    dao2.insert(new palfraEntity(41,535,5));
    dao2.insert(new palfraEntity(41,536,6));



    dao2.insert(new palfraEntity(40,323,1));
    dao2.insert(new palfraEntity(40,516,2));
    dao2.insert(new palfraEntity(40,337,3));
    dao2.insert(new palfraEntity(40,531,4));
    dao2.insert(new palfraEntity(40,242,5));
    dao2.insert(new palfraEntity(40,532,6));

    dao2.insert(new palfraEntity(39,323,1));
    dao2.insert(new palfraEntity(39,545,2));
    dao2.insert(new palfraEntity(39,546,3));
    dao2.insert(new palfraEntity(39,242,4));
    dao2.insert(new palfraEntity(39,304,5));
    dao2.insert(new palfraEntity(39,147,6));


    dao2.insert(new palfraEntity(38,323,1));
    dao2.insert(new palfraEntity(38,524,2));
    dao2.insert(new palfraEntity(38,525,3));
    dao2.insert(new palfraEntity(38,526,4));
    dao2.insert(new palfraEntity(38,239,5));
    dao2.insert(new palfraEntity(38,304,6));
    dao2.insert(new palfraEntity(38,527,7));

    dao2.insert(new palfraEntity(37,323,1));
    dao2.insert(new palfraEntity(37,524,2));
    dao2.insert(new palfraEntity(37,525,3));
    dao2.insert(new palfraEntity(37,239,4));
    dao2.insert(new palfraEntity(37,505,5));
    dao2.insert(new palfraEntity(37,526,6));

    dao2.insert(new palfraEntity(36,323,1));
    dao2.insert(new palfraEntity(36,521,2));
    dao2.insert(new palfraEntity(36,522,3));
    dao2.insert(new palfraEntity(36,375,4));
    dao2.insert(new palfraEntity(36,243,5));
    dao2.insert(new palfraEntity(36,308,6));
    dao2.insert(new palfraEntity(36,523,7));


    dao2.insert(new palfraEntity(35,323,1));
    dao2.insert(new palfraEntity(35,123,2));
    dao2.insert(new palfraEntity(35,305,3));
    dao2.insert(new palfraEntity(35,519,4));
    dao2.insert(new palfraEntity(35,520,5));


    dao2.insert(new palfraEntity(34,323,1));
    dao2.insert(new palfraEntity(34,516,2));
    dao2.insert(new palfraEntity(34,305,3));
    dao2.insert(new palfraEntity(34,517,4));
    dao2.insert(new palfraEntity(34,242,5));
    dao2.insert(new palfraEntity(34,304,6));
    dao2.insert(new palfraEntity(34,518,7));


    dao2.insert(new palfraEntity(33,323,1));
    dao2.insert(new palfraEntity(33,513,2));
    dao2.insert(new palfraEntity(33,514,3));
    dao2.insert(new palfraEntity(33,223,4));
    dao2.insert(new palfraEntity(33,307,5));
    dao2.insert(new palfraEntity(33,515,6));

    dao2.insert(new palfraEntity(32,445,1));
    dao2.insert(new palfraEntity(32,510,2));
    dao2.insert(new palfraEntity(32,323,3));
    dao2.insert(new palfraEntity(32,511,4));
    dao2.insert(new palfraEntity(32,306,5));
    dao2.insert(new palfraEntity(32,512,6));


    dao2.insert(new palfraEntity(31,323,1));
    dao2.insert(new palfraEntity(31,508,2));
    dao2.insert(new palfraEntity(31,415,3));
    dao2.insert(new palfraEntity(31,509,4));

    dao2.insert(new palfraEntity(30,483,1));
    dao2.insert(new palfraEntity(30,323,2));
    dao2.insert(new palfraEntity(30,506,3));
    dao2.insert(new palfraEntity(30,507,4));

    dao2.insert(new palfraEntity(29,323,1));
    dao2.insert(new palfraEntity(29,504,2));
    dao2.insert(new palfraEntity(29,350,3));
    dao2.insert(new palfraEntity(29,505,4));
    dao2.insert(new palfraEntity(29,463,5));

    dao2.insert(new palfraEntity(28,323,1));
    dao2.insert(new palfraEntity(28,502,2));
    dao2.insert(new palfraEntity(28,305,3));
    dao2.insert(new palfraEntity(28,503,4));
    dao2.insert(new palfraEntity(28,415,5));
    dao2.insert(new palfraEntity(28,202,6));


    dao2.insert(new palfraEntity(1,304,1));
    dao2.insert(new palfraEntity(1,414,2));
    dao2.insert(new palfraEntity(1,413,3));
    dao2.insert(new palfraEntity(1,415,4));
    dao2.insert(new palfraEntity(1,65,5));

    dao2.insert(new palfraEntity(2,416,1));
    dao2.insert(new palfraEntity(2,417,2));
    dao2.insert(new palfraEntity(2,418,3));
    dao2.insert(new palfraEntity(2,419,4));

    dao2.insert(new palfraEntity(3,304,1));
    dao2.insert(new palfraEntity(3,414,2));
    dao2.insert(new palfraEntity(3,420,3));
    dao2.insert(new palfraEntity(3,305,4));
    dao2.insert(new palfraEntity(3,11,5));
    dao2.insert(new palfraEntity(3,421,6));
    dao2.insert(new palfraEntity(3,422,7));
    dao2.insert(new palfraEntity(3,423,8));

    dao2.insert(new palfraEntity(4,308,1));
    dao2.insert(new palfraEntity(4,424,2));
    dao2.insert(new palfraEntity(4,324,3));
    dao2.insert(new palfraEntity(4,425,4));
    dao2.insert(new palfraEntity(4,305,5));
    dao2.insert(new palfraEntity(4,426,6));
    dao2.insert(new palfraEntity(4,242,7));
    dao2.insert(new palfraEntity(4,427,8));

    dao2.insert(new palfraEntity(5,308,1));
    dao2.insert(new palfraEntity(5,423,2));
    dao2.insert(new palfraEntity(5,324,3));
    dao2.insert(new palfraEntity(5,428,4));
    dao2.insert(new palfraEntity(5,422,5));
    dao2.insert(new palfraEntity(5,429,6));
    dao2.insert(new palfraEntity(5,430,7));


    dao2.insert(new palfraEntity(6,304,1));
    dao2.insert(new palfraEntity(6,431,2));
    dao2.insert(new palfraEntity(6,432,3));
    dao2.insert(new palfraEntity(6,304,4));
    dao2.insert(new palfraEntity(6,433,5));
    dao2.insert(new palfraEntity(6,434,6));

    dao2.insert(new palfraEntity(7,435,1));
    dao2.insert(new palfraEntity(7,243,2));
    dao2.insert(new palfraEntity(7,436,3));
    dao2.insert(new palfraEntity(7,437,4));
    dao2.insert(new palfraEntity(7,237,5));
    dao2.insert(new palfraEntity(7,308,6));
    dao2.insert(new palfraEntity(7,438,7));

    dao2.insert(new palfraEntity(8,439,1));
    dao2.insert(new palfraEntity(8,306,2));
    dao2.insert(new palfraEntity(8,440,3));
    dao2.insert(new palfraEntity(8,307,4));
    dao2.insert(new palfraEntity(8,441,5));
    dao2.insert(new palfraEntity(8,389,6));

    dao2.insert(new palfraEntity(9,442,1));
    dao2.insert(new palfraEntity(9,421,2));
    dao2.insert(new palfraEntity(9,308,3));
    dao2.insert(new palfraEntity(9,443,4));
    dao2.insert(new palfraEntity(9,444,5));
    dao2.insert(new palfraEntity(9,445,6));
    dao2.insert(new palfraEntity(9,446,7));

    dao2.insert(new palfraEntity(10,447,1));
    dao2.insert(new palfraEntity(10,239,2));
    dao2.insert(new palfraEntity(10,448,3));
    dao2.insert(new palfraEntity(10,449,4));

    dao2.insert(new palfraEntity(11,304,1));
    dao2.insert(new palfraEntity(11,450,2));
    dao2.insert(new palfraEntity(11,451,3));
    dao2.insert(new palfraEntity(11,452,4));

    dao2.insert(new palfraEntity(12,453,1));
    dao2.insert(new palfraEntity(12,305,2));
    dao2.insert(new palfraEntity(12,454,3));
    dao2.insert(new palfraEntity(12,332,4));
    dao2.insert(new palfraEntity(12,308,5));
    dao2.insert(new palfraEntity(12,455,6));

    dao2.insert(new palfraEntity(13,304,1));
    dao2.insert(new palfraEntity(13,456,2));
    dao2.insert(new palfraEntity(13,457,3));
    dao2.insert(new palfraEntity(13,445,4));
    dao2.insert(new palfraEntity(13,458,5));
    dao2.insert(new palfraEntity(13,239,6));
    dao2.insert(new palfraEntity(13,304,7));
    dao2.insert(new palfraEntity(13,459,8));

    dao2.insert(new palfraEntity(14,304,1));
    dao2.insert(new palfraEntity(14,460,2));
    dao2.insert(new palfraEntity(14,324,3));
    dao2.insert(new palfraEntity(14,461,4));
    dao2.insert(new palfraEntity(14,250,5));
    dao2.insert(new palfraEntity(14,462,6));
    dao2.insert(new palfraEntity(14,463,7));

    dao2.insert(new palfraEntity(15,464,1));
    dao2.insert(new palfraEntity(15,304,2));
    dao2.insert(new palfraEntity(15,465,3));
    dao2.insert(new palfraEntity(15,466,4));

    dao2.insert(new palfraEntity(16,308,1));
    dao2.insert(new palfraEntity(16,467,2));
    dao2.insert(new palfraEntity(16,308,3));
    dao2.insert(new palfraEntity(16,468,4));
    dao2.insert(new palfraEntity(16,448,5));
    dao2.insert(new palfraEntity(16,450,6));

    dao2.insert(new palfraEntity(17,469,1));
    dao2.insert(new palfraEntity(17,470,2));
    dao2.insert(new palfraEntity(17,446,3));
    dao2.insert(new palfraEntity(17,471,4));
    dao2.insert(new palfraEntity(17,472,5));

    dao2.insert(new palfraEntity(18,308,1));
    dao2.insert(new palfraEntity(18,473,2));
    dao2.insert(new palfraEntity(18,474,3));
    dao2.insert(new palfraEntity(18,421,4));
    dao2.insert(new palfraEntity(18,445,5));
    dao2.insert(new palfraEntity(18,475,6));

    dao2.insert(new palfraEntity(19,476,1));
    dao2.insert(new palfraEntity(19,332,2));
    dao2.insert(new palfraEntity(19,308,3));
    dao2.insert(new palfraEntity(19,477,4));
    dao2.insert(new palfraEntity(19,239,5));
    dao2.insert(new palfraEntity(19,478,6));
    dao2.insert(new palfraEntity(19,479,7));

    dao2.insert(new palfraEntity(20,480,1));
    dao2.insert(new palfraEntity(20,308,2));
    dao2.insert(new palfraEntity(20,481,3));
    dao2.insert(new palfraEntity(20,239,4));
    dao2.insert(new palfraEntity(20,308,5));
    dao2.insert(new palfraEntity(20,482,6));

    dao2.insert(new palfraEntity(21,483,1));
    dao2.insert(new palfraEntity(21,324,2));
    dao2.insert(new palfraEntity(21,484,3));
    dao2.insert(new palfraEntity(21,485,4));
    dao2.insert(new palfraEntity(21,436,5));
    dao2.insert(new palfraEntity(21,486,6));

    dao2.insert(new palfraEntity(22,445,1));
    dao2.insert(new palfraEntity(22,487,2));
    dao2.insert(new palfraEntity(22,324,3));
    dao2.insert(new palfraEntity(22,488,4));
    dao2.insert(new palfraEntity(22,239,5));
    dao2.insert(new palfraEntity(22,308,6));
    dao2.insert(new palfraEntity(22,489,7));

    dao2.insert(new palfraEntity(23,445,1));
    dao2.insert(new palfraEntity(23,490,2));
    dao2.insert(new palfraEntity(23,324,3));
    dao2.insert(new palfraEntity(23,491,4));
    dao2.insert(new palfraEntity(23,244,5));
    dao2.insert(new palfraEntity(23,492,6));

    dao2.insert(new palfraEntity(24,436,1));
    dao2.insert(new palfraEntity(24,493,2));
    dao2.insert(new palfraEntity(24,324,3));
    dao2.insert(new palfraEntity(24,494,4));
    dao2.insert(new palfraEntity(24,495,5));
    dao2.insert(new palfraEntity(24,496,6));

    dao2.insert(new palfraEntity(25,445,1));
    dao2.insert(new palfraEntity(25,497,2));
    dao2.insert(new palfraEntity(25,324,3));
    dao2.insert(new palfraEntity(25,498,4));
    dao2.insert(new palfraEntity(25,239,5));
    dao2.insert(new palfraEntity(25,308,6));
    dao2.insert(new palfraEntity(25,499,7));

    dao2.insert(new palfraEntity(26,324,1));
    dao2.insert(new palfraEntity(26,480,2));
    dao2.insert(new palfraEntity(26,308,3));
    dao2.insert(new palfraEntity(26,481,4));
    dao2.insert(new palfraEntity(26,239,5));
    dao2.insert(new palfraEntity(26,308,6));
    dao2.insert(new palfraEntity(26,482,7));

    dao2.insert(new palfraEntity(27,445,1));
    dao2.insert(new palfraEntity(27,500,2));
    dao2.insert(new palfraEntity(27,324,3));
    dao2.insert(new palfraEntity(27,501,4));
    dao2.insert(new palfraEntity(27,336,5));



}

}



