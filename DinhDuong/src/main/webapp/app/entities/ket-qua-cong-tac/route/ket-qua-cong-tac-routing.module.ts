import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { KetQuaCongTacComponent } from '../list/ket-qua-cong-tac.component';
import { KetQuaCongTacDetailComponent } from '../detail/ket-qua-cong-tac-detail.component';
import { KetQuaCongTacUpdateComponent } from '../update/ket-qua-cong-tac-update.component';
import { KetQuaCongTacRoutingResolveService } from './ket-qua-cong-tac-routing-resolve.service';

const ketQuaCongTacRoute: Routes = [
  {
    path: '',
    component: KetQuaCongTacComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: KetQuaCongTacDetailComponent,
    resolve: {
      ketQuaCongTac: KetQuaCongTacRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: KetQuaCongTacUpdateComponent,
    resolve: {
      ketQuaCongTac: KetQuaCongTacRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: KetQuaCongTacUpdateComponent,
    resolve: {
      ketQuaCongTac: KetQuaCongTacRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(ketQuaCongTacRoute)],
  exports: [RouterModule],
})
export class KetQuaCongTacRoutingModule {}
