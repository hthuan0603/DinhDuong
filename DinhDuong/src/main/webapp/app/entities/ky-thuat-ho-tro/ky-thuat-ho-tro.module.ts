import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { KyThuatHoTroComponent } from './list/ky-thuat-ho-tro.component';
import { KyThuatHoTroDetailComponent } from './detail/ky-thuat-ho-tro-detail.component';
import { KyThuatHoTroUpdateComponent } from './update/ky-thuat-ho-tro-update.component';
import { KyThuatHoTroDeleteDialogComponent } from './delete/ky-thuat-ho-tro-delete-dialog.component';
import { KyThuatHoTroRoutingModule } from './route/ky-thuat-ho-tro-routing.module';

@NgModule({
  imports: [SharedModule, KyThuatHoTroRoutingModule],
  declarations: [KyThuatHoTroComponent, KyThuatHoTroDetailComponent, KyThuatHoTroUpdateComponent, KyThuatHoTroDeleteDialogComponent],
  entryComponents: [KyThuatHoTroDeleteDialogComponent],
})
export class KyThuatHoTroModule {}
