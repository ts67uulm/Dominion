package stenzel.tim.dominion.Classes;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"id"},
        foreignKeys = {@ForeignKey(entity = Erweiterungsset.class, parentColumns = "name", childColumns = "deck")},
        indices = {@Index(value = "deck")})
public class Card {

    private int id;

    private int cost;

    private String name, type, text, deck;

    private boolean checked;

    public Card(int id, int cost, String name, String type, String text, String deck) {
        this.id = id;
        this.cost = cost;
        this.name = name;
        this.type = type;
        this.text = text;
        this.deck = deck;
    }

    public static Card[] populateData() {
        return new Card[]{
                new Card(0, 2, "Burggraben", "Abwehr", "Abwehr und +2 Aktionen", "Basis"),
                new Card(1, 2, "Kapelle", "", "", "Basis"),
                new Card(2, 2, "Keller", "", "", "Basis"),
                new Card(3, 3, "Dorf", "", "", "Basis"),
                new Card(4, 3, "Händlerin", "", "", "Basis"),
                new Card(5, 3, "Vasall", "", "", "Basis"),
                new Card(6, 3, "Vorbotin", "", "", "Basis"),
                new Card(7, 3, "Werkstatt", "", "", "Basis"),
                new Card(8, 4, "Bürokrat", "", "", "Basis"),
                new Card(9, 4, "Geldverleiher", "", "", "Basis"),
                new Card(10, 4, "Miliz", "", "", "Basis"),
                new Card(11, 4, "Schmiede", "", "", "Basis"),
                new Card(12, 4, "Thronsaal", "", "", "Basis"),
                new Card(13, 4, "Umbau", "", "", "Basis"),
                new Card(14, 4, "Wilddiebin", "", "", "Basis"),
                new Card(15, 5, "Banditin", "", "", "Basis"),
                new Card(16, 5, "Bibliothek", "", "", "Basis"),
                new Card(17, 5, "Hexe", "", "", "Basis"),
                new Card(18, 5, "Jahrmarkt", "", "", "Basis"),
                new Card(19, 5, "Laboratorium", "", "", "Basis"),
                new Card(20, 5, "Markt", "", "", "Basis"),
                new Card(21, 5, "Mine", "", "", "Basis"),
                new Card(22, 5, "Ratsversammlung", "", "", "Basis"),
                new Card(23, 5, "Torwächterin", "", "", "Basis"),
                new Card(24, 6, "Töpferei", "", "", "Basis"),
                new Card(25, 4, "Gärten", "", "", "Basis"),
                new Card(26, 2, "Kleinbauer", "", "", "Abenteuer"),
                new Card(27, 2, "Königliche Münze", "", "", "Abenteuer"),
                new Card(28, 2, "Page", "", "", "Abenteuer"),
                new Card(29, 2, "Rattenfänger", "", "", "Abenteuer"),
                new Card(30, 2, "Zerstörung", "", "", "Abenteuer"),
                new Card(31, 3, "Amulett", "", "", "Abenteuer"),
                new Card(32, 3, "Ausrüstung", "", "", "Abenteuer"),
                new Card(33, 3, "Karawanenwächter", "", "", "Abenteuer"),
                new Card(34, 3, "Kundschafter", "", "", "Abenteuer"),
                new Card(35, 3, "Verlies", "", "", "Abenteuer"),
                new Card(36, 4, "Duplikat", "", "", "Abenteuer"),
                new Card(37, 4, "Elster", "", "", "Abenteuer"),
                new Card(38, 4, "Geizhals", "", "", "Abenteuer"),
                new Card(39, 4, "Hafenstadt", "", "", "Abenteuer"),
                new Card(40, 4, "Kurier", "", "", "Abenteuer"),
                new Card(41, 4, "Transformation", "", "", "Abenteuer"),
                new Card(42, 4, "Wildhüterin", "", "", "Abenteuer"),
                new Card(43, 5, "Brückentroll", "", "", "Abenteuer"),
                new Card(44, 5, "Ferne Lande", "", "", "Abenteuer"),
                new Card(45, 5, "Geisterwald", "", "", "Abenteuer"),
                new Card(46, 5, "Geschichtenerzähler", "", "", "Abenteuer"),
                new Card(47, 5, "Kunsthandwerk", "", "", "Abenteuer"),
                new Card(48, 5, "Königliche Kutsche", "", "", "Abenteuer"),
                new Card(49, 5, "Relikt", "", "", "Abenteuer"),
                new Card(50, 5, "Riese", "", "", "Abenteuer"),
                new Card(51, 5, "Schatz", "", "", "Abenteuer"),
                new Card(52, 5, "Sumpfhexe", "", "", "Abenteuer"),
                new Card(53, 5, "Verlorene Stadt", "", "", "Abenteuer"),
                new Card(54, 5, "Weinhändler", "", "", "Abenteuer"),
                new Card(55, 6, "Gefolgsmann", "", "", "Abenteuer"),
                new Card(56, 2, "Burghof", "", "", "Intrige"),
                new Card(57, 2, "Geheimkammer", "", "", "Intrige"),
                new Card(58, 2, "Handlanger", "", "", "Intrige"),
                new Card(59, 3, "Armenviertel", "", "", "Intrige"),
                new Card(60, 3, "Grosse Halle", "", "", "Intrige"),
                new Card(61, 3, "Maskerade", "", "", "Intrige"),
                new Card(62, 3, "Trickser", "", "", "Intrige"),
                new Card(63, 3, "Verwalter", "", "", "Intrige"),
                new Card(64, 3, "Wunschbrunnen", "", "", "Intrige"),
                new Card(65, 4, "Baron", "", "", "Intrige"),
                new Card(66, 4, "Bergwerk", "", "", "Intrige"),
                new Card(67, 4, "Brücke", "", "", "Intrige"),
                new Card(68, 4, "Eisenhütte", "", "", "Intrige"),
                new Card(69, 4, "Kupferschmied", "", "", "Intrige"),
                new Card(70, 4, "Späher", "", "", "Intrige"),
                new Card(71, 4, "Verschwörer", "", "", "Intrige"),
                new Card(72, 5, "Anbau", "", "", "Intrige"),
                new Card(73, 5, "Handelsposten", "", "", "Intrige"),
                new Card(74, 5, "Herzog", "", "", "Intrige"),
                new Card(75, 5, "Kerkermeister", "", "", "Intrige"),
                new Card(76, 5, "Lakai", "", "", "Intrige"),
                new Card(77, 5, "Saboteur", "", "", "Intrige"),
                new Card(78, 5, "Tribut", "", "", "Intrige"),
                new Card(79, 6, "Adlige", "", "", "Intrige"),
                new Card(80, 6, "Harem", "", "", "Intrige"),
                new Card(81, 3, "Bauernmarkt", "", "", "Empires"),
                new Card(82, 3, "Wagenrennen", "", "", "Empires"),
                new Card(83, 3, "Zauberin", "", "", "Empires"),
                new Card(84, 4, "Ingenieurin", "", "", "Empires"),
                new Card(85, 4, "Opfer", "", "", "Empires"),
                new Card(86, 4, "Tempel", "", "", "Empires"),
                new Card(87, 4, "Villa", "", "", "Empires"),
                new Card(88, 5, "Archiv", "", "", "Empires"),
                new Card(89, 5, "Forum", "", "", "Empires"),
                new Card(90, 5, "Gärtnerin", "", "", "Empires"),
                new Card(91, 5, "Krone", "", "", "Empires"),
                new Card(92, 5, "Legionär", "", "", "Empires"),
                new Card(93, 5, "Vermögen", "", "", "Empires"),
                new Card(94, 5, "Wilde Jagd", "", "", "Empires"),
                new Card(95, 5, "Zauber", "", "", "Empires"),
                new Card(96, 8, "Königlicher Schmied", "", "", "Empires"),
                new Card(97, 8, "Lehnsherr", "", "", "Empires"),
                new Card(98, 8, "Stadtviertel", "", "", "Empires"),
                new Card(99, 2, "Feldlager/Diebesgut", "", "", "Empires"),
                new Card(100, 3, "Gladiator/Reichtum", "", "", "Empires"),
                new Card(101, 3, "Katapult/Felsen", "", "", "Empires"),
                new Card(102, 2, "Patrizier/Handelsplatz", "", "", "Empires"),
                new Card(103, 2, "Siedler/Emsiges Dorf", "", "", "Empires"),
                new Card(104, 0, "Schlösser", "", "", "Empires"),
                new Card(105, 0, "Hochzeit", "Ereignis", "", "Basis"),
                new Card(106, 0, "Handel", "Ereignis", "", "Basis"),
                new Card(107, 0, "Verlorene Kunst", "Ereignis", "", "Basis"),
                new Card(108, 0, "Ball", "Ereignis", "", "Basis"),
                new Card(109, 0, "Eroberung", "Ereignis", "", "Basis"),
                new Card(110, 0, "Erforschen", "Ereignis", "", "Basis"),
                new Card(111, 0, "Glücksfall", "Ereignis", "", "Basis"),
                new Card(112, 0, "Spende", "Ereignis", "", "Basis"),
                new Card(113, 0, "Ritual", "Ereignis", "", "Basis"),
                new Card(114, 0, "Expedition", "Ereignis", "", "Basis"),
                new Card(115, 0, "Leihgabe", "Ereignis", "", "Basis"),
                new Card(116, 0, "Quest", "Ereignis", "", "Basis"),
                new Card(117, 0, "Almosen", "Ereignis", "", "Basis"),
                new Card(118, 0, "Spähtrupp", "Ereignis", "", "Basis"),
                new Card(119, 0, "Wegsuche", "Ereignis", "", "Basis"),
                new Card(120, 0, "Erbschaft", "Ereignis", "", "Basis"),
                new Card(121, 0, "Training", "Ereignis", "", "Basis"),
                new Card(122, 0, "Versalzenes Land", "Ereignis", "", "Basis"),
                new Card(123, 0, "Steuer", "Ereignis", "", "Basis"),
                new Card(124, 0, "Aufstieg", "Ereignis", "", "Basis"),
                new Card(125, 0, "Überfall", "Ereignis", "", "Basis"),
                new Card(126, 0, "Schlacht", "Ereignis", "", "Basis"),
                new Card(127, 0, "Bankett", "Ereignis", "", "Basis"),
                new Card(128, 0, "Siegeszug", "Ereignis", "", "Basis"),
                new Card(129, 0, "Überfahrt", "Ereignis", "", "Basis"),
                new Card(130, 0, "Zuflucht", "Ereignis", "", "Basis"),
                new Card(131, 0, "Wanderzirkus", "Ereignis", "", "Basis"),
                new Card(132, 0, "Mission", "Ereignis", "", "Basis"),
                new Card(133, 0, "Pilgerfahrt", "Ereignis", "", "Basis"),
                new Card(134, 0, "Planung", "Ereignis", "", "Basis"),
                new Card(135, 0, "Freudenfeuer", "Ereignis", "", "Basis"),
                new Card(136, 0, "Seeweg", "Ereignis", "", "Basis"),
                new Card(137, 0, "Beherrschen", "Ereignis", "", "Basis"),
                new Card(138, 0, "Arena", "Landmarker", "", "Basis"),
                new Card(139, 0, "Obstgarten", "Landmarker", "", "Basis"),
                new Card(140, 0, "Obelisk", "Landmarker", "", "Basis"),
                new Card(141, 0, "Palast", "Landmarker", "", "Basis"),
                new Card(142, 0, "Museum", "Landmarker", "", "Basis"),
                new Card(143, 0, "Gebirgspass", "Landmarker", "", "Basis"),
                new Card(144, 0, "Brunnen", "Landmarker", "", "Basis"),
                new Card(145, 0, "Räuberfestung", "Landmarker", "", "Basis"),
                new Card(146, 0, "Bollwerk", "Landmarker", "", "Basis"),
                new Card(147, 0, "Triumphbogen", "Landmarker", "", "Basis"),
                new Card(148, 0, "Turm", "Landmarker", "", "Basis"),
                new Card(149, 0, "Wolfsbau", "Landmarker", "", "Basis"),
                new Card(150, 0, "Aquädukt", "Landmarker", "", "Basis"),
                new Card(151, 0, "Badehaus", "Landmarker", "", "Basis"),
                new Card(152, 0, "Schlachtfeld", "Landmarker", "", "Basis"),
                new Card(153, 0, "Labyrinth", "Landmarker", "", "Basis"),
                new Card(154, 0, "Entweihter Schrein", "Landmarker", "", "Basis"),
                new Card(155, 0, "Grabmal", "Landmarker", "", "Basis"),
                new Card(156, 0, "Kolonnaden", "Landmarker", "", "Basis"),
                new Card(157, 0, "Basilika", "Landmarker", "", "Basis"),
                new Card(158, 0, "Mauer", "Landmarker", "", "Basis"),
                new Card(159, 0, "Kein Landmarker", "LeererLandmarker", "", "Basis"),
                new Card(160, 0, "Kein Ereignis", "LeeresEreignis", "", "Basis"),

        };
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
