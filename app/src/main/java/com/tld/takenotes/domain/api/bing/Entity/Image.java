
package com.tld.takenotes.domain.api.bing.Entity;

import java.util.List;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Image {

    private long bot;
    private String copyright;
    private String copyrightlink;
    private long drk;
    private String enddate;
    private String fullstartdate;
    private List<Object> hs;
    private String hsh;
    private String quiz;
    private String startdate;
    private String title;
    private long top;
    private String url;
    private String urlbase;
    private Boolean wp;

}
