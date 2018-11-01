package stenzel.tim.dominion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import stenzel.tim.dominion.Classes.Deck;
import stenzel.tim.dominion.DB.AppDatabase;
import stenzel.tim.dominion.DB.DeckDao;
import stenzel.tim.dominion.ListAdapter.ListAdapterDecks;
import stenzel.tim.dominion.ListAdapter.ListAdapterErweiterungsset;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

public class ShowSavedDecks extends AppCompatActivity {

    private AppDatabase db;
    private DeckDao deckDao;

    private Context context;

    private List<Deck> allDecks;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_saved_decks);

        context = ShowSavedDecks.this;

        db = AppDatabase.getAppDatabase(context);
        deckDao = db.getDeckDao();

        allDecks = deckDao.getAllDecks();

        mRecyclerView = findViewById(R.id.show_saved_decks_recyclerview);
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ListAdapterDecks(context, allDecks);
        mRecyclerView.setAdapter(mAdapter);
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
