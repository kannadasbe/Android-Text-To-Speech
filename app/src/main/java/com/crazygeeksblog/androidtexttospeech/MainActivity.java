package com.crazygeeksblog.androidtexttospeech;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements
        TextToSpeech.OnInitListener {

    // Fields declaration
    private Button btnConvertText;
    private TextToSpeech textToSpeech;
    private EditText etInputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize fields
        textToSpeech = new TextToSpeech(this, this);
        btnConvertText = (Button) findViewById(R.id.btnConvertText);
        etInputText = (EditText) findViewById(R.id.etInputText);

        // Button onclick event listener
        btnConvertText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                convertTextToSpeech();
            }

        });

    }

    @Override
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS) {

            int result = textToSpeech.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(getApplicationContext(),"Language is not supported",Toast.LENGTH_SHORT).show();
            } else {
                btnConvertText.setEnabled(true);
                convertTextToSpeech();
            }
        } else {
            Toast.makeText(getApplicationContext(),"Initilization Failed",Toast.LENGTH_SHORT).show();
        }
    }

    private void convertTextToSpeech() {
        String inputText = etInputText.getText().toString();
        textToSpeech.speak(inputText, TextToSpeech.QUEUE_FLUSH, null);
    }
}
