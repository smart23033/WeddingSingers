package com.weddingsingers.wsapp.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Tacademy on 2016-09-07.
 */
public class SingerVideoAdd implements Serializable {
    /*title - 노래 제목
    url - 동영상 URL
    write_dtime - 작성 날짜 (string)
    hash - 해쉬태그 (배열, string)*/

    String title;

    String url;

    @SerializedName("write_dtime")
    String writeDtime;

    ArrayList<String> hash;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWriteDtime() {
        return writeDtime;
    }

    public void setWriteDtime(String writeDtime) {
        this.writeDtime = writeDtime;
    }

    public ArrayList<String> getHash() {
        return hash;
    }

    public void setHash(ArrayList<String> hash) {
        this.hash = hash;
    }
}
