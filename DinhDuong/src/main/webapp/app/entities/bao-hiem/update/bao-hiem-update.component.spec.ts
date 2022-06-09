import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { BaoHiemService } from '../service/bao-hiem.service';
import { IBaoHiem, BaoHiem } from '../bao-hiem.model';
import { IHoaDon } from 'app/entities/hoa-don/hoa-don.model';
import { HoaDonService } from 'app/entities/hoa-don/service/hoa-don.service';

import { BaoHiemUpdateComponent } from './bao-hiem-update.component';

describe('BaoHiem Management Update Component', () => {
  let comp: BaoHiemUpdateComponent;
  let fixture: ComponentFixture<BaoHiemUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let baoHiemService: BaoHiemService;
  let hoaDonService: HoaDonService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [BaoHiemUpdateComponent],
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
      .overrideTemplate(BaoHiemUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(BaoHiemUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    baoHiemService = TestBed.inject(BaoHiemService);
    hoaDonService = TestBed.inject(HoaDonService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call hoaDon query and add missing value', () => {
      const baoHiem: IBaoHiem = { id: 456 };
      const hoaDon: IHoaDon = { id: 50901 };
      baoHiem.hoaDon = hoaDon;

      const hoaDonCollection: IHoaDon[] = [{ id: 90788 }];
      jest.spyOn(hoaDonService, 'query').mockReturnValue(of(new HttpResponse({ body: hoaDonCollection })));
      const expectedCollection: IHoaDon[] = [hoaDon, ...hoaDonCollection];
      jest.spyOn(hoaDonService, 'addHoaDonToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ baoHiem });
      comp.ngOnInit();

      expect(hoaDonService.query).toHaveBeenCalled();
      expect(hoaDonService.addHoaDonToCollectionIfMissing).toHaveBeenCalledWith(hoaDonCollection, hoaDon);
      expect(comp.hoaDonsCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const baoHiem: IBaoHiem = { id: 456 };
      const hoaDon: IHoaDon = { id: 49913 };
      baoHiem.hoaDon = hoaDon;

      activatedRoute.data = of({ baoHiem });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(baoHiem));
      expect(comp.hoaDonsCollection).toContain(hoaDon);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<BaoHiem>>();
      const baoHiem = { id: 123 };
      jest.spyOn(baoHiemService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ baoHiem });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: baoHiem }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(baoHiemService.update).toHaveBeenCalledWith(baoHiem);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<BaoHiem>>();
      const baoHiem = new BaoHiem();
      jest.spyOn(baoHiemService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ baoHiem });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: baoHiem }));
      saveSubject.complete();

      // THEN
      expect(baoHiemService.create).toHaveBeenCalledWith(baoHiem);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<BaoHiem>>();
      const baoHiem = { id: 123 };
      jest.spyOn(baoHiemService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ baoHiem });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(baoHiemService.update).toHaveBeenCalledWith(baoHiem);
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
