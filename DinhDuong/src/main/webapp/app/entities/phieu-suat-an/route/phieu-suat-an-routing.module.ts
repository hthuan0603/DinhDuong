import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { PhieuSuatAnComponent } from '../list/phieu-suat-an.component';
import { PhieuSuatAnDetailComponent } from '../detail/phieu-suat-an-detail.component';
import { PhieuSuatAnUpdateComponent } from '../update/phieu-suat-an-update.component';
import { PhieuSuatAnRoutingResolveService } from './phieu-suat-an-routing-resolve.service';

const phieuSuatAnRoute: Routes = [
  {
    path: '',
    component: PhieuSuatAnComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PhieuSuatAnDetailComponent,
    resolve: {
      phieuSuatAn: PhieuSuatAnRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PhieuSuatAnUpdateComponent,
    resolve: {
      phieuSuatAn: PhieuSuatAnRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PhieuSuatAnUpdateComponent,
    resolve: {
      phieuSuatAn: PhieuSuatAnRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(phieuSuatAnRoute)],
  exports: [RouterModule],
})
export class PhieuSuatAnRoutingModule {}
