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

    @Query("SELECT * FROM Card")
    List<Card> getAllCards();

    @Query("SELECT * FROM Card WHERE deck = :deckName")
    List<Card> getCardsFromCheckedErweiterungsset(String deckName);

    @Query("SELECT * FROM Card WHERE checked = 1")
    List<Card> getSelectedCards();

    @Query("SELECT * FROM Card WHERE cost = :cost")
    List<Card> getCardByCost(int cost);

    @Query("UPDATE Card SET checked = :checked WHERE id = :id")
    void updateCardChecked(int id, boolean checked);

    @Delete
    void deleteElement(Card c);


}
