package stenzel.tim.dominion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import stenzel.tim.dominion.AlertDialogs.AlertDialogSaveDeck;
import stenzel.tim.dominion.Classes.CCD;
import stenzel.tim.dominion.Classes.Card;
import stenzel.tim.dominion.Classes.Deck;
import stenzel.tim.dominion.Classes.Kurvenmodell;
import stenzel.tim.dominion.DB.AppDatabase;
import stenzel.tim.dominion.DB.CCDDao;
import stenzel.tim.dominion.DB.CardDao;
import stenzel.tim.dominion.DB.DeckDao;
import stenzel.tim.dominion.DB.KurvenmodellDao;
import stenzel.tim.dominion.ListAdapter.ListAdapterShow;

public class ShowDeck extends AppCompatActivity {

    private TextView  textView, landmarkerTV, ereignisTV, kurvenTV;

    private Context context;

    private AppDatabase db;
    private CardDao cardDao;
    private KurvenmodellDao curveDao;
    private DeckDao deckDao;
    private CCDDao ccdDao;

    private ArrayList<Integer> generatedCardIds;
    private List<Card> generatedCards, allCards;
    private List<Kurvenmodell> allCurves;

    private Card generatedLandmarker, generatedEreignis;
    private Kurvenmodell generatedKurvenmodell;

    ListAdapterShow mAdapter;

    RecyclerView mRecyclerView;

    RecyclerView.LayoutManager mLayoutManager;

    private Button btnNewGenerate, btnSaveSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_deck);

        textView = findViewById(R.id.show_deck_textview);
        landmarkerTV = findViewById(R.id.show_deck_landmarker);
        ereignisTV = findViewById(R.id.show_deck_ereignis);
        kurvenTV = findViewById(R.id.show_deck_kurve);
        btnNewGenerate = findViewById(R.id.show_deck_new_btn);
        btnSaveSet = findViewById(R.id.show_deck_save_btn);
        mRecyclerView = findViewById(R.id.show_deck_recycler_view);

        context = ShowDeck.this;

        db = AppDatabase.getAppDatabase(context);
        cardDao = db.getCardDao();
        curveDao = db.getKurvenmodellDao();
        deckDao = db.getDeckDao();
        ccdDao = db.getCCDDao();

        generatedCardIds = new ArrayList<>();
        generatedCards = new ArrayList<>();
        allCards = new ArrayList<>();
        allCurves = new ArrayList<>();

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        allCards = cardDao.getAllCards();
        allCurves = curveDao.getAllKurvenmodelle();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");

        int landmarkerId = bundle.getInt("landmarker");
        int ereignisId = bundle.getInt("ereignis");
        final int kurvenId = bundle.getInt("kurvenmodell");
        generatedCardIds = bundle.getIntegerArrayList("generatedCardIds");

        generatedLandmarker = allCards.get(landmarkerId);
        generatedEreignis = allCards.get(ereignisId);
        generatedKurvenmodell = allCurves.get(kurvenId);

        for (Integer i : generatedCardIds){

            generatedCards.add(allCards.get(i));

        }

        landmarkerTV.setText(generatedLandmarker.getName());
        ereignisTV.setText(generatedEreignis.getName());

        String kurvenText = "Kosten2: " + generatedKurvenmodell.getCost2()
                + ", Kosten3: " + generatedKurvenmodell.getCost3()
                + ", Kosten4: " + generatedKurvenmodell.getCost4()
                + ", Kosten5: " + generatedKurvenmodell.getCost5()
                + ", Kosten6: " + generatedKurvenmodell.getCost6()
                + ", Kosten7: " + generatedKurvenmodell.getCost7()
                + ", Kosten8+: " + generatedKurvenmodell.getCost8Plus();

        kurvenTV.setText("Kurvenmodell: " + kurvenText);

        mAdapter = new ListAdapterShow(context, generatedCards);
        mRecyclerView.setAdapter(mAdapter);

        /*
        String text = "" + generatedCards.get(0).getName() + ", " + generatedCards.get(1).getName()
                + ", " + generatedCards.get(2).getName() + ", " + generatedCards.get(3).getName()
                + ", " + generatedCards.get(4).getName() + ", " + generatedCards.get(5).getName()
                + ", " + generatedCards.get(6).getName() + ", " + generatedCards.get(7).getName()
                + ", " + generatedCards.get(8).getName() + ", " + generatedCards.get(9).getName();
                */

        btnNewGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

        btnSaveSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, AlertDialogSaveDeck.class);
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0) {
            if(resultCode == Activity.RESULT_OK){
                String name = data.getStringExtra("name");
                boolean morePlayers = data.getBooleanExtra("morePlayers", false);
                String category = data.getStringExtra("category");

                int id = generatedKurvenmodell.getId();

                deckDao.insertElement(new Deck(0, name, category, morePlayers, id));

                Toast.makeText(context, "Deck gespeichert", Toast.LENGTH_SHORT);

                int deckId = deckDao.getDeckId(name, morePlayers, category, id);

                ccdDao.insertElement(new CCD(deckId, generatedCards.get(0).getId(),
                        generatedCards.get(1).getId(), generatedCards.get(2).getId(),
                        generatedCards.get(3).getId(), generatedCards.get(4).getId(),
                        generatedCards.get(5).getId(), generatedCards.get(6).getId(),
                        generatedCards.get(7).getId(), generatedCards.get(8).getId(),
                        generatedCards.get(9).getId(), generatedLandmarker.getId(),
                        generatedEreignis.getId()));

                Toast.makeText(context, "Gespeichert", Toast.LENGTH_SHORT);
            }
            if (resultCode == Activity.RESULT_CANCELED) {

            }
        }
    }//onActivityResult

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar, menu);

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

        }

        return super.onOptionsItemSelected(item);
    }

}
