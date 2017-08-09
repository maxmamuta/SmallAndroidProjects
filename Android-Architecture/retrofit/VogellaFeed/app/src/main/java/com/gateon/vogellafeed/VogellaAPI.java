package com.gateon.vogellafeed;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Maxim on 8/9/2017.
 */

public interface VogellaAPI {

    @GET("article.rss")
    Call<RSSFeed> loadRSSFeed();
}
