import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { KetQuaCongTacDetailComponent } from './ket-qua-cong-tac-detail.component';

describe('KetQuaCongTac Management Detail Component', () => {
  let comp: KetQuaCongTacDetailComponent;
  let fixture: ComponentFixture<KetQuaCongTacDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [KetQuaCongTacDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ ketQuaCongTac: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(KetQuaCongTacDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(KetQuaCongTacDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load ketQuaCongTac on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.ketQuaCongTac).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
