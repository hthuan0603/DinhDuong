import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { INhanVienTiepNhan, NhanVienTiepNhan } from '../nhan-vien-tiep-nhan.model';
import { NhanVienTiepNhanService } from '../service/nhan-vien-tiep-nhan.service';

@Injectable({ providedIn: 'root' })
export class NhanVienTiepNhanRoutingResolveService implements Resolve<INhanVienTiepNhan> {
  constructor(protected service: NhanVienTiepNhanService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INhanVienTiepNhan> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((nhanVienTiepNhan: HttpResponse<NhanVienTiepNhan>) => {
          if (nhanVienTiepNhan.body) {
            return of(nhanVienTiepNhan.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NhanVienTiepNhan());
  }
}
