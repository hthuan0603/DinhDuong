import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NhanVienTiepNhanDetailComponent } from './nhan-vien-tiep-nhan-detail.component';

describe('NhanVienTiepNhan Management Detail Component', () => {
  let comp: NhanVienTiepNhanDetailComponent;
  let fixture: ComponentFixture<NhanVienTiepNhanDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NhanVienTiepNhanDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ nhanVienTiepNhan: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(NhanVienTiepNhanDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(NhanVienTiepNhanDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load nhanVienTiepNhan on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.nhanVienTiepNhan).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
