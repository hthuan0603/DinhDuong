import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BaoCaoTaiChinhDetailComponent } from './bao-cao-tai-chinh-detail.component';

describe('BaoCaoTaiChinh Management Detail Component', () => {
  let comp: BaoCaoTaiChinhDetailComponent;
  let fixture: ComponentFixture<BaoCaoTaiChinhDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BaoCaoTaiChinhDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ baoCaoTaiChinh: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(BaoCaoTaiChinhDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(BaoCaoTaiChinhDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load baoCaoTaiChinh on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.baoCaoTaiChinh).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
