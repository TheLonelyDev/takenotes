package com.tld.takenotes.domain.api.bing;

import com.tld.takenotes.domain.api.bing.Entity.BingImageResponse;

import retrofit2.Call;
import retrofit2.Retrofit;

public class BingHandler implements BingInterface {
    BingRetrofit bingRetrofit;

    public BingHandler(Retrofit retrofit) {
        bingRetrofit = retrofit.create(BingRetrofit.class);
    }

    @Override
    public Call<BingImageResponse> getImage() {
        return bingRetrofit.getImage();
    }
}
