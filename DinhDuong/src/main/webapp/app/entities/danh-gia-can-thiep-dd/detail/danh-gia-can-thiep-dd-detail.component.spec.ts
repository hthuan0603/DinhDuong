import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DanhGiaCanThiepDDDetailComponent } from './danh-gia-can-thiep-dd-detail.component';

describe('DanhGiaCanThiepDD Management Detail Component', () => {
  let comp: DanhGiaCanThiepDDDetailComponent;
  let fixture: ComponentFixture<DanhGiaCanThiepDDDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DanhGiaCanThiepDDDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ danhGiaCanThiepDD: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(DanhGiaCanThiepDDDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(DanhGiaCanThiepDDDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load danhGiaCanThiepDD on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.danhGiaCanThiepDD).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
