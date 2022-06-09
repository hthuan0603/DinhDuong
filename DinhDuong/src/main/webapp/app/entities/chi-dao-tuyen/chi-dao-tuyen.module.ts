import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ChiDaoTuyenComponent } from './list/chi-dao-tuyen.component';
import { ChiDaoTuyenDetailComponent } from './detail/chi-dao-tuyen-detail.component';
import { ChiDaoTuyenUpdateComponent } from './update/chi-dao-tuyen-update.component';
import { ChiDaoTuyenDeleteDialogComponent } from './delete/chi-dao-tuyen-delete-dialog.component';
import { ChiDaoTuyenRoutingModule } from './route/chi-dao-tuyen-routing.module';

@NgModule({
  imports: [SharedModule, ChiDaoTuyenRoutingModule],
  declarations: [ChiDaoTuyenComponent, ChiDaoTuyenDetailComponent, ChiDaoTuyenUpdateComponent, ChiDaoTuyenDeleteDialogComponent],
  entryComponents: [ChiDaoTuyenDeleteDialogComponent],
})
export class ChiDaoTuyenModule {}
