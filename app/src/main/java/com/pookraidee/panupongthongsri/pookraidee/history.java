package com.pookraidee.panupongthongsri.pookraidee;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


public class history extends AppCompatActivity implements View.OnClickListener{



    private ImageView avatar,logout;
    private TextView name;
    private String myuser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        //getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new showhistorytotal()).commit();

        final showhistorytotal st = new showhistorytotal();

        avatar = findViewById(R.id.avatar_home);
        name = findViewById(R.id.name_home);
        logout = findViewById(R.id.logout);
        //Toast.makeText(this, myuser, Toast.LENGTH_SHORT).show();


//        Bundle bd = getIntent().getExtras();
//        if(bd != null){
//            Glide.with(getApplicationContext()).load(bd.getString("img")).apply( RequestOptions.circleCropTransform()).into(avatar);
//            name.setText(bd.getString("name"));
//        }



        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,st).commit();



        logout.setOnClickListener((View.OnClickListener) this);

    }

    public void onClick(View v){
        Intent i;

        switch (v.getId()) {


            case R.id.logout:
                SharedPreferences sharedpreferences = getSharedPreferences(Login.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(this,Login.class));
                break;


        }
    }


}