package uz.query.models;

import uz.query.models.enums.StatusType;

import java.util.List;

/**
 * Created by Mirjalol Bahodirov on 12/14/15 with love.
 */
public class PostStatus {
    private StatusType type = StatusType.Opened;
    private String reason = "shunchaki";

    private User owner;
    private List<User> acceptableUsers;

    public PostStatus(User owner) {
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<User> getAcceptableUsers() {
        return acceptableUsers;
    }

    public void setAcceptableUsers(List<User> acceptableUsers) {
        this.acceptableUsers = acceptableUsers;
    }

    public StatusType getType() {
        return type;
    }

    public void setType(StatusType type) {
        this.type = type;
    }
}
