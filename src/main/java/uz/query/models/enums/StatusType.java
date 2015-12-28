package uz.query.models.enums;

/**
 * Created by sherali on 12/28/15.
 */
public enum StatusType {
    Opened("Opened"),
    OnHold("Uslab turildi"),
    Dublicated("Nusxa olindi"),
    Closed("Yopildi"),
    Protected("Himoyalandi"),
    Translated("Tarjima qilindi");

    String title;
    String description;
    String url;

    StatusType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
