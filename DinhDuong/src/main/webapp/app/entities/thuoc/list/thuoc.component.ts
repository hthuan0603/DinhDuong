import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IThuoc } from '../thuoc.model';
import { ThuocService } from '../service/thuoc.service';
import { ThuocDeleteDialogComponent } from '../delete/thuoc-delete-dialog.component';

@Component({
  selector: 'jhi-thuoc',
  templateUrl: './thuoc.component.html',
})
export class ThuocComponent implements OnInit {
  thuocs?: IThuoc[];
  isLoading = false;

  constructor(protected thuocService: ThuocService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.thuocService.query().subscribe({
      next: (res: HttpResponse<IThuoc[]>) => {
        this.isLoading = false;
        this.thuocs = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IThuoc): number {
    return item.id!;
  }

  delete(thuoc: IThuoc): void {
    const modalRef = this.modalService.open(ThuocDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.thuoc = thuoc;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
