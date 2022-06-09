import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IKetQuaCongTac, KetQuaCongTac } from '../ket-qua-cong-tac.model';
import { KetQuaCongTacService } from '../service/ket-qua-cong-tac.service';

@Injectable({ providedIn: 'root' })
export class KetQuaCongTacRoutingResolveService implements Resolve<IKetQuaCongTac> {
  constructor(protected service: KetQuaCongTacService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IKetQuaCongTac> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((ketQuaCongTac: HttpResponse<KetQuaCongTac>) => {
          if (ketQuaCongTac.body) {
            return of(ketQuaCongTac.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new KetQuaCongTac());
  }
}
