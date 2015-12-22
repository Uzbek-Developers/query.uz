package uz.query.models;

import org.hibernate.annotations.ColumnDefault;
import uz.query.models.base.Post;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mirjalol Bahodirov on 12/14/15 with love.
 */
@Entity
@Table(name = "question")
public class Question extends Post {

    //todo quyidagi (voteCount, seenCount)fieldlar yangi logika bilan qayta yozlishi kerak
    @ColumnDefault(value = "0")
    private Integer voteCount = 0;
    @ColumnDefault(value = "0")
    private Integer seenCount = 0;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Tag> tags;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Answer> answers = new LinkedList<>();// LinkedList ko`p yozish uchun tez ishlaydi
    // hibernate bittalab listga add qiladi. Bu holatda LinkedList bo`lgani ma`qul

    public Integer getSeenCount() {
        return seenCount;
    }

    public void setSeenCount(Integer seenCount) {
        this.seenCount = seenCount;
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

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }
}
