package com.pookraidee.panupongthongsri.pookraidee;

import android.app.Activity;
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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;


public class user extends AppCompatActivity implements View.OnClickListener{
    private Button us1;


    private ImageView avatar,logout;
    private TextView Tname,Tyear,TTel,Tarea,Taddress;
    private String URL_Profile = "http://203.154.83.137/puklaidee/loadprofile.php";
    private String eusernameT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        avatar = findViewById(R.id.userpic);
        Tname = findViewById(R.id.txt_showname);
        Tyear = findViewById(R.id.txt_sYmomAge);
        TTel = findViewById(R.id.txt_showblood);
        Tarea = findViewById(R.id.txt_showweight);
        Taddress = findViewById(R.id.txt_showshight);
        logout = findViewById(R.id.logout);
        loadprofile();

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ite = new Intent(user.this, Home2.class);
                startActivity(ite);
            }
        });

        us1 = findViewById ( R.id.btn_edit );
        us1.setOnClickListener((View.OnClickListener) this);
        logout.setOnClickListener((View.OnClickListener) this);

    }

    public void onClick(View v){
        Intent i;

        switch (v.getId()) {

            case R.id.btn_edit:
                Intent ite = new Intent(user.this, edit_profile.class);
                startActivity(ite);
                break;
            case R.id.logout:
                SharedPreferences sharedpreferences = getSharedPreferences(Login.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(this,Login.class));
                break;


        }
    }

    public void loadprofile(){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,URL_Profile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject posts = array.getJSONObject(i);
                        String fname = posts.getString("fname");
                        String lname = posts.getString("lname");
                        String ye = posts.getString("yearold");
                        String tel = posts.getString("tel");
                        String ar = posts.getString("area");
                        String ad = posts.getString("address");
                        String im = posts.getString("img");
                        Glide.with(user.this).load(im).into(avatar);
                        Tyear.setText(ye+" ปี");
                        Tname.setText(fname+" "+lname);
                        TTel.setText(tel);
                        Tarea.setText(ar+" ไร่");
                        Taddress.setText(ad);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }

                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                SharedPreferences prefs = getSharedPreferences(Login.MyPREFERENCES, Activity.MODE_PRIVATE);
                String showidpre = prefs.getString("My_user","NoId");
                params.put("user",showidpre);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(user.this);
        requestQueue.add(stringRequest);
    }
}