package com.pookraidee.panupongthongsri.pookraidee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

public class treeshow extends AppCompatActivity {
    private String DataPH,
            URL_Loaddatasenser = "http://203.154.83.137/puklaidee/loadsensor.php",
            URL_Loaddatatree="http://203.154.83.137/puklaidee/loadtree.php";
    private TextView cdataPH,cdataMOIST,cdataLIGHT,datavph,way_edit,tree,
            richtext;
    private ImageView treepic;
    private RingProgressBar progressBar;
    private Button pukk,detree;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treeshow);
        cdataPH = findViewById(R.id.phtext);
        cdataMOIST = findViewById(R.id.droptext);
        cdataLIGHT = findViewById(R.id.sunnytext);
        datavph = findViewById(R.id.phname);
        //way_edit = findViewById(R.id.edit_way);
        tree = findViewById(R.id.treename);
        richtext = findViewById(R.id.richtext);
        treepic = findViewById(R.id.treepic);
        //progressBar = findViewById(R.id.progress_bar_1);
        pukk = findViewById(R.id.pukk);
        detree = findViewById(R.id.view_de);


        loaddataph();
        loadtree();

        pukk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(treeshow.this,treefollow.class);
                startActivity(intent);
            }
        });

        detree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(treeshow.this,detree.class);
                startActivity(intent);
            }
        });


    }



    public void loaddataph(){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,URL_Loaddatasenser, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject posts = array.getJSONObject(i);
                        String idM = posts.getString("id");
                        Double p = posts.getDouble("ph");
                        String DataPH  = posts.getString("ph");
                        String DataM = posts.getString("moist");
                        String DataL = posts.getString("light");
                        cdataPH.setText(DataPH);
                        cdataMOIST.setText(DataM);
                        cdataLIGHT.setText(DataL);
                       // progressBar.setProgress((int) Math.ceil(p));

//                        if(p >= 0 && p <= 4.5){
//                            datavph.setText("ระดับความเป็นกรด-เบส: กรดแก่จัด");
//                            way_edit.setText("ใส่ปูนขาวอัตราสูงร่วมกับปุ๋ยอินทรีย์");
//                        }else if (p >= 4.6 && p <= 5.0){
//                            datavph.setText("ระดับความเป็นกรด-เบส: กรดจัดมาก");
//                            way_edit.setText("ใส่ปูนขาวอัตราสูงร่วมกับปุ๋ยอินทรีย์");
//                        }else if (p >= 5.1 && p <= 5.4){
//                            datavph.setText("ระดับความเป็นกรด-เบส: กรดจัด");
//                            way_edit.setText("ใส่ปูนขาวอัตราสูงร่วมกับปุ๋ยอินทรีย์");
//                        }else if (p >= 5.5 && p <= 6.0){
//                            datavph.setText("ระดับความเป็นกรด-เบส: กรดปานกลาง");
//                            way_edit.setText("ใส่ปุ๋ยอินทรีย์");
//                        }else if (p >= 6.1 && p <= 6.5){
//                            datavph.setText("ระดับความเป็นกรด-เบส: กรดอ่อน");
//                            way_edit.setText("ใส่ปุ๋ยอินทรีย์");
//                        }else if (p >= 6.6 && p <= 7.3){
//                            datavph.setText("ระดับความเป็นกรด-เบส: กลาง");
//                            way_edit.setText("ใส่ปุ๋ยอินทรีย์");
//                        }else if (p >= 7.4 && p <= 7.8){
//                            datavph.setText("ระดับความเป็นกรด-เบส: เบสอ่อน");
//                            way_edit.setText("ดินดีอยู่แล้วเหมาะแก่การเพราะปลูก");
//                        }else if (p >= 7.9 && p <= 8.4){
//                            datavph.setText("ระดับความเป็นกรด-เบส: เบสปานกลาง");
//                            way_edit.setText("ดินดีอยู่แล้วเหมาะแก่การเพราะปลูก");
//                        }else if (p >= 8.5 && p <= 9.0){
//                            datavph.setText("ระดับความเป็นกรด-เบส: เบสจัด");
//                            way_edit.setText("ใส่ยิปซัมอัตราสูงร่วมกับปุ๋ยอินทรีย์");
//                        }else if (p >= 9.1 && p <= 10){
//                            datavph.setText("ระดับความเป็นกรด-เบส: เบสจัดมาก");
//                            way_edit.setText("ไม่เหมากับการเพราะปลูก");
//                        }

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

//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params = new HashMap<>();
//                params.put("user",secoin);
//                return params;
//            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public void loadtree(){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,URL_Loaddatatree, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject posts = array.getJSONObject(i);
                        String nameTr = posts.getString("nametree");
                        String price  = posts.getString("price");
                        String imgTr = posts.getString("img");
                        tree.setText(nameTr);
                        richtext.setText(price);
                        Glide.with(treeshow.this).load(imgTr).into(treepic);



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
                params.put("user","4.7");
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



}
