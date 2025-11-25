import {Component, inject} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-element-create-view',
  imports: [
    FormsModule
  ],
  templateUrl: './element-create-view.html',
  styleUrl: './element-create-view.css',
})
export class ElementCreateView {
  private http = inject(HttpClient);
  private router = inject(Router)
  private route = inject(ActivatedRoute)
  element: any = {
    name: '',
    price: 0,
  }


  onSubmit() {
    const categoryId = this.route.snapshot.paramMap.get('id');
    this.http.post<any>('http://localhost:8080/api/categories/' + categoryId + '/createWheelBarrow', this.element).subscribe(data => {
      this.element = data;
      alert('Element created successfully!');
      this.router.navigate(['/categories/' + categoryId])
    });
  }
}
