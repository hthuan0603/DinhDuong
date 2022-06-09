import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GiayMoiDanhGiaDetailComponent } from './giay-moi-danh-gia-detail.component';

describe('GiayMoiDanhGia Management Detail Component', () => {
  let comp: GiayMoiDanhGiaDetailComponent;
  let fixture: ComponentFixture<GiayMoiDanhGiaDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GiayMoiDanhGiaDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ giayMoiDanhGia: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(GiayMoiDanhGiaDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(GiayMoiDanhGiaDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load giayMoiDanhGia on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.giayMoiDanhGia).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
