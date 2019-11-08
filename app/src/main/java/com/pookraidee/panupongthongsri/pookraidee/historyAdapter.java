package com.pookraidee.panupongthongsri.pookraidee;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class historyAdapter extends RecyclerView.Adapter<historyAdapter.MyViewHolder> {

    Context mcontext;
    List<Shistory> mhistory;
    View view;
    Dialog myDialog;
    private ImageView img;

    public historyAdapter(Context mcontext, List<Shistory> mhistory) {
        this.mcontext = mcontext;
        this.mhistory = mhistory;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(mcontext).inflate(R.layout.showhistory,viewGroup,false);
        MyViewHolder vHolder = new MyViewHolder(view);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.stree.setText(mhistory.get(i).getStarttree());
        myViewHolder.etree.setText(mhistory.get(i).getEndtree());
        myViewHolder.nntree.setText(mhistory.get(i).getNtree());
        myViewHolder.wwtree.setText(mhistory.get(i).getWtree());
        myViewHolder.rrtree.setText(mhistory.get(i).getRtree());

    }

    @Override
    public int getItemCount() {
        return mhistory.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView stree,etree,nntree,wwtree,rrtree;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            

            stree = itemView.findViewById(R.id.start_tree);
            etree = itemView.findViewById(R.id.end_tree);
            nntree = itemView.findViewById(R.id.stree_name);
            wwtree = itemView.findViewById(R.id.weight_tree);
            rrtree = itemView.findViewById(R.id.total_revenue);

        }
    }
}
