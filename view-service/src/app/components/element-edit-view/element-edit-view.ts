import {Component, inject} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-element-edit-view',
  imports: [
    FormsModule
  ],
  templateUrl: './element-edit-view.html',
  styleUrl: './element-edit-view.css',
})
export class ElementEditView {
  private http = inject(HttpClient);
  private router = inject(Router);
  private route = inject(ActivatedRoute)
  element: any = null;

  constructor() {
    this.loadElement();
  }

  loadElement() {
    const elementId = this.route.snapshot.paramMap.get('id');
    this.http.get<any>('http://localhost:8080/api/wheelbarrows/' + elementId,
    ).subscribe(data => {
      this.element = data;
    });
  }

  onSubmit() {
    const elementId = this.route.snapshot.paramMap.get('id');
    this.http.put<any>('http://localhost:8080/api/wheelbarrows/' + elementId, this.element).subscribe(data => {
      this.element = data;
      alert('Element updated successfully!');
      this.router.navigate(['/wheelbarrows/' + elementId])
    });

  }
}
