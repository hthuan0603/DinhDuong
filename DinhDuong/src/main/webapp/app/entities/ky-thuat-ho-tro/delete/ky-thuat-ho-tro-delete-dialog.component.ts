import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IKyThuatHoTro } from '../ky-thuat-ho-tro.model';
import { KyThuatHoTroService } from '../service/ky-thuat-ho-tro.service';

@Component({
  templateUrl: './ky-thuat-ho-tro-delete-dialog.component.html',
})
export class KyThuatHoTroDeleteDialogComponent {
  kyThuatHoTro?: IKyThuatHoTro;

  constructor(protected kyThuatHoTroService: KyThuatHoTroService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.kyThuatHoTroService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
