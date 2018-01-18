package ro.ubb.istudent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailNotification implements NotificationInterface {
    @Override
    public void sendNotification(Notification notification) {

    }
}
