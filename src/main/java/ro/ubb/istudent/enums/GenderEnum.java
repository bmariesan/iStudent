package ro.ubb.istudent.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum GenderEnum {
    MALE("male"),
    FEMALE("female");

    private String gender;

    public static GenderEnum fromString(String value) {
        return Arrays.stream(GenderEnum.values())
                .filter(gender -> value.equalsIgnoreCase(gender.getGender()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Illegal gender inserted."));
    }

}
