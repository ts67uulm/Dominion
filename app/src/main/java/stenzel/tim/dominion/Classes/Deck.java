package stenzel.tim.dominion.Classes;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Kurvenmodell.class, parentColumns = "id", childColumns = "kurvenId")},
        indices = {@Index(value = "kurvenId")})
public class Deck {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String name;

    private String category;

    private boolean morePlayers;

    private int kurvenId;

    public Deck(@NonNull int id, @NonNull String name, String category, boolean morePlayers, int kurvenId) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.morePlayers = morePlayers;
        this.kurvenId = kurvenId;
    }

    public static Deck[] populateData() {

        return new Deck[] {

                new Deck(0, "Preset0",  "Profi", false, 0),
                new Deck(0, "Preset1", "Einsteiger", true, 0)

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isMorePlayers() {
        return morePlayers;
    }

    public void setMorePlayers(boolean morePlayers) {
        this.morePlayers = morePlayers;
    }

    public int getKurvenId() {
        return kurvenId;
    }

    public void setKurvenId(int kurvenId) {
        this.kurvenId = kurvenId;
    }
}
