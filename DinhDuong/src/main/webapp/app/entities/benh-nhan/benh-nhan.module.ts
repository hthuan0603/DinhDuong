import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { BenhNhanComponent } from './list/benh-nhan.component';
import { BenhNhanDetailComponent } from './detail/benh-nhan-detail.component';
import { BenhNhanUpdateComponent } from './update/benh-nhan-update.component';
import { BenhNhanDeleteDialogComponent } from './delete/benh-nhan-delete-dialog.component';
import { BenhNhanRoutingModule } from './route/benh-nhan-routing.module';

@NgModule({
  imports: [SharedModule, BenhNhanRoutingModule],
  declarations: [BenhNhanComponent, BenhNhanDetailComponent, BenhNhanUpdateComponent, BenhNhanDeleteDialogComponent],
  entryComponents: [BenhNhanDeleteDialogComponent],
})
export class BenhNhanModule {}
