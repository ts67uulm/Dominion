package stenzel.tim.dominion.AlertDialogs;

import androidx.appcompat.app.AppCompatActivity;
import stenzel.tim.dominion.DB.AppDatabase;
import stenzel.tim.dominion.DB.DeckDao;
import stenzel.tim.dominion.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class AlertDialogSaveDeck extends AppCompatActivity {

    private Context context;

    private Intent intent;

    private RadioButton beginner, advanced, lessPlayer, morePlayer;
    private RadioGroup playersGrp, catGrp;
    private Button cancel, save;
    private EditText nameEdit;
    private TextView error;

    private int requestCode;

    private String cat;
    private boolean morePlayers;

    private AppDatabase db;
    private DeckDao deckDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog_save_deck);

        context = AlertDialogSaveDeck.this;

        db = AppDatabase.getAppDatabase(context);
        deckDao = db.getDeckDao();

        nameEdit = findViewById(R.id.alert_save_deck_name_edit);
        playersGrp = findViewById(R.id.alert_save_deck_players_grp);
        lessPlayer = findViewById(R.id.alert_save_deck_more_no);
        morePlayer = findViewById(R.id.alert_save_deck_more_yes);
        catGrp = findViewById(R.id.alert_save_deck_cat_grp);
        beginner = findViewById(R.id.alert_save_deck_cat_beginner);
        advanced = findViewById(R.id.alert_save_deck_cat_advanced);
        error = findViewById(R.id.alert_save_deck_error);

        error.setVisibility(View.INVISIBLE);

        cancel = findViewById(R.id.alert_save_deck_cancel_btn);
        save = findViewById(R.id.alert_save_deck_save_btn);

        morePlayers = false;
        lessPlayer.setChecked(true);

        cat = "Einsteiger";
        beginner.setChecked(true);


        intent = getIntent();

        requestCode = intent.getIntExtra("requestCode", 0);

        playersGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (morePlayers && i == R.id.alert_save_deck_more_no){

                    morePlayers = false;

                } else if (!morePlayers && i == R.id.alert_save_deck_more_yes){

                    morePlayers = true;
                }

            }
        });


        catGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (cat.equals("Einsteiger") && i == R.id.alert_save_deck_cat_advanced){

                    cat = "Profis";

                } else if (cat.equals("Profis") && i == R.id.alert_save_deck_cat_beginner){

                    cat = "Einsteiger";

                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //return nothing cause cancel
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nameEdit.getText().toString();

                if (name.equals("")){
                    error.setVisibility(View.VISIBLE);
                    error.setTextColor(Color.RED);
                    error.setText("Name darf nicht leer sein.");
                } else if(deckDao.getDeckByName(name) != null){
                    error.setVisibility(View.VISIBLE);
                    error.setTextColor(Color.RED);
                    error.setText("Name existiert schon.");
                } else {

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("name", name);
                    returnIntent.putExtra("morePlayers", morePlayers);
                    returnIntent.putExtra("category", cat);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            }
        });
    }
}
