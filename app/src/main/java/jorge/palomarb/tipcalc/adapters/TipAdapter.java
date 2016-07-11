package jorge.palomarb.tipcalc.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jorge.palomarb.tipcalc.R;
import jorge.palomarb.tipcalc.models.TipRecord;

/**
 * Created by JORGE on 28/06/2016.
 */
public class TipAdapter extends RecyclerView.Adapter<TipAdapter.ViewHolder>
{
    private List<TipRecord> dataset;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public TipAdapter(Context context, List<TipRecord>dataset, OnItemClickListener onItemClickListener) {
        this.dataset = dataset;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    public TipAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.dataset = new ArrayList<TipRecord>();
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_road_xml,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        TipRecord element = dataset.get(position);
        String strTip = String.format(context.getString(R.string.global_message_tip),element.getTip());
        holder.txtContent.setText(strTip);
        holder.setOnItemClickListener(element, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void add(TipRecord record)
    {
        dataset.add(0,record);
        notifyDataSetChanged();
    }

    public void clear()
    {
        dataset.clear();
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.txtContent)TextView txtContent;


        public ViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setOnItemClickListener(final TipRecord element, final OnItemClickListener onItemClickListener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(element);
                }
            });
        }
    }


}
