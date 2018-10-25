package stenzel.tim.dominion.Classes;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"id", "name"}, indices = {@Index(value = "name", unique = true)})
public class Erweiterungsset {

    @NonNull
    private int id;

    @NonNull
    private String  name;

    private boolean checked;

    public Erweiterungsset(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Erweiterungsset[] populateData(){

     return new Erweiterungsset[]{
             new Erweiterungsset(0, "Basis"),
            new Erweiterungsset(1, "Abenteuer"),
            new Erweiterungsset(2, "Intrige"),
            new Erweiterungsset(3, "Empires"),

     };

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
