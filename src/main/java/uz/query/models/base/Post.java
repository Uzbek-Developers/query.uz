package uz.query.models.base;

import uz.query.models.User;
import uz.query.models.Vote;
import uz.query.models.enums.FlagType;
import uz.query.models.enums.PostType;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by sherali on 12/22/15.
 */
@MappedSuperclass
@Inheritance
public class Post extends BaseModel {

    @Column(columnDefinition = "text")
    private String title;
    @Column(columnDefinition = "text")
    private String content;
    @Column(columnDefinition = "text")
    private String editorContent;
    private String postLink;


    @Transient
    private PostType postType = PostType.Article;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User owner;
    @ManyToOne(fetch = FetchType.LAZY)
    private User editor;

    private FlagType flagType = FlagType.None;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Vote> votes;

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEditorContent() {
        return editorContent;
    }

    public void setEditorContent(String editorContent) {
        this.editorContent = editorContent;
    }

    public String getPostLink() {
        return postLink;
    }

    public void setPostLink(String postLink) {
        this.postLink = postLink;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getEditor() {
        return editor;
    }

    public void setEditor(User editor) {
        this.editor = editor;
    }

    public FlagType getFlagType() {
        return flagType;
    }

    public void setFlagType(FlagType flagType) {
        this.flagType = flagType;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public int voteRank() {
        Stream<Vote> list = votes.stream().filter(v -> v.getOwner().getId() == owner.getId());
        return list.count() == 0 ? 0 : list.findFirst().get().getRank();
    }
}
