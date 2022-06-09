import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { TTSanglocVaDanhGiaDDComponent } from '../list/tt-sangloc-va-danh-gia-dd.component';
import { TTSanglocVaDanhGiaDDDetailComponent } from '../detail/tt-sangloc-va-danh-gia-dd-detail.component';
import { TTSanglocVaDanhGiaDDUpdateComponent } from '../update/tt-sangloc-va-danh-gia-dd-update.component';
import { TTSanglocVaDanhGiaDDRoutingResolveService } from './tt-sangloc-va-danh-gia-dd-routing-resolve.service';

const tTSanglocVaDanhGiaDDRoute: Routes = [
  {
    path: '',
    component: TTSanglocVaDanhGiaDDComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TTSanglocVaDanhGiaDDDetailComponent,
    resolve: {
      tTSanglocVaDanhGiaDD: TTSanglocVaDanhGiaDDRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TTSanglocVaDanhGiaDDUpdateComponent,
    resolve: {
      tTSanglocVaDanhGiaDD: TTSanglocVaDanhGiaDDRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TTSanglocVaDanhGiaDDUpdateComponent,
    resolve: {
      tTSanglocVaDanhGiaDD: TTSanglocVaDanhGiaDDRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(tTSanglocVaDanhGiaDDRoute)],
  exports: [RouterModule],
})
export class TTSanglocVaDanhGiaDDRoutingModule {}
