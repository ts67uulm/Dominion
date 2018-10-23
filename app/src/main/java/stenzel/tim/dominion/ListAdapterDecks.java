package stenzel.tim.dominion;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListAdapterDecks extends RecyclerView.Adapter<ListAdapterDecks.MyViewHolder> {

    private static ArrayList<Deck> deckList;
    private LayoutInflater inflater;
    private Context context;

    public ListAdapterDecks(Context context, ArrayList<Deck> decks) {
        inflater = LayoutInflater.from(context);
        deckList = decks;
        this.context = context;
    }

    @Override
    public ListAdapterDecks.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view = inflater.inflate(R.layout.list_frag_decks, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ListAdapterDecks.MyViewHolder holder, int position) {


        //checkbox
        holder.checkbox.setText(deckList.get(position).getName());
        holder.checkbox.setChecked(deckList.get(position).isChecked());

        holder.checkbox.setTag(position);
        holder.checkbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Integer pos = (Integer) holder.checkbox.getTag();
                Toast.makeText(context, deckList.get(pos).getName() + " clicked!", Toast.LENGTH_SHORT).show();

                if (deckList.get(pos).isChecked()) {
                    deckList.get(pos).setChecked(false);
                } else {
                    deckList.get(pos).setChecked(true);
                }
            }

        });

    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return deckList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        protected  CheckBox checkbox;

        MyViewHolder(View itemView) {
            super(itemView);
            checkbox = itemView.findViewById(R.id.list_frag_decks_checkbox);
        }


    }
}