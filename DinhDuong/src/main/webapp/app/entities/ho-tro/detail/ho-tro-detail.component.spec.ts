import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { HoTroDetailComponent } from './ho-tro-detail.component';

describe('HoTro Management Detail Component', () => {
  let comp: HoTroDetailComponent;
  let fixture: ComponentFixture<HoTroDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HoTroDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ hoTro: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(HoTroDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(HoTroDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load hoTro on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.hoTro).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
