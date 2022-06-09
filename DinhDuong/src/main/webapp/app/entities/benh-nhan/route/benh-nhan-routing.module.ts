import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { BenhNhanComponent } from '../list/benh-nhan.component';
import { BenhNhanDetailComponent } from '../detail/benh-nhan-detail.component';
import { BenhNhanUpdateComponent } from '../update/benh-nhan-update.component';
import { BenhNhanRoutingResolveService } from './benh-nhan-routing-resolve.service';

const benhNhanRoute: Routes = [
  {
    path: '',
    component: BenhNhanComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BenhNhanDetailComponent,
    resolve: {
      benhNhan: BenhNhanRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BenhNhanUpdateComponent,
    resolve: {
      benhNhan: BenhNhanRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BenhNhanUpdateComponent,
    resolve: {
      benhNhan: BenhNhanRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(benhNhanRoute)],
  exports: [RouterModule],
})
export class BenhNhanRoutingModule {}
