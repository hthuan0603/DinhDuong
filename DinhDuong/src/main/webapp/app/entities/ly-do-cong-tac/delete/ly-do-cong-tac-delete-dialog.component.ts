import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ILyDoCongTac } from '../ly-do-cong-tac.model';
import { LyDoCongTacService } from '../service/ly-do-cong-tac.service';

@Component({
  templateUrl: './ly-do-cong-tac-delete-dialog.component.html',
})
export class LyDoCongTacDeleteDialogComponent {
  lyDoCongTac?: ILyDoCongTac;

  constructor(protected lyDoCongTacService: LyDoCongTacService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.lyDoCongTacService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
