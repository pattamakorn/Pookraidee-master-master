package com.pookraidee.panupongthongsri.pookraidee;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.time.Instant;


public class money extends AppCompatActivity implements View.OnClickListener{



    private ImageView avatar,logout;
    private TextView name;
    private String uu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        avatar = findViewById(R.id.avatar_home);
        name = findViewById(R.id.name_home);
        logout = findViewById(R.id.logout);




        final coin cc = new coin();

        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,cc).commit();


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