import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { TTSanglocVaDanhGiaDDComponent } from './list/tt-sangloc-va-danh-gia-dd.component';
import { TTSanglocVaDanhGiaDDDetailComponent } from './detail/tt-sangloc-va-danh-gia-dd-detail.component';
import { TTSanglocVaDanhGiaDDUpdateComponent } from './update/tt-sangloc-va-danh-gia-dd-update.component';
import { TTSanglocVaDanhGiaDDDeleteDialogComponent } from './delete/tt-sangloc-va-danh-gia-dd-delete-dialog.component';
import { TTSanglocVaDanhGiaDDRoutingModule } from './route/tt-sangloc-va-danh-gia-dd-routing.module';

@NgModule({
  imports: [SharedModule, TTSanglocVaDanhGiaDDRoutingModule],
  declarations: [
    TTSanglocVaDanhGiaDDComponent,
    TTSanglocVaDanhGiaDDDetailComponent,
    TTSanglocVaDanhGiaDDUpdateComponent,
    TTSanglocVaDanhGiaDDDeleteDialogComponent,
  ],
  entryComponents: [TTSanglocVaDanhGiaDDDeleteDialogComponent],
})
export class TTSanglocVaDanhGiaDDModule {}
