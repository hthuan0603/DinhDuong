import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { BenhNhanService } from '../service/benh-nhan.service';
import { IBenhNhan, BenhNhan } from '../benh-nhan.model';
import { IBaoHiem } from 'app/entities/bao-hiem/bao-hiem.model';
import { BaoHiemService } from 'app/entities/bao-hiem/service/bao-hiem.service';
import { IDieuTri } from 'app/entities/dieu-tri/dieu-tri.model';
import { DieuTriService } from 'app/entities/dieu-tri/service/dieu-tri.service';
import { ITTSanglocVaDanhGiaDD } from 'app/entities/tt-sangloc-va-danh-gia-dd/tt-sangloc-va-danh-gia-dd.model';
import { TTSanglocVaDanhGiaDDService } from 'app/entities/tt-sangloc-va-danh-gia-dd/service/tt-sangloc-va-danh-gia-dd.service';
import { IHoaDon } from 'app/entities/hoa-don/hoa-don.model';
import { HoaDonService } from 'app/entities/hoa-don/service/hoa-don.service';
import { IDanhGiaCanThiepDD } from 'app/entities/danh-gia-can-thiep-dd/danh-gia-can-thiep-dd.model';
import { DanhGiaCanThiepDDService } from 'app/entities/danh-gia-can-thiep-dd/service/danh-gia-can-thiep-dd.service';
import { IPhieuSuatAn } from 'app/entities/phieu-suat-an/phieu-suat-an.model';
import { PhieuSuatAnService } from 'app/entities/phieu-suat-an/service/phieu-suat-an.service';
import { IGiayMoiDanhGia } from 'app/entities/giay-moi-danh-gia/giay-moi-danh-gia.model';
import { GiayMoiDanhGiaService } from 'app/entities/giay-moi-danh-gia/service/giay-moi-danh-gia.service';

import { BenhNhanUpdateComponent } from './benh-nhan-update.component';

describe('BenhNhan Management Update Component', () => {
  let comp: BenhNhanUpdateComponent;
  let fixture: ComponentFixture<BenhNhanUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let benhNhanService: BenhNhanService;
  let baoHiemService: BaoHiemService;
  let dieuTriService: DieuTriService;
  let tTSanglocVaDanhGiaDDService: TTSanglocVaDanhGiaDDService;
  let hoaDonService: HoaDonService;
  let danhGiaCanThiepDDService: DanhGiaCanThiepDDService;
  let phieuSuatAnService: PhieuSuatAnService;
  let giayMoiDanhGiaService: GiayMoiDanhGiaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [BenhNhanUpdateComponent],
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
      .overrideTemplate(BenhNhanUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(BenhNhanUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    benhNhanService = TestBed.inject(BenhNhanService);
    baoHiemService = TestBed.inject(BaoHiemService);
    dieuTriService = TestBed.inject(DieuTriService);
    tTSanglocVaDanhGiaDDService = TestBed.inject(TTSanglocVaDanhGiaDDService);
    hoaDonService = TestBed.inject(HoaDonService);
    danhGiaCanThiepDDService = TestBed.inject(DanhGiaCanThiepDDService);
    phieuSuatAnService = TestBed.inject(PhieuSuatAnService);
    giayMoiDanhGiaService = TestBed.inject(GiayMoiDanhGiaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call baoHiem query and add missing value', () => {
      const benhNhan: IBenhNhan = { id: 456 };
      const baoHiem: IBaoHiem = { id: 44217 };
      benhNhan.baoHiem = baoHiem;

      const baoHiemCollection: IBaoHiem[] = [{ id: 56422 }];
      jest.spyOn(baoHiemService, 'query').mockReturnValue(of(new HttpResponse({ body: baoHiemCollection })));
      const expectedCollection: IBaoHiem[] = [baoHiem, ...baoHiemCollection];
      jest.spyOn(baoHiemService, 'addBaoHiemToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ benhNhan });
      comp.ngOnInit();

      expect(baoHiemService.query).toHaveBeenCalled();
      expect(baoHiemService.addBaoHiemToCollectionIfMissing).toHaveBeenCalledWith(baoHiemCollection, baoHiem);
      expect(comp.baoHiemsCollection).toEqual(expectedCollection);
    });

    it('Should call dieuTri query and add missing value', () => {
      const benhNhan: IBenhNhan = { id: 456 };
      const dieuTri: IDieuTri = { id: 2698 };
      benhNhan.dieuTri = dieuTri;

      const dieuTriCollection: IDieuTri[] = [{ id: 2672 }];
      jest.spyOn(dieuTriService, 'query').mockReturnValue(of(new HttpResponse({ body: dieuTriCollection })));
      const expectedCollection: IDieuTri[] = [dieuTri, ...dieuTriCollection];
      jest.spyOn(dieuTriService, 'addDieuTriToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ benhNhan });
      comp.ngOnInit();

      expect(dieuTriService.query).toHaveBeenCalled();
      expect(dieuTriService.addDieuTriToCollectionIfMissing).toHaveBeenCalledWith(dieuTriCollection, dieuTri);
      expect(comp.dieuTrisCollection).toEqual(expectedCollection);
    });

    it('Should call tTSanglocVaDanhGiaDD query and add missing value', () => {
      const benhNhan: IBenhNhan = { id: 456 };
      const tTSanglocVaDanhGiaDD: ITTSanglocVaDanhGiaDD = { id: 33651 };
      benhNhan.tTSanglocVaDanhGiaDD = tTSanglocVaDanhGiaDD;

      const tTSanglocVaDanhGiaDDCollection: ITTSanglocVaDanhGiaDD[] = [{ id: 6820 }];
      jest.spyOn(tTSanglocVaDanhGiaDDService, 'query').mockReturnValue(of(new HttpResponse({ body: tTSanglocVaDanhGiaDDCollection })));
      const expectedCollection: ITTSanglocVaDanhGiaDD[] = [tTSanglocVaDanhGiaDD, ...tTSanglocVaDanhGiaDDCollection];
      jest.spyOn(tTSanglocVaDanhGiaDDService, 'addTTSanglocVaDanhGiaDDToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ benhNhan });
      comp.ngOnInit();

      expect(tTSanglocVaDanhGiaDDService.query).toHaveBeenCalled();
      expect(tTSanglocVaDanhGiaDDService.addTTSanglocVaDanhGiaDDToCollectionIfMissing).toHaveBeenCalledWith(
        tTSanglocVaDanhGiaDDCollection,
        tTSanglocVaDanhGiaDD
      );
      expect(comp.tTSanglocVaDanhGiaDDSCollection).toEqual(expectedCollection);
    });

    it('Should call hoaDon query and add missing value', () => {
      const benhNhan: IBenhNhan = { id: 456 };
      const hoaDon: IHoaDon = { id: 42688 };
      benhNhan.hoaDon = hoaDon;

      const hoaDonCollection: IHoaDon[] = [{ id: 69562 }];
      jest.spyOn(hoaDonService, 'query').mockReturnValue(of(new HttpResponse({ body: hoaDonCollection })));
      const expectedCollection: IHoaDon[] = [hoaDon, ...hoaDonCollection];
      jest.spyOn(hoaDonService, 'addHoaDonToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ benhNhan });
      comp.ngOnInit();

      expect(hoaDonService.query).toHaveBeenCalled();
      expect(hoaDonService.addHoaDonToCollectionIfMissing).toHaveBeenCalledWith(hoaDonCollection, hoaDon);
      expect(comp.hoaDonsCollection).toEqual(expectedCollection);
    });

    it('Should call danhGiaCanThiepDD query and add missing value', () => {
      const benhNhan: IBenhNhan = { id: 456 };
      const danhGiaCanThiepDD: IDanhGiaCanThiepDD = { id: 95202 };
      benhNhan.danhGiaCanThiepDD = danhGiaCanThiepDD;

      const danhGiaCanThiepDDCollection: IDanhGiaCanThiepDD[] = [{ id: 43368 }];
      jest.spyOn(danhGiaCanThiepDDService, 'query').mockReturnValue(of(new HttpResponse({ body: danhGiaCanThiepDDCollection })));
      const expectedCollection: IDanhGiaCanThiepDD[] = [danhGiaCanThiepDD, ...danhGiaCanThiepDDCollection];
      jest.spyOn(danhGiaCanThiepDDService, 'addDanhGiaCanThiepDDToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ benhNhan });
      comp.ngOnInit();

      expect(danhGiaCanThiepDDService.query).toHaveBeenCalled();
      expect(danhGiaCanThiepDDService.addDanhGiaCanThiepDDToCollectionIfMissing).toHaveBeenCalledWith(
        danhGiaCanThiepDDCollection,
        danhGiaCanThiepDD
      );
      expect(comp.danhGiaCanThiepDDSCollection).toEqual(expectedCollection);
    });

    it('Should call phieuSuatAn query and add missing value', () => {
      const benhNhan: IBenhNhan = { id: 456 };
      const phieuSuatAn: IPhieuSuatAn = { id: 12794 };
      benhNhan.phieuSuatAn = phieuSuatAn;

      const phieuSuatAnCollection: IPhieuSuatAn[] = [{ id: 24409 }];
      jest.spyOn(phieuSuatAnService, 'query').mockReturnValue(of(new HttpResponse({ body: phieuSuatAnCollection })));
      const expectedCollection: IPhieuSuatAn[] = [phieuSuatAn, ...phieuSuatAnCollection];
      jest.spyOn(phieuSuatAnService, 'addPhieuSuatAnToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ benhNhan });
      comp.ngOnInit();

      expect(phieuSuatAnService.query).toHaveBeenCalled();
      expect(phieuSuatAnService.addPhieuSuatAnToCollectionIfMissing).toHaveBeenCalledWith(phieuSuatAnCollection, phieuSuatAn);
      expect(comp.phieuSuatAnsCollection).toEqual(expectedCollection);
    });

    it('Should call giayMoiDanhGia query and add missing value', () => {
      const benhNhan: IBenhNhan = { id: 456 };
      const giayMoiDanhGia: IGiayMoiDanhGia = { id: 81294 };
      benhNhan.giayMoiDanhGia = giayMoiDanhGia;

      const giayMoiDanhGiaCollection: IGiayMoiDanhGia[] = [{ id: 15128 }];
      jest.spyOn(giayMoiDanhGiaService, 'query').mockReturnValue(of(new HttpResponse({ body: giayMoiDanhGiaCollection })));
      const expectedCollection: IGiayMoiDanhGia[] = [giayMoiDanhGia, ...giayMoiDanhGiaCollection];
      jest.spyOn(giayMoiDanhGiaService, 'addGiayMoiDanhGiaToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ benhNhan });
      comp.ngOnInit();

      expect(giayMoiDanhGiaService.query).toHaveBeenCalled();
      expect(giayMoiDanhGiaService.addGiayMoiDanhGiaToCollectionIfMissing).toHaveBeenCalledWith(giayMoiDanhGiaCollection, giayMoiDanhGia);
      expect(comp.giayMoiDanhGiasCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const benhNhan: IBenhNhan = { id: 456 };
      const baoHiem: IBaoHiem = { id: 19147 };
      benhNhan.baoHiem = baoHiem;
      const dieuTri: IDieuTri = { id: 87711 };
      benhNhan.dieuTri = dieuTri;
      const tTSanglocVaDanhGiaDD: ITTSanglocVaDanhGiaDD = { id: 27034 };
      benhNhan.tTSanglocVaDanhGiaDD = tTSanglocVaDanhGiaDD;
      const hoaDon: IHoaDon = { id: 3155 };
      benhNhan.hoaDon = hoaDon;
      const danhGiaCanThiepDD: IDanhGiaCanThiepDD = { id: 55698 };
      benhNhan.danhGiaCanThiepDD = danhGiaCanThiepDD;
      const phieuSuatAn: IPhieuSuatAn = { id: 14009 };
      benhNhan.phieuSuatAn = phieuSuatAn;
      const giayMoiDanhGia: IGiayMoiDanhGia = { id: 44354 };
      benhNhan.giayMoiDanhGia = giayMoiDanhGia;

      activatedRoute.data = of({ benhNhan });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(benhNhan));
      expect(comp.baoHiemsCollection).toContain(baoHiem);
      expect(comp.dieuTrisCollection).toContain(dieuTri);
      expect(comp.tTSanglocVaDanhGiaDDSCollection).toContain(tTSanglocVaDanhGiaDD);
      expect(comp.hoaDonsCollection).toContain(hoaDon);
      expect(comp.danhGiaCanThiepDDSCollection).toContain(danhGiaCanThiepDD);
      expect(comp.phieuSuatAnsCollection).toContain(phieuSuatAn);
      expect(comp.giayMoiDanhGiasCollection).toContain(giayMoiDanhGia);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<BenhNhan>>();
      const benhNhan = { id: 123 };
      jest.spyOn(benhNhanService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ benhNhan });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: benhNhan }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(benhNhanService.update).toHaveBeenCalledWith(benhNhan);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<BenhNhan>>();
      const benhNhan = new BenhNhan();
      jest.spyOn(benhNhanService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ benhNhan });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: benhNhan }));
      saveSubject.complete();

      // THEN
      expect(benhNhanService.create).toHaveBeenCalledWith(benhNhan);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<BenhNhan>>();
      const benhNhan = { id: 123 };
      jest.spyOn(benhNhanService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ benhNhan });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(benhNhanService.update).toHaveBeenCalledWith(benhNhan);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackBaoHiemById', () => {
      it('Should return tracked BaoHiem primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackBaoHiemById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackDieuTriById', () => {
      it('Should return tracked DieuTri primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackDieuTriById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackTTSanglocVaDanhGiaDDById', () => {
      it('Should return tracked TTSanglocVaDanhGiaDD primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackTTSanglocVaDanhGiaDDById(0, entity);
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

    describe('trackDanhGiaCanThiepDDById', () => {
      it('Should return tracked DanhGiaCanThiepDD primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackDanhGiaCanThiepDDById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackPhieuSuatAnById', () => {
      it('Should return tracked PhieuSuatAn primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackPhieuSuatAnById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackGiayMoiDanhGiaById', () => {
      it('Should return tracked GiayMoiDanhGia primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackGiayMoiDanhGiaById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
