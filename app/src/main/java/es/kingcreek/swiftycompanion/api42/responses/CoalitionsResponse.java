package es.kingcreek.swiftycompanion.api42.responses;

import com.google.gson.annotations.SerializedName;

public class CoalitionsResponse  {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("slug")
    private String slug;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("color")
    private String color;

    @SerializedName("score")
    private int score;

    @SerializedName("user_id")
    private int userId;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getColor() {
        return color;
    }

    public int getScore() {
        return score;
    }

    public int getUserId() {
        return userId;
    }
}