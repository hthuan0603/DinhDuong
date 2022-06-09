import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { LyDoCongTacService } from '../service/ly-do-cong-tac.service';
import { ILyDoCongTac, LyDoCongTac } from '../ly-do-cong-tac.model';

import { LyDoCongTacUpdateComponent } from './ly-do-cong-tac-update.component';

describe('LyDoCongTac Management Update Component', () => {
  let comp: LyDoCongTacUpdateComponent;
  let fixture: ComponentFixture<LyDoCongTacUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let lyDoCongTacService: LyDoCongTacService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [LyDoCongTacUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(LyDoCongTacUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(LyDoCongTacUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    lyDoCongTacService = TestBed.inject(LyDoCongTacService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const lyDoCongTac: ILyDoCongTac = { id: 456 };

      activatedRoute.data = of({ lyDoCongTac });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(lyDoCongTac));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<LyDoCongTac>>();
      const lyDoCongTac = { id: 123 };
      jest.spyOn(lyDoCongTacService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ lyDoCongTac });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: lyDoCongTac }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(lyDoCongTacService.update).toHaveBeenCalledWith(lyDoCongTac);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<LyDoCongTac>>();
      const lyDoCongTac = new LyDoCongTac();
      jest.spyOn(lyDoCongTacService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ lyDoCongTac });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: lyDoCongTac }));
      saveSubject.complete();

      // THEN
      expect(lyDoCongTacService.create).toHaveBeenCalledWith(lyDoCongTac);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<LyDoCongTac>>();
      const lyDoCongTac = { id: 123 };
      jest.spyOn(lyDoCongTacService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ lyDoCongTac });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(lyDoCongTacService.update).toHaveBeenCalledWith(lyDoCongTac);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
