package stenzel.tim.dominion.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import stenzel.tim.dominion.Classes.Card;

@Dao
public interface CardDao {

    @Insert
    void insertElement(Card c);

    @Delete
    void deleteElement(Card c);


}
