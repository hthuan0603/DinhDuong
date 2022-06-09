import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { NoiDenCongTacComponent } from './list/noi-den-cong-tac.component';
import { NoiDenCongTacDetailComponent } from './detail/noi-den-cong-tac-detail.component';
import { NoiDenCongTacUpdateComponent } from './update/noi-den-cong-tac-update.component';
import { NoiDenCongTacDeleteDialogComponent } from './delete/noi-den-cong-tac-delete-dialog.component';
import { NoiDenCongTacRoutingModule } from './route/noi-den-cong-tac-routing.module';

@NgModule({
  imports: [SharedModule, NoiDenCongTacRoutingModule],
  declarations: [NoiDenCongTacComponent, NoiDenCongTacDetailComponent, NoiDenCongTacUpdateComponent, NoiDenCongTacDeleteDialogComponent],
  entryComponents: [NoiDenCongTacDeleteDialogComponent],
})
export class NoiDenCongTacModule {}
