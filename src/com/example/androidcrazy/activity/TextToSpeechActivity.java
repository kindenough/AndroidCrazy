
package com.example.androidcrazy.activity;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidcrazy.R;

public class TextToSpeechActivity extends Activity {

    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* 重写onDestroy()方法 */
        setContentView(R.layout.text_to_speech_view);
        Button btn_speech = (Button) findViewById(R.id.btn_speech);
        Button btn_record = (Button) findViewById(R.id.btn_record);
        tts = new TextToSpeech(this, new OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result != TextToSpeech.LANG_AVAILABLE
                            && result != TextToSpeech.LANG_COUNTRY_AVAILABLE) {
                        Toast.makeText(TextToSpeechActivity.this, "TTS暂时不支持这种语言的朗读",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btn_speech.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text2speech = (EditText) findViewById(R.id.speechText);
                tts.speak(text2speech.getText().toString(),
                        TextToSpeech.QUEUE_ADD, null);
            }
        });
        btn_record.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text2speech = (EditText) findViewById(R.id.speechText);
                tts.synthesizeToFile(text2speech.getText().toString(), null,
                        Environment.getExternalStorageDirectory()
                                + "/myspeech.wav");
                Toast.makeText(TextToSpeechActivity.this, "录音成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.shutdown();
        }
    }
}
