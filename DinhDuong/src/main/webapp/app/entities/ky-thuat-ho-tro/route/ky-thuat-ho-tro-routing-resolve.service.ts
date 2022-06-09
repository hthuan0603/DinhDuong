import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IKyThuatHoTro, KyThuatHoTro } from '../ky-thuat-ho-tro.model';
import { KyThuatHoTroService } from '../service/ky-thuat-ho-tro.service';

@Injectable({ providedIn: 'root' })
export class KyThuatHoTroRoutingResolveService implements Resolve<IKyThuatHoTro> {
  constructor(protected service: KyThuatHoTroService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IKyThuatHoTro> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((kyThuatHoTro: HttpResponse<KyThuatHoTro>) => {
          if (kyThuatHoTro.body) {
            return of(kyThuatHoTro.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new KyThuatHoTro());
  }
}
