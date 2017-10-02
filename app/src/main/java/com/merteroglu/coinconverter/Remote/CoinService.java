package com.merteroglu.coinconverter.Remote;

import com.merteroglu.coinconverter.Model.Coin;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mert on 02.10.2017.
 */

public interface CoinService {

    @GET("data/price")
    Call<Coin> calculateValue(@Query("fsym") String from , @Query("tsyms") String to);


}
