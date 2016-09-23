package net.thanatosx.bestpractice.bean;

import java.io.Serializable;

/**
 * Created by thanatos on 16/9/22.
 */

public class Article implements Serializable{

    private long id;
    private String title;
    private String content;
    private String bannerLink;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBannerLink() {
        return bannerLink;
    }

    public void setBannerLink(String bannerLink) {
        this.bannerLink = bannerLink;
    }
}
