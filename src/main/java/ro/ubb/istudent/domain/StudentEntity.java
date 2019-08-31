package ro.ubb.istudent.domain;

import com.sun.tools.javac.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

@Document
public class StudentEntity implements Serializable{
    @Id
    private ObjectId id;

    private String notificationType = "sms";

    private static final ArrayList<String> notificationTypes = new ArrayList<>(Arrays.asList("email", "sms", "push"));

    public String getNotificationType() {
        return notificationType;
    }
}
