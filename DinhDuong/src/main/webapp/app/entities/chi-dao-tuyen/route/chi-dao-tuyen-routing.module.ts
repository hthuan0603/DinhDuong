import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ChiDaoTuyenComponent } from '../list/chi-dao-tuyen.component';
import { ChiDaoTuyenDetailComponent } from '../detail/chi-dao-tuyen-detail.component';
import { ChiDaoTuyenUpdateComponent } from '../update/chi-dao-tuyen-update.component';
import { ChiDaoTuyenRoutingResolveService } from './chi-dao-tuyen-routing-resolve.service';

const chiDaoTuyenRoute: Routes = [
  {
    path: '',
    component: ChiDaoTuyenComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ChiDaoTuyenDetailComponent,
    resolve: {
      chiDaoTuyen: ChiDaoTuyenRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ChiDaoTuyenUpdateComponent,
    resolve: {
      chiDaoTuyen: ChiDaoTuyenRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ChiDaoTuyenUpdateComponent,
    resolve: {
      chiDaoTuyen: ChiDaoTuyenRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(chiDaoTuyenRoute)],
  exports: [RouterModule],
})
export class ChiDaoTuyenRoutingModule {}
