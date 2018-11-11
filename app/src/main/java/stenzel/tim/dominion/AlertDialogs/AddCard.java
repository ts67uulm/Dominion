package stenzel.tim.dominion.AlertDialogs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import stenzel.tim.dominion.Classes.Card;
import stenzel.tim.dominion.Classes.Erweiterungsset;
import stenzel.tim.dominion.DB.AppDatabase;
import stenzel.tim.dominion.DB.CardDao;
import stenzel.tim.dominion.DB.ErweiterungssetDao;
import stenzel.tim.dominion.R;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddCard extends AppCompatActivity {

    private Context context;

    private EditText nameEdit, costEdit, textEdit;
    private TextView errorView;
    private Button addBtn;
    private AppCompatSpinner typeSpinner, ewSpinner;

    private String name, type, text, ew;
    private int cost;

    private AppDatabase db;
    private CardDao cardDao;
    private ErweiterungssetDao ewDao;

    private List<Erweiterungsset> erweiterungssets;
    private List<String> namesOfEWs, types;
    private List<Card> allCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        context = AddCard.this;

        db = AppDatabase.getAppDatabase(context);
        cardDao = db.getCardDao();
        ewDao = db.getErweiterungssetDao();

        nameEdit = findViewById(R.id.add_card_name_edit);
        costEdit = findViewById(R.id.add_card_cost_edit);
        textEdit = findViewById(R.id.add_card_text_edit);
        typeSpinner = findViewById(R.id.add_card_type_spinner);
        ewSpinner = findViewById(R.id.add_card_ew_spinner);
        errorView = findViewById(R.id.add_card_errorview);
        addBtn = findViewById(R.id.add_card_add_btn);

        errorView.setVisibility(View.INVISIBLE);

        namesOfEWs = new ArrayList<>();
        types = new ArrayList<>();

        types.add("Vorrat");
        types.add("Ereignis");
        types.add("Landmarker");

        erweiterungssets = ewDao.getAllErweiterungssets();
        allCards = cardDao.getAllCards();

        for (Erweiterungsset e : erweiterungssets){

            namesOfEWs.add(e.getName());

        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, namesOfEWs);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, types);

        typeSpinner.setAdapter(dataAdapter2);
        ewSpinner.setAdapter(dataAdapter);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = nameEdit.getText().toString();
                text = textEdit.getText().toString();
                cost = Integer.parseInt(costEdit.getText().toString());
                type = types.get(typeSpinner.getSelectedItemPosition());
                ew = namesOfEWs.get(ewSpinner.getSelectedItemPosition());

                if (name.equals("")){
                    errorView.setVisibility(View.VISIBLE);
                    errorView.setTextColor(Color.RED);
                    errorView.setText("Name darf nicht leer sein");
                } else if (cardDao.getCardByName(name) != null){
                    errorView.setVisibility(View.VISIBLE);
                    errorView.setTextColor(Color.RED);
                    errorView.setText("Karte existiert schon");
                } else {

                    cardDao.insertElement(new Card(allCards.size(), cost, name, type, text, ew));

                    Intent returnIntent = new Intent();
                    setResult(RESULT_OK, returnIntent);
                    finish();
                }


            }
        });


    }
}
