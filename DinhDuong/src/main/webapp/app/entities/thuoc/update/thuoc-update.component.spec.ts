import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ThuocService } from '../service/thuoc.service';
import { IThuoc, Thuoc } from '../thuoc.model';

import { ThuocUpdateComponent } from './thuoc-update.component';

describe('Thuoc Management Update Component', () => {
  let comp: ThuocUpdateComponent;
  let fixture: ComponentFixture<ThuocUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let thuocService: ThuocService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [ThuocUpdateComponent],
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
      .overrideTemplate(ThuocUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ThuocUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    thuocService = TestBed.inject(ThuocService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const thuoc: IThuoc = { id: 456 };

      activatedRoute.data = of({ thuoc });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(thuoc));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Thuoc>>();
      const thuoc = { id: 123 };
      jest.spyOn(thuocService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ thuoc });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: thuoc }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(thuocService.update).toHaveBeenCalledWith(thuoc);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Thuoc>>();
      const thuoc = new Thuoc();
      jest.spyOn(thuocService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ thuoc });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: thuoc }));
      saveSubject.complete();

      // THEN
      expect(thuocService.create).toHaveBeenCalledWith(thuoc);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Thuoc>>();
      const thuoc = { id: 123 };
      jest.spyOn(thuocService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ thuoc });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(thuocService.update).toHaveBeenCalledWith(thuoc);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
