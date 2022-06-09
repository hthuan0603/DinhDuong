import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { GiayMoiDanhGiaComponent } from './list/giay-moi-danh-gia.component';
import { GiayMoiDanhGiaDetailComponent } from './detail/giay-moi-danh-gia-detail.component';
import { GiayMoiDanhGiaUpdateComponent } from './update/giay-moi-danh-gia-update.component';
import { GiayMoiDanhGiaDeleteDialogComponent } from './delete/giay-moi-danh-gia-delete-dialog.component';
import { GiayMoiDanhGiaRoutingModule } from './route/giay-moi-danh-gia-routing.module';

@NgModule({
  imports: [SharedModule, GiayMoiDanhGiaRoutingModule],
  declarations: [
    GiayMoiDanhGiaComponent,
    GiayMoiDanhGiaDetailComponent,
    GiayMoiDanhGiaUpdateComponent,
    GiayMoiDanhGiaDeleteDialogComponent,
  ],
  entryComponents: [GiayMoiDanhGiaDeleteDialogComponent],
})
export class GiayMoiDanhGiaModule {}
