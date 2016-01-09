package uz.query.models;

import org.hibernate.annotations.ColumnDefault;
import uz.query.models.base.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mirjalol Bahodirov on 11/28/15.
 */
@Entity(name = "my_user")
public class User extends BaseModel {

    private String email;
    private String displayName;
    private String job;
    private String link;
    @Column(columnDefinition = "text")
    private String about;
    @Column(columnDefinition = "text")
    private String password;    

    @OneToMany(fetch = FetchType.LAZY)
    @Column(nullable = true)
    private List<Tag> skills = new LinkedList<>();

    @ColumnDefault(value = "0")
    private Integer reputation = 0;

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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<Tag> getSkills() {
        return skills;
    }

    public void setSkills(List<Tag> skills) {
        this.skills = skills;
    }
}
