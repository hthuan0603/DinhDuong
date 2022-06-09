import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { HoaDonService } from '../service/hoa-don.service';
import { IHoaDon, HoaDon } from '../hoa-don.model';

import { HoaDonUpdateComponent } from './hoa-don-update.component';

describe('HoaDon Management Update Component', () => {
  let comp: HoaDonUpdateComponent;
  let fixture: ComponentFixture<HoaDonUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let hoaDonService: HoaDonService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [HoaDonUpdateComponent],
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
      .overrideTemplate(HoaDonUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(HoaDonUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    hoaDonService = TestBed.inject(HoaDonService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const hoaDon: IHoaDon = { id: 456 };

      activatedRoute.data = of({ hoaDon });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(hoaDon));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<HoaDon>>();
      const hoaDon = { id: 123 };
      jest.spyOn(hoaDonService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hoaDon });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: hoaDon }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(hoaDonService.update).toHaveBeenCalledWith(hoaDon);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<HoaDon>>();
      const hoaDon = new HoaDon();
      jest.spyOn(hoaDonService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hoaDon });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: hoaDon }));
      saveSubject.complete();

      // THEN
      expect(hoaDonService.create).toHaveBeenCalledWith(hoaDon);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<HoaDon>>();
      const hoaDon = { id: 123 };
      jest.spyOn(hoaDonService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ hoaDon });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(hoaDonService.update).toHaveBeenCalledWith(hoaDon);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
