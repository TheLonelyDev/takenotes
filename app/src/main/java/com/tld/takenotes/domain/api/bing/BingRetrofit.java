package com.tld.takenotes.domain.api.bing;

import com.tld.takenotes.domain.api.bing.Entity.BingImageResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BingRetrofit {
    @GET("HPImageArchive.aspx?format=js&idx=0&n=1&mkt=en-US")
    Call<BingImageResponse> getImage();
}
