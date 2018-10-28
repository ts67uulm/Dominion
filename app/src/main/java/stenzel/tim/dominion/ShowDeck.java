package stenzel.tim.dominion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import stenzel.tim.dominion.Classes.Card;
import stenzel.tim.dominion.Classes.Erweiterungsset;
import stenzel.tim.dominion.DB.AppDatabase;
import stenzel.tim.dominion.DB.CardDao;

public class ShowDeck extends AppCompatActivity {

    private TextView  textView, landmarkerTV, ereignisTV;

    private Context context;

    private AppDatabase db;
    private CardDao cardDao;

    private ArrayList<Integer> generatedCardIds;
    private List<Card> generatedCards, allCards;

    private Card generatedLandmarker, generatedEreignis;

    private Button btnNewGenerate, btnSaveSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_deck);

        textView = findViewById(R.id.show_deck_textview);
        landmarkerTV = findViewById(R.id.show_deck_landmarker);
        ereignisTV = findViewById(R.id.show_deck_ereignis);
        btnNewGenerate = findViewById(R.id.show_deck_new_btn);
        btnSaveSet = findViewById(R.id.show_deck_save_btn);

        context = ShowDeck.this;

        db = AppDatabase.getAppDatabase(context);
        cardDao = db.getCardDao();

        generatedCardIds = new ArrayList<>();
        generatedCards = new ArrayList<>();
        allCards= new ArrayList<>();

        allCards = cardDao.getAllCards();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");

        int landmarkerId = bundle.getInt("landmarker");
        int ereignisId = bundle.getInt("ereignis");
        generatedCardIds = bundle.getIntegerArrayList("generatedCardIds");

        generatedLandmarker = allCards.get(landmarkerId);
        generatedEreignis = allCards.get(ereignisId);

        for (Card c : allCards){

            if (generatedCardIds.contains(c.getId())){

                generatedCards.add(c);

            }

        }

        landmarkerTV.setText(generatedLandmarker.getName());
        ereignisTV.setText(generatedEreignis.getName());

        String text = "" + generatedCards.get(0).getName() + ", " + generatedCards.get(1).getName()
                + ", " + generatedCards.get(2).getName() + ", " + generatedCards.get(3).getName()
                + ", " + generatedCards.get(4).getName() + ", " + generatedCards.get(5).getName()
                + ", " + generatedCards.get(6).getName() + ", " + generatedCards.get(7).getName()
                + ", " + generatedCards.get(8).getName() + ", " + generatedCards.get(9).getName();

        textView.setText(text);

        /*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });
        */
    }
}
