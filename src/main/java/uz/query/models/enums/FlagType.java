package uz.query.models.enums;

/**
 * Created by sherali on 12/31/15.
 */
public enum FlagType {
    Spam("spam", "Bu post spam"),
    Danger("xavf", "Sayt qoidalariga zid post"),
    Mistake("xato", "Bu postda xatolar juda ko`p");

    String name;
    String title;
    String description;

    FlagType(String title, String description) {
        this.title = title;
        this.description = description;
        this.name = this.toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
