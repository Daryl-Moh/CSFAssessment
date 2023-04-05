import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { lastValueFrom } from "rxjs";
import { Review } from "../model/review";
import { Comment } from "../model/comment"

@Injectable({
    providedIn: 'root'
  })
  export class MovieReviewService {
    
    private API_URI: string = "/api";

    constructor (private httpClient: HttpClient) { }

    getMovieReviews(searchValue: string): Promise<any>{
        const params = new HttpParams()
            .set("searchValue", searchValue)
        const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
        console.log('[getCharacters] >>> params = ' + params);
        console.log('[getCharacters] >>> headers = ' + headers);

        return lastValueFrom(this.httpClient
            .get<Review[]>(this.API_URI, {params: params, headers: headers}));
      }

    // saveComment(c:Comment) : Promise<any>{
    //     const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    //     const body=JSON.stringify(c);
    //     console.log('[saveComent] >>> ' + body);
    //     return lastValueFrom(this.httpClient.post<Comment>(this.API_URI+"/comment", body, {headers: headers}));
    //   }

    saveComment(comment: Comment): Promise<any> {
      const headers = new HttpHeaders()
        .set('Content-Type', 'application/x-www-form-urlencoded');
  
      const body = new HttpParams()
        .set("commentorName", comment.commentorName)
        .set("rating", comment.rating)
        .set("comments", comment.comments);
  
      return lastValueFrom(this.httpClient
        .post<Comment>(this.API_URI+"/comment", body.toString(), { headers }));
    }

    getReviewCount(movieName: string): Promise<any> {

      const headers = new HttpHeaders()
        .set('Content-Type', 'application/json; charset=utf-8');

      return lastValueFrom(this.httpClient
        .post<Comment>(this.API_URI+"/comment/count", { headers }));

    }
    
    
  }