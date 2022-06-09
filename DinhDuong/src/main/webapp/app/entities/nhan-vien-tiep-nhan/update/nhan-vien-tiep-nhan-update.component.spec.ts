import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { NhanVienTiepNhanService } from '../service/nhan-vien-tiep-nhan.service';
import { INhanVienTiepNhan, NhanVienTiepNhan } from '../nhan-vien-tiep-nhan.model';

import { NhanVienTiepNhanUpdateComponent } from './nhan-vien-tiep-nhan-update.component';

describe('NhanVienTiepNhan Management Update Component', () => {
  let comp: NhanVienTiepNhanUpdateComponent;
  let fixture: ComponentFixture<NhanVienTiepNhanUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let nhanVienTiepNhanService: NhanVienTiepNhanService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [NhanVienTiepNhanUpdateComponent],
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
      .overrideTemplate(NhanVienTiepNhanUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(NhanVienTiepNhanUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    nhanVienTiepNhanService = TestBed.inject(NhanVienTiepNhanService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const nhanVienTiepNhan: INhanVienTiepNhan = { id: 456 };

      activatedRoute.data = of({ nhanVienTiepNhan });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(nhanVienTiepNhan));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<NhanVienTiepNhan>>();
      const nhanVienTiepNhan = { id: 123 };
      jest.spyOn(nhanVienTiepNhanService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ nhanVienTiepNhan });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: nhanVienTiepNhan }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(nhanVienTiepNhanService.update).toHaveBeenCalledWith(nhanVienTiepNhan);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<NhanVienTiepNhan>>();
      const nhanVienTiepNhan = new NhanVienTiepNhan();
      jest.spyOn(nhanVienTiepNhanService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ nhanVienTiepNhan });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: nhanVienTiepNhan }));
      saveSubject.complete();

      // THEN
      expect(nhanVienTiepNhanService.create).toHaveBeenCalledWith(nhanVienTiepNhan);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<NhanVienTiepNhan>>();
      const nhanVienTiepNhan = { id: 123 };
      jest.spyOn(nhanVienTiepNhanService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ nhanVienTiepNhan });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(nhanVienTiepNhanService.update).toHaveBeenCalledWith(nhanVienTiepNhan);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
