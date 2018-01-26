package ro.ubb.model;

import java.util.Date;

public class PostBuilder {
    private Post post;

    public PostBuilder() {
        this.post = post;
    }

    public PostBuilder withUser(String user){
        post.setAuthor(user);
        return this;
    }

    public PostBuilder withCreatedAt(Date created_at){
        post.setCreated_at(created_at);
        return this;
    }

    public PostBuilder withUpdatedAt(Date updated_at){
        post.setUpdated_at(updated_at);
        return this;
    }

    public PostBuilder withComment(String comment){
        post.addComment(comment);
        return this;
    }

}
