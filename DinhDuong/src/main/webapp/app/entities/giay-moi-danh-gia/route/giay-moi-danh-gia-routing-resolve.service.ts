import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IGiayMoiDanhGia, GiayMoiDanhGia } from '../giay-moi-danh-gia.model';
import { GiayMoiDanhGiaService } from '../service/giay-moi-danh-gia.service';

@Injectable({ providedIn: 'root' })
export class GiayMoiDanhGiaRoutingResolveService implements Resolve<IGiayMoiDanhGia> {
  constructor(protected service: GiayMoiDanhGiaService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGiayMoiDanhGia> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((giayMoiDanhGia: HttpResponse<GiayMoiDanhGia>) => {
          if (giayMoiDanhGia.body) {
            return of(giayMoiDanhGia.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GiayMoiDanhGia());
  }
}
