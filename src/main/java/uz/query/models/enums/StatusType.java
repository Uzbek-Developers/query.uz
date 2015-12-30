package uz.query.models.enums;

/**
 * Created by sherali on 12/28/15.
 */
public enum StatusType {
    Opened("Ochildi"),
    OnHold("Uslab turildi"),
    Dublicated("Nusxa olindi"),
    Closed("Yopildi"),
    Protected("Himoyalandi"),
    Translated("Tarjima qilindi");

    String name;
    String title;
    String description;
    String url;

    StatusType(String title) {
        this.title = title;
        this.name = this.toString();
    }

    public static StatusType getTypeByName(String name) {
        for (StatusType type : StatusType.values()) {
            if (name.equals(type.getName())) {
                return type;
            }
        }
        return Opened;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
