package com.example.ebookreader.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ebookreader.databinding.ActivityPdfReaderBinding;
import com.github.barteksc.pdfviewer.PDFView;

public class PdfReaderActivity extends AppCompatActivity {
    private ActivityPdfReaderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdfReaderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String url = getIntent().getStringExtra("URL");
        if (url != null) {
            new Thread(() -> {
                try {
                    PDFView pdfView = binding.pdfView;
                    pdfView.fromUri(android.net.Uri.parse(url))
                            .enableSwipe(true)
                            .swipeHorizontal(false)
                            .enableDoubletap(true)
                            .load();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}