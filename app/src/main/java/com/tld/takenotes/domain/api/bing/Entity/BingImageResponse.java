package com.tld.takenotes.domain.api.bing.Entity;

import java.util.List;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class BingImageResponse {

    private List<Image> images;
    private Tooltips tooltips;

}
