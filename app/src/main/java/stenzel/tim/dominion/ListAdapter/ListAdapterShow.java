package stenzel.tim.dominion.ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import stenzel.tim.dominion.Classes.Card;
import stenzel.tim.dominion.DB.AppDatabase;
import stenzel.tim.dominion.DB.CardDao;
import stenzel.tim.dominion.R;

public class ListAdapterShow extends RecyclerView.Adapter<ListAdapterShow.MyViewHolder> {

    private static List<Card> cardList;
    private LayoutInflater inflater;
    private Context context;

    private AppDatabase db;
    private CardDao cardDao;

    public ListAdapterShow(Context context, List<Card> cards) {
        inflater = LayoutInflater.from(context);
        cardList = cards;
        this.context = context;
        db = AppDatabase.getAppDatabase(context);
        cardDao = db.getCardDao();
    }

    @Override
    public ListAdapterShow.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view = inflater.inflate(R.layout.list_frag_show_cards, parent, false);
        ListAdapterShow.MyViewHolder holder = new ListAdapterShow.MyViewHolder(view);

        return holder;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ListAdapterShow.MyViewHolder holder, int position) {


        //checkbox
        holder.textView.setText(cardList.get(position).getName());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return cardList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        protected TextView textView;

        MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.list_frag_show_textview);
        }


    }
}
