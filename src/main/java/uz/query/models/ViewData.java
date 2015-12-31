package uz.query.models;

import uz.query.Constants;

/**
 * Created by sherali on 12/23/15.
 */
public class ViewData {
    private String name;
    private String title;
    private String metaKeyword;
    private String metaDescription;
    private String viewLink;
    private String viewImage;

    public ViewData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return Constants.APP_DOMEN + " - " + this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMetaKeyword() {
        return metaKeyword;
    }

    public void setMetaKeyword(String metaKeyword) {
        this.metaKeyword = metaKeyword;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getViewLink() {
        return viewLink;
    }

    public void setViewLink(String viewLink) {
        this.viewLink = viewLink;
    }

    public String getViewImage() {
        return viewImage;
    }

    public void setViewImage(String viewImage) {
        this.viewImage = viewImage;
    }
}
