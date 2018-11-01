package stenzel.tim.dominion.DB;

import android.content.Context;

import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import stenzel.tim.dominion.Classes.CCD;
import stenzel.tim.dominion.Classes.Card;
import stenzel.tim.dominion.Classes.Deck;
import stenzel.tim.dominion.Classes.Erweiterungsset;
import stenzel.tim.dominion.Classes.Kurvenmodell;

@Database(entities = {Card.class, Erweiterungsset.class, Deck.class, Kurvenmodell.class, CCD.class}, version = 11, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(Context context){

        if (INSTANCE == null){
            INSTANCE = buildDB(context);
                    //Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "DB").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    private static AppDatabase buildDB(final Context context){

        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "DB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getAppDatabase(context).getErweiterungssetDao().insertAll(Erweiterungsset.populateData());
                                getAppDatabase(context).getCardDao().insertAll(Card.populateData());
                                getAppDatabase(context).getKurvenmodellDao().insertAll(Kurvenmodell.populateData());
                                getAppDatabase(context).getDeckDao().insertAll(Deck.populateData());
                                getAppDatabase(context).getCCDDao().insertAll(CCD.populateData());
                            }
                        });
                    }
                })
                .build();

    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    public abstract ErweiterungssetDao getErweiterungssetDao();

    public abstract CardDao getCardDao();

    public abstract CCDDao getCCDDao();

    public abstract DeckDao getDeckDao();

    public abstract KurvenmodellDao getKurvenmodellDao();

}
