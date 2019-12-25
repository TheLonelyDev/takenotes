package com.tld.takenotes.domain.api.bing;

public interface BingSource {
    Observable<BingImageResponse> getImage();
}
