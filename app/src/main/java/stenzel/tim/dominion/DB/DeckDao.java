package stenzel.tim.dominion.DB;


import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import stenzel.tim.dominion.Classes.Deck;

@Dao
public interface DeckDao {

    @Insert
    void insertElement(Deck d);

    @Insert
    void insertAll(Deck... decks);

    @Query("SELECT id FROM Deck WHERE name = :name AND morePlayers = :morePlayers AND category = :category AND kurvenId = :id")
    int getDeckId(String name, boolean morePlayers, String category, int id);

    @Delete
    void deleteElement(Deck d);


}
