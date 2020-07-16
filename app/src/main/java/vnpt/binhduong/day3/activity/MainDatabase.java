package vnpt.binhduong.day3.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vnpt.binhduong.day3.ApiManager;
import vnpt.binhduong.day3.R;
import vnpt.binhduong.day3.database.AppDatabase;
import vnpt.binhduong.day3.database.BookmarkEntity;
import vnpt.binhduong.day3.model.Item;


public class MainDatabase extends AppCompatActivity {

    AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main_database );


        db = AppDatabase.getAppDatabase( this );
        // getData();

        insertBookmark();

        // updateBookmark(  );

        getAllBookmark();

    }

    private void insertBookmark() {
        BookmarkEntity bm = new BookmarkEntity();
        bm.title = "this is title!";
        bm.content = "this is content";
        long id = db.bookmarkDao().insertBookmark( bm );
        Toast.makeText( this, "ID: " + id, Toast.LENGTH_SHORT ).show();
    }

    private void updateBookmark(int id) {
        BookmarkEntity bm = db.bookmarkDao().getBookmark( id );
        bm.title = "this is title";
        db.bookmarkDao().updateBookmark( bm );
    }

    private void findBookmark(int id) {
        BookmarkEntity model = db.bookmarkDao().getBookmark( id );
        Log.d( "TAG", "find bookmark with id: " + model.id + ", title: " + model.title );
    }

    private void deleteBookmark(int id) {
        BookmarkEntity model = db.bookmarkDao().getBookmark( id );
        db.bookmarkDao().deleteBookmark( model );
    }

    private void deleteAllBookmark() {
        db.bookmarkDao().deleteAll();
    }

    private void getAllBookmark() {
        List<BookmarkEntity> list = db.bookmarkDao().getAllBookmark();
        for (BookmarkEntity model : list){
            Log.d( "TAG", "id: "+ model.id + " , title: " + model.title );
        }
    }
}
