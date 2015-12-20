package uz.query.models;

import org.hibernate.annotations.ColumnDefault;
import uz.query.models.base.BaseModel;

import javax.persistence.Entity;

/**
 * Created by Mirjalol Bahodirov on 11/28/15.
 */
@Entity(name = "my_user")
public class User extends BaseModel {

    private String fullName;
    private String userName;
    private String password;
    private String email;

    @ColumnDefault(value = "0")
    private Integer reputation;

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
