package ro.ubb.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.ubb.dto.SAResponseDTO;

import java.util.List;

@Service
public class SentimentService {

    public SAResponseDTO getSentimentsFor(List<String> texts){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        SAResponseDTO result = new SAResponseDTO();
        int count = 0;
        for(String text: texts) {
            HttpEntity<String> request = new HttpEntity<>("language=english&text=" + text, headers);
            SAResponseDTO partialRes = restTemplate
                    .postForObject("http://text-processing.com/api/sentiment/", request, SAResponseDTO.class);
            result.combineWith(partialRes, count);
            count++;
        }
        return result;

    }
}
