import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { PhieuSuatAnService } from '../service/phieu-suat-an.service';
import { IPhieuSuatAn, PhieuSuatAn } from '../phieu-suat-an.model';
import { IHoaDon } from 'app/entities/hoa-don/hoa-don.model';
import { HoaDonService } from 'app/entities/hoa-don/service/hoa-don.service';

import { PhieuSuatAnUpdateComponent } from './phieu-suat-an-update.component';

describe('PhieuSuatAn Management Update Component', () => {
  let comp: PhieuSuatAnUpdateComponent;
  let fixture: ComponentFixture<PhieuSuatAnUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let phieuSuatAnService: PhieuSuatAnService;
  let hoaDonService: HoaDonService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [PhieuSuatAnUpdateComponent],
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
      .overrideTemplate(PhieuSuatAnUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(PhieuSuatAnUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    phieuSuatAnService = TestBed.inject(PhieuSuatAnService);
    hoaDonService = TestBed.inject(HoaDonService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call hoaDon query and add missing value', () => {
      const phieuSuatAn: IPhieuSuatAn = { id: 456 };
      const hoaDon: IHoaDon = { id: 17637 };
      phieuSuatAn.hoaDon = hoaDon;

      const hoaDonCollection: IHoaDon[] = [{ id: 19249 }];
      jest.spyOn(hoaDonService, 'query').mockReturnValue(of(new HttpResponse({ body: hoaDonCollection })));
      const expectedCollection: IHoaDon[] = [hoaDon, ...hoaDonCollection];
      jest.spyOn(hoaDonService, 'addHoaDonToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ phieuSuatAn });
      comp.ngOnInit();

      expect(hoaDonService.query).toHaveBeenCalled();
      expect(hoaDonService.addHoaDonToCollectionIfMissing).toHaveBeenCalledWith(hoaDonCollection, hoaDon);
      expect(comp.hoaDonsCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const phieuSuatAn: IPhieuSuatAn = { id: 456 };
      const hoaDon: IHoaDon = { id: 6869 };
      phieuSuatAn.hoaDon = hoaDon;

      activatedRoute.data = of({ phieuSuatAn });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(phieuSuatAn));
      expect(comp.hoaDonsCollection).toContain(hoaDon);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<PhieuSuatAn>>();
      const phieuSuatAn = { id: 123 };
      jest.spyOn(phieuSuatAnService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ phieuSuatAn });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: phieuSuatAn }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(phieuSuatAnService.update).toHaveBeenCalledWith(phieuSuatAn);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<PhieuSuatAn>>();
      const phieuSuatAn = new PhieuSuatAn();
      jest.spyOn(phieuSuatAnService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ phieuSuatAn });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: phieuSuatAn }));
      saveSubject.complete();

      // THEN
      expect(phieuSuatAnService.create).toHaveBeenCalledWith(phieuSuatAn);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<PhieuSuatAn>>();
      const phieuSuatAn = { id: 123 };
      jest.spyOn(phieuSuatAnService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ phieuSuatAn });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(phieuSuatAnService.update).toHaveBeenCalledWith(phieuSuatAn);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackHoaDonById', () => {
      it('Should return tracked HoaDon primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackHoaDonById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
