import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { LyDoCongTacComponent } from './list/ly-do-cong-tac.component';
import { LyDoCongTacDetailComponent } from './detail/ly-do-cong-tac-detail.component';
import { LyDoCongTacUpdateComponent } from './update/ly-do-cong-tac-update.component';
import { LyDoCongTacDeleteDialogComponent } from './delete/ly-do-cong-tac-delete-dialog.component';
import { LyDoCongTacRoutingModule } from './route/ly-do-cong-tac-routing.module';

@NgModule({
  imports: [SharedModule, LyDoCongTacRoutingModule],
  declarations: [LyDoCongTacComponent, LyDoCongTacDetailComponent, LyDoCongTacUpdateComponent, LyDoCongTacDeleteDialogComponent],
  entryComponents: [LyDoCongTacDeleteDialogComponent],
})
export class LyDoCongTacModule {}
