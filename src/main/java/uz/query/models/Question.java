package uz.query.models;

import org.hibernate.annotations.ColumnDefault;
import uz.query.models.base.BaseModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mirjalol Bahodirov on 12/14/15 with love.
 */
@Entity
public class Question extends BaseModel {

    @Column(columnDefinition = "text")
    private String questionTitle;
    @Column(columnDefinition = "text")
    private String questionContent;

    //todo quyidagi (voteCount, seenCount)fieldlar yangi logika bilan qayta yozlishi kerak
    @ColumnDefault(value = "0")
    private Integer voteCount = 0;
    @ColumnDefault(value = "0")
    private Integer seenCount = 0;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User questionOwner = new User();
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Tag> tags = new ArrayList<Tag>();
    @OneToMany(fetch = FetchType.LAZY)
    private List<Answer> answers = new LinkedList<>();// LinkedList ko`p yozish uchun tez ishlaydi
    // hibernate bittalab listga add qiladi. Bu holatda LinkedList bo`lgani ma`qul

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getSeenCount() {
        return seenCount;
    }

    public void setSeenCount(Integer seenCount) {
        this.seenCount = seenCount;
    }

    public User getQuestionOwner() {
        return questionOwner;
    }

    public void setQuestionOwner(User questionOwner) {
        this.questionOwner = questionOwner;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
