package stenzel.tim.dominion.ListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import stenzel.tim.dominion.Classes.Erweiterungsset;
import stenzel.tim.dominion.DB.AppDatabase;
import stenzel.tim.dominion.DB.ErweiterungssetDao;
import stenzel.tim.dominion.R;

public class ListAdapterAddEW extends RecyclerView.Adapter<ListAdapterAddEW.MyViewHolder> {

    private AppDatabase db;
    private ErweiterungssetDao ewDao;

    private static List<Erweiterungsset> erweiterungssetsList;
    private LayoutInflater inflater;
    private Context context;

    public ListAdapterAddEW(Context context, List<Erweiterungsset> erweiterungssets) {
        inflater = LayoutInflater.from(context);
        erweiterungssetsList = erweiterungssets;
        this.context = context;
    }

    @Override
    public ListAdapterAddEW.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view = inflater.inflate(R.layout.list_frag_add_ew, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ListAdapterAddEW.MyViewHolder holder, int position) {

        holder.textView.setText(erweiterungssetsList.get(position).getName());
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return erweiterungssetsList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        protected TextView textView;

        MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.list_frag_add_ew_textview);
        }


    }
}