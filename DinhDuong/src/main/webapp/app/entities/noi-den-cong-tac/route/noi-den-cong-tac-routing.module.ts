import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { NoiDenCongTacComponent } from '../list/noi-den-cong-tac.component';
import { NoiDenCongTacDetailComponent } from '../detail/noi-den-cong-tac-detail.component';
import { NoiDenCongTacUpdateComponent } from '../update/noi-den-cong-tac-update.component';
import { NoiDenCongTacRoutingResolveService } from './noi-den-cong-tac-routing-resolve.service';

const noiDenCongTacRoute: Routes = [
  {
    path: '',
    component: NoiDenCongTacComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NoiDenCongTacDetailComponent,
    resolve: {
      noiDenCongTac: NoiDenCongTacRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NoiDenCongTacUpdateComponent,
    resolve: {
      noiDenCongTac: NoiDenCongTacRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NoiDenCongTacUpdateComponent,
    resolve: {
      noiDenCongTac: NoiDenCongTacRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(noiDenCongTacRoute)],
  exports: [RouterModule],
})
export class NoiDenCongTacRoutingModule {}
