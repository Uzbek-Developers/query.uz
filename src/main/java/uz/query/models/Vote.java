package uz.query.models;

import org.hibernate.annotations.ColumnDefault;
import uz.query.models.base.BaseModel;

import javax.persistence.*;
import java.util.List;

/**
 * Created by sherali on 12/28/15.
 */
@Entity
public class Vote extends BaseModel {

    @OneToOne(fetch = FetchType.LAZY)
    private User owner;
    @ColumnDefault(value = "0")
    private int rank;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
