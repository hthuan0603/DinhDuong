import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ChiDaoTuyenDetailComponent } from './chi-dao-tuyen-detail.component';

describe('ChiDaoTuyen Management Detail Component', () => {
  let comp: ChiDaoTuyenDetailComponent;
  let fixture: ComponentFixture<ChiDaoTuyenDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ChiDaoTuyenDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ chiDaoTuyen: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(ChiDaoTuyenDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(ChiDaoTuyenDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load chiDaoTuyen on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.chiDaoTuyen).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
