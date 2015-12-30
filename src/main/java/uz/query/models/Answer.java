package uz.query.models;

import org.hibernate.annotations.ColumnDefault;
import uz.query.models.base.BaseModel;
import uz.query.models.base.Post;
import uz.query.models.enums.PostType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

/**
 * Created by Mirjalol Bahodirov on 12/14/15 with love.
 */
@Entity
public class Answer extends Post {

    @ColumnDefault(value = "0")
    private Integer voteCount = 0;
    @ColumnDefault(value = "0")
    private Integer seenCount = 0;

    @OneToOne(fetch = FetchType.LAZY)
    private Question parent;

    public Answer() {
        this.setPostType(PostType.Answer);
    }
    public Question getParent() {
        return parent;
    }

    public void setParent(Question parent) {
        this.parent = parent;
    }

    public Integer getSeenCount() {
        return seenCount;
    }

    public void setSeenCount(Integer seenCount) {
        this.seenCount = seenCount;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }
}
