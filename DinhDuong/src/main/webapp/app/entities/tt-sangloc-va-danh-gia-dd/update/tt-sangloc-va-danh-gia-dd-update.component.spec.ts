import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { TTSanglocVaDanhGiaDDService } from '../service/tt-sangloc-va-danh-gia-dd.service';
import { ITTSanglocVaDanhGiaDD, TTSanglocVaDanhGiaDD } from '../tt-sangloc-va-danh-gia-dd.model';
import { IDanhGiaCanThiepDD } from 'app/entities/danh-gia-can-thiep-dd/danh-gia-can-thiep-dd.model';
import { DanhGiaCanThiepDDService } from 'app/entities/danh-gia-can-thiep-dd/service/danh-gia-can-thiep-dd.service';

import { TTSanglocVaDanhGiaDDUpdateComponent } from './tt-sangloc-va-danh-gia-dd-update.component';

describe('TTSanglocVaDanhGiaDD Management Update Component', () => {
  let comp: TTSanglocVaDanhGiaDDUpdateComponent;
  let fixture: ComponentFixture<TTSanglocVaDanhGiaDDUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let tTSanglocVaDanhGiaDDService: TTSanglocVaDanhGiaDDService;
  let danhGiaCanThiepDDService: DanhGiaCanThiepDDService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [TTSanglocVaDanhGiaDDUpdateComponent],
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
      .overrideTemplate(TTSanglocVaDanhGiaDDUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TTSanglocVaDanhGiaDDUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    tTSanglocVaDanhGiaDDService = TestBed.inject(TTSanglocVaDanhGiaDDService);
    danhGiaCanThiepDDService = TestBed.inject(DanhGiaCanThiepDDService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call danhGiaCanThiepDD query and add missing value', () => {
      const tTSanglocVaDanhGiaDD: ITTSanglocVaDanhGiaDD = { id: 456 };
      const danhGiaCanThiepDD: IDanhGiaCanThiepDD = { id: 7085 };
      tTSanglocVaDanhGiaDD.danhGiaCanThiepDD = danhGiaCanThiepDD;

      const danhGiaCanThiepDDCollection: IDanhGiaCanThiepDD[] = [{ id: 37686 }];
      jest.spyOn(danhGiaCanThiepDDService, 'query').mockReturnValue(of(new HttpResponse({ body: danhGiaCanThiepDDCollection })));
      const expectedCollection: IDanhGiaCanThiepDD[] = [danhGiaCanThiepDD, ...danhGiaCanThiepDDCollection];
      jest.spyOn(danhGiaCanThiepDDService, 'addDanhGiaCanThiepDDToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ tTSanglocVaDanhGiaDD });
      comp.ngOnInit();

      expect(danhGiaCanThiepDDService.query).toHaveBeenCalled();
      expect(danhGiaCanThiepDDService.addDanhGiaCanThiepDDToCollectionIfMissing).toHaveBeenCalledWith(
        danhGiaCanThiepDDCollection,
        danhGiaCanThiepDD
      );
      expect(comp.danhGiaCanThiepDDSCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const tTSanglocVaDanhGiaDD: ITTSanglocVaDanhGiaDD = { id: 456 };
      const danhGiaCanThiepDD: IDanhGiaCanThiepDD = { id: 62728 };
      tTSanglocVaDanhGiaDD.danhGiaCanThiepDD = danhGiaCanThiepDD;

      activatedRoute.data = of({ tTSanglocVaDanhGiaDD });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(tTSanglocVaDanhGiaDD));
      expect(comp.danhGiaCanThiepDDSCollection).toContain(danhGiaCanThiepDD);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<TTSanglocVaDanhGiaDD>>();
      const tTSanglocVaDanhGiaDD = { id: 123 };
      jest.spyOn(tTSanglocVaDanhGiaDDService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ tTSanglocVaDanhGiaDD });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: tTSanglocVaDanhGiaDD }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(tTSanglocVaDanhGiaDDService.update).toHaveBeenCalledWith(tTSanglocVaDanhGiaDD);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<TTSanglocVaDanhGiaDD>>();
      const tTSanglocVaDanhGiaDD = new TTSanglocVaDanhGiaDD();
      jest.spyOn(tTSanglocVaDanhGiaDDService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ tTSanglocVaDanhGiaDD });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: tTSanglocVaDanhGiaDD }));
      saveSubject.complete();

      // THEN
      expect(tTSanglocVaDanhGiaDDService.create).toHaveBeenCalledWith(tTSanglocVaDanhGiaDD);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<TTSanglocVaDanhGiaDD>>();
      const tTSanglocVaDanhGiaDD = { id: 123 };
      jest.spyOn(tTSanglocVaDanhGiaDDService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ tTSanglocVaDanhGiaDD });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(tTSanglocVaDanhGiaDDService.update).toHaveBeenCalledWith(tTSanglocVaDanhGiaDD);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackDanhGiaCanThiepDDById', () => {
      it('Should return tracked DanhGiaCanThiepDD primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackDanhGiaCanThiepDDById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
