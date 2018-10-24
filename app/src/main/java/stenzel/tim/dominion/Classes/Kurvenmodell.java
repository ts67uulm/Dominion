package stenzel.tim.dominion.Classes;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Kurvenmodell {

    @PrimaryKey
    private int id;

    private int cost2, cost3, cost4, cost5, cost6, cost7, cost8Plus;

    public Kurvenmodell(int id, int cost2, int cost3, int cost4, int cost5, int cost6, int cost7, int cost8Plus) {
        this.id = id;
        this.cost2 = cost2;
        this.cost3 = cost3;
        this.cost4 = cost4;
        this.cost5 = cost5;
        this.cost6 = cost6;
        this.cost7 = cost7;
        this.cost8Plus = cost8Plus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCost2() {
        return cost2;
    }

    public void setCost2(int cost2) {
        this.cost2 = cost2;
    }

    public int getCost3() {
        return cost3;
    }

    public void setCost3(int cost3) {
        this.cost3 = cost3;
    }

    public int getCost4() {
        return cost4;
    }

    public void setCost4(int cost4) {
        this.cost4 = cost4;
    }

    public int getCost5() {
        return cost5;
    }

    public void setCost5(int cost5) {
        this.cost5 = cost5;
    }

    public int getCost6() {
        return cost6;
    }

    public void setCost6(int cost6) {
        this.cost6 = cost6;
    }

    public int getCost7() {
        return cost7;
    }

    public void setCost7(int cost7) {
        this.cost7 = cost7;
    }

    public int getCost8Plus() {
        return cost8Plus;
    }

    public void setCost8Plus(int cost8Plus) {
        this.cost8Plus = cost8Plus;
    }
}
