package stenzel.tim.dominion.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import stenzel.tim.dominion.Classes.Card;
import stenzel.tim.dominion.Classes.Deck;
import stenzel.tim.dominion.Classes.Erweiterungsset;
import stenzel.tim.dominion.Classes.Kurvenmodell;

@Database(entities = {Card.class, Erweiterungsset.class, Deck.class, Kurvenmodell.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(Context context){

        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "DB").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    public abstract ErweiterungssetDao getErweiterungssetDao();

    public abstract CardDao getCardDao();

    public abstract DeckDao getDeckDao();

    public abstract KurvenmodellDao getKurvenmodellDao();

}
