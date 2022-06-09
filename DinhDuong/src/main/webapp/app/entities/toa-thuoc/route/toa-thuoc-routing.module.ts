import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ToaThuocComponent } from '../list/toa-thuoc.component';
import { ToaThuocDetailComponent } from '../detail/toa-thuoc-detail.component';
import { ToaThuocUpdateComponent } from '../update/toa-thuoc-update.component';
import { ToaThuocRoutingResolveService } from './toa-thuoc-routing-resolve.service';

const toaThuocRoute: Routes = [
  {
    path: '',
    component: ToaThuocComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ToaThuocDetailComponent,
    resolve: {
      toaThuoc: ToaThuocRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ToaThuocUpdateComponent,
    resolve: {
      toaThuoc: ToaThuocRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ToaThuocUpdateComponent,
    resolve: {
      toaThuoc: ToaThuocRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(toaThuocRoute)],
  exports: [RouterModule],
})
export class ToaThuocRoutingModule {}
