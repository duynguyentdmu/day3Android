package vnpt.binhduong.day3.activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import vnpt.binhduong.day3.R;
import vnpt.binhduong.day3.ApiManager;
import vnpt.binhduong.day3.database.AppDatabase;
import vnpt.binhduong.day3.database.BookmarkEntity;
import vnpt.binhduong.day3.model.Item;

public class DetailActivity extends AppCompatActivity {
    AppDatabase db;
    Item model;
    FloatingActionButton btBookmark;
    boolean isBookmark = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detail );


        db = AppDatabase.getAppDatabase( this );
        initView();
        checkBookmark();
    }

    private void initView() {
        getSupportActionBar().setTitle( "Detail" );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        btBookmark = findViewById( R.id.btBookmark );
        btBookmark.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBookmark) {
                    deleteBookmark();
                    btBookmark.setImageResource( R.drawable.ic_bookmark );
                } else {
                    addBookmark();
                    btBookmark.setImageResource( R.drawable.ic_bookmark_fill );
                }
                isBookmark = !isBookmark;
            }
        } );

        Intent intent = getIntent();
        String url = intent.getStringExtra( "URL" );
        String link = ApiManager.SERVER_URL + url;

        model = (Item) intent.getSerializableExtra( "ITEM" );

        WebView webView = findViewById( R.id.webView );
        webView.getSettings().setJavaScriptEnabled( true );
        webView.setWebViewClient( new WebViewClient() );
        webView.loadUrl( link );

    }


    private void addBookmark() {
        BookmarkEntity bookmarkEntity = new BookmarkEntity();
        bookmarkEntity.id = model.getId();
        bookmarkEntity.content = model.getContent().getDescription();
        bookmarkEntity.date = model.getDate();
        bookmarkEntity.image = model.getImage();
        bookmarkEntity.title = model.getTitle();
        bookmarkEntity.url = model.getContent().getUrl();
        long id = db.bookmarkDao().insertBookmark( bookmarkEntity );
        Toast.makeText( this, "Lưu bookmark bài viết " + bookmarkEntity.title + " thành công", Toast.LENGTH_LONG ).show();
    }

    private void deleteBookmark() {
        BookmarkEntity bookmarkEntity = db.bookmarkDao().getBookmark( model.getId() );
        db.bookmarkDao().deleteBookmark( bookmarkEntity );
        Toast.makeText( this, "Hủy bookmark bài viết " + bookmarkEntity.title + " thành công", Toast.LENGTH_LONG ).show();
    }

    private void checkBookmark() {
        BookmarkEntity bookmarkEntity = db.bookmarkDao().getBookmark( model.getId() );

        if (bookmarkEntity != null) {
            btBookmark.setImageResource( R.drawable.ic_bookmark_fill );
            isBookmark = true;
        } else {
            btBookmark.setImageResource( R.drawable.ic_bookmark );
            isBookmark = false;
        }
        // db.bookmarkDao().deleteBookmark( bookmarkEntity );
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
