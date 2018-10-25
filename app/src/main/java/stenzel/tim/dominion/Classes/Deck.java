package stenzel.tim.dominion.Classes;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"id"}, indices = {@Index(value = "name")})
public class Deck {

    @NonNull
    private int id;

    @NonNull
    private String name;

    private String cards, category;

    private boolean morePlayers;

    public Deck(int id, String name, String cards, String category, boolean morePlayers) {
        this.id = id;
        this.name = name;
        this.cards = cards;
        this.category = category;
        this.morePlayers = morePlayers;
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

    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
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
}
