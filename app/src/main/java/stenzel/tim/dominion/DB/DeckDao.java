package stenzel.tim.dominion.DB;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import stenzel.tim.dominion.Classes.Deck;

@Dao
public interface DeckDao {

    @Insert
    void insertElement(Deck d);

    @Insert
    void insertAll(Deck... decks);

    @Delete
    void deleteElement(Deck d);


}
