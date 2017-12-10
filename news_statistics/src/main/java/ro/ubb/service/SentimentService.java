package ro.ubb.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.ubb.dto.SAResponseDTO;

@Service
public class SentimentService {

    public SAResponseDTO getSentimentsFor(String text){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> request = new HttpEntity<>("language=english&text=" + text, headers);
        return restTemplate
                .postForObject("http://text-processing.com/api/sentiment/", request, SAResponseDTO.class);
    }
}
