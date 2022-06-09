import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { KyThuatHoTroService } from '../service/ky-thuat-ho-tro.service';
import { IKyThuatHoTro, KyThuatHoTro } from '../ky-thuat-ho-tro.model';
import { IHoTro } from 'app/entities/ho-tro/ho-tro.model';
import { HoTroService } from 'app/entities/ho-tro/service/ho-tro.service';

import { KyThuatHoTroUpdateComponent } from './ky-thuat-ho-tro-update.component';

describe('KyThuatHoTro Management Update Component', () => {
  let comp: KyThuatHoTroUpdateComponent;
  let fixture: ComponentFixture<KyThuatHoTroUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let kyThuatHoTroService: KyThuatHoTroService;
  let hoTroService: HoTroService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [KyThuatHoTroUpdateComponent],
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
      .overrideTemplate(KyThuatHoTroUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(KyThuatHoTroUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    kyThuatHoTroService = TestBed.inject(KyThuatHoTroService);
    hoTroService = TestBed.inject(HoTroService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call hoTro query and add missing value', () => {
      const kyThuatHoTro: IKyThuatHoTro = { id: 456 };
      const hoTro: IHoTro = { id: 82671 };
      kyThuatHoTro.hoTro = hoTro;

      const hoTroCollection: IHoTro[] = [{ id: 93848 }];
      jest.spyOn(hoTroService, 'query').mockReturnValue(of(new HttpResponse({ body: hoTroCollection })));
      const expectedCollection: IHoTro[] = [hoTro, ...hoTroCollection];
      jest.spyOn(hoTroService, 'addHoTroToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ kyThuatHoTro });
      comp.ngOnInit();

      expect(hoTroService.query).toHaveBeenCalled();
      expect(hoTroService.addHoTroToCollectionIfMissing).toHaveBeenCalledWith(hoTroCollection, hoTro);
      expect(comp.hoTrosCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const kyThuatHoTro: IKyThuatHoTro = { id: 456 };
      const hoTro: IHoTro = { id: 10564 };
      kyThuatHoTro.hoTro = hoTro;

      activatedRoute.data = of({ kyThuatHoTro });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(kyThuatHoTro));
      expect(comp.hoTrosCollection).toContain(hoTro);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<KyThuatHoTro>>();
      const kyThuatHoTro = { id: 123 };
      jest.spyOn(kyThuatHoTroService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ kyThuatHoTro });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: kyThuatHoTro }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(kyThuatHoTroService.update).toHaveBeenCalledWith(kyThuatHoTro);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<KyThuatHoTro>>();
      const kyThuatHoTro = new KyThuatHoTro();
      jest.spyOn(kyThuatHoTroService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ kyThuatHoTro });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: kyThuatHoTro }));
      saveSubject.complete();

      // THEN
      expect(kyThuatHoTroService.create).toHaveBeenCalledWith(kyThuatHoTro);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<KyThuatHoTro>>();
      const kyThuatHoTro = { id: 123 };
      jest.spyOn(kyThuatHoTroService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ kyThuatHoTro });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(kyThuatHoTroService.update).toHaveBeenCalledWith(kyThuatHoTro);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackHoTroById', () => {
      it('Should return tracked HoTro primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackHoTroById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
