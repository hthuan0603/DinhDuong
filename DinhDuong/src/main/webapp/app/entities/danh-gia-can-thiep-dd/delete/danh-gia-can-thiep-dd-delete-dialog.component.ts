import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IDanhGiaCanThiepDD } from '../danh-gia-can-thiep-dd.model';
import { DanhGiaCanThiepDDService } from '../service/danh-gia-can-thiep-dd.service';

@Component({
  templateUrl: './danh-gia-can-thiep-dd-delete-dialog.component.html',
})
export class DanhGiaCanThiepDDDeleteDialogComponent {
  danhGiaCanThiepDD?: IDanhGiaCanThiepDD;

  constructor(protected danhGiaCanThiepDDService: DanhGiaCanThiepDDService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.danhGiaCanThiepDDService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
