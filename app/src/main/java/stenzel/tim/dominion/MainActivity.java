package stenzel.tim.dominion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import stenzel.tim.dominion.Classes.Erweiterungsset;
import stenzel.tim.dominion.ListAdapter.ListAdapterErweiterungsset;

public class MainActivity extends AppCompatActivity {

    ArrayList<Erweiterungsset> erweiterungssets;
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
        erweiterungssets = new ArrayList<>();

        erweiterungssets.add(new Erweiterungsset(0, "Basis"));
        erweiterungssets.add(new Erweiterungsset(1, "Abenteuer"));
        erweiterungssets.add(new Erweiterungsset(2, "Intrige"));
        erweiterungssets.add(new Erweiterungsset(3, "Empires"));

        erweiterungssets = getModel(false);

        mAdapter = new ListAdapterErweiterungsset(context, erweiterungssets);
        mRecyclerView.setAdapter(mAdapter);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<Integer> selectedIds = new ArrayList<>();

                for (Erweiterungsset e : erweiterungssets){

                    if (e.isChecked()){
                        selectedIds.add(e.getId());
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

    private ArrayList<Erweiterungsset> getModel(boolean selected){

        ArrayList<Erweiterungsset> list = new ArrayList<>();

        for (int i = 0; i < erweiterungssets.size(); i++){

            Erweiterungsset d = erweiterungssets.get(i);
            d.setChecked(selected);

            list.add(d);

        }

        return list;

    }
}
