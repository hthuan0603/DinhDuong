import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DieuTriService } from '../service/dieu-tri.service';
import { IDieuTri, DieuTri } from '../dieu-tri.model';
import { IHoaDon } from 'app/entities/hoa-don/hoa-don.model';
import { HoaDonService } from 'app/entities/hoa-don/service/hoa-don.service';

import { DieuTriUpdateComponent } from './dieu-tri-update.component';

describe('DieuTri Management Update Component', () => {
  let comp: DieuTriUpdateComponent;
  let fixture: ComponentFixture<DieuTriUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let dieuTriService: DieuTriService;
  let hoaDonService: HoaDonService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DieuTriUpdateComponent],
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
      .overrideTemplate(DieuTriUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DieuTriUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    dieuTriService = TestBed.inject(DieuTriService);
    hoaDonService = TestBed.inject(HoaDonService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call hoaDon query and add missing value', () => {
      const dieuTri: IDieuTri = { id: 456 };
      const hoaDon: IHoaDon = { id: 6132 };
      dieuTri.hoaDon = hoaDon;

      const hoaDonCollection: IHoaDon[] = [{ id: 24174 }];
      jest.spyOn(hoaDonService, 'query').mockReturnValue(of(new HttpResponse({ body: hoaDonCollection })));
      const expectedCollection: IHoaDon[] = [hoaDon, ...hoaDonCollection];
      jest.spyOn(hoaDonService, 'addHoaDonToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ dieuTri });
      comp.ngOnInit();

      expect(hoaDonService.query).toHaveBeenCalled();
      expect(hoaDonService.addHoaDonToCollectionIfMissing).toHaveBeenCalledWith(hoaDonCollection, hoaDon);
      expect(comp.hoaDonsCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const dieuTri: IDieuTri = { id: 456 };
      const hoaDon: IHoaDon = { id: 16965 };
      dieuTri.hoaDon = hoaDon;

      activatedRoute.data = of({ dieuTri });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(dieuTri));
      expect(comp.hoaDonsCollection).toContain(hoaDon);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DieuTri>>();
      const dieuTri = { id: 123 };
      jest.spyOn(dieuTriService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ dieuTri });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: dieuTri }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(dieuTriService.update).toHaveBeenCalledWith(dieuTri);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DieuTri>>();
      const dieuTri = new DieuTri();
      jest.spyOn(dieuTriService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ dieuTri });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: dieuTri }));
      saveSubject.complete();

      // THEN
      expect(dieuTriService.create).toHaveBeenCalledWith(dieuTri);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DieuTri>>();
      const dieuTri = { id: 123 };
      jest.spyOn(dieuTriService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ dieuTri });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(dieuTriService.update).toHaveBeenCalledWith(dieuTri);
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
