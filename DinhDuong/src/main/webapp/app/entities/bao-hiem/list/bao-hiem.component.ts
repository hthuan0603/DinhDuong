import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBaoHiem } from '../bao-hiem.model';
import { BaoHiemService } from '../service/bao-hiem.service';
import { BaoHiemDeleteDialogComponent } from '../delete/bao-hiem-delete-dialog.component';

@Component({
  selector: 'jhi-bao-hiem',
  templateUrl: './bao-hiem.component.html',
})
export class BaoHiemComponent implements OnInit {
  baoHiems?: IBaoHiem[];
  isLoading = false;

  constructor(protected baoHiemService: BaoHiemService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.baoHiemService.query().subscribe({
      next: (res: HttpResponse<IBaoHiem[]>) => {
        this.isLoading = false;
        this.baoHiems = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IBaoHiem): number {
    return item.id!;
  }

  delete(baoHiem: IBaoHiem): void {
    const modalRef = this.modalService.open(BaoHiemDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.baoHiem = baoHiem;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
