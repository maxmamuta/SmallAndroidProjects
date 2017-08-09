package com.gateon.querrygerrit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Maxim on 8/9/2017.
 */

public interface GerritAPI {

    @GET("changes")
    Call<List<Change>> loadChanges(@Query("q") String status);
}
