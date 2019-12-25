package com.tld.takenotes.domain.api.bing;

import com.tld.takenotes.domain.Constants;
import com.tld.takenotes.domain.api.bing.Entity.BingImageResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BingRetrofit {
    @GET(Constants.BING_API_DAILY_IMAGE)
    Call<BingImageResponse> getImage();
}
