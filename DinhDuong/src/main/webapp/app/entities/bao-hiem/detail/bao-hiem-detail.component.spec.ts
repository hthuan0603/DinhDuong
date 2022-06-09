import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BaoHiemDetailComponent } from './bao-hiem-detail.component';

describe('BaoHiem Management Detail Component', () => {
  let comp: BaoHiemDetailComponent;
  let fixture: ComponentFixture<BaoHiemDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BaoHiemDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ baoHiem: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(BaoHiemDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(BaoHiemDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load baoHiem on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.baoHiem).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
