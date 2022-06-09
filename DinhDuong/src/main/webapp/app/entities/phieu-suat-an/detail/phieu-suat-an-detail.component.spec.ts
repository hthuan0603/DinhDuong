import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PhieuSuatAnDetailComponent } from './phieu-suat-an-detail.component';

describe('PhieuSuatAn Management Detail Component', () => {
  let comp: PhieuSuatAnDetailComponent;
  let fixture: ComponentFixture<PhieuSuatAnDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PhieuSuatAnDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ phieuSuatAn: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(PhieuSuatAnDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(PhieuSuatAnDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load phieuSuatAn on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.phieuSuatAn).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
