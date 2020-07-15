package vnpt.binhduong.day3.activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import vnpt.binhduong.day3.R;
import vnpt.binhduong.day3.ApiManager;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detail );

        getSupportActionBar().setTitle( "Detail" );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        Intent intent = getIntent();
        String url = intent.getStringExtra( "URL" );
        String link = ApiManager.SERVER_URL + url;

        WebView webView = findViewById( R.id.webView );
        webView.getSettings().setJavaScriptEnabled( true );
         webView.setWebViewClient( new WebViewClient() );
        webView.loadUrl( link );
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected( item );
        }

    }
}
