import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { BaoCaoTaiChinhService } from '../service/bao-cao-tai-chinh.service';
import { IBaoCaoTaiChinh, BaoCaoTaiChinh } from '../bao-cao-tai-chinh.model';
import { IChiDaoTuyen } from 'app/entities/chi-dao-tuyen/chi-dao-tuyen.model';
import { ChiDaoTuyenService } from 'app/entities/chi-dao-tuyen/service/chi-dao-tuyen.service';

import { BaoCaoTaiChinhUpdateComponent } from './bao-cao-tai-chinh-update.component';

describe('BaoCaoTaiChinh Management Update Component', () => {
  let comp: BaoCaoTaiChinhUpdateComponent;
  let fixture: ComponentFixture<BaoCaoTaiChinhUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let baoCaoTaiChinhService: BaoCaoTaiChinhService;
  let chiDaoTuyenService: ChiDaoTuyenService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [BaoCaoTaiChinhUpdateComponent],
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
      .overrideTemplate(BaoCaoTaiChinhUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(BaoCaoTaiChinhUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    baoCaoTaiChinhService = TestBed.inject(BaoCaoTaiChinhService);
    chiDaoTuyenService = TestBed.inject(ChiDaoTuyenService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call chiDaoTuyen query and add missing value', () => {
      const baoCaoTaiChinh: IBaoCaoTaiChinh = { id: 456 };
      const chiDaoTuyen: IChiDaoTuyen = { id: 52720 };
      baoCaoTaiChinh.chiDaoTuyen = chiDaoTuyen;

      const chiDaoTuyenCollection: IChiDaoTuyen[] = [{ id: 94912 }];
      jest.spyOn(chiDaoTuyenService, 'query').mockReturnValue(of(new HttpResponse({ body: chiDaoTuyenCollection })));
      const expectedCollection: IChiDaoTuyen[] = [chiDaoTuyen, ...chiDaoTuyenCollection];
      jest.spyOn(chiDaoTuyenService, 'addChiDaoTuyenToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ baoCaoTaiChinh });
      comp.ngOnInit();

      expect(chiDaoTuyenService.query).toHaveBeenCalled();
      expect(chiDaoTuyenService.addChiDaoTuyenToCollectionIfMissing).toHaveBeenCalledWith(chiDaoTuyenCollection, chiDaoTuyen);
      expect(comp.chiDaoTuyensCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const baoCaoTaiChinh: IBaoCaoTaiChinh = { id: 456 };
      const chiDaoTuyen: IChiDaoTuyen = { id: 46790 };
      baoCaoTaiChinh.chiDaoTuyen = chiDaoTuyen;

      activatedRoute.data = of({ baoCaoTaiChinh });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(baoCaoTaiChinh));
      expect(comp.chiDaoTuyensCollection).toContain(chiDaoTuyen);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<BaoCaoTaiChinh>>();
      const baoCaoTaiChinh = { id: 123 };
      jest.spyOn(baoCaoTaiChinhService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ baoCaoTaiChinh });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: baoCaoTaiChinh }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(baoCaoTaiChinhService.update).toHaveBeenCalledWith(baoCaoTaiChinh);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<BaoCaoTaiChinh>>();
      const baoCaoTaiChinh = new BaoCaoTaiChinh();
      jest.spyOn(baoCaoTaiChinhService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ baoCaoTaiChinh });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: baoCaoTaiChinh }));
      saveSubject.complete();

      // THEN
      expect(baoCaoTaiChinhService.create).toHaveBeenCalledWith(baoCaoTaiChinh);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<BaoCaoTaiChinh>>();
      const baoCaoTaiChinh = { id: 123 };
      jest.spyOn(baoCaoTaiChinhService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ baoCaoTaiChinh });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(baoCaoTaiChinhService.update).toHaveBeenCalledWith(baoCaoTaiChinh);
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
