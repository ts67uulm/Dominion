package stenzel.tim.dominion.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import stenzel.tim.dominion.Classes.Erweiterungsset;

@Dao
public interface ErweiterungssetDao {

    @Insert
    void insertElement(Erweiterungsset e);

    @Delete
    void deleteElement(Erweiterungsset e);


}
