package com.example.qrcodescanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

import java.net.URL;

public class QRResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrresult);

        TextView textView = findViewById(R.id.result_text);

        String result = getIntent().getExtras().getString("result").trim();
        String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

        if (isValidURL(result) || result.matches(regex) || result.startsWith("www."))
        {
            textView.setAutoLinkMask(Linkify.WEB_URLS);
            textView.setText(result);
        }
        else
        {
            textView.setText(result+" is not a valid URL");
        }
    }

    public boolean isValidURL(String url)
    {
        try
        {
            new URL(url).toURI();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
