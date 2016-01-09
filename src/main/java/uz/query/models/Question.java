package uz.query.models;

import org.hibernate.annotations.ColumnDefault;
import uz.query.models.base.Post;
import uz.query.models.enums.PostType;
import uz.query.models.enums.StatusType;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mirjalol Bahodirov on 12/14/15 with love.
 */
@Entity
public class Question extends Post {

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Tag> tags;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Answer> answers;// LinkedList ko`p yozish uchun tez ishlaydi
    // hibernate bittalab listga add qiladi. Bu holatda LinkedList bo`lgani ma`qul

    private StatusType statusType = StatusType.Opened;
    private String reason;
    private String statusLink;

    public Question() {
        this.setPostType(PostType.Question);
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



    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType type) {
        this.statusType = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatusLink() {
        return statusLink;
    }

    public void setStatusLink(String link) {
        this.statusLink = link;
    }
}

