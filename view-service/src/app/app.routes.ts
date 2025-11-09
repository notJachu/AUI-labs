import { Routes } from '@angular/router';
import {CategoriesView} from './components/categories-view/categories-view';
import {CategoryView} from './components/category-view/category-view';
import {CategoryEditView} from './components/category-edit-view/category-edit-view';
import {CategoryCreateView} from './components/category-create-view/category-create-view';

export const routes: Routes = [
  {
    path: 'categories',
    component: CategoriesView,
  },
  {
    path: 'categories/new',
    component: CategoryCreateView,
  },
  {
    path: 'categories/:id',
    component: CategoryView,
  },
  {
    path: 'categories/edit/:id',
    component: CategoryEditView,
  }
];

