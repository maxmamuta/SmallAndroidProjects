package com.gateon.vogellafeed;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Maxim on 8/9/2017.
 */

public class Controller implements Callback<RSSFeed> {

    static final String BASE_URL = "http://vogella.com/";

    public void start() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create()).build();

        VogellaAPI vogellaAPI = retrofit.create(VogellaAPI.class);

        Call<RSSFeed> call = vogellaAPI.loadRSSFeed();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<RSSFeed> call, Response<RSSFeed> response) {
        if(response.isSuccessful()) {
            RSSFeed rss = response.body();
            System.out.println("Channel title: "+ rss.getChannelTitle());
            rss.getArticleList().forEach(article -> System.out.println("Title: " + article.getTitle() +
                                        article.getLink()));
        } else {
            System.out.println(response.body());
        }
    }

    @Override
    public void onFailure(Call<RSSFeed> call, Throwable t) {
        t.printStackTrace();
    }
}
