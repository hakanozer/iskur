
package models;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Campaign {

    @SerializedName("campaignTypeId")
    @Expose
    private String campaignTypeId;
    @SerializedName("campaignType")
    @Expose
    private String campaignType;

    public String getCampaignTypeId() {
        return campaignTypeId;
    }

    public void setCampaignTypeId(String campaignTypeId) {
        this.campaignTypeId = campaignTypeId;
    }

    public String getCampaignType() {
        return campaignType;
    }

    public void setCampaignType(String campaignType) {
        this.campaignType = campaignType;
    }

}
