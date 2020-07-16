package vnpt.binhduong.day3;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import vnpt.binhduong.day3.model.Item;
import vnpt.binhduong.day3.model.Wheather;

public interface ApiManager {

    String SERVER_URL = "https://api-demo-anhth.herokuapp.com/";
    public static String BASE_URL = "http://dataservice.accuweather.com";
    //"/forecasts/v1/hourly/12hour/353412?apikey=tbFOLXfZmAxAexEY0mXhcxnbZBDjQBSh&language=vi-vn&metric=true"
    //"/forecasts/v1/daily/5day/353412?apikey=tbFOLXfZmAxAexEY0mXhcxnbZBDjQBSh&language=vi-vn&metric=true"
    @GET("data.json")
    Call<Item> getItemData();

    @GET("datas.json")
    Call<List<Item>> getListData();

    @GET("/forecasts/v1/hourly/12hour/353412?apikey=tbFOLXfZmAxAexEY0mXhcxnbZBDjQBSh&language=vi-vn&metric=true")
    Call<List<Wheather>> gethour();

    @GET("/forecasts/v1/daily/5day/353412?apikey=tbFOLXfZmAxAexEY0mXhcxnbZBDjQBSh&language=vi-vn&metric=true")
    Call<List<Wheather>> getDay();


}
