import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ToaThuocDetailComponent } from './toa-thuoc-detail.component';

describe('ToaThuoc Management Detail Component', () => {
  let comp: ToaThuocDetailComponent;
  let fixture: ComponentFixture<ToaThuocDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ToaThuocDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ toaThuoc: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(ToaThuocDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(ToaThuocDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load toaThuoc on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.toaThuoc).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
