import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { VatTuHoTroComponent } from './list/vat-tu-ho-tro.component';
import { VatTuHoTroDetailComponent } from './detail/vat-tu-ho-tro-detail.component';
import { VatTuHoTroUpdateComponent } from './update/vat-tu-ho-tro-update.component';
import { VatTuHoTroDeleteDialogComponent } from './delete/vat-tu-ho-tro-delete-dialog.component';
import { VatTuHoTroRoutingModule } from './route/vat-tu-ho-tro-routing.module';

@NgModule({
  imports: [SharedModule, VatTuHoTroRoutingModule],
  declarations: [VatTuHoTroComponent, VatTuHoTroDetailComponent, VatTuHoTroUpdateComponent, VatTuHoTroDeleteDialogComponent],
  entryComponents: [VatTuHoTroDeleteDialogComponent],
})
export class VatTuHoTroModule {}
