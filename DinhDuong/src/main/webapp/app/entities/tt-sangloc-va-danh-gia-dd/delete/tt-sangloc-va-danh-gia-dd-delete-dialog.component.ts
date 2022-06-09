import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ITTSanglocVaDanhGiaDD } from '../tt-sangloc-va-danh-gia-dd.model';
import { TTSanglocVaDanhGiaDDService } from '../service/tt-sangloc-va-danh-gia-dd.service';

@Component({
  templateUrl: './tt-sangloc-va-danh-gia-dd-delete-dialog.component.html',
})
export class TTSanglocVaDanhGiaDDDeleteDialogComponent {
  tTSanglocVaDanhGiaDD?: ITTSanglocVaDanhGiaDD;

  constructor(protected tTSanglocVaDanhGiaDDService: TTSanglocVaDanhGiaDDService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tTSanglocVaDanhGiaDDService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
