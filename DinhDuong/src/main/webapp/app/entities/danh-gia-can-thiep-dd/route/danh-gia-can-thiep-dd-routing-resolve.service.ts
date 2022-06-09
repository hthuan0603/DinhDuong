import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IDanhGiaCanThiepDD, DanhGiaCanThiepDD } from '../danh-gia-can-thiep-dd.model';
import { DanhGiaCanThiepDDService } from '../service/danh-gia-can-thiep-dd.service';

@Injectable({ providedIn: 'root' })
export class DanhGiaCanThiepDDRoutingResolveService implements Resolve<IDanhGiaCanThiepDD> {
  constructor(protected service: DanhGiaCanThiepDDService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDanhGiaCanThiepDD> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((danhGiaCanThiepDD: HttpResponse<DanhGiaCanThiepDD>) => {
          if (danhGiaCanThiepDD.body) {
            return of(danhGiaCanThiepDD.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DanhGiaCanThiepDD());
  }
}
