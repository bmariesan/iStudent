package ro.ubb.istudent.dto;


/**
 * Created by catablack.
 */
public class NewDto implements Dto {

    String id;
    String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
