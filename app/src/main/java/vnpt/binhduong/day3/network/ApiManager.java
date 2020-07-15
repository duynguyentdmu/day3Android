package vnpt.binhduong.day3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import vnpt.binhduong.day3.model.Item;

public interface ApiManager {

    String SERVER_URL = "https://api-demo-anhth.herokuapp.com/";

    @GET("data.json")
    Call<Item> getItemData();

    @GET("data.json")
    Call<List> getListData();
}
