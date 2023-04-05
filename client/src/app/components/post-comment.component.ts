import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MovieReviewService } from '../service/movie-review.service';
import { Review } from '../model/review';
import { HttpClient } from '@angular/common/http';
import { UploadResult } from '../model/upload-result';
import { firstValueFrom } from "rxjs";


@Component({
  selector: 'app-post-comment',
  templateUrl: './post-comment.component.html',
  styleUrls: ['./post-comment.component.css']
})
export class PostCommentComponent implements OnInit, OnDestroy {

  searchValue!: string
  movieReviews!: Review[];
  movieName = "temp";

  commentForm!: FormGroup

  constructor(private fb: FormBuilder, 
    private activatedRoute: ActivatedRoute,
    private movieRevSvc: MovieReviewService, 
    private router: Router,
    private httpClient: HttpClient) { }

  ngOnInit(): void {
    this.commentForm = this.createForm()
    
  }

  private createForm(): FormGroup {
    return this.fb.group({
      commentorName: this.fb.control<string>('', [Validators.required, Validators.minLength(3)]),
      rating: this.fb.control<number>(1, [Validators.required, Validators.min(1), Validators.max(5)]),
      comments: this.fb.control<string>('', [Validators.required]),
    })
  }

  ngOnDestroy(): void {
    console.log('[ngOnDestroy] >>> unsubscribe ');
  }

  goBack(): void {
    this.router.navigate(['/search', this.searchValue])
  }

  saveComment() {
    const formVal = this.commentForm.value;
    this.movieRevSvc.saveComment(formVal);
    this.router.navigate(['/search', this.searchValue])
  }
}
