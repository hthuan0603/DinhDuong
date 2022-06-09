import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { NhanVienTiepNhanComponent } from '../list/nhan-vien-tiep-nhan.component';
import { NhanVienTiepNhanDetailComponent } from '../detail/nhan-vien-tiep-nhan-detail.component';
import { NhanVienTiepNhanUpdateComponent } from '../update/nhan-vien-tiep-nhan-update.component';
import { NhanVienTiepNhanRoutingResolveService } from './nhan-vien-tiep-nhan-routing-resolve.service';

const nhanVienTiepNhanRoute: Routes = [
  {
    path: '',
    component: NhanVienTiepNhanComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NhanVienTiepNhanDetailComponent,
    resolve: {
      nhanVienTiepNhan: NhanVienTiepNhanRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NhanVienTiepNhanUpdateComponent,
    resolve: {
      nhanVienTiepNhan: NhanVienTiepNhanRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NhanVienTiepNhanUpdateComponent,
    resolve: {
      nhanVienTiepNhan: NhanVienTiepNhanRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(nhanVienTiepNhanRoute)],
  exports: [RouterModule],
})
export class NhanVienTiepNhanRoutingModule {}
