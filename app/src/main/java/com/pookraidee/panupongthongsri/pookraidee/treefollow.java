package com.pookraidee.panupongthongsri.pookraidee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.Toast;

import com.app.adprogressbarlib.AdCircleProgress;

import java.util.Timer;
import java.util.TimerTask;

public class treefollow extends AppCompatActivity {
    int i = 0;
    AdCircleProgress adCircleProgress4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treefollow);
        adCircleProgress4 = findViewById(R.id.pgb_progress4);

        final Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {


                        adCircleProgress4.setAdProgress(i);

                        i++;
                    }
                });
            }
        }, 0, 12000);

        adCircleProgress4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(treefollow.this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
