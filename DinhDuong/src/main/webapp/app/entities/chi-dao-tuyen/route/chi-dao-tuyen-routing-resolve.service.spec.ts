import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IChiDaoTuyen, ChiDaoTuyen } from '../chi-dao-tuyen.model';
import { ChiDaoTuyenService } from '../service/chi-dao-tuyen.service';

import { ChiDaoTuyenRoutingResolveService } from './chi-dao-tuyen-routing-resolve.service';

describe('ChiDaoTuyen routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: ChiDaoTuyenRoutingResolveService;
  let service: ChiDaoTuyenService;
  let resultChiDaoTuyen: IChiDaoTuyen | undefined;

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
    routingResolveService = TestBed.inject(ChiDaoTuyenRoutingResolveService);
    service = TestBed.inject(ChiDaoTuyenService);
    resultChiDaoTuyen = undefined;
  });

  describe('resolve', () => {
    it('should return IChiDaoTuyen returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultChiDaoTuyen = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultChiDaoTuyen).toEqual({ id: 123 });
    });

    it('should return new IChiDaoTuyen if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultChiDaoTuyen = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultChiDaoTuyen).toEqual(new ChiDaoTuyen());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as ChiDaoTuyen })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultChiDaoTuyen = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultChiDaoTuyen).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
