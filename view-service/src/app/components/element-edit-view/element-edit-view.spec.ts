import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElementEditView } from './element-edit-view';

describe('ElementEditView', () => {
  let component: ElementEditView;
  let fixture: ComponentFixture<ElementEditView>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ElementEditView]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ElementEditView);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
