package stenzel.tim.dominion.ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import stenzel.tim.dominion.Classes.Erweiterungsset;
import stenzel.tim.dominion.DB.AppDatabase;
import stenzel.tim.dominion.DB.ErweiterungssetDao;
import stenzel.tim.dominion.R;

public class ListAdapterErweiterungsset extends RecyclerView.Adapter<ListAdapterErweiterungsset.MyViewHolder> {

    private AppDatabase db;
    private ErweiterungssetDao ewDao;

    private static List<Erweiterungsset> erweiterungssetsList;
    private LayoutInflater inflater;
    private Context context;

    public ListAdapterErweiterungsset(Context context, List<Erweiterungsset> erweiterungssets) {
        inflater = LayoutInflater.from(context);
        erweiterungssetsList = erweiterungssets;
        this.context = context;

        db = AppDatabase.getAppDatabase(context);
        ewDao = db.getErweiterungssetDao();
    }

    @Override
    public ListAdapterErweiterungsset.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view = inflater.inflate(R.layout.list_frag_decks, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ListAdapterErweiterungsset.MyViewHolder holder, int position) {


        //checkbox
        holder.checkbox.setText(erweiterungssetsList.get(position).getName());
        holder.checkbox.setChecked(erweiterungssetsList.get(position).isChecked());

        holder.checkbox.setTag(position);
        holder.checkbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Integer pos = (Integer) holder.checkbox.getTag();

                if (erweiterungssetsList.get(pos).isChecked()) {
                    erweiterungssetsList.get(pos).setChecked(false);
                    ewDao.updateEwChecked(erweiterungssetsList.get(pos).getId(), false);
                } else {
                    erweiterungssetsList.get(pos).setChecked(true);
                    ewDao.updateEwChecked(erweiterungssetsList.get(pos).getId(), true);
                }
            }

        });

    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return erweiterungssetsList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        protected  CheckBox checkbox;

        MyViewHolder(View itemView) {
            super(itemView);
            checkbox = itemView.findViewById(R.id.list_frag_decks_checkbox);
        }


    }
}