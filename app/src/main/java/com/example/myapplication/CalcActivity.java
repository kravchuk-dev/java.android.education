package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalcActivity extends AppCompatActivity {
    private TextView tvHistory ;
    private TextView tvResult ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        tvHistory = findViewById( R.id.tvHistory ) ;
        tvResult  = findViewById( R.id.tvResult  ) ;
        tvHistory.setText( "" ) ;
        tvResult.setText( "0" ) ;

        findViewById( R.id.btn7 ).setOnClickListener( this::digitClick ) ;
        findViewById( R.id.btnPlusMinus ).setOnClickListener( this::pmClick ) ;
    }
    private void pmClick( View v ) {  // изменение знака (плюс/минус)

    }
    private void digitClick( View v ) {
        String result = tvResult.getText().toString() ;
        if( result.length() >= 10 ) return ;

        String digit = ((Button) v).getText().toString() ;
        if( result.equals( "0" ) ) {
            result = digit ;
        }
        else {
            result += digit ;
        }
        tvResult.setText( result ) ;
    }
}
