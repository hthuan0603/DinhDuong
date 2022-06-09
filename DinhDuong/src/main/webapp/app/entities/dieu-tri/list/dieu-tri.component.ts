import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDieuTri } from '../dieu-tri.model';
import { DieuTriService } from '../service/dieu-tri.service';
import { DieuTriDeleteDialogComponent } from '../delete/dieu-tri-delete-dialog.component';

@Component({
  selector: 'jhi-dieu-tri',
  templateUrl: './dieu-tri.component.html',
})
export class DieuTriComponent implements OnInit {
  dieuTris?: IDieuTri[];
  isLoading = false;

  constructor(protected dieuTriService: DieuTriService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.dieuTriService.query().subscribe({
      next: (res: HttpResponse<IDieuTri[]>) => {
        this.isLoading = false;
        this.dieuTris = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IDieuTri): number {
    return item.id!;
  }

  delete(dieuTri: IDieuTri): void {
    const modalRef = this.modalService.open(DieuTriDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.dieuTri = dieuTri;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
