package ibf2022.batch1.csf.assessment.server.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch1.csf.assessment.server.models.Comment;
import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.repositories.MovieRepository;
import ibf2022.batch1.csf.assessment.server.services.MovieService;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

	@Autowired
	private MovieService movieSvc;

	@Autowired
	private MovieRepository movieRepo;

	@GetMapping
	public ResponseEntity<String> getReviews(@RequestParam(required=true) String searchValue) throws IOException {

        JsonArray result = null;
		
        List<Review> ListReview = this.movieSvc.searchReviews(searchValue);

        //List<Review> results = ListReview;
		
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

        for (Review r : ListReview)
            arrBuilder.add(r.toJSON());

        result = arrBuilder.build();

		System.out.println("result >>> " + result.toString() );

        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(result.toString());

    }

    @GetMapping(path="/comment/count")
    public ResponseEntity<String> getCount (@RequestParam(required=true) String movieName) throws IOException {

        Integer result = 0;

        result = this.movieRepo.countComments(movieName);

        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(result.toString());

    }

	@PostMapping(path="/comment", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String> saveMovieComment(Comment comment) {
        Comment c = new Comment();
        // c.setMovieName(comment.getMovieName());
		c.setCommentorName(comment.getCommentorName());
		c.setRating(comment.getRating());
		c.setComments(comment.getComments());

        Comment r = this.movieRepo.insertComment(c);

        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(r.toJSON().toString());
    }

	// TODO: Task 4, 
	
	// TODO: Task 8
	
}


