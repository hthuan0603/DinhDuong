import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IThuoc, Thuoc } from '../thuoc.model';
import { ThuocService } from '../service/thuoc.service';

@Injectable({ providedIn: 'root' })
export class ThuocRoutingResolveService implements Resolve<IThuoc> {
  constructor(protected service: ThuocService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IThuoc> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((thuoc: HttpResponse<Thuoc>) => {
          if (thuoc.body) {
            return of(thuoc.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Thuoc());
  }
}
