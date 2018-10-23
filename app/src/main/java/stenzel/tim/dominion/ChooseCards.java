package stenzel.tim.dominion;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ChooseCards extends AppCompatActivity {

    ArrayList<Deck> decks;
    ArrayList<Card> possibleCards, cards;
    ArrayList<Integer> selectedDeckIds, selectedCardIds, randomNumbers, generatedCardIds;
    ArrayList<String> checkboxNames;

    Button selectAll, deselectAll, generateBtn;

    ListAdapterCards mAdapter;

    RecyclerView mRecyclerView;

    RecyclerView.LayoutManager mLayoutManager;

    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_cards);

        context = ChooseCards.this;

        decks = new ArrayList<>();
        cards = new ArrayList<>();
        selectedDeckIds = new ArrayList<>();
        possibleCards = new ArrayList<>();
        selectedCardIds = new ArrayList<>();
        randomNumbers = new ArrayList<>();
        generatedCardIds = new ArrayList<>();
        checkboxNames = new ArrayList<>();

        generateBtn = findViewById(R.id.choose_cards_generate);
        mRecyclerView = findViewById(R.id.choose_cards_recycler_view);
        selectAll = findViewById(R.id.choose_cards_selectAll);
        deselectAll = findViewById(R.id.choose_cards_deselectAll);
        generateBtn = findViewById(R.id.choose_cards_generate);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("bundle");
        selectedDeckIds = bundle.getIntegerArrayList("selectedIds");

        decks.add(new Deck(0, "Basis"));
        decks.add(new Deck(1, "Abenteuer"));
        decks.add(new Deck(2, "Intrige"));
        decks.add(new Deck(3, "Empires"));

        for (int i = 0; i < decks.size(); i++){

            if (selectedDeckIds.contains(i)){
                decks.get(i).setChecked(true);
            } else {
                decks.get(i).setChecked(false);
            }
        }

        cards.add(new Card(0, 2, "Burggraben", "Abwehr", "Basis"));
        cards.add(new Card(1, 2, "Kapelle", "", "Basis"));
        cards.add(new Card(2, 2, "Keller", "", "Basis"));
        cards.add(new Card(3, 3, "Dorf", "", "Basis"));
        cards.add(new Card(4, 3, "Händlerin", "", "Basis"));
        cards.add(new Card(5, 3, "Vasall", "", "Basis"));
        cards.add(new Card(6, 3, "Vorbotin", "", "Basis"));
        cards.add(new Card(7, 3, "Werkstatt", "", "Basis"));
        cards.add(new Card(8, 4, "Bürokrat", "", "Basis"));
        cards.add(new Card(9, 4, "Geldverleiher", "", "Basis"));
        cards.add(new Card(10, 4, "Miliz", "", "Basis"));
        cards.add(new Card(11, 4, "Schmiede", "", "Basis"));
        cards.add(new Card(12, 4, "Thronsaal", "", "Basis"));
        cards.add(new Card(13, 4, "Umbau", "", "Basis"));
        cards.add(new Card(14, 4, "Wilddiebin", "", "Basis"));
        cards.add(new Card(15, 5, "Banditin", "", "Basis"));
        cards.add(new Card(16, 5, "Bibliothek", "", "Basis"));
        cards.add(new Card(17, 5, "Hexe", "", "Basis"));
        cards.add(new Card(18, 5, "Jahrmarkt", "", "Basis"));
        cards.add(new Card(19, 5, "Laboratorium", "", "Basis"));
        cards.add(new Card(20, 5, "Markt", "", "Basis"));
        cards.add(new Card(21, 5, "Mine", "", "Basis"));
        cards.add(new Card(22, 5, "Ratsversammlung", "", "Basis"));
        cards.add(new Card(23, 5, "Torwächterin", "", "Basis"));
        cards.add(new Card(24, 6, "Töpferin", "", "Basis"));
        cards.add(new Card(25, 4, "Gärten", "", "Basis"));
        cards.add(new Card(26, 2, "Kleinbauer", "", "Abenteuer"));
        cards.add(new Card(27, 2, "Königliche Münze", "", "Abenteuer"));
        cards.add(new Card(28, 2, "Page", "", "Abenteuer"));
        cards.add(new Card(29, 2, "Rattenfänger", "", "Abenteuer"));
        cards.add(new Card(30, 2, "Zerstörung", "", "Abenteuer"));
        cards.add(new Card(31, 3, "Amulett", "", "Abenteuer"));
        cards.add(new Card(32, 3, "Ausrüstung", "", "Abenteuer"));
        cards.add(new Card(33, 3, "Karawanenwächter", "", "Abenteuer"));
        cards.add(new Card(34, 3, "Kundschafter", "", "Abenteuer"));
        cards.add(new Card(35, 3, "Verlies", "", "Abenteuer"));
        cards.add(new Card(36, 4, "Duplikat", "", "Abenteuer"));
        cards.add(new Card(37, 4, "Elster", "", "Abenteuer"));
        cards.add(new Card(38, 4, "Geizhals", "", "Abenteuer"));
        cards.add(new Card(39, 4, "Hafenstadt", "", "Abenteuer"));
        cards.add(new Card(40, 4, "Kurier", "", "Abenteuer"));
        cards.add(new Card(41, 4, "Transformation", "", "Abenteuer"));
        cards.add(new Card(42, 4, "Wildhüterin", "", "Abenteuer"));
        cards.add(new Card(43, 5, "Brückentroll", "", "Abenteuer"));
        cards.add(new Card(44, 5, "Ferne Lande", "", "Abenteuer"));
        cards.add(new Card(45, 5, "Geisterwald", "", "Abenteuer"));
        cards.add(new Card(46, 5, "Geschichtenerzähler", "", "Abenteuer"));
        cards.add(new Card(47, 5, "Kunsthandwerk", "", "Abenteuer"));
        cards.add(new Card(48, 5, "Königliche Kutsche", "", "Abenteuer"));
        cards.add(new Card(49, 5, "Relikt", "", "Abenteuer"));
        cards.add(new Card(50, 5, "Riese", "", "Abenteuer"));
        cards.add(new Card(51, 5, "Schatz", "", "Abenteuer"));
        cards.add(new Card(52, 5, "Sumpfhexe", "", "Abenteuer"));
        cards.add(new Card(53, 5, "Verlorene Stadt", "", "Abenteuer"));
        cards.add(new Card(54, 5, "Weinhändler", "", "Abenteuer"));
        cards.add(new Card(55, 6, "Gefolgsmann", "", "Abenteuer"));
        cards.add(new Card(56, 2, "Burghof", "", "Intrige"));
        cards.add(new Card(57, 2, "Geheimkammer", "", "Intrige"));
        cards.add(new Card(58, 2, "Handlanger", "", "Intrige"));
        cards.add(new Card(59, 3, "Armenviertel", "", "Intrige"));
        cards.add(new Card(60, 3, "Grosse Halle", "", "Intrige"));
        cards.add(new Card(61, 3, "Maskerade", "", "Intrige"));
        cards.add(new Card(62, 3, "Trickser", "", "Intrige"));
        cards.add(new Card(63, 3, "Verwalter", "", "Intrige"));
        cards.add(new Card(64, 3, "Wunschbrunnen", "", "Intrige"));
        cards.add(new Card(65, 4, "Baron", "", "Intrige"));
        cards.add(new Card(66, 4, "Bergwerk", "", "Intrige"));
        cards.add(new Card(67, 4, "Brücke", "", "Intrige"));
        cards.add(new Card(68, 4, "Eisenhütte", "", "Intrige"));
        cards.add(new Card(69, 4, "Kupferschmied", "", "Intrige"));
        cards.add(new Card(70, 4, "Späher", "", "Intrige"));
        cards.add(new Card(71, 4, "Verschwörer", "", "Intrige"));
        cards.add(new Card(72, 5, "Anbau", "", "Intrige"));
        cards.add(new Card(73, 5, "Handelsposten", "", "Intrige"));
        cards.add(new Card(74, 5, "Herzog", "", "Intrige"));
        cards.add(new Card(75, 5, "Kerkermeister", "", "Intrige"));
        cards.add(new Card(76, 5, "Lakai", "", "Intrige"));
        cards.add(new Card(77, 5, "Saboteur", "", "Intrige"));
        cards.add(new Card(78, 5, "Tribut", "", "Intrige"));
        cards.add(new Card(79, 6, "Adlige", "", "Intrige"));
        cards.add(new Card(80, 6, "Harem", "", "Intrige"));
        cards.add(new Card(81, 3, "Bauernmarkt", "", "Empires"));
        cards.add(new Card(82, 3, "Wagenrennen", "", "Empires"));
        cards.add(new Card(83, 3, "Zauberin", "", "Empires"));
        cards.add(new Card(84, 4, "Ingenieurin", "", "Empires"));
        cards.add(new Card(85, 4, "Opfer", "", "Empires"));
        cards.add(new Card(86, 4, "Tempel", "", "Empires"));
        cards.add(new Card(87, 4, "Villa", "", "Empires"));
        cards.add(new Card(88, 5, "Archiv", "", "Empires"));
        cards.add(new Card(89, 5, "Forum", "", "Empires"));
        cards.add(new Card(90, 5, "Gärtnerin", "", "Empires"));
        cards.add(new Card(91, 5, "Krone", "", "Empires"));
        cards.add(new Card(92, 5, "Legionär", "", "Empires"));
        cards.add(new Card(93, 5, "Vermögen", "", "Empires"));
        cards.add(new Card(94, 5, "Wilde Jagd", "", "Empires"));
        cards.add(new Card(95, 5, "Zauber", "", "Empires"));
        cards.add(new Card(96, 8, "Königlicher Schmied", "", "Empires"));
        cards.add(new Card(97, 8, "Lehnsherr", "", "Empires"));
        cards.add(new Card(98, 8, "Stadtviertel", "", "Empires"));
        cards.add(new Card(99, 0, "Feldlager/Diebesgut", "", "Empires"));
        cards.add(new Card(100, 0, "Gladiator/Reichtum", "", "Empires"));
        cards.add(new Card(101, 0, "Katapult/Felsen", "", "Empires"));
        cards.add(new Card(102, 0, "Patrizier/Handelsplatz", "", "Empires"));
        cards.add(new Card(103, 0, "Siedler/Emsiges Dorf", "", "Empires"));
        cards.add(new Card(104, 0, "Schlösser", "", "Empires"));


        //------------hier unten nichts ändern -------------------------

        for (Card c : cards){

            for (Deck d : decks){

                if (d.isChecked() && c.getDeck().equals(d.getName())){

                    possibleCards.add(c);

                }
            }
        }

        possibleCards = getModel(false);

        mAdapter = new ListAdapterCards(context, possibleCards);
        mRecyclerView.setAdapter(mAdapter);



        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                possibleCards = getModel(true);
                mAdapter = new ListAdapterCards(context, possibleCards);
                mRecyclerView.setAdapter(mAdapter);
            }
        });

        deselectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                possibleCards = getModel(false);
                mAdapter = new ListAdapterCards(context, possibleCards);
                mRecyclerView.setAdapter(mAdapter);
            }
        });

        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (Card c : possibleCards){

                    if (c.isChecked()){

                        selectedCardIds.add(c.getId());

                    }

                }


                if (selectedCardIds.size() < 10) {

                    Toast.makeText(context, "Sei nicht dumm.", Toast.LENGTH_SHORT);

                } else if (selectedCardIds.size() == 10) {

                    //alle ausgewählten spielen mit
                    /*
                    Bundle bundle = new Bundle();
                    bundle.putIntegerArrayList("generatedCardIds", selectedCardIds);

                    Intent intent = new Intent(context, ShowDeck.class);
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                     */
                } else {

                    //generate 10 random numbers between 0 and selectedCardIds.size()-1
                    generateRNG(selectedCardIds, randomNumbers);
                    randomNumbers.clear();
                }

            }
        });
    }

    private ArrayList<Card> getModel(boolean selected){

        ArrayList<Card> list = new ArrayList<>();

        for (int i = 0; i < possibleCards.size(); i++){

            Card c = possibleCards.get(i);
            c.setChecked(selected);

            list.add(c);

        }

        return list;

    }

    private void generateRNG(ArrayList<Integer> cardIds, ArrayList<Integer> randomNumbers) {

        int min = cardIds.get(0);
        int max = cardIds.get(cardIds.size() - 1);

        Random r = new Random();
        int r1 = r.nextInt(max - min) + min;

        if (!randomNumbers.contains(r1) && cardIds.contains(r1)) {
            randomNumbers.add(r1);

            if (randomNumbers.size() == 10) {

                Collections.sort(randomNumbers);

                Log.d("10 Randoms: ","" + randomNumbers.get(0) + ", "
                        + randomNumbers.get(1) + ", "+ randomNumbers.get(2) + ", "
                        + randomNumbers.get(3) + ", " + randomNumbers.get(4) + ", "
                        + randomNumbers.get(5) + ", " + randomNumbers.get(6) + ", "
                        + randomNumbers.get(7) + ", " + randomNumbers.get(8) + ", "
                        + randomNumbers.get(9));

                /*
                Bundle bundle = new Bundle();
                bundle.putIntegerArrayList("generatedCardIds", randomNumbers);

                Intent intent = new Intent(context, ShowDeck.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
                */

            } else {
                generateRNG(cardIds, randomNumbers);
            }

        } else {
            generateRNG(cardIds, randomNumbers);
        }


    }


}
