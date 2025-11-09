import {Component, inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-categories-view',
  imports: [
    RouterLink
  ],
  templateUrl: './categories-view.html',
  styleUrl: './categories-view.css',
})

@Injectable({providedIn: "root"})
export class CategoriesView {
  private http = inject(HttpClient);
  categories: any[] = [];

  constructor() {
    this.loadCategories();
  }

  loadCategories() {
    this.http.get<any[]>('http://localhost:8080/api/categories',
    ).subscribe(data => {
      this.categories = data;
    });
  }

  deleteCategory(id: number) {
    this.http.delete(`http://localhost:8080/api/categories/${id}`
    ).subscribe(() => {
      this.loadCategories();
    });
  }
}
