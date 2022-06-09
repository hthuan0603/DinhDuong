import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ILyDoCongTac, LyDoCongTac } from '../ly-do-cong-tac.model';
import { LyDoCongTacService } from '../service/ly-do-cong-tac.service';

@Injectable({ providedIn: 'root' })
export class LyDoCongTacRoutingResolveService implements Resolve<ILyDoCongTac> {
  constructor(protected service: LyDoCongTacService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILyDoCongTac> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((lyDoCongTac: HttpResponse<LyDoCongTac>) => {
          if (lyDoCongTac.body) {
            return of(lyDoCongTac.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new LyDoCongTac());
  }
}
