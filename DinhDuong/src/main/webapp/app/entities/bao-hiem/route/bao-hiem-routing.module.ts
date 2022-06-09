import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { BaoHiemComponent } from '../list/bao-hiem.component';
import { BaoHiemDetailComponent } from '../detail/bao-hiem-detail.component';
import { BaoHiemUpdateComponent } from '../update/bao-hiem-update.component';
import { BaoHiemRoutingResolveService } from './bao-hiem-routing-resolve.service';

const baoHiemRoute: Routes = [
  {
    path: '',
    component: BaoHiemComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BaoHiemDetailComponent,
    resolve: {
      baoHiem: BaoHiemRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BaoHiemUpdateComponent,
    resolve: {
      baoHiem: BaoHiemRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BaoHiemUpdateComponent,
    resolve: {
      baoHiem: BaoHiemRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(baoHiemRoute)],
  exports: [RouterModule],
})
export class BaoHiemRoutingModule {}
