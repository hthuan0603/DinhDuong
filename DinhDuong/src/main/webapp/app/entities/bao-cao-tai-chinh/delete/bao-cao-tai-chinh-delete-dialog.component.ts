import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IBaoCaoTaiChinh } from '../bao-cao-tai-chinh.model';
import { BaoCaoTaiChinhService } from '../service/bao-cao-tai-chinh.service';

@Component({
  templateUrl: './bao-cao-tai-chinh-delete-dialog.component.html',
})
export class BaoCaoTaiChinhDeleteDialogComponent {
  baoCaoTaiChinh?: IBaoCaoTaiChinh;

  constructor(protected baoCaoTaiChinhService: BaoCaoTaiChinhService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.baoCaoTaiChinhService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
