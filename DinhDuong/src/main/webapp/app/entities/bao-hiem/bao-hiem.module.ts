import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { BaoHiemComponent } from './list/bao-hiem.component';
import { BaoHiemDetailComponent } from './detail/bao-hiem-detail.component';
import { BaoHiemUpdateComponent } from './update/bao-hiem-update.component';
import { BaoHiemDeleteDialogComponent } from './delete/bao-hiem-delete-dialog.component';
import { BaoHiemRoutingModule } from './route/bao-hiem-routing.module';

@NgModule({
  imports: [SharedModule, BaoHiemRoutingModule],
  declarations: [BaoHiemComponent, BaoHiemDetailComponent, BaoHiemUpdateComponent, BaoHiemDeleteDialogComponent],
  entryComponents: [BaoHiemDeleteDialogComponent],
})
export class BaoHiemModule {}
