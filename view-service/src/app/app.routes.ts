import { Routes } from '@angular/router';
import {CategoriesView} from './components/categories-view/categories-view';
import {CategoryView} from './components/category-view/category-view';
import {CategoryEditView} from './components/category-edit-view/category-edit-view';
import {CategoryCreateView} from './components/category-create-view/category-create-view';
import {ElementView} from './components/element-view/element-view';
import {ElementCreateView} from './components/element-create-view/element-create-view';
import {ElementEditView} from './components/element-edit-view/element-edit-view';

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
  },
  {
    path: 'wheelbarrows/:id',
    component: ElementView
  },
  {
    path: 'categories/:id/new',
    component: ElementCreateView
  },
  {
    path: 'wheelbarrows/edit/:id',
    component: ElementEditView
  }
];

