import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IGiayMoiDanhGia } from '../giay-moi-danh-gia.model';
import { GiayMoiDanhGiaService } from '../service/giay-moi-danh-gia.service';

@Component({
  templateUrl: './giay-moi-danh-gia-delete-dialog.component.html',
})
export class GiayMoiDanhGiaDeleteDialogComponent {
  giayMoiDanhGia?: IGiayMoiDanhGia;

  constructor(protected giayMoiDanhGiaService: GiayMoiDanhGiaService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.giayMoiDanhGiaService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
