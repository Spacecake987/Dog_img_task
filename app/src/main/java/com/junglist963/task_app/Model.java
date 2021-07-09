package com.junglist963.task_app;

import java.util.List;

public class Model {
    private String imgUrl;

    public Model(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "List{" +
                "imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
