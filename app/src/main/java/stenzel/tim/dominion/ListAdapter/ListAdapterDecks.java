package stenzel.tim.dominion.ListAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import stenzel.tim.dominion.Classes.Deck;
import stenzel.tim.dominion.R;
import stenzel.tim.dominion.ShowSavedDeck;

public class ListAdapterDecks extends RecyclerView.Adapter<ListAdapterDecks.MyViewHolder> {

    private static List<Deck> deckList;
    private LayoutInflater inflater;
    private Context context;

    public ListAdapterDecks(Context context, List<Deck> decks) {
        inflater = LayoutInflater.from(context);
        deckList = decks;
        this.context = context;
    }

    @Override
    public ListAdapterDecks.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_frag_saved_decks, parent, false);
        ListAdapterDecks.MyViewHolder holder = new ListAdapterDecks.MyViewHolder(view);



        return holder;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ListAdapterDecks.MyViewHolder holder, int position) {

        holder.textView.setText(deckList.get(position).getName());
        holder.textView.setTag(position);

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pos = (Integer) holder.textView.getTag();

                int deckId = deckList.get(pos).getId();

                Intent intent = new Intent(context, ShowSavedDeck.class);
                intent.putExtra("deckId", deckId);
                context.startActivity(intent);

            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return deckList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        protected TextView textView;

        MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.list_frag_saved_decks_textview);
        }


    }
}
