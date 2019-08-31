package ro.ubb.istudent.service;

import org.springframework.stereotype.Service;

@Service
public interface NotificationInterface {
    public void sendNotification(Notification notification);
}
