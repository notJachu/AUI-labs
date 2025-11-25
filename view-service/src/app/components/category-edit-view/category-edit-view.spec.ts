import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryEditView } from './category-edit-view';

describe('CategoryEditView', () => {
  let component: CategoryEditView;
  let fixture: ComponentFixture<CategoryEditView>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CategoryEditView]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CategoryEditView);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
