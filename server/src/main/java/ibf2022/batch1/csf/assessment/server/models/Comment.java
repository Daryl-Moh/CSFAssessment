package ibf2022.batch1.csf.assessment.server.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Comment {
    //private String movieName;
    private String commentorName;
    private Integer rating;
    private String comments;

    public String getCommentorName() {
        return commentorName;
    }
    public void setCommentorName(String commentorName) {
        this.commentorName = commentorName;
    }
    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

    public JsonObject toJSON() {
        JsonObject o = Json.createObjectBuilder()
                //.add("movieName", getMovieName())
                .add("commentorName", getCommentorName())
                .add("rating", getRating())
                .add("comments", getComments())
                .build();
        return o;
    }
}
