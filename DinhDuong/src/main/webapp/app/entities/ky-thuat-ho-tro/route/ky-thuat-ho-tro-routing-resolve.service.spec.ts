import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IKyThuatHoTro, KyThuatHoTro } from '../ky-thuat-ho-tro.model';
import { KyThuatHoTroService } from '../service/ky-thuat-ho-tro.service';

import { KyThuatHoTroRoutingResolveService } from './ky-thuat-ho-tro-routing-resolve.service';

describe('KyThuatHoTro routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: KyThuatHoTroRoutingResolveService;
  let service: KyThuatHoTroService;
  let resultKyThuatHoTro: IKyThuatHoTro | undefined;

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
    routingResolveService = TestBed.inject(KyThuatHoTroRoutingResolveService);
    service = TestBed.inject(KyThuatHoTroService);
    resultKyThuatHoTro = undefined;
  });

  describe('resolve', () => {
    it('should return IKyThuatHoTro returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultKyThuatHoTro = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultKyThuatHoTro).toEqual({ id: 123 });
    });

    it('should return new IKyThuatHoTro if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultKyThuatHoTro = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultKyThuatHoTro).toEqual(new KyThuatHoTro());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as KyThuatHoTro })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultKyThuatHoTro = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultKyThuatHoTro).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
