import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { BaoCaoTaiChinhComponent } from './list/bao-cao-tai-chinh.component';
import { BaoCaoTaiChinhDetailComponent } from './detail/bao-cao-tai-chinh-detail.component';
import { BaoCaoTaiChinhUpdateComponent } from './update/bao-cao-tai-chinh-update.component';
import { BaoCaoTaiChinhDeleteDialogComponent } from './delete/bao-cao-tai-chinh-delete-dialog.component';
import { BaoCaoTaiChinhRoutingModule } from './route/bao-cao-tai-chinh-routing.module';

@NgModule({
  imports: [SharedModule, BaoCaoTaiChinhRoutingModule],
  declarations: [
    BaoCaoTaiChinhComponent,
    BaoCaoTaiChinhDetailComponent,
    BaoCaoTaiChinhUpdateComponent,
    BaoCaoTaiChinhDeleteDialogComponent,
  ],
  entryComponents: [BaoCaoTaiChinhDeleteDialogComponent],
})
export class BaoCaoTaiChinhModule {}
