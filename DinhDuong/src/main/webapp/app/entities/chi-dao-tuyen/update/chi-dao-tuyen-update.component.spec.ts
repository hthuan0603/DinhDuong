import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ChiDaoTuyenService } from '../service/chi-dao-tuyen.service';
import { IChiDaoTuyen, ChiDaoTuyen } from '../chi-dao-tuyen.model';
import { ILyDoCongTac } from 'app/entities/ly-do-cong-tac/ly-do-cong-tac.model';
import { LyDoCongTacService } from 'app/entities/ly-do-cong-tac/service/ly-do-cong-tac.service';
import { INhanVienTiepNhan } from 'app/entities/nhan-vien-tiep-nhan/nhan-vien-tiep-nhan.model';
import { NhanVienTiepNhanService } from 'app/entities/nhan-vien-tiep-nhan/service/nhan-vien-tiep-nhan.service';

import { ChiDaoTuyenUpdateComponent } from './chi-dao-tuyen-update.component';

describe('ChiDaoTuyen Management Update Component', () => {
  let comp: ChiDaoTuyenUpdateComponent;
  let fixture: ComponentFixture<ChiDaoTuyenUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let chiDaoTuyenService: ChiDaoTuyenService;
  let lyDoCongTacService: LyDoCongTacService;
  let nhanVienTiepNhanService: NhanVienTiepNhanService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [ChiDaoTuyenUpdateComponent],
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
      .overrideTemplate(ChiDaoTuyenUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ChiDaoTuyenUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    chiDaoTuyenService = TestBed.inject(ChiDaoTuyenService);
    lyDoCongTacService = TestBed.inject(LyDoCongTacService);
    nhanVienTiepNhanService = TestBed.inject(NhanVienTiepNhanService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call LyDoCongTac query and add missing value', () => {
      const chiDaoTuyen: IChiDaoTuyen = { id: 456 };
      const lyDoCongTac: ILyDoCongTac = { id: 68092 };
      chiDaoTuyen.lyDoCongTac = lyDoCongTac;

      const lyDoCongTacCollection: ILyDoCongTac[] = [{ id: 83853 }];
      jest.spyOn(lyDoCongTacService, 'query').mockReturnValue(of(new HttpResponse({ body: lyDoCongTacCollection })));
      const additionalLyDoCongTacs = [lyDoCongTac];
      const expectedCollection: ILyDoCongTac[] = [...additionalLyDoCongTacs, ...lyDoCongTacCollection];
      jest.spyOn(lyDoCongTacService, 'addLyDoCongTacToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ chiDaoTuyen });
      comp.ngOnInit();

      expect(lyDoCongTacService.query).toHaveBeenCalled();
      expect(lyDoCongTacService.addLyDoCongTacToCollectionIfMissing).toHaveBeenCalledWith(lyDoCongTacCollection, ...additionalLyDoCongTacs);
      expect(comp.lyDoCongTacsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call NhanVienTiepNhan query and add missing value', () => {
      const chiDaoTuyen: IChiDaoTuyen = { id: 456 };
      const nhanVienTiepNhan: INhanVienTiepNhan = { id: 82234 };
      chiDaoTuyen.nhanVienTiepNhan = nhanVienTiepNhan;

      const nhanVienTiepNhanCollection: INhanVienTiepNhan[] = [{ id: 38843 }];
      jest.spyOn(nhanVienTiepNhanService, 'query').mockReturnValue(of(new HttpResponse({ body: nhanVienTiepNhanCollection })));
      const additionalNhanVienTiepNhans = [nhanVienTiepNhan];
      const expectedCollection: INhanVienTiepNhan[] = [...additionalNhanVienTiepNhans, ...nhanVienTiepNhanCollection];
      jest.spyOn(nhanVienTiepNhanService, 'addNhanVienTiepNhanToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ chiDaoTuyen });
      comp.ngOnInit();

      expect(nhanVienTiepNhanService.query).toHaveBeenCalled();
      expect(nhanVienTiepNhanService.addNhanVienTiepNhanToCollectionIfMissing).toHaveBeenCalledWith(
        nhanVienTiepNhanCollection,
        ...additionalNhanVienTiepNhans
      );
      expect(comp.nhanVienTiepNhansSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const chiDaoTuyen: IChiDaoTuyen = { id: 456 };
      const lyDoCongTac: ILyDoCongTac = { id: 55206 };
      chiDaoTuyen.lyDoCongTac = lyDoCongTac;
      const nhanVienTiepNhan: INhanVienTiepNhan = { id: 86741 };
      chiDaoTuyen.nhanVienTiepNhan = nhanVienTiepNhan;

      activatedRoute.data = of({ chiDaoTuyen });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(chiDaoTuyen));
      expect(comp.lyDoCongTacsSharedCollection).toContain(lyDoCongTac);
      expect(comp.nhanVienTiepNhansSharedCollection).toContain(nhanVienTiepNhan);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ChiDaoTuyen>>();
      const chiDaoTuyen = { id: 123 };
      jest.spyOn(chiDaoTuyenService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ chiDaoTuyen });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: chiDaoTuyen }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(chiDaoTuyenService.update).toHaveBeenCalledWith(chiDaoTuyen);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ChiDaoTuyen>>();
      const chiDaoTuyen = new ChiDaoTuyen();
      jest.spyOn(chiDaoTuyenService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ chiDaoTuyen });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: chiDaoTuyen }));
      saveSubject.complete();

      // THEN
      expect(chiDaoTuyenService.create).toHaveBeenCalledWith(chiDaoTuyen);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ChiDaoTuyen>>();
      const chiDaoTuyen = { id: 123 };
      jest.spyOn(chiDaoTuyenService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ chiDaoTuyen });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(chiDaoTuyenService.update).toHaveBeenCalledWith(chiDaoTuyen);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackLyDoCongTacById', () => {
      it('Should return tracked LyDoCongTac primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackLyDoCongTacById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackNhanVienTiepNhanById', () => {
      it('Should return tracked NhanVienTiepNhan primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackNhanVienTiepNhanById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
