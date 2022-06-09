import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IChiDaoTuyen, ChiDaoTuyen } from '../chi-dao-tuyen.model';
import { ChiDaoTuyenService } from '../service/chi-dao-tuyen.service';

@Injectable({ providedIn: 'root' })
export class ChiDaoTuyenRoutingResolveService implements Resolve<IChiDaoTuyen> {
  constructor(protected service: ChiDaoTuyenService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IChiDaoTuyen> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((chiDaoTuyen: HttpResponse<ChiDaoTuyen>) => {
          if (chiDaoTuyen.body) {
            return of(chiDaoTuyen.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ChiDaoTuyen());
  }
}
