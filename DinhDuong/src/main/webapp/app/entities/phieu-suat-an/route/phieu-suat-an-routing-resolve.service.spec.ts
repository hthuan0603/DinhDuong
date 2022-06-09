import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IPhieuSuatAn, PhieuSuatAn } from '../phieu-suat-an.model';
import { PhieuSuatAnService } from '../service/phieu-suat-an.service';

import { PhieuSuatAnRoutingResolveService } from './phieu-suat-an-routing-resolve.service';

describe('PhieuSuatAn routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: PhieuSuatAnRoutingResolveService;
  let service: PhieuSuatAnService;
  let resultPhieuSuatAn: IPhieuSuatAn | undefined;

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
    routingResolveService = TestBed.inject(PhieuSuatAnRoutingResolveService);
    service = TestBed.inject(PhieuSuatAnService);
    resultPhieuSuatAn = undefined;
  });

  describe('resolve', () => {
    it('should return IPhieuSuatAn returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultPhieuSuatAn = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultPhieuSuatAn).toEqual({ id: 123 });
    });

    it('should return new IPhieuSuatAn if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultPhieuSuatAn = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultPhieuSuatAn).toEqual(new PhieuSuatAn());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as PhieuSuatAn })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultPhieuSuatAn = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultPhieuSuatAn).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
