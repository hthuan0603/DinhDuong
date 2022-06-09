import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITTSanglocVaDanhGiaDD, TTSanglocVaDanhGiaDD } from '../tt-sangloc-va-danh-gia-dd.model';
import { TTSanglocVaDanhGiaDDService } from '../service/tt-sangloc-va-danh-gia-dd.service';

@Injectable({ providedIn: 'root' })
export class TTSanglocVaDanhGiaDDRoutingResolveService implements Resolve<ITTSanglocVaDanhGiaDD> {
  constructor(protected service: TTSanglocVaDanhGiaDDService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITTSanglocVaDanhGiaDD> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((tTSanglocVaDanhGiaDD: HttpResponse<TTSanglocVaDanhGiaDD>) => {
          if (tTSanglocVaDanhGiaDD.body) {
            return of(tTSanglocVaDanhGiaDD.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TTSanglocVaDanhGiaDD());
  }
}
