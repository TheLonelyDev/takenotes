package com.tld.takenotes.domain.api.bing;

import com.tld.takenotes.domain.api.bing.Entity.BingImageResponse;

import retrofit2.Call;

public interface BingInterface {
    Call<BingImageResponse> getImage();
}
