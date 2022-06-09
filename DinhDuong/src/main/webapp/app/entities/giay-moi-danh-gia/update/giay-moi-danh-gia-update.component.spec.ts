import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { GiayMoiDanhGiaService } from '../service/giay-moi-danh-gia.service';
import { IGiayMoiDanhGia, GiayMoiDanhGia } from '../giay-moi-danh-gia.model';

import { GiayMoiDanhGiaUpdateComponent } from './giay-moi-danh-gia-update.component';

describe('GiayMoiDanhGia Management Update Component', () => {
  let comp: GiayMoiDanhGiaUpdateComponent;
  let fixture: ComponentFixture<GiayMoiDanhGiaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let giayMoiDanhGiaService: GiayMoiDanhGiaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [GiayMoiDanhGiaUpdateComponent],
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
      .overrideTemplate(GiayMoiDanhGiaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(GiayMoiDanhGiaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    giayMoiDanhGiaService = TestBed.inject(GiayMoiDanhGiaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const giayMoiDanhGia: IGiayMoiDanhGia = { id: 456 };

      activatedRoute.data = of({ giayMoiDanhGia });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(giayMoiDanhGia));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<GiayMoiDanhGia>>();
      const giayMoiDanhGia = { id: 123 };
      jest.spyOn(giayMoiDanhGiaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ giayMoiDanhGia });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: giayMoiDanhGia }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(giayMoiDanhGiaService.update).toHaveBeenCalledWith(giayMoiDanhGia);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<GiayMoiDanhGia>>();
      const giayMoiDanhGia = new GiayMoiDanhGia();
      jest.spyOn(giayMoiDanhGiaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ giayMoiDanhGia });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: giayMoiDanhGia }));
      saveSubject.complete();

      // THEN
      expect(giayMoiDanhGiaService.create).toHaveBeenCalledWith(giayMoiDanhGia);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<GiayMoiDanhGia>>();
      const giayMoiDanhGia = { id: 123 };
      jest.spyOn(giayMoiDanhGiaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ giayMoiDanhGia });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(giayMoiDanhGiaService.update).toHaveBeenCalledWith(giayMoiDanhGia);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
