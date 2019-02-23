
package com.example.rabinhowlader.interdcr.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserList {

    @SerializedName("product_group_list")
    @Expose
    private List<ProductGroupList> productGroupList = null;
    @SerializedName("literature_list")
    @Expose
    private List<LiteratureList> literatureList = null;
    @SerializedName("physician_sample_list")
    @Expose
    private List<PhysicianSampleList> physicianSampleList = null;
    @SerializedName("gift_list")
    @Expose
    private List<GiftList> giftList = null;

    public UserList(String productGroupList, List<String> literatureList) {

    }

    public List<ProductGroupList> getProductGroupList() {
        return productGroupList;
    }

    public void setProductGroupList(List<ProductGroupList> productGroupList) {
        this.productGroupList = productGroupList;
    }

    public List<LiteratureList> getLiteratureList() {
        return literatureList;
    }

    public void setLiteratureList(List<LiteratureList> literatureList) {
        this.literatureList = literatureList;
    }

    public List<PhysicianSampleList> getPhysicianSampleList() {
        return physicianSampleList;
    }

    public void setPhysicianSampleList(List<PhysicianSampleList> physicianSampleList) {
        this.physicianSampleList = physicianSampleList;
    }

    public List<GiftList> getGiftList() {
        return giftList;
    }

    public void setGiftList(List<GiftList> giftList) {
        this.giftList = giftList;
    }

}
