import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IChiDaoTuyen } from '../chi-dao-tuyen.model';
import { ChiDaoTuyenService } from '../service/chi-dao-tuyen.service';

@Component({
  templateUrl: './chi-dao-tuyen-delete-dialog.component.html',
})
export class ChiDaoTuyenDeleteDialogComponent {
  chiDaoTuyen?: IChiDaoTuyen;

  constructor(protected chiDaoTuyenService: ChiDaoTuyenService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.chiDaoTuyenService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
