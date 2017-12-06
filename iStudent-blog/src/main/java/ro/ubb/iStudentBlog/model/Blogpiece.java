package ro.ubb.iStudentBlog.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by Cata on 12/6/2017.
 */

@Document(collection = "blogpiece")
public class Blogpiece {

    @Id
    private UUID uuid;
    private String content;
    //here it should be a User class but that will change once auth is finished
    private String user;
    private Integer rating;

    public Blogpiece() {
    }

    public Blogpiece( String content, String user, Integer rating) {
        this.uuid = UUID.randomUUID();
        this.content = content;
        this.user = user;
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Blogpiece blogpiece = (Blogpiece) o;
        return Objects.equals(uuid, blogpiece.uuid) &&
                Objects.equals(content, blogpiece.content);


    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid,content);
    }

    @Override
    public String toString() {
        return "ro.ubb.iStudentBlog.model.Blogpiece{" +
                "content='" + content + '\'' +
                ", user='" + user + '\'' +
                ", rating=" + rating +
                '}';
    }
}
