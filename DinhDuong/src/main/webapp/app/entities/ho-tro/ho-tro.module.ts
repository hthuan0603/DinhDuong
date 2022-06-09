import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { HoTroComponent } from './list/ho-tro.component';
import { HoTroDetailComponent } from './detail/ho-tro-detail.component';
import { HoTroUpdateComponent } from './update/ho-tro-update.component';
import { HoTroDeleteDialogComponent } from './delete/ho-tro-delete-dialog.component';
import { HoTroRoutingModule } from './route/ho-tro-routing.module';

@NgModule({
  imports: [SharedModule, HoTroRoutingModule],
  declarations: [HoTroComponent, HoTroDetailComponent, HoTroUpdateComponent, HoTroDeleteDialogComponent],
  entryComponents: [HoTroDeleteDialogComponent],
})
export class HoTroModule {}
