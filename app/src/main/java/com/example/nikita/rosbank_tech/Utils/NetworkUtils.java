package com.example.nikita.rosbank_tech.Utils;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    private WebApi webApi;

    private static final String BASE_URL = "https://bankonline.azurewebsites.net/";

    public NetworkUtils(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
        webApi = retrofit.create(WebApi.class);
    }

    public WebApi getWebApi() {
        return webApi;
    }
}
