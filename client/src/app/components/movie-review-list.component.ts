import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { MovieReviewService } from '../service/movie-review.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Review } from '../model/review';

@Component({
  selector: 'app-movie-review-list',
  templateUrl: './movie-review-list.component.html',
  styleUrls: ['./movie-review-list.component.css']
})
export class MovieReviewListComponent implements OnInit, OnDestroy {
  searchValue = "";
  param$!: Subscription;
  movieReviews!: Review[];
  commentCount: number = 0;
  movieName = "";

  constructor(private activatedRoute: ActivatedRoute,
    private movieRevSvc: MovieReviewService, 
    private router: Router) { }

  ngOnInit(): void {
    this.param$ = this.activatedRoute.params.subscribe(
      async (params) => {
        this.searchValue = params['searchValue'];
        console.log('[ngOnInit] >>> this.charName = ' + this.searchValue);
        const listOfMovieReviews = await this.movieRevSvc.getMovieReviews(this.searchValue);
        console.log('[ngOnInit] >>> movieRevSvc.getMovieReviews >>> ' + listOfMovieReviews);
        if (listOfMovieReviews === undefined || listOfMovieReviews.length == 0) {
          this.router.navigate(['/'])
        } else {
          this.movieReviews = listOfMovieReviews;
        }
        //this.commentCount = this.movieRevSvc.getReviewCount(this.searchValue);
      }
    );
  }

  ngOnDestroy(): void {
    console.log('[ngOnDestroy] >>> unsubscribe ');
    this.param$.unsubscribe();
  }

  goHome(){
    console.log('[goHome] >>> navigating to / ');
    this.router.navigate(['/']);
  }

  postComment(movieName: any): void{
    this.movieName = movieName;
    console.log('button pressed >>> ' + this.movieName);
    this.router.navigate(['/comment'])
  }
}
