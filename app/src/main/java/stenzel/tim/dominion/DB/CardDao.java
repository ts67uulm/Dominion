package stenzel.tim.dominion.DB;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import stenzel.tim.dominion.Classes.Card;
import stenzel.tim.dominion.Classes.Erweiterungsset;

@Dao
public interface CardDao {

    @Insert
    void insertElement(Card c);

    @Insert
    void insertAll(Card... cards);

    @Query("SELECT * FROM card")
    List<Card> getAllCards();

    @Delete
    void deleteElement(Card c);


}
