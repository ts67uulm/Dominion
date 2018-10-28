package stenzel.tim.dominion.ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import stenzel.tim.dominion.Classes.Card;
import stenzel.tim.dominion.DB.AppDatabase;
import stenzel.tim.dominion.DB.CardDao;
import stenzel.tim.dominion.DB.ErweiterungssetDao;
import stenzel.tim.dominion.R;

public class ListAdapterCards extends RecyclerView.Adapter<ListAdapterCards.MyViewHolder> {

    private static List<Card> cardList;
    private LayoutInflater inflater;
    private Context context;

    private AppDatabase db;
    private CardDao cardDao;

    public ListAdapterCards(Context context, List<Card> cards) {
        inflater = LayoutInflater.from(context);
        cardList = cards;
        this.context = context;
        db = AppDatabase.getAppDatabase(context);
        cardDao = db.getCardDao();
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

                if (cardList.get(pos).isChecked()) {
                    cardList.get(pos).setChecked(false);
                    cardDao.updateCardChecked(cardList.get(pos).getId(), false);
                } else {
                    cardList.get(pos).setChecked(true);
                    cardDao.updateCardChecked(cardList.get(pos).getId(), true);
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
