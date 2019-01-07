package com.example.lenovo.bio_calculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.korean)
    TextView korean;
    @BindView(R.id.english)
    TextView english;

    SharedPreferences pr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        pr = getSharedPreferences("lan", MODE_PRIVATE);

        korean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                SharedPreferences.Editor editor = pr.edit();
                editor.putString("lan","kor");
                editor.commit();
                startActivity(i);
            }
        });

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "영어 버전 선택", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = pr.edit();
                editor.putString("lan","eng");
                editor.commit();
            }
        });
    }

}
