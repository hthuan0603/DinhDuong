import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { VatTuHoTroDetailComponent } from './vat-tu-ho-tro-detail.component';

describe('VatTuHoTro Management Detail Component', () => {
  let comp: VatTuHoTroDetailComponent;
  let fixture: ComponentFixture<VatTuHoTroDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VatTuHoTroDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ vatTuHoTro: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(VatTuHoTroDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(VatTuHoTroDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load vatTuHoTro on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.vatTuHoTro).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
