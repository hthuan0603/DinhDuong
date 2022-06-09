import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { LyDoCongTacComponent } from '../list/ly-do-cong-tac.component';
import { LyDoCongTacDetailComponent } from '../detail/ly-do-cong-tac-detail.component';
import { LyDoCongTacUpdateComponent } from '../update/ly-do-cong-tac-update.component';
import { LyDoCongTacRoutingResolveService } from './ly-do-cong-tac-routing-resolve.service';

const lyDoCongTacRoute: Routes = [
  {
    path: '',
    component: LyDoCongTacComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: LyDoCongTacDetailComponent,
    resolve: {
      lyDoCongTac: LyDoCongTacRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: LyDoCongTacUpdateComponent,
    resolve: {
      lyDoCongTac: LyDoCongTacRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: LyDoCongTacUpdateComponent,
    resolve: {
      lyDoCongTac: LyDoCongTacRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(lyDoCongTacRoute)],
  exports: [RouterModule],
})
export class LyDoCongTacRoutingModule {}
