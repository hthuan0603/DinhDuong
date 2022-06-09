import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IDieuTri, DieuTri } from '../dieu-tri.model';
import { DieuTriService } from '../service/dieu-tri.service';

@Injectable({ providedIn: 'root' })
export class DieuTriRoutingResolveService implements Resolve<IDieuTri> {
  constructor(protected service: DieuTriService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDieuTri> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((dieuTri: HttpResponse<DieuTri>) => {
          if (dieuTri.body) {
            return of(dieuTri.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DieuTri());
  }
}
