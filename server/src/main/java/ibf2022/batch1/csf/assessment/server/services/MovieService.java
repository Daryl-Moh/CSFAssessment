package ibf2022.batch1.csf.assessment.server.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.batch1.csf.assessment.server.models.Review;;

@Service
public class MovieService {

	@Autowired
	private MovieApiService movieApiSvc;

	// TODO: Task 4
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	public List<Review> searchReviews(String query) throws IOException {
		return movieApiSvc.getReviews(query);
	}

}