package uz.query.models;

import uz.query.models.base.BaseModel;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sherali on 12/13/15.
 */
@Entity(name = "question")
public class Question extends BaseModel {
    private String title;
    private String description;
    private String shortDescription;
    //    private List<Tag> tagList = new ArrayList<Tag>();
    private Integer userId;


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

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

//    public List<Tag> getTagList() {
//        return tagList;
//    }
//
//    public void setTagList(List<Tag> tagList) {
//        this.tagList = tagList;
//    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
