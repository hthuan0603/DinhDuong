import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { PhieuSuatAnComponent } from './list/phieu-suat-an.component';
import { PhieuSuatAnDetailComponent } from './detail/phieu-suat-an-detail.component';
import { PhieuSuatAnUpdateComponent } from './update/phieu-suat-an-update.component';
import { PhieuSuatAnDeleteDialogComponent } from './delete/phieu-suat-an-delete-dialog.component';
import { PhieuSuatAnRoutingModule } from './route/phieu-suat-an-routing.module';

@NgModule({
  imports: [SharedModule, PhieuSuatAnRoutingModule],
  declarations: [PhieuSuatAnComponent, PhieuSuatAnDetailComponent, PhieuSuatAnUpdateComponent, PhieuSuatAnDeleteDialogComponent],
  entryComponents: [PhieuSuatAnDeleteDialogComponent],
})
export class PhieuSuatAnModule {}
