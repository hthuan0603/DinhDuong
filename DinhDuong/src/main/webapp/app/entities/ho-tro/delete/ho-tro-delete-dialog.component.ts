import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IHoTro } from '../ho-tro.model';
import { HoTroService } from '../service/ho-tro.service';

@Component({
  templateUrl: './ho-tro-delete-dialog.component.html',
})
export class HoTroDeleteDialogComponent {
  hoTro?: IHoTro;

  constructor(protected hoTroService: HoTroService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.hoTroService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
