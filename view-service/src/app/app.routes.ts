import { Routes } from '@angular/router';
import {CategoriesView} from './components/categories-view/categories-view';
import {CategoryView} from './components/category-view/category-view';

export const routes: Routes = [
  {
    path: 'categories',
    component: CategoriesView,
  },
  {
    path: 'categories/:id',
    component: CategoryView,
  }
];

