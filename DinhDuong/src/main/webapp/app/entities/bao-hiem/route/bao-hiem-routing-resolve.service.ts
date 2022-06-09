import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IBaoHiem, BaoHiem } from '../bao-hiem.model';
import { BaoHiemService } from '../service/bao-hiem.service';

@Injectable({ providedIn: 'root' })
export class BaoHiemRoutingResolveService implements Resolve<IBaoHiem> {
  constructor(protected service: BaoHiemService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBaoHiem> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((baoHiem: HttpResponse<BaoHiem>) => {
          if (baoHiem.body) {
            return of(baoHiem.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BaoHiem());
  }
}
