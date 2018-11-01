package stenzel.tim.dominion.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import stenzel.tim.dominion.Classes.CCD;

@Dao
public interface CCDDao {

    @Insert
    void insertElement(CCD ccd);

    @Insert
    void insertAll(CCD... ccds);

    @Query("SELECT * FROM CCD WHERE deckId = :id")
    CCD getCCDByDeckId(int id);

    @Delete
    void deleteElement(CCD ccd);

}
