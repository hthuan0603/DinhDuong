import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ThuocComponent } from './list/thuoc.component';
import { ThuocDetailComponent } from './detail/thuoc-detail.component';
import { ThuocUpdateComponent } from './update/thuoc-update.component';
import { ThuocDeleteDialogComponent } from './delete/thuoc-delete-dialog.component';
import { ThuocRoutingModule } from './route/thuoc-routing.module';

@NgModule({
  imports: [SharedModule, ThuocRoutingModule],
  declarations: [ThuocComponent, ThuocDetailComponent, ThuocUpdateComponent, ThuocDeleteDialogComponent],
  entryComponents: [ThuocDeleteDialogComponent],
})
export class ThuocModule {}
