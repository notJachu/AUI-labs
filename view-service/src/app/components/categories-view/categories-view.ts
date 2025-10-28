import {Component, inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-categories-view',
  imports: [],
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
}
