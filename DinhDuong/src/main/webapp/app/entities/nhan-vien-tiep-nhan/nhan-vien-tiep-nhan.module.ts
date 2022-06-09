import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { NhanVienTiepNhanComponent } from './list/nhan-vien-tiep-nhan.component';
import { NhanVienTiepNhanDetailComponent } from './detail/nhan-vien-tiep-nhan-detail.component';
import { NhanVienTiepNhanUpdateComponent } from './update/nhan-vien-tiep-nhan-update.component';
import { NhanVienTiepNhanDeleteDialogComponent } from './delete/nhan-vien-tiep-nhan-delete-dialog.component';
import { NhanVienTiepNhanRoutingModule } from './route/nhan-vien-tiep-nhan-routing.module';

@NgModule({
  imports: [SharedModule, NhanVienTiepNhanRoutingModule],
  declarations: [
    NhanVienTiepNhanComponent,
    NhanVienTiepNhanDetailComponent,
    NhanVienTiepNhanUpdateComponent,
    NhanVienTiepNhanDeleteDialogComponent,
  ],
  entryComponents: [NhanVienTiepNhanDeleteDialogComponent],
})
export class NhanVienTiepNhanModule {}
