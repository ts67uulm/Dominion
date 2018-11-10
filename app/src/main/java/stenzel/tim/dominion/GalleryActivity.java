package stenzel.tim.dominion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import stenzel.tim.dominion.AlertDialogs.AddErweiterungsset;
import stenzel.tim.dominion.Classes.Erweiterungsset;
import stenzel.tim.dominion.DB.AppDatabase;
import stenzel.tim.dominion.DB.ErweiterungssetDao;
import stenzel.tim.dominion.ListAdapter.ListAdapterAddEW;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    private Context context;

    private Button showAllCardsBtn, showAllDecksBtn;
    private com.google.android.material.floatingactionbutton.FloatingActionButton fab;

    private AppDatabase db;
    private ErweiterungssetDao ewDao;

    private List<Erweiterungsset> allErweiterungssets;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        context = GalleryActivity.this;

        db = AppDatabase.getAppDatabase(context);
        ewDao = db.getErweiterungssetDao();

        mRecyclerView = findViewById(R.id.add_ac_recycler_view);
        fab = findViewById(R.id.add_ac_add_ew);
        showAllCardsBtn = findViewById(R.id.add_ac_show_all_cards);
        showAllDecksBtn = findViewById(R.id.add_ac_show_all_decks);

        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        allErweiterungssets = ewDao.getAllErweiterungssets();

        mAdapter = new ListAdapterAddEW(context, allErweiterungssets);
        mRecyclerView.setAdapter(mAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddErweiterungsset.class);
                startActivityForResult(intent, 0);
            }
        });

        showAllCardsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowCardsActivity.class);
                startActivity(intent);
            }
        });

        showAllDecksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowSavedDecks.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0) {
            if(resultCode == Activity.RESULT_OK){

                String name = data.getStringExtra("name");

                ewDao.insertElement(new Erweiterungsset(allErweiterungssets.size(), name));

                Toast.makeText(context, "Erfolgreich hinzugefügt.", Toast.LENGTH_SHORT);

                allErweiterungssets = ewDao.getAllErweiterungssets();
                mAdapter = new ListAdapterAddEW(context, allErweiterungssets);
                mRecyclerView.setAdapter(mAdapter);

            } else {
                Toast.makeText(context, "Fehler. Erneut versuchen.", Toast.LENGTH_SHORT);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar_home, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_home:
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
