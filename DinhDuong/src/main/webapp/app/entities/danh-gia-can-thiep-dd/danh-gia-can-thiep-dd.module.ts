import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { DanhGiaCanThiepDDComponent } from './list/danh-gia-can-thiep-dd.component';
import { DanhGiaCanThiepDDDetailComponent } from './detail/danh-gia-can-thiep-dd-detail.component';
import { DanhGiaCanThiepDDUpdateComponent } from './update/danh-gia-can-thiep-dd-update.component';
import { DanhGiaCanThiepDDDeleteDialogComponent } from './delete/danh-gia-can-thiep-dd-delete-dialog.component';
import { DanhGiaCanThiepDDRoutingModule } from './route/danh-gia-can-thiep-dd-routing.module';

@NgModule({
  imports: [SharedModule, DanhGiaCanThiepDDRoutingModule],
  declarations: [
    DanhGiaCanThiepDDComponent,
    DanhGiaCanThiepDDDetailComponent,
    DanhGiaCanThiepDDUpdateComponent,
    DanhGiaCanThiepDDDeleteDialogComponent,
  ],
  entryComponents: [DanhGiaCanThiepDDDeleteDialogComponent],
})
export class DanhGiaCanThiepDDModule {}
