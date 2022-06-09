import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { DieuTriComponent } from './list/dieu-tri.component';
import { DieuTriDetailComponent } from './detail/dieu-tri-detail.component';
import { DieuTriUpdateComponent } from './update/dieu-tri-update.component';
import { DieuTriDeleteDialogComponent } from './delete/dieu-tri-delete-dialog.component';
import { DieuTriRoutingModule } from './route/dieu-tri-routing.module';

@NgModule({
  imports: [SharedModule, DieuTriRoutingModule],
  declarations: [DieuTriComponent, DieuTriDetailComponent, DieuTriUpdateComponent, DieuTriDeleteDialogComponent],
  entryComponents: [DieuTriDeleteDialogComponent],
})
export class DieuTriModule {}
