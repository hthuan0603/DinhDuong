import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IBaoHiem } from '../bao-hiem.model';
import { BaoHiemService } from '../service/bao-hiem.service';

@Component({
  templateUrl: './bao-hiem-delete-dialog.component.html',
})
export class BaoHiemDeleteDialogComponent {
  baoHiem?: IBaoHiem;

  constructor(protected baoHiemService: BaoHiemService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.baoHiemService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
