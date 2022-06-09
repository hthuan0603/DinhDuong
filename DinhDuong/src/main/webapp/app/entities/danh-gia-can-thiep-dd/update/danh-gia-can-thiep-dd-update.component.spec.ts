import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DanhGiaCanThiepDDService } from '../service/danh-gia-can-thiep-dd.service';
import { IDanhGiaCanThiepDD, DanhGiaCanThiepDD } from '../danh-gia-can-thiep-dd.model';
import { IPhieuSuatAn } from 'app/entities/phieu-suat-an/phieu-suat-an.model';
import { PhieuSuatAnService } from 'app/entities/phieu-suat-an/service/phieu-suat-an.service';
import { IToaThuoc } from 'app/entities/toa-thuoc/toa-thuoc.model';
import { ToaThuocService } from 'app/entities/toa-thuoc/service/toa-thuoc.service';

import { DanhGiaCanThiepDDUpdateComponent } from './danh-gia-can-thiep-dd-update.component';

describe('DanhGiaCanThiepDD Management Update Component', () => {
  let comp: DanhGiaCanThiepDDUpdateComponent;
  let fixture: ComponentFixture<DanhGiaCanThiepDDUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let danhGiaCanThiepDDService: DanhGiaCanThiepDDService;
  let phieuSuatAnService: PhieuSuatAnService;
  let toaThuocService: ToaThuocService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DanhGiaCanThiepDDUpdateComponent],
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
      .overrideTemplate(DanhGiaCanThiepDDUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DanhGiaCanThiepDDUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    danhGiaCanThiepDDService = TestBed.inject(DanhGiaCanThiepDDService);
    phieuSuatAnService = TestBed.inject(PhieuSuatAnService);
    toaThuocService = TestBed.inject(ToaThuocService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call phieuSuatAn query and add missing value', () => {
      const danhGiaCanThiepDD: IDanhGiaCanThiepDD = { id: 456 };
      const phieuSuatAn: IPhieuSuatAn = { id: 48798 };
      danhGiaCanThiepDD.phieuSuatAn = phieuSuatAn;

      const phieuSuatAnCollection: IPhieuSuatAn[] = [{ id: 37341 }];
      jest.spyOn(phieuSuatAnService, 'query').mockReturnValue(of(new HttpResponse({ body: phieuSuatAnCollection })));
      const expectedCollection: IPhieuSuatAn[] = [phieuSuatAn, ...phieuSuatAnCollection];
      jest.spyOn(phieuSuatAnService, 'addPhieuSuatAnToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ danhGiaCanThiepDD });
      comp.ngOnInit();

      expect(phieuSuatAnService.query).toHaveBeenCalled();
      expect(phieuSuatAnService.addPhieuSuatAnToCollectionIfMissing).toHaveBeenCalledWith(phieuSuatAnCollection, phieuSuatAn);
      expect(comp.phieuSuatAnsCollection).toEqual(expectedCollection);
    });

    it('Should call toaThuoc query and add missing value', () => {
      const danhGiaCanThiepDD: IDanhGiaCanThiepDD = { id: 456 };
      const toaThuoc: IToaThuoc = { id: 18840 };
      danhGiaCanThiepDD.toaThuoc = toaThuoc;

      const toaThuocCollection: IToaThuoc[] = [{ id: 65139 }];
      jest.spyOn(toaThuocService, 'query').mockReturnValue(of(new HttpResponse({ body: toaThuocCollection })));
      const expectedCollection: IToaThuoc[] = [toaThuoc, ...toaThuocCollection];
      jest.spyOn(toaThuocService, 'addToaThuocToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ danhGiaCanThiepDD });
      comp.ngOnInit();

      expect(toaThuocService.query).toHaveBeenCalled();
      expect(toaThuocService.addToaThuocToCollectionIfMissing).toHaveBeenCalledWith(toaThuocCollection, toaThuoc);
      expect(comp.toaThuocsCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const danhGiaCanThiepDD: IDanhGiaCanThiepDD = { id: 456 };
      const phieuSuatAn: IPhieuSuatAn = { id: 59168 };
      danhGiaCanThiepDD.phieuSuatAn = phieuSuatAn;
      const toaThuoc: IToaThuoc = { id: 2477 };
      danhGiaCanThiepDD.toaThuoc = toaThuoc;

      activatedRoute.data = of({ danhGiaCanThiepDD });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(danhGiaCanThiepDD));
      expect(comp.phieuSuatAnsCollection).toContain(phieuSuatAn);
      expect(comp.toaThuocsCollection).toContain(toaThuoc);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DanhGiaCanThiepDD>>();
      const danhGiaCanThiepDD = { id: 123 };
      jest.spyOn(danhGiaCanThiepDDService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ danhGiaCanThiepDD });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: danhGiaCanThiepDD }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(danhGiaCanThiepDDService.update).toHaveBeenCalledWith(danhGiaCanThiepDD);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DanhGiaCanThiepDD>>();
      const danhGiaCanThiepDD = new DanhGiaCanThiepDD();
      jest.spyOn(danhGiaCanThiepDDService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ danhGiaCanThiepDD });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: danhGiaCanThiepDD }));
      saveSubject.complete();

      // THEN
      expect(danhGiaCanThiepDDService.create).toHaveBeenCalledWith(danhGiaCanThiepDD);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DanhGiaCanThiepDD>>();
      const danhGiaCanThiepDD = { id: 123 };
      jest.spyOn(danhGiaCanThiepDDService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ danhGiaCanThiepDD });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(danhGiaCanThiepDDService.update).toHaveBeenCalledWith(danhGiaCanThiepDD);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackPhieuSuatAnById', () => {
      it('Should return tracked PhieuSuatAn primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackPhieuSuatAnById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackToaThuocById', () => {
      it('Should return tracked ToaThuoc primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackToaThuocById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
