package stenzel.tim.dominion.DB;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import stenzel.tim.dominion.Classes.Erweiterungsset;

@Dao
public interface ErweiterungssetDao {

    @Insert
    void insertElement(Erweiterungsset e);

    @Insert
    void insertAll(Erweiterungsset... erweiterungssets);

    @Query("SELECT * FROM Erweiterungsset")
    List<Erweiterungsset> getAllErweiterungssets();

    @Query("SELECT * FROM Erweiterungsset WHERE checked = 1")
    List<Erweiterungsset> getAllCheckedErweiterungssets();

    @Query("SELECT * FROM Erweiterungsset WHERE name = :name")
    Erweiterungsset getErweiterungssetByName(String name);

    @Query("UPDATE Erweiterungsset SET checked = :checked WHERE id = :id")
    void updateEwChecked(int id, boolean checked);

    @Delete
    void deleteElement(Erweiterungsset e);


}
