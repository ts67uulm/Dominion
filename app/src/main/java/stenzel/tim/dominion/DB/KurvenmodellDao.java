package stenzel.tim.dominion.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import stenzel.tim.dominion.Classes.Kurvenmodell;

@Dao
public interface KurvenmodellDao {

    @Insert
    void insertElement(Kurvenmodell k);

    @Delete
    void deleteElement(Kurvenmodell k);


}
