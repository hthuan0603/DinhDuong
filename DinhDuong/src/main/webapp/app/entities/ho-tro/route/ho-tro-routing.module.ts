import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { HoTroComponent } from '../list/ho-tro.component';
import { HoTroDetailComponent } from '../detail/ho-tro-detail.component';
import { HoTroUpdateComponent } from '../update/ho-tro-update.component';
import { HoTroRoutingResolveService } from './ho-tro-routing-resolve.service';

const hoTroRoute: Routes = [
  {
    path: '',
    component: HoTroComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: HoTroDetailComponent,
    resolve: {
      hoTro: HoTroRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: HoTroUpdateComponent,
    resolve: {
      hoTro: HoTroRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: HoTroUpdateComponent,
    resolve: {
      hoTro: HoTroRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(hoTroRoute)],
  exports: [RouterModule],
})
export class HoTroRoutingModule {}
