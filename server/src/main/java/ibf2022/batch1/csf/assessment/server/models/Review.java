package ibf2022.batch1.csf.assessment.server.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

// DO NOT MODIFY THIS CLASS
public class Review {
	// display_title
	private String title;
	// mpaa_rating
	private String rating;
	// byline
	private String byline;
	// headline
	private String headline;
	// summary_short 
	private String summary;
	// link.url
	private String reviewURL;
	// multimedia.src
	private String image = null;

	private int commentCount = 0;

	public void setTitle(String title) { this.title = title; }
	public String getTitle() { return this.title; }

	public void setRating(String rating) { this.rating = rating; }
	public String getRating() { return this.rating; }

	public void setByline(String byline) { this.byline = byline; }
	public String getByline() { return this.byline; }

	public void setHeadline(String headline) { this.headline = headline; }
	public String getHeadline() { return this.headline; }

	public void setSummary(String summary) { this.summary = summary; }
	public String getSummary() { return this.summary; }

	public void setReviewURL(String reviewURL) { this.reviewURL = reviewURL; }
	public String getReviewURL() { return this.reviewURL; }

	public void setImage(String image) { this.image = image; }
	public String getImage() { return this.image; }
	public boolean hasImage() { return null != this.image; }

	public void setCommentCount(int commentCount) { this.commentCount = commentCount; };
	public int getCommentCount() { return this.commentCount; }

	public static Review create(JsonObject obj) {

		Review rev = new Review();
        JsonObject tempObj = obj.getJsonObject("link");

		if (obj.get("multimedia") != JsonValue.NULL) {
            JsonObject multimediaObj = obj.getJsonObject("multimedia");
            rev.setImage(multimediaObj.getString("src"));
        } else
			rev.setImage("placeholder.jpg");

		rev.title = obj.getString("display_title");
		rev.rating = obj.getString("mpaa_rating");
		rev.byline = obj.getString("byline");
		rev.headline= obj.getString("headline");
		rev.summary = obj.getString("summary_short");
		rev.reviewURL = tempObj.getString("url");
		
        System.out.println("rev.title >>> " + rev.title);
        System.out.println("rev.rating >>> " + rev.rating);
        System.out.println("rev.byline >>> " + rev.byline);
		System.out.println("rev.headline >>> " + rev.headline);
		System.out.println("rev.summary >>> " + rev.summary);
		System.out.println("rev.reviewURL >>> " + rev.reviewURL);
		System.out.println("rev.image >>> " + rev.image);

        return rev;
    }

	public static List<Review> createListOfMovReview (String json) throws IOException {
        List<Review> movRevList = new LinkedList<>();
		
        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
             JsonReader r = Json.createReader(is);
			 JsonObject obj = r.readObject();
			 movRevList = obj.getJsonArray("results").stream()
		 		.map(JsonValue::asJsonObject)
		 	 	.map(Review::create)
		 	 	.toList();
		}

		return movRevList;
    }

	public JsonObject toJSON() {
        JsonObject JsonObj = Json.createObjectBuilder()
            .add("title", getTitle())
            .add("rating", getRating())
            .add("byline", getByline())
            .add("headline", getHeadline())
			.add("summary", getSummary())
			.add("reviewURL", getReviewURL())
			.add("image", getImage())
            .build();
        return JsonObj;
    }

	@Override
	public String toString() {
		return "Review{title:%s, rating:%s}".formatted(title, rating);
	}
}
