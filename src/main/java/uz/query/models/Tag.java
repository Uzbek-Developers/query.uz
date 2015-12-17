package uz.query.models;

import org.hibernate.annotations.ColumnDefault;
import uz.query.models.base.BaseModel;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

/**
 * Created by Mirjalol Bahodirov on 12/14/15 with love.
 */
@Entity
public class Tag extends BaseModel {

    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Question> questions;

    @ColumnDefault(value = "0")
    private Integer questionCount = 0;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User tagOwner = new User();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Integer getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Integer questionCount) {
        this.questionCount = questionCount;
    }

    public User getTagOwner() {
        return tagOwner;
    }

    public void setTagOwner(User tagOwner) {
        this.tagOwner = tagOwner;
    }
}
