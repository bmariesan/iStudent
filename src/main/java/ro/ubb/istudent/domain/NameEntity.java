package ro.ubb.istudent.domain;

import groovy.transform.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by Nicu on 25/01/2018.
 */

@ToString
@Embeddable
public class NameEntity extends BaseEntity {
    @Column(name = "name")
    private String name;

    public NameEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
