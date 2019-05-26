package ro.ubb.istudent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.istudent.repository.ValidTokenRepository;

import java.util.Date;

@Component
public class ScheduledJobs {

    private final ValidTokenRepository validTokenRepository;

    @Autowired
    public ScheduledJobs(ValidTokenRepository validTokenRepository) {
        this.validTokenRepository = validTokenRepository;
    }

    @Scheduled(cron = "0 */30 * * * ?")
    @Transactional
    public void cleanUpTokenDB() {
        validTokenRepository.deleteByExpirationDateBefore(new Date());
    }

}
