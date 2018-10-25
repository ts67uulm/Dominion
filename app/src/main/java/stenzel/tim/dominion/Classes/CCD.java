package stenzel.tim.dominion.Classes;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(primaryKeys = {"deckId"},
        foreignKeys = {
        @ForeignKey(entity = Deck.class, parentColumns = "id", childColumns = "deckId")},
        indices = {@Index(value = "deckId")}
        )
public class CCD {

    private int deckId, card0, card1, card2, card3, card4, card5, card6, card7, card8, card9;

    public CCD(int deckId, int card0, int card1, int card2, int card3, int card4, int card5, int card6, int card7, int card8, int card9) {
        this.deckId = deckId;
        this.card0 = card0;
        this.card1 = card1;
        this.card2 = card2;
        this.card3 = card3;
        this.card4 = card4;
        this.card5 = card5;
        this.card6 = card6;
        this.card7 = card7;
        this.card8 = card8;
        this.card9 = card9;
    }

    public static CCD[] populateData(){

        return new CCD[]{
                new CCD(0, 0,1, 2, 3, 4, 5, 6, 7,8, 9),

                new CCD(1, 1, 3, 5, 7, 10, 12, 14, 16, 19, 20),


        };

    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public int getCard0() {
        return card0;
    }

    public void setCard0(int card0) {
        this.card0 = card0;
    }

    public int getCard1() {
        return card1;
    }

    public void setCard1(int card1) {
        this.card1 = card1;
    }

    public int getCard2() {
        return card2;
    }

    public void setCard2(int card2) {
        this.card2 = card2;
    }

    public int getCard3() {
        return card3;
    }

    public void setCard3(int card3) {
        this.card3 = card3;
    }

    public int getCard4() {
        return card4;
    }

    public void setCard4(int card4) {
        this.card4 = card4;
    }

    public int getCard5() {
        return card5;
    }

    public void setCard5(int card5) {
        this.card5 = card5;
    }

    public int getCard6() {
        return card6;
    }

    public void setCard6(int card6) {
        this.card6 = card6;
    }

    public int getCard7() {
        return card7;
    }

    public void setCard7(int card7) {
        this.card7 = card7;
    }

    public int getCard8() {
        return card8;
    }

    public void setCard8(int card8) {
        this.card8 = card8;
    }

    public int getCard9() {
        return card9;
    }

    public void setCard9(int card9) {
        this.card9 = card9;
    }
}
