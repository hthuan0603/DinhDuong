import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IVatTuHoTro, VatTuHoTro } from '../vat-tu-ho-tro.model';
import { VatTuHoTroService } from '../service/vat-tu-ho-tro.service';

import { VatTuHoTroRoutingResolveService } from './vat-tu-ho-tro-routing-resolve.service';

describe('VatTuHoTro routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: VatTuHoTroRoutingResolveService;
  let service: VatTuHoTroService;
  let resultVatTuHoTro: IVatTuHoTro | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
      ],
    });
    mockRouter = TestBed.inject(Router);
    jest.spyOn(mockRouter, 'navigate').mockImplementation(() => Promise.resolve(true));
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRoute).snapshot;
    routingResolveService = TestBed.inject(VatTuHoTroRoutingResolveService);
    service = TestBed.inject(VatTuHoTroService);
    resultVatTuHoTro = undefined;
  });

  describe('resolve', () => {
    it('should return IVatTuHoTro returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultVatTuHoTro = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultVatTuHoTro).toEqual({ id: 123 });
    });

    it('should return new IVatTuHoTro if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultVatTuHoTro = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultVatTuHoTro).toEqual(new VatTuHoTro());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as VatTuHoTro })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultVatTuHoTro = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultVatTuHoTro).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
