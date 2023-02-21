package com.example.myapplication;

import androidx.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class CalcActivity extends AppCompatActivity {
    private TextView tvHistory ;
    private TextView tvResult ;
    private String minusSign ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        tvHistory = findViewById(R.id.tvHistory);
        tvResult = findViewById(R.id.tvResult);
        tvHistory.setText("");
        tvResult.setText("0");

        findViewById(R.id.btn7).setOnClickListener(this::digitClick);
        findViewById(R.id.btnPlusMinus).setOnClickListener(this::pmClick);
        minusSign = getString(R.string.minus_sign);

        for (int i = 0; i < 10; ++i) {                     //  Поиск ресурсов по имени (по строке)
            findViewById(                                   //
                    getResources()                              //  Обращение к ресурсу (R.)
                            .getIdentifier(                         //  запрос идентификатора (числа)
                                    "btn" + i,                          //   имя
                                    "id",                               //   поиск по группе (.id)
                                    getPackageName()                    //   пакет поиска (наш проект)
                            )                                       //
            ).setOnClickListener(this::digitClick);
        }

        findViewById( R.id.btnPlusMinus ).setOnClickListener( this::pmClick ) ;
        findViewById( R.id.btnInverse ).setOnClickListener( this::inverseClick ) ;
        Log.d( "onCreate", "onCreate" ) ;
    }

    /**
     * Вызывается при разрушении активности, используется для сохранения данных
     * @param outState сохраняемый объект
     */
    @Override
    protected void onSaveInstanceState( @NonNull Bundle outState ) {
        super.onSaveInstanceState( outState ) ;  // обязательно оставить
        // сохраняем history и result в объекте outState
        outState.putCharSequence( "history", tvHistory.getText() ) ;   // помещаем в словарь outState текст "истории" под ключем "history"
        outState.putCharSequence( "result",  tvResult.getText()  ) ;
        Log.d( "onSaveInstanceState", "Данные сохранены" ) ;
    }

    /**
     * Вызывается после пересоздания активности, используется для восстановления данных
     * @param savedInstanceState сохраненный объект
     */
    @Override
    protected void onRestoreInstanceState( @NonNull Bundle savedInstanceState ) {
        super.onRestoreInstanceState( savedInstanceState ) ;
        tvHistory.setText( savedInstanceState.getCharSequence( "history" ) ) ;
        tvResult.setText(  savedInstanceState.getCharSequence( "result"  ) ) ;
        Log.d( "onRestoreInstanceState", "Данные восстановлены" ) ;
    }

    private void pmClick( View v ) {  // изменение знака (плюс/минус)
        String result = tvResult.getText().toString();
        if (result.equals("0")) {
            return;
        }
        if (result.startsWith(minusSign)) {
            result = result.substring(1);
        } else {
            result = minusSign + result;
        }
        tvResult.setText(result);
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

    private void inverseClick( View v ) {   //  1/x
        String result = tvResult.getText().toString() ;
        double arg = getArgument( result ) ;
        if( arg == 0 ) {
            Toast                                     //  Системное сообщение (всплывающее)
                    .makeText(                            //    (~MessageBox)
                            CalcActivity.this,            //  привязано к нашей активности
                            "Cannot divide by zero",      //  текст сообщения
                            Toast.LENGTH_SHORT )          //  длительность показа (продолжительность)
                    .show() ;                             //  Запуск показа
            return ;
        }
        tvHistory.setText( String.format( "1/(%s) =", result ) ) ;
        arg = 1 / arg ;
        result = String.format( Locale.getDefault(), "%.10f", arg ) ;
        while( result.endsWith( "0" ) ) {
            result = result.substring( 0, result.length() - 1 ) ;
        }
        tvResult.setText( result.replace( "-", minusSign ) ) ;
    }
    private double getArgument( String resultText ) {
        return Double.parseDouble(
                resultText.replace( minusSign, "-" ) ) ;
    }
}
