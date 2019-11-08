package com.pookraidee.panupongthongsri.pookraidee;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

public class treeAdapter extends RecyclerView.Adapter<treeAdapter.MyViewHolder >{
    private List<plantsmodel> plantsmodelList;
    private View plantView;
    private Context context;
    class MyViewHolder extends RecyclerView.ViewHolder {
    private ImageView tree;
    private TextView sun,drop,ph,rich,nameph,edit,nametree;
    private RingProgressBar progressBar;
        public MyViewHolder(@NonNull View view) {
            super(view);
            tree = view.findViewById(R.id.treepic);
            nametree = view.findViewById(R.id.treename2);
            sun = view.findViewById(R.id.sunnytext);
            drop = view.findViewById(R.id.droptext);
            ph = view.findViewById(R.id.phtext);
            nameph = view.findViewById(R.id.phname);
            rich = view.findViewById(R.id.richtext);
           progressBar = view.findViewById(R.id.progress_bar_1);
            edit = view.findViewById(R.id.edit_way);
        }
    }

    public treeAdapter(List<plantsmodel> plantsmodelList, Context context) {
        this.plantsmodelList = plantsmodelList;
        this.context = context;
    }


    @NonNull
    @Override
    public treeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        plantView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.treelayout, parent, false);
        return new treeAdapter.MyViewHolder(plantView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        plantsmodel pd = plantsmodelList.get(position);
        Glide.with(plantView.getContext()).load(pd.getImg()).into(holder.tree);
        holder.nametree.setText(pd.getName());
        holder.sun.setText(pd.getSun());
        holder.drop.setText(pd.getMoi());
        holder.ph.setText(pd.getPh());
        holder.nameph.setText(pd.getName_ph());
        holder.rich.setText(pd.getRich());
        holder.rich.setText(pd.getRich());
        float ab = Float.valueOf(pd.getPh());
        holder.progressBar.setProgress((int) Math.ceil(ab));
       if(((int) Math.ceil(ab)) > 4 && ((int) Math.ceil(ab)) <= 8 ){
            holder.edit.setText("ใส่ปุ๋ยอินทรีย์");
        }else if(((int) Math.ceil(ab)) > 8){
          holder.edit.setText("ใช้กำมะถันผง");
       }
    }
    @Override
    public int getItemCount() {
        return plantsmodelList.size();
    }
}
