import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IKetQuaCongTac } from '../ket-qua-cong-tac.model';
import { KetQuaCongTacService } from '../service/ket-qua-cong-tac.service';

@Component({
  templateUrl: './ket-qua-cong-tac-delete-dialog.component.html',
})
export class KetQuaCongTacDeleteDialogComponent {
  ketQuaCongTac?: IKetQuaCongTac;

  constructor(protected ketQuaCongTacService: KetQuaCongTacService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ketQuaCongTacService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
