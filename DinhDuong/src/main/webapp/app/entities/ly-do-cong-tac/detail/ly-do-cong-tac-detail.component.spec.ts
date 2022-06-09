import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { LyDoCongTacDetailComponent } from './ly-do-cong-tac-detail.component';

describe('LyDoCongTac Management Detail Component', () => {
  let comp: LyDoCongTacDetailComponent;
  let fixture: ComponentFixture<LyDoCongTacDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LyDoCongTacDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ lyDoCongTac: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(LyDoCongTacDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(LyDoCongTacDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load lyDoCongTac on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.lyDoCongTac).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
