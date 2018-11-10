package stenzel.tim.dominion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import stenzel.tim.dominion.Classes.Card;
import stenzel.tim.dominion.Classes.Erweiterungsset;
import stenzel.tim.dominion.DB.AppDatabase;
import stenzel.tim.dominion.DB.CardDao;
import stenzel.tim.dominion.DB.ErweiterungssetDao;
import stenzel.tim.dominion.ListAdapter.ListAdapterErweiterungsset;

public class MainActivity extends AppCompatActivity {

    List<Erweiterungsset> erweiterungssets;
    List<Integer> selectedIds;
    List<Card> cards;

    AppDatabase db;
    ErweiterungssetDao ewDao;
    CardDao cardDao;

    Button next, selectAll, deselectAll;

    Context context;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        Stetho.initializeWithDefaults(context);

        db = AppDatabase.getAppDatabase(context);
        ewDao = db.getErweiterungssetDao();
        cardDao = db.getCardDao();

        next = findViewById(R.id.main_next_btn);
        selectAll = findViewById(R.id.main_selectAll);
        deselectAll = findViewById(R.id.main_deselectAll);
        mRecyclerView = findViewById(R.id.main_recycler_view);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        selectedIds = new ArrayList<>();
        erweiterungssets = new ArrayList<>();
        cards = new ArrayList<>();

        erweiterungssets = ewDao.getAllErweiterungssets();
        cards = cardDao.getAllCards();

        //setze alle erweiterungssets und karten auf false
        erweiterungssets = getModel(false);

        for (Card c : cards){

            cardDao.updateCardChecked(c.getId(), false);

        }

        mAdapter = new ListAdapterErweiterungsset(context, erweiterungssets);
        mRecyclerView.setAdapter(mAdapter);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean test = false;

                for (Erweiterungsset e : erweiterungssets){

                    if (e.isChecked()){

                        if (test == false){

                            test = true;

                        }
                    }
                }

                if (test){
                    Intent intent = new Intent(context, ChooseCards.class);
                    startActivity(intent);
                } else {

                    Toast.makeText(context, "Sei nicht dumm", Toast.LENGTH_SHORT);

                }

            }
        });

        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                erweiterungssets = getModel(true);
                mAdapter = new ListAdapterErweiterungsset(context, erweiterungssets);
                mRecyclerView.setAdapter(mAdapter);
            }
        });

        deselectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                erweiterungssets = getModel(false);
                mAdapter = new ListAdapterErweiterungsset(context, erweiterungssets);
                mRecyclerView.setAdapter(mAdapter);
            }
        });
    }

    private ArrayList<Erweiterungsset> getModel(boolean selected) {

        ArrayList<Erweiterungsset> list = new ArrayList<>();

        for (int i = 0; i < erweiterungssets.size(); i++) {

            Erweiterungsset d = erweiterungssets.get(i);
            d.setChecked(selected);
            ewDao.updateEwChecked(erweiterungssets.get(i).getId(), selected);

            list.add(d);

        }

        return list;

    }

    @Override
    public void onResume(){
        super.onResume();

        erweiterungssets = ewDao.getAllErweiterungssets();

        erweiterungssets = getModel(false);
        mAdapter = new ListAdapterErweiterungsset(context, erweiterungssets);
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar_plus, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_preset:
                Intent intent = new Intent(context, ShowSavedDecks.class);
                startActivity(intent);
                return true;
            case R.id.action_home:
                intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_add:
                intent = new Intent(context, GalleryActivity.class);
                startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
}

    /**
    ewDao.insertElement(new Erweiterungsset(0, "Basis"));
    ewDao.insertElement(new Erweiterungsset(1, "Abenteuer"));
    ewDao.insertElement(new Erweiterungsset(2, "Intrige"));
    ewDao.insertElement(new Erweiterungsset(3, "Empires"));

    cardDao.insertElement(new Card(0, 2, "Burggraben", "Abwehr", "","Basis"));
    cardDao.insertElement(new Card(1, 2, "Kapelle", "", "","Basis"));
    cardDao.insertElement(new Card(2, 2, "Keller", "", "","Basis"));
    cardDao.insertElement(new Card(3, 3, "Dorf", "", "","Basis"));
    cardDao.insertElement(new Card(4, 3, "Händlerin", "", "","Basis"));
    cardDao.insertElement(new Card(5, 3, "Vasall", "", "","Basis"));
    cardDao.insertElement(new Card(6, 3, "Vorbotin", "", "","Basis"));
    cardDao.insertElement(new Card(7, 3, "Werkstatt", "", "","Basis"));
    cardDao.insertElement(new Card(8, 4, "Bürokrat", "", "","Basis"));
    cardDao.insertElement(new Card(9, 4, "Geldverleiher", "", "","Basis"));
    cardDao.insertElement(new Card(10, 4, "Miliz", "", "","Basis"));
    cardDao.insertElement(new Card(11, 4, "Schmiede", "", "","Basis"));
    cardDao.insertElement(new Card(12, 4, "Thronsaal", "", "","Basis"));
    cardDao.insertElement(new Card(13, 4, "Umbau", "", "","Basis"));
    cardDao.insertElement(new Card(14, 4, "Wilddiebin", "", "","Basis"));
    cardDao.insertElement(new Card(15, 5, "Banditin", "", "","Basis"));
    cardDao.insertElement(new Card(16, 5, "Bibliothek", "", "","Basis"));
    cardDao.insertElement(new Card(17, 5, "Hexe", "", "","Basis"));
    cardDao.insertElement(new Card(18, 5, "Jahrmarkt", "", "","Basis"));
    cardDao.insertElement(new Card(19, 5, "Laboratorium", "", "","Basis"));
    cardDao.insertElement(new Card(20, 5, "Markt", "", "","Basis"));
    cardDao.insertElement(new Card(21, 5, "Mine", "", "","Basis"));
    cardDao.insertElement(new Card(22, 5, "Ratsversammlung", "","", "Basis"));
    cardDao.insertElement(new Card(23, 5, "Torwächterin", "", "","Basis"));
    cardDao.insertElement(new Card(24, 6, "Töpferin", "", "","Basis"));
    cardDao.insertElement(new Card(25, 4, "Gärten", "", "","Basis"));
    cardDao.insertElement(new Card(26, 2, "Kleinbauer", "", "","Abenteuer"));
    cardDao.insertElement(new Card(27, 2, "Königliche Münze", "", "","Abenteuer"));
    cardDao.insertElement(new Card(28, 2, "Page", "", "","Abenteuer"));
    cardDao.insertElement(new Card(29, 2, "Rattenfänger", "", "","Abenteuer"));
    cardDao.insertElement(new Card(30, 2, "Zerstörung", "", "","Abenteuer"));
    cardDao.insertElement(new Card(31, 3, "Amulett", "", "","Abenteuer"));
    cardDao.insertElement(new Card(32, 3, "Ausrüstung", "", "","Abenteuer"));
    cardDao.insertElement(new Card(33, 3, "Karawanenwächter", "", "","Abenteuer"));
    cardDao.insertElement(new Card(34, 3, "Kundschafter", "", "","Abenteuer"));
    cardDao.insertElement(new Card(35, 3, "Verlies", "", "","Abenteuer"));
    cardDao.insertElement(new Card(36, 4, "Duplikat", "", "","Abenteuer"));
    cardDao.insertElement(new Card(37, 4, "Elster", "", "","Abenteuer"));
    cardDao.insertElement(new Card(38, 4, "Geizhals", "", "","Abenteuer"));
    cardDao.insertElement(new Card(39, 4, "Hafenstadt", "", "","Abenteuer"));
    cardDao.insertElement(new Card(40, 4, "Kurier", "", "","Abenteuer"));
    cardDao.insertElement(new Card(41, 4, "Transformation", "", "","Abenteuer"));
    cardDao.insertElement(new Card(42, 4, "Wildhüterin", "", "","Abenteuer"));
    cardDao.insertElement(new Card(43, 5, "Brückentroll", "", "","Abenteuer"));
    cardDao.insertElement(new Card(44, 5, "Ferne Lande", "", "","Abenteuer"));
    cardDao.insertElement(new Card(45, 5, "Geisterwald", "", "","Abenteuer"));
    cardDao.insertElement(new Card(46, 5, "Geschichtenerzähler", "", "","Abenteuer"));
    cardDao.insertElement(new Card(47, 5, "Kunsthandwerk", "", "","Abenteuer"));
    cardDao.insertElement(new Card(48, 5, "Königliche Kutsche", "", "","Abenteuer"));
    cardDao.insertElement(new Card(49, 5, "Relikt", "", "","Abenteuer"));
    cardDao.insertElement(new Card(50, 5, "Riese", "", "","Abenteuer"));
    cardDao.insertElement(new Card(51, 5, "Schatz", "", "","Abenteuer"));
    cardDao.insertElement(new Card(52, 5, "Sumpfhexe", "", "","Abenteuer"));
    cardDao.insertElement(new Card(53, 5, "Verlorene Stadt", "", "","Abenteuer"));
    cardDao.insertElement(new Card(54, 5, "Weinhändler", "", "","Abenteuer"));
    cardDao.insertElement(new Card(55, 6, "Gefolgsmann", "", "","Abenteuer"));
    cardDao.insertElement(new Card(56, 2, "Burghof", "", "","Intrige"));
    cardDao.insertElement(new Card(57, 2, "Geheimkammer", "", "","Intrige"));
    cardDao.insertElement(new Card(58, 2, "Handlanger", "", "","Intrige"));
    cardDao.insertElement(new Card(59, 3, "Armenviertel", "", "","Intrige"));
    cardDao.insertElement(new Card(60, 3, "Grosse Halle", "", "","Intrige"));
    cardDao.insertElement(new Card(61, 3, "Maskerade", "", "","Intrige"));
    cardDao.insertElement(new Card(62, 3, "Trickser", "", "","Intrige"));
    cardDao.insertElement(new Card(63, 3, "Verwalter", "", "","Intrige"));
    cardDao.insertElement(new Card(64, 3, "Wunschbrunnen", "", "","Intrige"));
    cardDao.insertElement(new Card(65, 4, "Baron", "", "","Intrige"));
    cardDao.insertElement(new Card(66, 4, "Bergwerk", "", "","Intrige"));
    cardDao.insertElement(new Card(67, 4, "Brücke", "", "","Intrige"));
    cardDao.insertElement(new Card(68, 4, "Eisenhütte", "", "","Intrige"));
    cardDao.insertElement(new Card(69, 4, "Kupferschmied", "", "","Intrige"));
    cardDao.insertElement(new Card(70, 4, "Späher", "", "","Intrige"));
    cardDao.insertElement(new Card(71, 4, "Verschwörer", "", "","Intrige"));
    cardDao.insertElement(new Card(72, 5, "Anbau", "", "","Intrige"));
    cardDao.insertElement(new Card(73, 5, "Handelsposten", "", "","Intrige"));
    cardDao.insertElement(new Card(74, 5, "Herzog", "", "","Intrige"));
    cardDao.insertElement(new Card(75, 5, "Kerkermeister", "", "","Intrige"));
    cardDao.insertElement(new Card(76, 5, "Lakai", "", "","Intrige"));
    cardDao.insertElement(new Card(77, 5, "Saboteur", "", "","Intrige"));
    cardDao.insertElement(new Card(78, 5, "Tribut", "", "","Intrige"));
    cardDao.insertElement(new Card(79, 6, "Adlige", "", "","Intrige"));
    cardDao.insertElement(new Card(80, 6, "Harem", "", "","Intrige"));
    cardDao.insertElement(new Card(81, 3, "Bauernmarkt", "", "","Empires"));
    cardDao.insertElement(new Card(82, 3, "Wagenrennen", "", "","Empires"));
    cardDao.insertElement(new Card(83, 3, "Zauberin", "", "","Empires"));
    cardDao.insertElement(new Card(84, 4, "Ingenieurin", "", "","Empires"));
    cardDao.insertElement(new Card(85, 4, "Opfer", "", "","Empires"));
    cardDao.insertElement(new Card(86, 4, "Tempel", "", "","Empires"));
    cardDao.insertElement(new Card(87, 4, "Villa", "", "","Empires"));
    cardDao.insertElement(new Card(88, 5, "Archiv", "", "","Empires"));
    cardDao.insertElement(new Card(89, 5, "Forum", "", "","Empires"));
    cardDao.insertElement(new Card(90, 5, "Gärtnerin", "", "","Empires"));
    cardDao.insertElement(new Card(91, 5, "Krone", "", "","Empires"));
    cardDao.insertElement(new Card(92, 5, "Legionär", "", "","Empires"));
    cardDao.insertElement(new Card(93, 5, "Vermögen", "", "","Empires"));
    cardDao.insertElement(new Card(94, 5, "Wilde Jagd", "", "","Empires"));
    cardDao.insertElement(new Card(95, 5, "Zauber", "", "","Empires"));
    cardDao.insertElement(new Card(96, 8, "Königlicher Schmied", "", "","Empires"));
    cardDao.insertElement(new Card(97, 8, "Lehnsherr", "", "","Empires"));
    cardDao.insertElement(new Card(98, 8, "Stadtviertel", "", "","Empires"));
    cardDao.insertElement(new Card(99, 0, "Feldlager/Diebesgut", "", "","Empires"));
    cardDao.insertElement(new Card(100, 0, "Gladiator/Reichtum", "", "","Empires"));
    cardDao.insertElement(new Card(101, 0, "Katapult/Felsen", "", "","Empires"));
    cardDao.insertElement(new Card(102, 0, "Patrizier/Handelsplatz", "", "","Empires"));
    cardDao.insertElement(new Card(103, 0, "Siedler/Emsiges Dorf", "", "","Empires"));
    cardDao.insertElement(new Card(104, 0, "Schlösser", "", "","Empires"));
    */

