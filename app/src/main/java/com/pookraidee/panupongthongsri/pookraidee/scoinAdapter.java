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

public class scoinAdapter extends RecyclerView.Adapter<scoinAdapter.MyViewHolder> {

    Context mcontext;
    List<scoin> mcoin;
    View view;
    Dialog myDialog;

    public scoinAdapter(Context mcontext, List<scoin> mcoin) {
        this.mcontext = mcontext;
        this.mcoin = mcoin;
    }


    @NonNull
    @Override
    public scoinAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(mcontext).inflate(R.layout.showitemcoin,viewGroup,false);
        scoinAdapter.MyViewHolder vHolder = new scoinAdapter.MyViewHolder(view);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull scoinAdapter.MyViewHolder myViewHolder, int i) {

        myViewHolder.shcoin.setText(mcoin.get(i).getScoin());
        myViewHolder.dacoin.setText(mcoin.get(i).getSdate());

    }

    @Override
    public int getItemCount() {
        return mcoin.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView shcoin,dacoin;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            shcoin = itemView.findViewById(R.id.showmoney);
            dacoin = itemView.findViewById(R.id.timemoney);


        }
    }
}
