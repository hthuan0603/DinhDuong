import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IHoTro, HoTro } from '../ho-tro.model';
import { HoTroService } from '../service/ho-tro.service';

@Injectable({ providedIn: 'root' })
export class HoTroRoutingResolveService implements Resolve<IHoTro> {
  constructor(protected service: HoTroService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IHoTro> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((hoTro: HttpResponse<HoTro>) => {
          if (hoTro.body) {
            return of(hoTro.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new HoTro());
  }
}
