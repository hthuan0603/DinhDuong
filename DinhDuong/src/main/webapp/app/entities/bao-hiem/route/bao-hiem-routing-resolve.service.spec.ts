import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IBaoHiem, BaoHiem } from '../bao-hiem.model';
import { BaoHiemService } from '../service/bao-hiem.service';

import { BaoHiemRoutingResolveService } from './bao-hiem-routing-resolve.service';

describe('BaoHiem routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: BaoHiemRoutingResolveService;
  let service: BaoHiemService;
  let resultBaoHiem: IBaoHiem | undefined;

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
    routingResolveService = TestBed.inject(BaoHiemRoutingResolveService);
    service = TestBed.inject(BaoHiemService);
    resultBaoHiem = undefined;
  });

  describe('resolve', () => {
    it('should return IBaoHiem returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultBaoHiem = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultBaoHiem).toEqual({ id: 123 });
    });

    it('should return new IBaoHiem if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultBaoHiem = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultBaoHiem).toEqual(new BaoHiem());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as BaoHiem })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultBaoHiem = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultBaoHiem).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
