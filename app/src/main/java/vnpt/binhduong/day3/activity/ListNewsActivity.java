package vnpt.binhduong.day3.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vnpt.binhduong.day3.R;
import vnpt.binhduong.day3.apdater.NewsAdapter;
import vnpt.binhduong.day3.interfaces.NewsOnClick;
import vnpt.binhduong.day3.model.Item;
import vnpt.binhduong.day3.ApiManager;


public class ListNewsActivity extends AppCompatActivity {

    RecyclerView rvListNews;
    List<Item> listData;
    NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_list_news );

        //B1: Data source
        getListData();

        //B2: Adapter
        listData = new ArrayList<>();
        adapter = new NewsAdapter( ListNewsActivity.this, listData );

        //B3: Layout Manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( this, RecyclerView.VERTICAL, false );

        //B4: RecyclerView
        rvListNews = findViewById( R.id.rvListNews );
        rvListNews.setLayoutManager( layoutManager );
        rvListNews.setAdapter( adapter );

        adapter.setiOnClick( new NewsOnClick() {
            @Override
            public void onClickItem(int position) {
                Item model = listData.get( position );
                Intent intent = new Intent( ListNewsActivity.this, DetailActivity.class );
                intent.putExtra( "URL", model.getContent().getUrl() );
                intent.putExtra ( "ITEM", model );
                startActivity( intent );
            }
        } );
    }

    private void getListData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( ApiManager.SERVER_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();


        ApiManager service = retrofit.create( ApiManager.class );
        service.getListData().enqueue( new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.body() != null) {
                    listData = response.body();
                    adapter.reloadData( listData );
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Toast.makeText( ListNewsActivity.this, "Fail", Toast.LENGTH_SHORT ).show();
            }
        } );


    }
}
