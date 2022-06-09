import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IDieuTri, DieuTri } from '../dieu-tri.model';
import { DieuTriService } from '../service/dieu-tri.service';

import { DieuTriRoutingResolveService } from './dieu-tri-routing-resolve.service';

describe('DieuTri routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: DieuTriRoutingResolveService;
  let service: DieuTriService;
  let resultDieuTri: IDieuTri | undefined;

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
    routingResolveService = TestBed.inject(DieuTriRoutingResolveService);
    service = TestBed.inject(DieuTriService);
    resultDieuTri = undefined;
  });

  describe('resolve', () => {
    it('should return IDieuTri returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDieuTri = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultDieuTri).toEqual({ id: 123 });
    });

    it('should return new IDieuTri if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDieuTri = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultDieuTri).toEqual(new DieuTri());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as DieuTri })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDieuTri = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultDieuTri).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
