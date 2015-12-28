package uz.query.models;

import org.hibernate.annotations.ColumnDefault;
import uz.query.models.base.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by Mirjalol Bahodirov on 11/28/15.
 */
@Entity(name = "my_user")
public class User extends BaseModel {

    private String email;
    private String displayName;
    @Column(columnDefinition = "text")
    private String password;

    @ColumnDefault(value = "0")
    private Integer reputation;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getReputation() {
        return reputation;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }
}
