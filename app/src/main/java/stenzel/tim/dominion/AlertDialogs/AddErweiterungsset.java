package stenzel.tim.dominion.AlertDialogs;

import androidx.appcompat.app.AppCompatActivity;
import stenzel.tim.dominion.DB.AppDatabase;
import stenzel.tim.dominion.DB.ErweiterungssetDao;
import stenzel.tim.dominion.MainActivity;
import stenzel.tim.dominion.R;
import stenzel.tim.dominion.ShowSavedDecks;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddErweiterungsset extends AppCompatActivity {

    private Context context;

    private EditText nameEdit;
    private Button addBtn;
    private TextView errorView;

    private AppDatabase db;
    private ErweiterungssetDao ewDao;

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_erweiterungsset);

        context = AddErweiterungsset.this;

        db = AppDatabase.getAppDatabase(context);
        ewDao = db.getErweiterungssetDao();

        nameEdit = findViewById(R.id.add_ew_name_edit);
        addBtn = findViewById(R.id.add_ew_add_btn);
        errorView = findViewById(R.id.add_ew_error_view);

        errorView.setVisibility(View.INVISIBLE);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = nameEdit.getText().toString();

                if (name.equals("")){

                    errorView.setVisibility(View.VISIBLE);
                    errorView.setTextColor(Color.RED);
                    errorView.setText("Name darf nicht leer sein");

                } else if (ewDao.getErweiterungssetByName(name) != null){

                    errorView.setVisibility(View.VISIBLE);
                    errorView.setTextColor(Color.RED);
                    errorView.setText("Name existiert schon");

                } else {

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("name", name);
                    setResult(RESULT_OK, returnIntent);
                    finish();

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar_plus, menu);

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
            case R.id.action_add:
                intent = new Intent(context, AddErweiterungsset.class);
                startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
}
