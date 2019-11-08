package com.pookraidee.panupongthongsri.pookraidee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class fragmentHome extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ModelFeed> modelFeedArrayList = new ArrayList<>();
    AdapterFeed adapterFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_home);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapterFeed = new AdapterFeed(this, modelFeedArrayList);
        recyclerView.setAdapter(adapterFeed);

        populateRecyclerView();
    }
    public void populateRecyclerView() {

        ModelFeed modelFeed = new ModelFeed(1, 9, 2, R.drawable.ic_propic1, R.drawable.img_post3,
                "NEWS", "2 hrs", "ไอเดียปลูกผักสวนครัวในบ้าน หมุนเวียนตามฤดูกาลต่างๆ ทำให้บ้านมีผักปลอดภัยกินตลอดทั้งปี");
        modelFeedArrayList.add(modelFeed);
//        modelFeed = new ModelFeed(2, 26, 6, R.drawable.ic_propic2, 0,
//                "Karun Shrestha", "9 hrs", "Don't be afraid of your fears. They're not there to scare you. They're there to \n" +
//                "let you know that something is worth it.");
//        modelFeedArrayList.add(modelFeed);
        modelFeed = new ModelFeed(3, 17, 5, R.drawable.ic_propic1, R.drawable.img_post4,
                "NEWS", "13 hrs", "เคล็ดลับและเทคนิคในการเพาะต้นกล้าก็มีวิธีการเพาะที่ไม่ยุ่งยากมากมาย เรามาดูวิธีการเพาะต้นกล้าให้แข็งแรงกันเลยนะคะเผื่อท่านไหนสนใจอยากทำการเพาะต้นกล้าขายจะได้ทำวิธีการเพาะไว้เป็นแนวทาง");
        modelFeedArrayList.add(modelFeed);

        adapterFeed.notifyDataSetChanged();
    }
}
