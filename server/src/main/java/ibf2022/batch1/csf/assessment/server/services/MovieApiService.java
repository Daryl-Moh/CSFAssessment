package ibf2022.batch1.csf.assessment.server.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.batch1.csf.assessment.server.models.Review;

@Service
public class MovieApiService {
    
    @Value("${csfassessment.movie.api.url}")
    private String movieApiUrl;

    @Value("${csfassessment.movie.api.key}")
    private String movieApiPublicKey;

    public List<Review> getReviews(String searchValue) throws IOException {

        ResponseEntity<String> resp = null;
        List<Review> listOfReview = null;

        // System.out.println("movieApiPublicKey >>> " + movieApiPublicKey);

        String movieReviewApiUrl = UriComponentsBuilder
                .fromUriString(movieApiUrl)
                .queryParam("query", searchValue.replaceAll(" ", "+"))
                .queryParam("api-key", movieApiPublicKey.trim())
                .toUriString();

        System.out.println(movieReviewApiUrl);

        RestTemplate template = new RestTemplate();

        resp = template.getForEntity(movieReviewApiUrl, String.class);

        // System.out.println("resp from movieApi >>> " + resp);
        
        listOfReview = Review.createListOfMovReview(resp.getBody());
     
        // System.out.println("listOfReview >>> " + listOfReview.toString());

        return listOfReview;
    }
}
