import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IDanhGiaCanThiepDD, DanhGiaCanThiepDD } from '../danh-gia-can-thiep-dd.model';
import { DanhGiaCanThiepDDService } from '../service/danh-gia-can-thiep-dd.service';

import { DanhGiaCanThiepDDRoutingResolveService } from './danh-gia-can-thiep-dd-routing-resolve.service';

describe('DanhGiaCanThiepDD routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: DanhGiaCanThiepDDRoutingResolveService;
  let service: DanhGiaCanThiepDDService;
  let resultDanhGiaCanThiepDD: IDanhGiaCanThiepDD | undefined;

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
    routingResolveService = TestBed.inject(DanhGiaCanThiepDDRoutingResolveService);
    service = TestBed.inject(DanhGiaCanThiepDDService);
    resultDanhGiaCanThiepDD = undefined;
  });

  describe('resolve', () => {
    it('should return IDanhGiaCanThiepDD returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDanhGiaCanThiepDD = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultDanhGiaCanThiepDD).toEqual({ id: 123 });
    });

    it('should return new IDanhGiaCanThiepDD if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDanhGiaCanThiepDD = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultDanhGiaCanThiepDD).toEqual(new DanhGiaCanThiepDD());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as DanhGiaCanThiepDD })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDanhGiaCanThiepDD = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultDanhGiaCanThiepDD).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
