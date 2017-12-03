package ro.ubb.istudent.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {
    MALE("male"),
    FEMALE("female");

    private String gender;
}
