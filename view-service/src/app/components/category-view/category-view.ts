import {Component, inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-category-view',
  imports: [],
  templateUrl: './category-view.html',
  styleUrl: './category-view.css',
})

@Injectable({providedIn: "root"})
export class CategoryView {
  private http = inject(HttpClient);
  category: any = null;
  private route = inject(ActivatedRoute)

  constructor() {
    this.loadCategory();
  }

  loadCategory() {
    const categoryId = this.route.snapshot.paramMap.get('id');
    this.http.get<any>('http://localhost:8080/api/categories/' + categoryId,
    ).subscribe(data => {
      this.category = data;
    });
  }
}
