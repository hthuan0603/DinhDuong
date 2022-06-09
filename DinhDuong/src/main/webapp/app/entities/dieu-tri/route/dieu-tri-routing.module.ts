import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { DieuTriComponent } from '../list/dieu-tri.component';
import { DieuTriDetailComponent } from '../detail/dieu-tri-detail.component';
import { DieuTriUpdateComponent } from '../update/dieu-tri-update.component';
import { DieuTriRoutingResolveService } from './dieu-tri-routing-resolve.service';

const dieuTriRoute: Routes = [
  {
    path: '',
    component: DieuTriComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DieuTriDetailComponent,
    resolve: {
      dieuTri: DieuTriRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DieuTriUpdateComponent,
    resolve: {
      dieuTri: DieuTriRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DieuTriUpdateComponent,
    resolve: {
      dieuTri: DieuTriRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(dieuTriRoute)],
  exports: [RouterModule],
})
export class DieuTriRoutingModule {}
