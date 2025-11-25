import {Component, inject} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-element-view',
  imports: [],
  templateUrl: './element-view.html',
  styleUrl: './element-view.css',
})
export class ElementView {
  private http = inject(HttpClient);
  private route = inject(ActivatedRoute)
  element: any = null;

  constructor() {
    this.loadElement();
  }

  loadElement() {
    const elementId = this.route.snapshot.paramMap.get('elementId');
    this.http.get<any>('http://localhost:8080/api/wheelbarrows/' + elementId,
    ).subscribe(data => {
      this.element = data;
    });
  }
}
