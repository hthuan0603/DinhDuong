import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IBaoCaoTaiChinh, BaoCaoTaiChinh } from '../bao-cao-tai-chinh.model';
import { BaoCaoTaiChinhService } from '../service/bao-cao-tai-chinh.service';

import { BaoCaoTaiChinhRoutingResolveService } from './bao-cao-tai-chinh-routing-resolve.service';

describe('BaoCaoTaiChinh routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: BaoCaoTaiChinhRoutingResolveService;
  let service: BaoCaoTaiChinhService;
  let resultBaoCaoTaiChinh: IBaoCaoTaiChinh | undefined;

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
    routingResolveService = TestBed.inject(BaoCaoTaiChinhRoutingResolveService);
    service = TestBed.inject(BaoCaoTaiChinhService);
    resultBaoCaoTaiChinh = undefined;
  });

  describe('resolve', () => {
    it('should return IBaoCaoTaiChinh returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultBaoCaoTaiChinh = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultBaoCaoTaiChinh).toEqual({ id: 123 });
    });

    it('should return new IBaoCaoTaiChinh if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultBaoCaoTaiChinh = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultBaoCaoTaiChinh).toEqual(new BaoCaoTaiChinh());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as BaoCaoTaiChinh })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultBaoCaoTaiChinh = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultBaoCaoTaiChinh).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
