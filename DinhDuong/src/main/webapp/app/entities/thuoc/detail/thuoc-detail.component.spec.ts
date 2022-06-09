import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ThuocDetailComponent } from './thuoc-detail.component';

describe('Thuoc Management Detail Component', () => {
  let comp: ThuocDetailComponent;
  let fixture: ComponentFixture<ThuocDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ThuocDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ thuoc: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(ThuocDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(ThuocDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load thuoc on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.thuoc).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
