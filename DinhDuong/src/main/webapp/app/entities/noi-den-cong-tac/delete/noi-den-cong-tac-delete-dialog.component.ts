import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { INoiDenCongTac } from '../noi-den-cong-tac.model';
import { NoiDenCongTacService } from '../service/noi-den-cong-tac.service';

@Component({
  templateUrl: './noi-den-cong-tac-delete-dialog.component.html',
})
export class NoiDenCongTacDeleteDialogComponent {
  noiDenCongTac?: INoiDenCongTac;

  constructor(protected noiDenCongTacService: NoiDenCongTacService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.noiDenCongTacService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
