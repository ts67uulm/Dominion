package stenzel.tim.dominion.Classes;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(primaryKeys = {"cardId", "landmarkerId", "ereignisId", "deckId"},
        foreignKeys = {
        @ForeignKey(entity = Card.class, parentColumns = "id", childColumns = "cards"),
        @ForeignKey(entity = Deck.class, parentColumns = "id", childColumns = "deckId")},
        indices = {@Index(value = "deckId")}
        )
public class CCD {

    private int cardId, deckId;

    public CCD(int cardId, int deckId) {
        this.cardId = cardId;
        this.deckId = deckId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }
}
