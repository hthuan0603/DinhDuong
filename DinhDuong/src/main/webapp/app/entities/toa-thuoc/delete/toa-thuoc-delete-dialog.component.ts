import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IToaThuoc } from '../toa-thuoc.model';
import { ToaThuocService } from '../service/toa-thuoc.service';

@Component({
  templateUrl: './toa-thuoc-delete-dialog.component.html',
})
export class ToaThuocDeleteDialogComponent {
  toaThuoc?: IToaThuoc;

  constructor(protected toaThuocService: ToaThuocService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.toaThuocService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
