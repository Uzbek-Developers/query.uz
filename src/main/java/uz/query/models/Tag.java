package uz.query.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.ColumnDefault;
import uz.query.models.base.BaseModel;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Mirjalol Bahodirov on 12/14/15 with love.
 */
@Entity
@JsonIgnoreProperties({"questions", "tagOwner"})
public class Tag extends BaseModel {

    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Question> questions;

    @ColumnDefault(value = "0")
    private Integer questionCount;

    @ColumnDefault(value = "0")
    @JsonProperty(value = "stat")
    private Long statisticCount;

    @Column(columnDefinition = "text")
    @JsonProperty(value = "shortDesc")
    private String shortDescription;

    @Column(columnDefinition = "text")
    private String content;

    @Column(columnDefinition = "text")
    private String editorContent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User tagOwner;

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

    public Long getStatisticCount() {
        return statisticCount;
    }

    public void setStatisticCount(Long statisticCount) {
        this.statisticCount = statisticCount;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEditorContent() {
        return editorContent;
    }

    public void setEditorContent(String editorContent) {
        this.editorContent = editorContent;
    }
}
