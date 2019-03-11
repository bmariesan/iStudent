package ro.ubb.domain;

import java.io.Serializable;

/**
 * Created by tudorstanila on 14/12/2017.
 */
public class News implements Serializable {
    private int id;
    private Person author;
    private String text;

    public News(int Id,Person author, String text) {
        this.id=Id;
        this.author = author;
        this.text = text;
    }

    public Person getAuthor() {
        return author;
    }

    public int getId(){return this.id;}

    public void setId(int id){this.id=id;}

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
                "id=" + id +
                ", author=" + author +
                ", text='" + text + '\'' +
                '}';
    }
}
