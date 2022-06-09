import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { INoiDenCongTac, NoiDenCongTac } from '../noi-den-cong-tac.model';
import { NoiDenCongTacService } from '../service/noi-den-cong-tac.service';

import { NoiDenCongTacRoutingResolveService } from './noi-den-cong-tac-routing-resolve.service';

describe('NoiDenCongTac routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: NoiDenCongTacRoutingResolveService;
  let service: NoiDenCongTacService;
  let resultNoiDenCongTac: INoiDenCongTac | undefined;

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
    routingResolveService = TestBed.inject(NoiDenCongTacRoutingResolveService);
    service = TestBed.inject(NoiDenCongTacService);
    resultNoiDenCongTac = undefined;
  });

  describe('resolve', () => {
    it('should return INoiDenCongTac returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultNoiDenCongTac = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultNoiDenCongTac).toEqual({ id: 123 });
    });

    it('should return new INoiDenCongTac if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultNoiDenCongTac = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultNoiDenCongTac).toEqual(new NoiDenCongTac());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as NoiDenCongTac })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultNoiDenCongTac = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultNoiDenCongTac).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
