import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { GiayMoiDanhGiaComponent } from '../list/giay-moi-danh-gia.component';
import { GiayMoiDanhGiaDetailComponent } from '../detail/giay-moi-danh-gia-detail.component';
import { GiayMoiDanhGiaUpdateComponent } from '../update/giay-moi-danh-gia-update.component';
import { GiayMoiDanhGiaRoutingResolveService } from './giay-moi-danh-gia-routing-resolve.service';

const giayMoiDanhGiaRoute: Routes = [
  {
    path: '',
    component: GiayMoiDanhGiaComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GiayMoiDanhGiaDetailComponent,
    resolve: {
      giayMoiDanhGia: GiayMoiDanhGiaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GiayMoiDanhGiaUpdateComponent,
    resolve: {
      giayMoiDanhGia: GiayMoiDanhGiaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GiayMoiDanhGiaUpdateComponent,
    resolve: {
      giayMoiDanhGia: GiayMoiDanhGiaRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(giayMoiDanhGiaRoute)],
  exports: [RouterModule],
})
export class GiayMoiDanhGiaRoutingModule {}
