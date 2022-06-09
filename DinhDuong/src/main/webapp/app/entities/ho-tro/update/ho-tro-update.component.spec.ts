import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { HoTroService } from '../service/ho-tro.service';
import { IHoTro, HoTro } from '../ho-tro.model';
import { IChiDaoTuyen } from 'app/entities/chi-dao-tuyen/chi-dao-tuyen.model';
import { ChiDaoTuyenService } from 'app/entities/chi-dao-tuyen/service/chi-dao-tuyen.service';

import { HoTroUpdateComponent } from './ho-tro-update.component';

describe('HoTro Management Update Component', () => {
  let comp: HoTroUpdateComponent;
  let fixture: ComponentFixture<HoTroUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let hoTroService: HoTroService;
  let chiDaoTuyenService: ChiDaoTuyenService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [HoTroUpdateComponent],
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
      .overrideTemplate(HoTroUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(HoTroUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    hoTroService = TestBed.inject(HoTroService);
    chiDaoTuyenService = TestBed.inject(ChiDaoTuyenService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call ChiDaoTuyen query and add missing value', () => {
      const hoTro: IHoTro = { id: 456 };
      const chiDaoTuyen: IChiDaoTuyen = { id: 5029 };
      hoTro.chiDaoTuyen = chiDaoTuyen;

      const chiDaoTuyenCollection: IChiDaoTuyen[] = [{ id: 32223 }];
      jest.spyOn(chiDaoTuyenService, 'query').mockReturnValue(of(new HttpResponse({ body: chiDaoTuyenCollection })));
      const additionalChiDaoTuyens = [chiDaoTuyen];
      const expectedCollection: IChiDaoTuyen[] = [...additionalChiDaoTuyens, ...chiDaoTuyenCollection];
      jest.spyOn(chiDaoTuyenService, 'addChiDaoTuyenToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ hoTro });
      comp.ngOnInit();

      expect(chiDaoTuyenService.query).toHaveBeenCalled();
      expect(chiDaoTuyenService.addChiDaoTuyenToCollectionIfMissing).toHaveBeenCalledWith(chiDaoTuyenCollection, ...additionalChiDaoTuyens);
      expect(comp.chiDaoTuyensSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const hoTro: IHoTro = { id: 456 };
      const chiDaoTuyen: IChiDaoTuyen = { id: 47599 };
      hoTro.chiDaoTuyen = chiDaoTuyen;

      activatedRoute.data = of({ hoTro });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(hoTro));
      expect(comp.chiDaoTuyensSharedCollection).toContain(chiDaoTuyen);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<HoTro>>();
      const hoTro = { id: 123 };
      jest.spyOn(hoTroService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hoTro });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: hoTro }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(hoTroService.update).toHaveBeenCalledWith(hoTro);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<HoTro>>();
      const hoTro = new HoTro();
      jest.spyOn(hoTroService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hoTro });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: hoTro }));
      saveSubject.complete();

      // THEN
      expect(hoTroService.create).toHaveBeenCalledWith(hoTro);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<HoTro>>();
      const hoTro = { id: 123 };
      jest.spyOn(hoTroService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hoTro });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(hoTroService.update).toHaveBeenCalledWith(hoTro);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackChiDaoTuyenById', () => {
      it('Should return tracked ChiDaoTuyen primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackChiDaoTuyenById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
