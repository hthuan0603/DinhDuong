import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NoiDenCongTacDetailComponent } from './noi-den-cong-tac-detail.component';

describe('NoiDenCongTac Management Detail Component', () => {
  let comp: NoiDenCongTacDetailComponent;
  let fixture: ComponentFixture<NoiDenCongTacDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NoiDenCongTacDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ noiDenCongTac: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(NoiDenCongTacDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(NoiDenCongTacDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load noiDenCongTac on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.noiDenCongTac).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
