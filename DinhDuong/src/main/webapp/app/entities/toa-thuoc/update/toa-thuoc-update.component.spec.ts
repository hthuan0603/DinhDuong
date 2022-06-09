import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ToaThuocService } from '../service/toa-thuoc.service';
import { IToaThuoc, ToaThuoc } from '../toa-thuoc.model';
import { IThuoc } from 'app/entities/thuoc/thuoc.model';
import { ThuocService } from 'app/entities/thuoc/service/thuoc.service';
import { IHoaDon } from 'app/entities/hoa-don/hoa-don.model';
import { HoaDonService } from 'app/entities/hoa-don/service/hoa-don.service';
import { IBenhNhan } from 'app/entities/benh-nhan/benh-nhan.model';
import { BenhNhanService } from 'app/entities/benh-nhan/service/benh-nhan.service';

import { ToaThuocUpdateComponent } from './toa-thuoc-update.component';

describe('ToaThuoc Management Update Component', () => {
  let comp: ToaThuocUpdateComponent;
  let fixture: ComponentFixture<ToaThuocUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let toaThuocService: ToaThuocService;
  let thuocService: ThuocService;
  let hoaDonService: HoaDonService;
  let benhNhanService: BenhNhanService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [ToaThuocUpdateComponent],
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
      .overrideTemplate(ToaThuocUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ToaThuocUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    toaThuocService = TestBed.inject(ToaThuocService);
    thuocService = TestBed.inject(ThuocService);
    hoaDonService = TestBed.inject(HoaDonService);
    benhNhanService = TestBed.inject(BenhNhanService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call thuoc query and add missing value', () => {
      const toaThuoc: IToaThuoc = { id: 456 };
      const thuoc: IThuoc = { id: 73435 };
      toaThuoc.thuoc = thuoc;

      const thuocCollection: IThuoc[] = [{ id: 83180 }];
      jest.spyOn(thuocService, 'query').mockReturnValue(of(new HttpResponse({ body: thuocCollection })));
      const expectedCollection: IThuoc[] = [thuoc, ...thuocCollection];
      jest.spyOn(thuocService, 'addThuocToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ toaThuoc });
      comp.ngOnInit();

      expect(thuocService.query).toHaveBeenCalled();
      expect(thuocService.addThuocToCollectionIfMissing).toHaveBeenCalledWith(thuocCollection, thuoc);
      expect(comp.thuocsCollection).toEqual(expectedCollection);
    });

    it('Should call hoaDon query and add missing value', () => {
      const toaThuoc: IToaThuoc = { id: 456 };
      const hoaDon: IHoaDon = { id: 26305 };
      toaThuoc.hoaDon = hoaDon;

      const hoaDonCollection: IHoaDon[] = [{ id: 66619 }];
      jest.spyOn(hoaDonService, 'query').mockReturnValue(of(new HttpResponse({ body: hoaDonCollection })));
      const expectedCollection: IHoaDon[] = [hoaDon, ...hoaDonCollection];
      jest.spyOn(hoaDonService, 'addHoaDonToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ toaThuoc });
      comp.ngOnInit();

      expect(hoaDonService.query).toHaveBeenCalled();
      expect(hoaDonService.addHoaDonToCollectionIfMissing).toHaveBeenCalledWith(hoaDonCollection, hoaDon);
      expect(comp.hoaDonsCollection).toEqual(expectedCollection);
    });

    it('Should call BenhNhan query and add missing value', () => {
      const toaThuoc: IToaThuoc = { id: 456 };
      const benhNhan: IBenhNhan = { id: 89194 };
      toaThuoc.benhNhan = benhNhan;

      const benhNhanCollection: IBenhNhan[] = [{ id: 3878 }];
      jest.spyOn(benhNhanService, 'query').mockReturnValue(of(new HttpResponse({ body: benhNhanCollection })));
      const additionalBenhNhans = [benhNhan];
      const expectedCollection: IBenhNhan[] = [...additionalBenhNhans, ...benhNhanCollection];
      jest.spyOn(benhNhanService, 'addBenhNhanToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ toaThuoc });
      comp.ngOnInit();

      expect(benhNhanService.query).toHaveBeenCalled();
      expect(benhNhanService.addBenhNhanToCollectionIfMissing).toHaveBeenCalledWith(benhNhanCollection, ...additionalBenhNhans);
      expect(comp.benhNhansSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const toaThuoc: IToaThuoc = { id: 456 };
      const thuoc: IThuoc = { id: 24467 };
      toaThuoc.thuoc = thuoc;
      const hoaDon: IHoaDon = { id: 415 };
      toaThuoc.hoaDon = hoaDon;
      const benhNhan: IBenhNhan = { id: 94674 };
      toaThuoc.benhNhan = benhNhan;

      activatedRoute.data = of({ toaThuoc });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(toaThuoc));
      expect(comp.thuocsCollection).toContain(thuoc);
      expect(comp.hoaDonsCollection).toContain(hoaDon);
      expect(comp.benhNhansSharedCollection).toContain(benhNhan);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ToaThuoc>>();
      const toaThuoc = { id: 123 };
      jest.spyOn(toaThuocService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ toaThuoc });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: toaThuoc }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(toaThuocService.update).toHaveBeenCalledWith(toaThuoc);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ToaThuoc>>();
      const toaThuoc = new ToaThuoc();
      jest.spyOn(toaThuocService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ toaThuoc });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: toaThuoc }));
      saveSubject.complete();

      // THEN
      expect(toaThuocService.create).toHaveBeenCalledWith(toaThuoc);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ToaThuoc>>();
      const toaThuoc = { id: 123 };
      jest.spyOn(toaThuocService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ toaThuoc });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(toaThuocService.update).toHaveBeenCalledWith(toaThuoc);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackThuocById', () => {
      it('Should return tracked Thuoc primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackThuocById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackHoaDonById', () => {
      it('Should return tracked HoaDon primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackHoaDonById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackBenhNhanById', () => {
      it('Should return tracked BenhNhan primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackBenhNhanById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
