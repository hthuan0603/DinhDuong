import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ThuocComponent } from '../list/thuoc.component';
import { ThuocDetailComponent } from '../detail/thuoc-detail.component';
import { ThuocUpdateComponent } from '../update/thuoc-update.component';
import { ThuocRoutingResolveService } from './thuoc-routing-resolve.service';

const thuocRoute: Routes = [
  {
    path: '',
    component: ThuocComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ThuocDetailComponent,
    resolve: {
      thuoc: ThuocRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ThuocUpdateComponent,
    resolve: {
      thuoc: ThuocRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ThuocUpdateComponent,
    resolve: {
      thuoc: ThuocRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(thuocRoute)],
  exports: [RouterModule],
})
export class ThuocRoutingModule {}
