package stenzel.tim.dominion.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import stenzel.tim.dominion.Classes.CCD;

@Dao
public interface CCDDao {

    @Insert
    void insertElement(CCD ccd);

    @Insert
    void insertAll(CCD... ccds);

    @Delete
    void deleteElement(CCD ccd);

}
