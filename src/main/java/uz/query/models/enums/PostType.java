package uz.query.models.enums;

/**
 * Created by sherali on 12/30/15.
 */
public enum PostType {
    Article("0"),
    Question("1"),
    Answer("2"),
    Comment("3");


    private String name;
    private String id;

    PostType(String id) {
        this.id = id;
        this.name = this.toString();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
