package stenzel.tim.dominion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import stenzel.tim.dominion.AlertDialogs.AddCard;
import stenzel.tim.dominion.Classes.Card;
import stenzel.tim.dominion.DB.AppDatabase;
import stenzel.tim.dominion.DB.CardDao;
import stenzel.tim.dominion.ListAdapter.ListAdapterErweiterungsset;
import stenzel.tim.dominion.ListAdapter.ListAdapterShowAllCards;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class ShowCardsActivity extends AppCompatActivity {

    private Context context;

    private AppDatabase db;
    private CardDao cardDao;

    private List<Card> allCards;

    private com.google.android.material.floatingactionbutton.FloatingActionButton fab;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cards);

        context = ShowCardsActivity.this;

        getSupportActionBar().setTitle("Gallerie - Karten");

        db = AppDatabase.getAppDatabase(context);
        cardDao = db.getCardDao();

        allCards = cardDao.getAllCards();

        mRecyclerView = findViewById(R.id.show_all_cards_recyclerview);
        fab = findViewById(R.id.show_all_cards_fab);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ListAdapterShowAllCards(context, allCards);
        mRecyclerView.setAdapter(mAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, AddCard.class);
                startActivityForResult(intent, 0);

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0) {
            if(resultCode == Activity.RESULT_OK){
                allCards = cardDao.getAllCards();
                mAdapter = new ListAdapterShowAllCards(context, allCards);
                mRecyclerView.setAdapter(mAdapter);
            }

        }
    }
}
