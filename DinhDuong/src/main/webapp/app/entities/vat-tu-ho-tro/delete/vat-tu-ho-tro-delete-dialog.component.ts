import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IVatTuHoTro } from '../vat-tu-ho-tro.model';
import { VatTuHoTroService } from '../service/vat-tu-ho-tro.service';

@Component({
  templateUrl: './vat-tu-ho-tro-delete-dialog.component.html',
})
export class VatTuHoTroDeleteDialogComponent {
  vatTuHoTro?: IVatTuHoTro;

  constructor(protected vatTuHoTroService: VatTuHoTroService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.vatTuHoTroService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
