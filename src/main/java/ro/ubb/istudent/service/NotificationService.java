package ro.ubb.istudent.service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.ubb.istudent.domain.StudentEntity;

public class NotificationService {
    private final SmsNotification smsNotificationService;
    private final PushNotification pushNotificationService;
    private final EmailNotification emailNotificationService;

    @Autowired
    public NotificationService(SmsNotification smsNotificationService, PushNotification pushNotificationService, EmailNotification emailNotificationService) {
        this.smsNotificationService = smsNotificationService;
        this.pushNotificationService = pushNotificationService;
        this.emailNotificationService = emailNotificationService;
    }

    public void sendNotification(Notification notification) {
        if (notification.getType().equals("sms"))
            smsNotificationService.sendNotification(notification);
        else if (notification.getType().equals("push")) {
            pushNotificationService.sendNotification(notification);
        } else {
            emailNotificationService.sendNotification(notification);
        }
    }
}
