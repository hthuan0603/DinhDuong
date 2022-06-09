import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IBenhNhan, BenhNhan } from '../benh-nhan.model';
import { BenhNhanService } from '../service/benh-nhan.service';

import { BenhNhanRoutingResolveService } from './benh-nhan-routing-resolve.service';

describe('BenhNhan routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: BenhNhanRoutingResolveService;
  let service: BenhNhanService;
  let resultBenhNhan: IBenhNhan | undefined;

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
    routingResolveService = TestBed.inject(BenhNhanRoutingResolveService);
    service = TestBed.inject(BenhNhanService);
    resultBenhNhan = undefined;
  });

  describe('resolve', () => {
    it('should return IBenhNhan returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultBenhNhan = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultBenhNhan).toEqual({ id: 123 });
    });

    it('should return new IBenhNhan if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultBenhNhan = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultBenhNhan).toEqual(new BenhNhan());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as BenhNhan })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultBenhNhan = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultBenhNhan).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
