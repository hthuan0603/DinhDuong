import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ToaThuocComponent } from './list/toa-thuoc.component';
import { ToaThuocDetailComponent } from './detail/toa-thuoc-detail.component';
import { ToaThuocUpdateComponent } from './update/toa-thuoc-update.component';
import { ToaThuocDeleteDialogComponent } from './delete/toa-thuoc-delete-dialog.component';
import { ToaThuocRoutingModule } from './route/toa-thuoc-routing.module';

@NgModule({
  imports: [SharedModule, ToaThuocRoutingModule],
  declarations: [ToaThuocComponent, ToaThuocDetailComponent, ToaThuocUpdateComponent, ToaThuocDeleteDialogComponent],
  entryComponents: [ToaThuocDeleteDialogComponent],
})
export class ToaThuocModule {}
