package stenzel.tim.dominion.Classes;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Erweiterungsset {

    @PrimaryKey
    private int id;

    private String  name;

    private boolean checked;

    public Erweiterungsset(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
