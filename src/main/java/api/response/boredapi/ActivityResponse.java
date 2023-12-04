package api.response.boredapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ActivityResponse {
    @Expose
    @SerializedName("activity")
    String activity;
    @Expose
    @SerializedName("type")
    String type;
    @Expose
    @SerializedName("participants")
    Integer participants;
    @Expose
    @SerializedName("price")
    Double price;
    @Expose
    @SerializedName("link")
    String link;
    @Expose
    @SerializedName("key")
    String key;
    @Expose
    @SerializedName("accessibility")
    Double accessibility;
}
