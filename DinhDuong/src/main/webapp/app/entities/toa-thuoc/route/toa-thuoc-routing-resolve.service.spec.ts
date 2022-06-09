import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IToaThuoc, ToaThuoc } from '../toa-thuoc.model';
import { ToaThuocService } from '../service/toa-thuoc.service';

import { ToaThuocRoutingResolveService } from './toa-thuoc-routing-resolve.service';

describe('ToaThuoc routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: ToaThuocRoutingResolveService;
  let service: ToaThuocService;
  let resultToaThuoc: IToaThuoc | undefined;

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
    routingResolveService = TestBed.inject(ToaThuocRoutingResolveService);
    service = TestBed.inject(ToaThuocService);
    resultToaThuoc = undefined;
  });

  describe('resolve', () => {
    it('should return IToaThuoc returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultToaThuoc = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultToaThuoc).toEqual({ id: 123 });
    });

    it('should return new IToaThuoc if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultToaThuoc = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultToaThuoc).toEqual(new ToaThuoc());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as ToaThuoc })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultToaThuoc = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultToaThuoc).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
