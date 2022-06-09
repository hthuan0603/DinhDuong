import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IVatTuHoTro, VatTuHoTro } from '../vat-tu-ho-tro.model';
import { VatTuHoTroService } from '../service/vat-tu-ho-tro.service';

@Injectable({ providedIn: 'root' })
export class VatTuHoTroRoutingResolveService implements Resolve<IVatTuHoTro> {
  constructor(protected service: VatTuHoTroService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IVatTuHoTro> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((vatTuHoTro: HttpResponse<VatTuHoTro>) => {
          if (vatTuHoTro.body) {
            return of(vatTuHoTro.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new VatTuHoTro());
  }
}
