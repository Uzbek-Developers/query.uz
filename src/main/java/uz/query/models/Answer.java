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
}
