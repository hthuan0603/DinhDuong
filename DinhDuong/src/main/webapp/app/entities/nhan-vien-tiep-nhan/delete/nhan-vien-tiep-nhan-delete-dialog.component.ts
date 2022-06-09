import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { INhanVienTiepNhan } from '../nhan-vien-tiep-nhan.model';
import { NhanVienTiepNhanService } from '../service/nhan-vien-tiep-nhan.service';

@Component({
  templateUrl: './nhan-vien-tiep-nhan-delete-dialog.component.html',
})
export class NhanVienTiepNhanDeleteDialogComponent {
  nhanVienTiepNhan?: INhanVienTiepNhan;

  constructor(protected nhanVienTiepNhanService: NhanVienTiepNhanService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.nhanVienTiepNhanService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
