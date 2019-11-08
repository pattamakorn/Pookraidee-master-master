package com.pookraidee.panupongthongsri.pookraidee;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Home extends AppCompatActivity {
private RecyclerView recyclerView;
private List<plantsmodel> plantsmodelList;
private ImageView avatar,logout;
private TextView name;
private SharedPreferences.Editor editor;
private SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView = findViewById(R.id.recyclerview);
        plantsmodelList = new ArrayList<>();
//        editor = getSharedPreferences("MyPref", MODE_PRIVATE).edit();
//        pref = getSharedPreferences("MyPref", MODE_PRIVATE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        avatar = findViewById(R.id.avatar_home);
        name = findViewById(R.id.name_home);
        logout = findViewById(R.id.logout);
        loadtree();
//        Bundle bd = getIntent().getExtras();
//        if(bd != null){
//            Glide.with(getApplicationContext()).load(bd.getString("img")).apply(RequestOptions.circleCropTransform()).into(avatar);
//            name.setText(bd.getString("name"));
//        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                plantsmodelList.clear();
//                loadtree();
//            }
//        },0,200);

    }
    private void loadtree(){
        String url = "https://kkku.000webhostapp.com/puklaidee/loadtree.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for(int i =0;i<array.length();i++){
                        JSONObject storena = array.getJSONObject(i);
                        plantsmodelList.add(new plantsmodel(
                                storena.getString("id"),
                                storena.getString("ph"),
                                storena.getString("moi"),
                                storena.getString("light"),
                                storena.getString("price"),
                                storena.getString("name"),
                                storena.getString("img"),
                                storena.getString("price"),
                                storena.getString("name_ph")
                        ));
                    }
                    treeAdapter aspt = new treeAdapter(plantsmodelList,getApplicationContext());
                    recyclerView.setAdapter(aspt);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
}
