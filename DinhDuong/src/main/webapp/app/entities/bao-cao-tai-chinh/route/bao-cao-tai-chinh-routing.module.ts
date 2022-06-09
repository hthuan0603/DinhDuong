import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { BaoCaoTaiChinhComponent } from '../list/bao-cao-tai-chinh.component';
import { BaoCaoTaiChinhDetailComponent } from '../detail/bao-cao-tai-chinh-detail.component';
import { BaoCaoTaiChinhUpdateComponent } from '../update/bao-cao-tai-chinh-update.component';
import { BaoCaoTaiChinhRoutingResolveService } from './bao-cao-tai-chinh-routing-resolve.service';

const baoCaoTaiChinhRoute: Routes = [
  {
    path: '',
    component: BaoCaoTaiChinhComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BaoCaoTaiChinhDetailComponent,
    resolve: {
      baoCaoTaiChinh: BaoCaoTaiChinhRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BaoCaoTaiChinhUpdateComponent,
    resolve: {
      baoCaoTaiChinh: BaoCaoTaiChinhRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BaoCaoTaiChinhUpdateComponent,
    resolve: {
      baoCaoTaiChinh: BaoCaoTaiChinhRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(baoCaoTaiChinhRoute)],
  exports: [RouterModule],
})
export class BaoCaoTaiChinhRoutingModule {}
