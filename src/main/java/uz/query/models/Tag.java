package uz.query.models;

import uz.query.models.base.BaseModel;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Mirjalol Bahodirov on 12/14/15 with love.
 */
@Entity
public class Tag extends BaseModel {

    private String tagName;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Question> questions;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
