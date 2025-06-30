package com.example.ebookreader.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ebookreader.R;
import com.example.ebookreader.databinding.ActivityTextReaderBinding;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;

public class TextReaderActivity extends AppCompatActivity {
    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_reader);

        tvContent = findViewById(R.id.tvContent);

        String url = getIntent().getStringExtra("URL");
        if (url != null) {
            new Thread(() -> {
                try {
                    URL bookUrl = new URL(url);
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(bookUrl.openStream()));
                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    reader.close();

                    runOnUiThread(() -> tvContent.setText(content.toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(() -> tvContent.setText("Không thể tải nội dung."));
                }
            }).start();
        } else {
            tvContent.setText("Không có URL nội dung.");
        }
    }
}
