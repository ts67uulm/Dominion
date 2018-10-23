package stenzel.tim.dominion;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ArrayList<Deck> decks;
    ArrayList<Integer> selectedIds;

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

        next = findViewById(R.id.main_next_btn);
        selectAll = findViewById(R.id.main_selectAll);
        deselectAll = findViewById(R.id.main_deselectAll);
        mRecyclerView = findViewById(R.id.main_recycler_view);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        selectedIds = new ArrayList<>();
        decks = new ArrayList<>();

        decks.add(new Deck(0, "Basis"));
        decks.add(new Deck(1, "Abenteuer"));
        decks.add(new Deck(2, "Intrige"));
        decks.add(new Deck(3, "Empires"));

        decks = getModel(false);

        mAdapter = new ListAdapterDecks(context, decks);
        mRecyclerView.setAdapter(mAdapter);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<Integer> selectedIds = new ArrayList<>();

                for (Deck d : decks){

                    if (d.isChecked()){
                        selectedIds.add(d.getId());
                    }

                }

                Bundle bundle = new Bundle();
                bundle.putIntegerArrayList("selectedIds", selectedIds);
                Intent intent = new Intent(context, ChooseCards.class);
                intent.putExtra("bundle", bundle);
                startActivity(intent);

            }
        });

        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decks = getModel(true);
                mAdapter = new ListAdapterDecks(context, decks);
                mRecyclerView.setAdapter(mAdapter);
            }
        });

        deselectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decks = getModel(false);
                mAdapter = new ListAdapterDecks(context, decks);
                mRecyclerView.setAdapter(mAdapter);
            }
        });
    }

    private ArrayList<Deck> getModel(boolean selected){

        ArrayList<Deck> list = new ArrayList<>();

        for (int i = 0; i < decks.size(); i++){

            Deck d = decks.get(i);
            d.setChecked(selected);

            list.add(d);

        }

        return list;

    }
}
