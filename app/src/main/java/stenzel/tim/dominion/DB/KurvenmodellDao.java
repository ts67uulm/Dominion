package stenzel.tim.dominion.DB;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import stenzel.tim.dominion.Classes.Kurvenmodell;

@Dao
public interface KurvenmodellDao {

    @Insert
    void insertElement(Kurvenmodell k);

    @Insert
    void insertAll(Kurvenmodell... kurvenmodelle);

    @Query("SELECT * FROM Kurvenmodell")
    List<Kurvenmodell> getAllKurvenmodelle();

    @Delete
    void deleteElement(Kurvenmodell k);
}
