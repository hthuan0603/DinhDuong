import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IBenhNhan, BenhNhan } from '../benh-nhan.model';
import { BenhNhanService } from '../service/benh-nhan.service';

@Injectable({ providedIn: 'root' })
export class BenhNhanRoutingResolveService implements Resolve<IBenhNhan> {
  constructor(protected service: BenhNhanService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBenhNhan> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((benhNhan: HttpResponse<BenhNhan>) => {
          if (benhNhan.body) {
            return of(benhNhan.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BenhNhan());
  }
}
