import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IBenhNhan } from '../benh-nhan.model';
import { BenhNhanService } from '../service/benh-nhan.service';

@Component({
  templateUrl: './benh-nhan-delete-dialog.component.html',
})
export class BenhNhanDeleteDialogComponent {
  benhNhan?: IBenhNhan;

  constructor(protected benhNhanService: BenhNhanService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.benhNhanService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
