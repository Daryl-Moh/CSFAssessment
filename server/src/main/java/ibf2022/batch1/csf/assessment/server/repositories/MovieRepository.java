package ibf2022.batch1.csf.assessment.server.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.batch1.csf.assessment.server.models.Comment;

@Repository
public class MovieRepository {

	@Autowired
    private MongoTemplate mongoTemplate;

	private static final String COMMENTS_COL = "comments";

	// TODO: Task 5
	// You may modify the parameter but not the return type
	// Write the native mongo database query in the comment below
	// 
	// Mongo native database query
	// db.comments.aggregate([
		// {
		// 	$match: { movieName: "<movie name>" }
		//   },
		//   {
		// 	$group: { count: { $sum: 1 } }
		//   }

	public int countComments(Object param) {
		
		
		return 0;
	}

	// TODO: Task 8
	// Write a method to insert movie comments comments collection
	// Write the native mongo database query in the comment below
	// 
	// Mongo native database query
	// db.comments.insertOne(
	// 	{ "movieName": "The Pale Blue Eye", 
	// 	"commentorName": "Daryl", "rating": 4, 
	// 	"comments": "This is a movie I never heard of" })

	public Comment insertComment(Comment comment) {
        System.out.println("Inserting into MongoDB >>> " + comment.toString());
        return mongoTemplate.insert(comment, COMMENTS_COL);
    }
}
