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

    private int deckId, card0, card1, card2, card3, card4, card5, card6, card7, card8, card9, landmarker, ereignis;

    public CCD(int deckId, int card0, int card1, int card2, int card3, int card4, int card5, int card6, int card7, int card8, int card9, int landmarker, int ereignis) {
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
        this.landmarker = landmarker;
        this.ereignis = ereignis;
    }

    public static CCD[] populateData(){

        return new CCD[]{
                new CCD(1, 0,3, 4, 2, 20, 10, 21, 11,13, 7, 159, 160),
                new CCD(2, 15, 8, 25, 17, 18, 1, 12, 24, 23, 7, 159, 160),
                new CCD(3, 8, 3, 9, 19, 18, 22, 24, 23, 5, 6, 159, 160),
                new CCD(4, 16, 25, 18, 2, 10, 22, 11, 12, 6, 14, 159, 160),
                new CCD(5, 0, 9, 4, 17, 2, 20, 21, 24, 13, 14, 159, 160),
                new CCD(6, 15, 8, 9, 4, 1, 19, 21, 12, 5, 6, 159, 160),


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

    public int getLandmarker() {
        return landmarker;
    }

    public void setLandmarker(int landmarker) {
        this.landmarker = landmarker;
    }

    public int getEreignis() {
        return ereignis;
    }

    public void setEreignis(int ereignis) {
        this.ereignis = ereignis;
    }
}
