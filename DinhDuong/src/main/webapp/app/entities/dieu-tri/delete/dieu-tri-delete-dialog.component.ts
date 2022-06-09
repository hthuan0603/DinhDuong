import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IDieuTri } from '../dieu-tri.model';
import { DieuTriService } from '../service/dieu-tri.service';

@Component({
  templateUrl: './dieu-tri-delete-dialog.component.html',
})
export class DieuTriDeleteDialogComponent {
  dieuTri?: IDieuTri;

  constructor(protected dieuTriService: DieuTriService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.dieuTriService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
