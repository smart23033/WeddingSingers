package com.weddingsingers.wsapp.data;

import android.provider.BaseColumns;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tacademy on 2016-09-21.
 */
public class Alarm {
    private String userName;
    private String userImage;

    @SerializedName("singer_name")
    private String singerName;

    @SerializedName("singer_photoURL")
    private String singerImage;

    @SerializedName("customer_name")
    private String customerName;

    @SerializedName("customer_photoURL")
    private String customerImage;
    private String message;
    private int type;
    @SerializedName("data_id")
    private int dataId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSingerImage() {
        return singerImage;
    }

    public void setSingerImage(String singerImage) {
        this.singerImage = singerImage;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerImage() {
        return customerImage;
    }

    public void setCustomerImage(String customerImage) {
        this.customerImage = customerImage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }
}
