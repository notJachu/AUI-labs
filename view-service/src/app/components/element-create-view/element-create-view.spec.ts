import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElementCreateView } from './element-create-view';

describe('ElementCreateView', () => {
  let component: ElementCreateView;
  let fixture: ComponentFixture<ElementCreateView>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ElementCreateView]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ElementCreateView);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
