import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { INoiDenCongTac, NoiDenCongTac } from '../noi-den-cong-tac.model';
import { NoiDenCongTacService } from '../service/noi-den-cong-tac.service';

@Injectable({ providedIn: 'root' })
export class NoiDenCongTacRoutingResolveService implements Resolve<INoiDenCongTac> {
  constructor(protected service: NoiDenCongTacService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INoiDenCongTac> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((noiDenCongTac: HttpResponse<NoiDenCongTac>) => {
          if (noiDenCongTac.body) {
            return of(noiDenCongTac.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NoiDenCongTac());
  }
}
