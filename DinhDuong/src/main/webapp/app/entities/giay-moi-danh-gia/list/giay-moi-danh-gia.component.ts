import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IGiayMoiDanhGia } from '../giay-moi-danh-gia.model';
import { GiayMoiDanhGiaService } from '../service/giay-moi-danh-gia.service';
import { GiayMoiDanhGiaDeleteDialogComponent } from '../delete/giay-moi-danh-gia-delete-dialog.component';

@Component({
  selector: 'jhi-giay-moi-danh-gia',
  templateUrl: './giay-moi-danh-gia.component.html',
})
export class GiayMoiDanhGiaComponent implements OnInit {
  giayMoiDanhGias?: IGiayMoiDanhGia[];
  isLoading = false;

  constructor(protected giayMoiDanhGiaService: GiayMoiDanhGiaService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.giayMoiDanhGiaService.query().subscribe({
      next: (res: HttpResponse<IGiayMoiDanhGia[]>) => {
        this.isLoading = false;
        this.giayMoiDanhGias = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IGiayMoiDanhGia): number {
    return item.id!;
  }

  delete(giayMoiDanhGia: IGiayMoiDanhGia): void {
    const modalRef = this.modalService.open(GiayMoiDanhGiaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.giayMoiDanhGia = giayMoiDanhGia;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
