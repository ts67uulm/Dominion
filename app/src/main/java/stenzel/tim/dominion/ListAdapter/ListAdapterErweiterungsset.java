package stenzel.tim.dominion.ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import stenzel.tim.dominion.Classes.Erweiterungsset;
import stenzel.tim.dominion.R;

public class ListAdapterErweiterungsset extends RecyclerView.Adapter<ListAdapterErweiterungsset.MyViewHolder> {

    private static ArrayList<Erweiterungsset> erweiterungssetsList;
    private LayoutInflater inflater;
    private Context context;

    public ListAdapterErweiterungsset(Context context, ArrayList<Erweiterungsset> erweiterungssets) {
        inflater = LayoutInflater.from(context);
        erweiterungssetsList = erweiterungssets;
        this.context = context;
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
                Toast.makeText(context, erweiterungssetsList.get(pos).getName() + " clicked!", Toast.LENGTH_SHORT).show();

                if (erweiterungssetsList.get(pos).isChecked()) {
                    erweiterungssetsList.get(pos).setChecked(false);
                } else {
                    erweiterungssetsList.get(pos).setChecked(true);
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