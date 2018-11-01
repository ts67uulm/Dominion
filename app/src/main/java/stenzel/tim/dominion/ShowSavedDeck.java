package stenzel.tim.dominion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import stenzel.tim.dominion.Classes.CCD;
import stenzel.tim.dominion.Classes.Card;
import stenzel.tim.dominion.Classes.Deck;
import stenzel.tim.dominion.DB.AppDatabase;
import stenzel.tim.dominion.DB.CCDDao;
import stenzel.tim.dominion.DB.CardDao;
import stenzel.tim.dominion.DB.DeckDao;
import stenzel.tim.dominion.ListAdapter.ListAdapterShowDeck;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShowSavedDeck extends AppCompatActivity {

    private Context context;

    private TextView name, morePlayers, category, landmarker, ereignis;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private AppDatabase db;
    private DeckDao deckDao;
    private CCDDao ccdDao;
    private CardDao cardDao;

    private Deck chosenDeck;
    private CCD ccd;
    private Card landmarkerInDeck, ereignisInDeck;

    private List<Card> allCards, cardsInDeck;

    private int deckId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_saved_deck);

        context = ShowSavedDeck.this;

        db = AppDatabase.getAppDatabase(context);
        deckDao = db.getDeckDao();
        ccdDao = db.getCCDDao();
        cardDao = db.getCardDao();

        name = findViewById(R.id.show_saved_deck_name);
        morePlayers = findViewById(R.id.show_saved_deck_morePlayers);
        category = findViewById(R.id.show_saved_deck_category);
        landmarker = findViewById(R.id.show_saved_deck_landmarker);
        ereignis = findViewById(R.id.show_saved_deck_ereignis);
        mRecyclerView = findViewById(R.id.show_saved_deck_recyclerview);

        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        cardsInDeck = new ArrayList<>();

        Intent intent = getIntent();

        deckId = intent.getIntExtra("deckId", -1);

        if (deckId != -1){

            chosenDeck = deckDao.getDeckById(deckId);

            ccd = ccdDao.getCCDByDeckId(deckId);

            allCards = cardDao.getAllCards();

            int tmpCardId = ccd.getCard0();
            cardsInDeck.add(allCards.get(tmpCardId));
            tmpCardId = ccd.getCard1();
            cardsInDeck.add(allCards.get(tmpCardId));
            tmpCardId = ccd.getCard2();
            cardsInDeck.add(allCards.get(tmpCardId));
            tmpCardId = ccd.getCard3();
            cardsInDeck.add(allCards.get(tmpCardId));
            tmpCardId = ccd.getCard4();
            cardsInDeck.add(allCards.get(tmpCardId));
            tmpCardId = ccd.getCard5();
            cardsInDeck.add(allCards.get(tmpCardId));
            tmpCardId = ccd.getCard6();
            cardsInDeck.add(allCards.get(tmpCardId));
            tmpCardId = ccd.getCard7();
            cardsInDeck.add(allCards.get(tmpCardId));
            tmpCardId = ccd.getCard8();
            cardsInDeck.add(allCards.get(tmpCardId));
            tmpCardId = ccd.getCard9();
            cardsInDeck.add(allCards.get(tmpCardId));

            landmarkerInDeck = allCards.get(ccd.getLandmarker());
            ereignisInDeck = allCards.get(ccd.getEreignis());

            //set all data in view
            name.setText(chosenDeck.getName());

            if (chosenDeck.isMorePlayers()){
                morePlayers.setText("Für mehr als 4 Spieler geeignet.");
            } else {
                morePlayers.setText("Nicht für mehr als 4 Spieler geeignet.");
            }

            category.setText(chosenDeck.getCategory());

            landmarker.setText(landmarkerInDeck.getName());

            ereignis.setText(ereignisInDeck.getName());

            mAdapter = new ListAdapterShowDeck(context, cardsInDeck);
            mRecyclerView.setAdapter(mAdapter);

        }
    }

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
