package stenzel.tim.dominion.Classes;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(primaryKeys = {"id"},
        foreignKeys = {@ForeignKey(entity = Erweiterungsset.class, parentColumns = "name", childColumns = "deck")},
        indices ={@Index(value = "deck")})
public class Card {

    private int id, cost;

    private String name, text, deck;

    private boolean checked;

    public Card(int id, int cost, String name, String text, String deck) {
        this.id = id;
        this.cost = cost;
        this.name = name;
        this.text = text;
        this.deck = deck;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
