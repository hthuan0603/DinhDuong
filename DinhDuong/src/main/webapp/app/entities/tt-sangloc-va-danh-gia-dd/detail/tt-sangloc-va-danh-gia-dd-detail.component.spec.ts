import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TTSanglocVaDanhGiaDDDetailComponent } from './tt-sangloc-va-danh-gia-dd-detail.component';

describe('TTSanglocVaDanhGiaDD Management Detail Component', () => {
  let comp: TTSanglocVaDanhGiaDDDetailComponent;
  let fixture: ComponentFixture<TTSanglocVaDanhGiaDDDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TTSanglocVaDanhGiaDDDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ tTSanglocVaDanhGiaDD: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(TTSanglocVaDanhGiaDDDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(TTSanglocVaDanhGiaDDDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load tTSanglocVaDanhGiaDD on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.tTSanglocVaDanhGiaDD).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
