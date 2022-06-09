import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPhieuSuatAn } from '../phieu-suat-an.model';
import { PhieuSuatAnService } from '../service/phieu-suat-an.service';
import { PhieuSuatAnDeleteDialogComponent } from '../delete/phieu-suat-an-delete-dialog.component';

@Component({
  selector: 'jhi-phieu-suat-an',
  templateUrl: './phieu-suat-an.component.html',
})
export class PhieuSuatAnComponent implements OnInit {
  phieuSuatAns?: IPhieuSuatAn[];
  isLoading = false;

  constructor(protected phieuSuatAnService: PhieuSuatAnService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.phieuSuatAnService.query().subscribe({
      next: (res: HttpResponse<IPhieuSuatAn[]>) => {
        this.isLoading = false;
        this.phieuSuatAns = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IPhieuSuatAn): number {
    return item.id!;
  }

  delete(phieuSuatAn: IPhieuSuatAn): void {
    const modalRef = this.modalService.open(PhieuSuatAnDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.phieuSuatAn = phieuSuatAn;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
