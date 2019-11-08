package com.pookraidee.panupongthongsri.pookraidee;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    private DatabaseReference mDatabaseRef;
    private StorageReference mStorageRef;
    private RelativeLayout reg,regload;
    private ImageView imgAdd;
    private Uri imgUri;
    private EditText name,username,password,surname,year,area,address;
    private String URL_regis = "http://203.154.83.137/puklaidee/register.php";
    private Button Register;
    public static final String FB_STORAGE_PATH = "image/";
    public static final String FB_DATABASE_PATH = "image";
    public static final int REQUEST_CODE = 1234;
    private ProgressBar spin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        imgAdd = findViewById(R.id.reg_avatar);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        Register = findViewById(R.id.register_naja);
        spin = findViewById(R.id.spin_kit_reg);
        reg = findViewById(R.id.reg_form);
        username = findViewById(R.id.reg_username_et);
        password = findViewById(R.id.reg_password_et);
        name =findViewById(R.id.reg_name_et);
        surname =findViewById(R.id.reg_Sname_et);
        regload = findViewById(R.id.reg_load);
        year = findViewById(R.id.reg_yearold_et);
        area = findViewById(R.id.reg_area_et);
        address = findViewById(R.id.reg_address_et);


        regload.setVisibility(View.GONE);
//        imgAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Select_photo();
//            }
//        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }



    public void register(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URL_regis,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject posts = array.getJSONObject(i);
                                }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            startActivity(new Intent(Register.this,Login.class));
                            //Toast.makeText(Register.this,e.toString()+ "\n ไม่สามารถเข้าสู่ระบบได้กรุณาเช็ค \n E-mail\n Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Register.this, "Error:"+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username",name.getText().toString());
                params.put("password",password.getText().toString());
                params.put("tel",username.getText().toString());
                params.put("lname",surname.getText().toString());
                params.put("year",year.getText().toString());
                params.put("area",area.getText().toString());
                params.put("address",address.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

//    public void Select_photo() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select image"), REQUEST_CODE);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            imgUri = data.getData();
//            try {
//                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), imgUri);
//                imgAdd.setImageBitmap(bm);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    public String getImageExt(Uri uri) {
//        ContentResolver contentResolver = getContentResolver();
//        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
//    }
//    public void Upload(){
//        if (imgUri != null) {
//            final StorageReference ref = mStorageRef.child(FB_STORAGE_PATH + System.currentTimeMillis() + "." + getImageExt(imgUri));
//            reg.setVisibility(View.GONE);
//            regload.setVisibility(View.VISIBLE);
//            ref.putFile(imgUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
//                @Override
//                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
//                    if (!task.isSuccessful()) {
//                        throw task.getException();
//                    }
//                    return ref.getDownloadUrl();
//                }
//            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
//                @Override
//                public void onComplete(@NonNull Task<Uri> task) {
//                    if (task.isSuccessful()) {
//                        Uri downloadUri = task.getResult();
//                        Register(String.valueOf(downloadUri));
//                    } else {
//                        Toast.makeText(Register.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//    }
//    public void Register(final String pathimg){
//        bind();
//        String URL = "https://kkku.000webhostapp.com/puklaidee/Register.php";
//        StringRequest stringRequest = new StringRequest(Request.Method.POST,
//                URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                if(response.equalsIgnoreCase("Success")){
//                    regload.setVisibility(View.GONE);
//                    reg.setVisibility(View.VISIBLE);
//                    Snackbar snackbar_success = Snackbar.make(reg, "สมัครสำเร็จ", 5000)
//                            .setAction("Action", null);
//                    View sbView_succ = snackbar_success.getView();
//                    TextView mTextView = (TextView) sbView_succ.findViewById(android.support.design.R.id.snackbar_text);
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//                        mTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//                    } else {
//                        mTextView.setGravity(Gravity.CENTER_HORIZONTAL);
//                    }
//                    sbView_succ.setBackgroundColor(ContextCompat.getColor(Register.this,R.color.green));
//                    mTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.happy,0,0,0);
//                    snackbar_success.show();
//                    Intent i = new Intent(getApplicationContext(),Login.class);
//                    startActivity(i);
//                }
//            }
//        },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params = new HashMap<>();
//                params.put("username",username.getText().toString());
//                params.put("password",password.getText().toString());
//                params.put("name",name.getText().toString());
//                params.put("img",pathimg);
//                return params;
//            }
//        };
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//    }
}
