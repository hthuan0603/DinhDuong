import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IHoaDon } from '../hoa-don.model';
import { HoaDonService } from '../service/hoa-don.service';

@Component({
  templateUrl: './hoa-don-delete-dialog.component.html',
})
export class HoaDonDeleteDialogComponent {
  hoaDon?: IHoaDon;

  constructor(protected hoaDonService: HoaDonService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.hoaDonService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
