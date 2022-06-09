import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { VatTuHoTroComponent } from '../list/vat-tu-ho-tro.component';
import { VatTuHoTroDetailComponent } from '../detail/vat-tu-ho-tro-detail.component';
import { VatTuHoTroUpdateComponent } from '../update/vat-tu-ho-tro-update.component';
import { VatTuHoTroRoutingResolveService } from './vat-tu-ho-tro-routing-resolve.service';

const vatTuHoTroRoute: Routes = [
  {
    path: '',
    component: VatTuHoTroComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: VatTuHoTroDetailComponent,
    resolve: {
      vatTuHoTro: VatTuHoTroRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: VatTuHoTroUpdateComponent,
    resolve: {
      vatTuHoTro: VatTuHoTroRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: VatTuHoTroUpdateComponent,
    resolve: {
      vatTuHoTro: VatTuHoTroRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(vatTuHoTroRoute)],
  exports: [RouterModule],
})
export class VatTuHoTroRoutingModule {}
