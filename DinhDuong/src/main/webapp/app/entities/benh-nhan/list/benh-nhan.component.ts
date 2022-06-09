import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBenhNhan } from '../benh-nhan.model';
import { BenhNhanService } from '../service/benh-nhan.service';
import { BenhNhanDeleteDialogComponent } from '../delete/benh-nhan-delete-dialog.component';

@Component({
  selector: 'jhi-benh-nhan',
  templateUrl: './benh-nhan.component.html',
})
export class BenhNhanComponent implements OnInit {
  benhNhans?: IBenhNhan[];
  isLoading = false;

  constructor(protected benhNhanService: BenhNhanService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.benhNhanService.query().subscribe({
      next: (res: HttpResponse<IBenhNhan[]>) => {
        this.isLoading = false;
        this.benhNhans = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IBenhNhan): number {
    return item.id!;
  }

  delete(benhNhan: IBenhNhan): void {
    const modalRef = this.modalService.open(BenhNhanDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.benhNhan = benhNhan;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
