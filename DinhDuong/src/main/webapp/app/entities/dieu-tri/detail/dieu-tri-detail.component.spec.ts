import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DieuTriDetailComponent } from './dieu-tri-detail.component';

describe('DieuTri Management Detail Component', () => {
  let comp: DieuTriDetailComponent;
  let fixture: ComponentFixture<DieuTriDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DieuTriDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ dieuTri: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(DieuTriDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(DieuTriDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load dieuTri on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.dieuTri).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
