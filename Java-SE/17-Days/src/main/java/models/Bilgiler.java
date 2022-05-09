
package models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Bilgiler {

    @SerializedName("productId")
    @Expose
    private String productId;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("brief")
    @Expose
    private String brief;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("saleInformation")
    @Expose
    private SaleInformation saleInformation;
    @SerializedName("campaign")
    @Expose
    private Campaign campaign;
    @SerializedName("campaignTitle")
    @Expose
    private String campaignTitle;
    @SerializedName("campaignDescription")
    @Expose
    private String campaignDescription;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("image")
    @Expose
    private Boolean image;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("likes")
    @Expose
    private Likes likes;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public SaleInformation getSaleInformation() {
        return saleInformation;
    }

    public void setSaleInformation(SaleInformation saleInformation) {
        this.saleInformation = saleInformation;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public String getCampaignTitle() {
        return campaignTitle;
    }

    public void setCampaignTitle(String campaignTitle) {
        this.campaignTitle = campaignTitle;
    }

    public String getCampaignDescription() {
        return campaignDescription;
    }

    public void setCampaignDescription(String campaignDescription) {
        this.campaignDescription = campaignDescription;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Boolean getImage() {
        return image;
    }

    public void setImage(Boolean image) {
        this.image = image;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

}
