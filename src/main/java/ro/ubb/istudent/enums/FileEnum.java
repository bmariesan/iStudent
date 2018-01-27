package ro.ubb.istudent.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum FileEnum {
    JSON("json"),
    CSV("csv");

    private String type;

    public static FileEnum fromString(String fileType) {
        return Arrays.stream(FileEnum.values())
                .filter(type -> fileType.equalsIgnoreCase(type.getType()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Illegal file type inserted."));
    }
}
