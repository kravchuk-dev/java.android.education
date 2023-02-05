package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = findViewById( R.id.exitButton ) ;
        addButton.setOnClickListener( this::exitButtonClick ) ;

        findViewById( R.id.calcButton )
                .setOnClickListener( this::calcButtonClick ) ;
    }

    private void calcButtonClick(View view) {
        Intent calcIntent = new Intent( this, CalcActivity.class ) ;
        startActivity( calcIntent ) ;
    }

    private void exitButtonClick(View view) {
        finish();
    }

}