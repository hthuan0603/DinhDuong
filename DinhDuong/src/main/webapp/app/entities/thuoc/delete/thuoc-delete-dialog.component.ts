import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IThuoc } from '../thuoc.model';
import { ThuocService } from '../service/thuoc.service';

@Component({
  templateUrl: './thuoc-delete-dialog.component.html',
})
export class ThuocDeleteDialogComponent {
  thuoc?: IThuoc;

  constructor(protected thuocService: ThuocService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.thuocService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
