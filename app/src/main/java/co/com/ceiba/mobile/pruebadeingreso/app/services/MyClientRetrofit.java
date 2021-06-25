package co.com.ceiba.mobile.pruebadeingreso.app.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyClientRetrofit {


    private static MyClientRetrofit myClient;
    private Retrofit retrofit;

    private MyClientRetrofit(){
        retrofit=new Retrofit.Builder().baseUrl(Endpoints.URL_BASE).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized MyClientRetrofit getInstance(){
        if (myClient==null){
            myClient=new MyClientRetrofit();
        }
        return myClient;
    }

    public ApiData apiData() {
        return retrofit.create(ApiData.class);
    }

}


