import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IPhieuSuatAn } from '../phieu-suat-an.model';
import { PhieuSuatAnService } from '../service/phieu-suat-an.service';

@Component({
  templateUrl: './phieu-suat-an-delete-dialog.component.html',
})
export class PhieuSuatAnDeleteDialogComponent {
  phieuSuatAn?: IPhieuSuatAn;

  constructor(protected phieuSuatAnService: PhieuSuatAnService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.phieuSuatAnService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
