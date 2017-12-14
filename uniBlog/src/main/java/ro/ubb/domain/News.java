package ro.ubb.domain;

import java.io.Serializable;

/**
 * Created by tudorstanila on 14/12/2017.
 */
public class News implements Serializable {

    private Person author;
    private String text;

    public News(Person author, String text) {
        this.author = author;
        this.text = text;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "News{" +
                "author=" + author +
                ", text='" + text + '\'' +
                '}';
    }
}
