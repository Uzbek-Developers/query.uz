package uz.query.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.ColumnDefault;
import uz.query.models.base.Post;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Mirjalol Bahodirov on 12/14/15 with love.
 */
@Entity
@JsonIgnoreProperties({"questions", "owner", "editor", "editorContent","votes", "modifiedDate", "flagType"})
public class Tag extends Post {

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Question> questions;

    @ColumnDefault(value = "0")
    private Integer questionCount = 0;

    @ColumnDefault(value = "0")
    @JsonProperty(value = "stat")
    private Integer statisticCount = 0;

    @Column(columnDefinition = "text")
    @JsonProperty(value = "shortDesc")
    private String shortDescription;

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

    public Integer getStatisticCount() {
        return statisticCount;
    }

    public void setStatisticCount(Integer statisticCount) {
        this.statisticCount = statisticCount;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
