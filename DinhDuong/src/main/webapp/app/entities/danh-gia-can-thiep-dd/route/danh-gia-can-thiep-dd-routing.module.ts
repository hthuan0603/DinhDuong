import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { DanhGiaCanThiepDDComponent } from '../list/danh-gia-can-thiep-dd.component';
import { DanhGiaCanThiepDDDetailComponent } from '../detail/danh-gia-can-thiep-dd-detail.component';
import { DanhGiaCanThiepDDUpdateComponent } from '../update/danh-gia-can-thiep-dd-update.component';
import { DanhGiaCanThiepDDRoutingResolveService } from './danh-gia-can-thiep-dd-routing-resolve.service';

const danhGiaCanThiepDDRoute: Routes = [
  {
    path: '',
    component: DanhGiaCanThiepDDComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DanhGiaCanThiepDDDetailComponent,
    resolve: {
      danhGiaCanThiepDD: DanhGiaCanThiepDDRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DanhGiaCanThiepDDUpdateComponent,
    resolve: {
      danhGiaCanThiepDD: DanhGiaCanThiepDDRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DanhGiaCanThiepDDUpdateComponent,
    resolve: {
      danhGiaCanThiepDD: DanhGiaCanThiepDDRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(danhGiaCanThiepDDRoute)],
  exports: [RouterModule],
})
export class DanhGiaCanThiepDDRoutingModule {}
