package main;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "dummies")
public class DummyClass implements Serializable{
    @Id
    Long id;
    String field1;

    public DummyClass(Long id, String field1) {
        this.id = id;
        this.field1 = field1;
    }
}
