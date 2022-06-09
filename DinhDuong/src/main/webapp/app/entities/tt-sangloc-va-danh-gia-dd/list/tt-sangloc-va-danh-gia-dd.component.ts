import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITTSanglocVaDanhGiaDD } from '../tt-sangloc-va-danh-gia-dd.model';
import { TTSanglocVaDanhGiaDDService } from '../service/tt-sangloc-va-danh-gia-dd.service';
import { TTSanglocVaDanhGiaDDDeleteDialogComponent } from '../delete/tt-sangloc-va-danh-gia-dd-delete-dialog.component';

@Component({
  selector: 'jhi-tt-sangloc-va-danh-gia-dd',
  templateUrl: './tt-sangloc-va-danh-gia-dd.component.html',
})
export class TTSanglocVaDanhGiaDDComponent implements OnInit {
  tTSanglocVaDanhGiaDDS?: ITTSanglocVaDanhGiaDD[];
  isLoading = false;

  constructor(protected tTSanglocVaDanhGiaDDService: TTSanglocVaDanhGiaDDService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.tTSanglocVaDanhGiaDDService.query().subscribe({
      next: (res: HttpResponse<ITTSanglocVaDanhGiaDD[]>) => {
        this.isLoading = false;
        this.tTSanglocVaDanhGiaDDS = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: ITTSanglocVaDanhGiaDD): number {
    return item.id!;
  }

  delete(tTSanglocVaDanhGiaDD: ITTSanglocVaDanhGiaDD): void {
    const modalRef = this.modalService.open(TTSanglocVaDanhGiaDDDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tTSanglocVaDanhGiaDD = tTSanglocVaDanhGiaDD;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
