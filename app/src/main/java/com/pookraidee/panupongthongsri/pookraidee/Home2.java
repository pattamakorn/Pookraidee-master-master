package com.pookraidee.panupongthongsri.pookraidee;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.time.Instant;
import java.util.HashMap;
import java.util.Locale;


public class Home2 extends AppCompatActivity implements View.OnClickListener{
private CardView bc1,bc2,bc3,bc4,bc5,bc6;

    private ImageView avatar,logout,changelang;
    private TextView name;


        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home2);
            avatar = findViewById(R.id.avatar_home);
            name = findViewById(R.id.txt_showname);
            logout = findViewById(R.id.logout);
           // changelang = findViewById(R.id.change_home);

            bc1 = findViewById(R.id.User_jet);
            bc2 = findViewById(R.id.tree_jet);
            bc3 = findViewById(R.id.histor_jet);
            bc4 = findViewById(R.id.money_jet);
            bc5 = findViewById(R.id.feed_news);
            bc6 = findViewById(R.id.pukfollow);
            bc1.setOnClickListener((View.OnClickListener) this);
            bc2.setOnClickListener((View.OnClickListener) this);
            bc3.setOnClickListener((View.OnClickListener) this);
            bc4.setOnClickListener((View.OnClickListener) this);
            bc5.setOnClickListener((View.OnClickListener) this);
            bc6.setOnClickListener((View.OnClickListener) this);
            logout.setOnClickListener((View.OnClickListener) this);

        }

        public void onClick(View v){
            Intent i;

            switch (v.getId()) {
                case R.id.User_jet:
                    Intent intent = new Intent(Home2.this, user.class);
                    startActivity(intent);
                    break;
                case R.id.tree_jet:
                    i = new Intent ( this , treeshow.class );
                    startActivity ( i );
                    break;
                case R.id.histor_jet:
                    Intent it = new Intent(Home2.this, history.class);
                    startActivity(it);
                    break;
                case R.id.money_jet:
                    Intent itm = new Intent(Home2.this, money.class);
                    startActivity(itm);
                    break;
                case R.id.logout:
                    SharedPreferences sharedpreferences = getSharedPreferences(Login.MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.clear();
                    editor.commit();
                    startActivity(new Intent(this,Login.class));
                    break;
                case R.id.feed_news:
                    i = new Intent ( this , fragmentHome.class );
                    startActivity ( i );
                    break;
                case R.id.pukfollow:
                    i = new Intent ( this , treefollow.class );
                    startActivity ( i );
                    break;


            }
            }
}