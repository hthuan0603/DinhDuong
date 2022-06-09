import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IToaThuoc } from '../toa-thuoc.model';
import { ToaThuocService } from '../service/toa-thuoc.service';
import { ToaThuocDeleteDialogComponent } from '../delete/toa-thuoc-delete-dialog.component';

@Component({
  selector: 'jhi-toa-thuoc',
  templateUrl: './toa-thuoc.component.html',
})
export class ToaThuocComponent implements OnInit {
  toaThuocs?: IToaThuoc[];
  isLoading = false;

  constructor(protected toaThuocService: ToaThuocService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.toaThuocService.query().subscribe({
      next: (res: HttpResponse<IToaThuoc[]>) => {
        this.isLoading = false;
        this.toaThuocs = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IToaThuoc): number {
    return item.id!;
  }

  delete(toaThuoc: IToaThuoc): void {
    const modalRef = this.modalService.open(ToaThuocDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.toaThuoc = toaThuoc;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
