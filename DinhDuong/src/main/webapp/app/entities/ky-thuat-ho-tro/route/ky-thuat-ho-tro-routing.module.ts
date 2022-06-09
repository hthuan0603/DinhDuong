import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { KyThuatHoTroComponent } from '../list/ky-thuat-ho-tro.component';
import { KyThuatHoTroDetailComponent } from '../detail/ky-thuat-ho-tro-detail.component';
import { KyThuatHoTroUpdateComponent } from '../update/ky-thuat-ho-tro-update.component';
import { KyThuatHoTroRoutingResolveService } from './ky-thuat-ho-tro-routing-resolve.service';

const kyThuatHoTroRoute: Routes = [
  {
    path: '',
    component: KyThuatHoTroComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: KyThuatHoTroDetailComponent,
    resolve: {
      kyThuatHoTro: KyThuatHoTroRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: KyThuatHoTroUpdateComponent,
    resolve: {
      kyThuatHoTro: KyThuatHoTroRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: KyThuatHoTroUpdateComponent,
    resolve: {
      kyThuatHoTro: KyThuatHoTroRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(kyThuatHoTroRoute)],
  exports: [RouterModule],
})
export class KyThuatHoTroRoutingModule {}
