import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { KyThuatHoTroDetailComponent } from './ky-thuat-ho-tro-detail.component';

describe('KyThuatHoTro Management Detail Component', () => {
  let comp: KyThuatHoTroDetailComponent;
  let fixture: ComponentFixture<KyThuatHoTroDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [KyThuatHoTroDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ kyThuatHoTro: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(KyThuatHoTroDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(KyThuatHoTroDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load kyThuatHoTro on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.kyThuatHoTro).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
