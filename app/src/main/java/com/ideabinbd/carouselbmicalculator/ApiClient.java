package com.ideabinbd.carouselbmicalculator;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ramim on 2/25/2018.
 */

public class ApiClient {
    public static final String BASEURL="http://10.0.3.2/mycrud/";

    public static Retrofit retrofit=null;

    public static Retrofit getApiClient(){
        if(retrofit==null){
            retrofit= new Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
