import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BenhNhanDetailComponent } from './benh-nhan-detail.component';

describe('BenhNhan Management Detail Component', () => {
  let comp: BenhNhanDetailComponent;
  let fixture: ComponentFixture<BenhNhanDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BenhNhanDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ benhNhan: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(BenhNhanDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(BenhNhanDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load benhNhan on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.benhNhan).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
