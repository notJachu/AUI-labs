import {Component, inject} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-category-create-view',
  imports: [
    FormsModule
  ],
  templateUrl: './category-create-view.html',
  styleUrl: './category-create-view.css',
})
export class CategoryCreateView {
  private http = inject(HttpClient);
  private router = inject(Router)
  category: any = {
    name: '',
    carryWeight: 0
  };

  onSubmit() {
    this.http.post<any>('http://localhost:8080/api/categories', this.category).subscribe(data => {
      this.category = data;
      alert('Category created successfully!');
      this.router.navigate(['/categories'])
    });
  }
}
