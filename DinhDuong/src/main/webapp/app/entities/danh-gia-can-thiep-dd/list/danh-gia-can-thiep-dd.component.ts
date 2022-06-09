import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDanhGiaCanThiepDD } from '../danh-gia-can-thiep-dd.model';
import { DanhGiaCanThiepDDService } from '../service/danh-gia-can-thiep-dd.service';
import { DanhGiaCanThiepDDDeleteDialogComponent } from '../delete/danh-gia-can-thiep-dd-delete-dialog.component';

@Component({
  selector: 'jhi-danh-gia-can-thiep-dd',
  templateUrl: './danh-gia-can-thiep-dd.component.html',
})
export class DanhGiaCanThiepDDComponent implements OnInit {
  danhGiaCanThiepDDS?: IDanhGiaCanThiepDD[];
  isLoading = false;

  constructor(protected danhGiaCanThiepDDService: DanhGiaCanThiepDDService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.danhGiaCanThiepDDService.query().subscribe({
      next: (res: HttpResponse<IDanhGiaCanThiepDD[]>) => {
        this.isLoading = false;
        this.danhGiaCanThiepDDS = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IDanhGiaCanThiepDD): number {
    return item.id!;
  }

  delete(danhGiaCanThiepDD: IDanhGiaCanThiepDD): void {
    const modalRef = this.modalService.open(DanhGiaCanThiepDDDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.danhGiaCanThiepDD = danhGiaCanThiepDD;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
