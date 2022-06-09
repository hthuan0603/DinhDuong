import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IToaThuoc, ToaThuoc } from '../toa-thuoc.model';
import { ToaThuocService } from '../service/toa-thuoc.service';

@Injectable({ providedIn: 'root' })
export class ToaThuocRoutingResolveService implements Resolve<IToaThuoc> {
  constructor(protected service: ToaThuocService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IToaThuoc> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((toaThuoc: HttpResponse<ToaThuoc>) => {
          if (toaThuoc.body) {
            return of(toaThuoc.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ToaThuoc());
  }
}
