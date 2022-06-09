import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IBaoCaoTaiChinh, BaoCaoTaiChinh } from '../bao-cao-tai-chinh.model';
import { BaoCaoTaiChinhService } from '../service/bao-cao-tai-chinh.service';

@Injectable({ providedIn: 'root' })
export class BaoCaoTaiChinhRoutingResolveService implements Resolve<IBaoCaoTaiChinh> {
  constructor(protected service: BaoCaoTaiChinhService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBaoCaoTaiChinh> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((baoCaoTaiChinh: HttpResponse<BaoCaoTaiChinh>) => {
          if (baoCaoTaiChinh.body) {
            return of(baoCaoTaiChinh.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BaoCaoTaiChinh());
  }
}
