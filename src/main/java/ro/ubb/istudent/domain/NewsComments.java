package ro.ubb.istudent.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/**
 * Created by catablack.
 */
@Document(collection = "newComments")
public class NewsComments {
    @Id
    private ObjectId id;
    private String newsId;
    private String message;
    private String posterName;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsComments that = (NewsComments) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(newsId, that.newsId) &&
                Objects.equals(message, that.message) &&
                Objects.equals(posterName, that.posterName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, newsId, message, posterName);
    }
}
