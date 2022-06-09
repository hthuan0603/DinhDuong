import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { KetQuaCongTacComponent } from './list/ket-qua-cong-tac.component';
import { KetQuaCongTacDetailComponent } from './detail/ket-qua-cong-tac-detail.component';
import { KetQuaCongTacUpdateComponent } from './update/ket-qua-cong-tac-update.component';
import { KetQuaCongTacDeleteDialogComponent } from './delete/ket-qua-cong-tac-delete-dialog.component';
import { KetQuaCongTacRoutingModule } from './route/ket-qua-cong-tac-routing.module';

@NgModule({
  imports: [SharedModule, KetQuaCongTacRoutingModule],
  declarations: [KetQuaCongTacComponent, KetQuaCongTacDetailComponent, KetQuaCongTacUpdateComponent, KetQuaCongTacDeleteDialogComponent],
  entryComponents: [KetQuaCongTacDeleteDialogComponent],
})
export class KetQuaCongTacModule {}
