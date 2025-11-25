import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElementView } from './element-view';

describe('ElementView', () => {
  let component: ElementView;
  let fixture: ComponentFixture<ElementView>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ElementView]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ElementView);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
