package com.ideabinbd;

import com.ideabinbd.carouselbmicalculator.BmiData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Ramim on 2/25/2018.
 */

public interface APIInterface {
    @POST("singledata.php")
    Call<List<BmiData>> getBmiData();

    @POST("createBMIData.php")
   Call<String> uploadBMIData(@Body BmiData bmiData);
}
