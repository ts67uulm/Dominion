package stenzel.tim.dominion;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

public class ListAdapterCards extends RecyclerView.Adapter<ListAdapterCards.MyViewHolder> {

    private static ArrayList<Card> cardList;
    private LayoutInflater inflater;
    private Context context;

    public ListAdapterCards(Context context, ArrayList<Card> cards) {
        inflater = LayoutInflater.from(context);
        cardList = cards;
        this.context = context;
    }

    @Override
    public ListAdapterCards.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view = inflater.inflate(R.layout.list_frag_cards, parent, false);
        ListAdapterCards.MyViewHolder holder = new ListAdapterCards.MyViewHolder(view);

        return holder;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ListAdapterCards.MyViewHolder holder, int position) {


        //checkbox
        holder.checkbox.setText(cardList.get(position).getName());
        holder.checkbox.setChecked(cardList.get(position).isChecked());

        holder.checkbox.setTag(position);
        holder.checkbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Integer pos = (Integer) holder.checkbox.getTag();
                Toast.makeText(context, cardList.get(pos).getName() + " clicked!", Toast.LENGTH_SHORT).show();

                if (cardList.get(pos).isChecked()) {
                    cardList.get(pos).setChecked(false);
                } else {
                    cardList.get(pos).setChecked(true);
                }
            }

        });

    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return cardList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        protected CheckBox checkbox;

        MyViewHolder(View itemView) {
            super(itemView);
            checkbox = itemView.findViewById(R.id.list_frag_cards_checkbox);
        }


    }
}
