package uz.query.models;

import uz.query.models.base.BaseModel;

import javax.persistence.Entity;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sherali on 12/13/15.
 */
@Entity(name = "tag")
public class Tag extends BaseModel {

    private String title;
    private String shortDescription;
    private String description;
//    private Map<String, Object> associatedType = new HashMap<String, Object>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Map<String, Object> getAssociatedType() {
//        return associatedType;
//    }
//
//    public void setAssociatedType(Map<String, Object> associatedType) {
//        this.associatedType = associatedType;
//    }
}
