package uz.query.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.ColumnDefault;
import uz.query.models.base.Post;

import javax.persistence.*;

/**
 * Created by Mirjalol Bahodirov on 12/14/15 with love.
 */
@Entity
@JsonIgnoreProperties({"parentArticle", "owner"})
public class Article extends Post {

    @ManyToOne(fetch = FetchType.LAZY)
    private Article parentArticle;

    public Article getParentArticle() {
        return parentArticle;
    }

    public void setParentArticle(Article childArticle) {
        this.parentArticle = childArticle;
    }
}

