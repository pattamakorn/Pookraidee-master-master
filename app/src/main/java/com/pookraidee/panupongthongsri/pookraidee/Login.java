package com.pookraidee.panupongthongsri.pookraidee;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Login extends AppCompatActivity {
    private TextView reg;
    private EditText loguser,logpass;
    private ImageView cle;
    private Button login;
    private String URL_LOGIN = "http://203.154.83.137/puklaidee/login2.php";
    public static final String MyPREFERENCES = "Setting" ;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loadLocale();

        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Activity.MODE_PRIVATE);
        String showidpre = prefs.getString("My_user","NoId");
        if (showidpre != "NoId"){
            startActivity(new Intent(this,Home2.class));
        }

        reg = findViewById(R.id.reg_page);
        loguser = findViewById(R.id.log_name_et);
        logpass = findViewById(R.id.log_password_et);
        login = findViewById(R.id.login);
        cle = findViewById(R.id.cle);

        cle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeLanguage();
            }
        });



        //sessionManager = new SessionManager(this);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Register.class);
                startActivity(i);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
                //Toast.makeText(Login.this, "Test : "+loguser.getText().toString()+"\n"+logpass.getText().toString(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Login.this,Home2.class);
//                startActivity(intent);
            }
        });
    }

    private void login(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL_LOGIN,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");
                            if (success.equals("1")){
                                for (int i = 0;i < jsonArray.length();i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String musername = object.getString("username");
                                    String mpassword = object.getString("password");
                                    String fname = object.getString("fname");
                                    String lname = object.getString("lname");
                                    String yearOld = object.getString("yearold");
                                    String tel = object.getString("tel");
                                    String area = object.getString("area");
                                    String address = object.getString("address");
                                    SharedPreferences.Editor editor = getSharedPreferences(MyPREFERENCES,MODE_PRIVATE).edit();
                                    editor.putString("My_user",musername);
                                    editor.apply();

                                    Intent intent = new Intent(Login.this, Home2.class);
                                    intent.putExtra("usernamee", musername);
                                    startActivity(intent);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Login.this, "ไม่สามารถเข้าสู่ระบบได้กรุณาเช็ค \n Username\n Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, "Error:"+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("user",loguser.getText().toString());
                params.put("password",logpass.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void changeLanguage(){
        final String[] listItems = {"Thai","Eng"};
        AlertDialog.Builder mbuilder = new AlertDialog.Builder(Login.this);
        mbuilder.setTitle("Choose Language...");
        mbuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i == 0){
                    setLocale("th");
                    recreate();
                }
                else if(i == 1){
                    setLocale("en");
                    recreate();
                }
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = mbuilder.create();
        dialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences(MyPREFERENCES,MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();

    }

    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences(MyPREFERENCES, Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang","");
        setLocale(language);

    }


}
