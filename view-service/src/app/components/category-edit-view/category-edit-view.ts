import {Component, inject} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-category-edit-view',
  imports: [
    FormsModule
  ],
  templateUrl: './category-edit-view.html',
  styleUrl: './category-edit-view.css',
})
export class CategoryEditView {
  private http = inject(HttpClient);
  private router = inject(Router);
  category: any = null;
  private route = inject(ActivatedRoute)

  constructor() {
    this.loadCategory();
  }

  loadCategory() {
    const categoryId = this.route.snapshot.paramMap.get('id');
    this.http.get<any>('/api/categories/' + categoryId,
    ).subscribe(data => {
      this.category = data;
    });
  }

  onSubmit() {
    const categoryId = this.route.snapshot.paramMap.get('id');
    this.http.put<any>('/api/categories/' + categoryId, this.category).subscribe(data => {
      this.category = data;
      alert('Category updated successfully!');
      this.router.navigate(['/categories'])
    });

  }
}
