import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl, ValidatorFn } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-review',
  templateUrl: './search-review.component.html',
  styleUrls: ['./search-review.component.css']
})
export class SearchReviewComponent implements OnInit {
  form!: FormGroup
  searchValue?: String

  constructor(private fb: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.form = this.createForm()
  }

  private createForm(): FormGroup {
    return this.fb.group({
      searchValue: this.fb.control<string>('', [Validators.required, Validators.minLength(2), Validators.pattern(/^(\s+\S+\s*)*(?!\s).*$/)]),
    })
  }

  searchMovie() {
    const searchValue = this.form?.value['searchValue'].trim();
    console.log('searchValue --> ' + searchValue);
    this.router.navigate(['/search', searchValue]);
  }

  isControlInvalid(ctrlName: string): boolean {
    const ctrl = this.form.get(ctrlName) as FormControl
    return ctrl.invalid && (!ctrl.pristine)
  }

}
