import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { INhanVienTiepNhan, NhanVienTiepNhan } from '../nhan-vien-tiep-nhan.model';
import { NhanVienTiepNhanService } from '../service/nhan-vien-tiep-nhan.service';

@Component({
  selector: 'jhi-nhan-vien-tiep-nhan-update',
  templateUrl: './nhan-vien-tiep-nhan-update.component.html',
})
export class NhanVienTiepNhanUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    maNhanVien: [],
    tenNhanVien: [],
  });

  constructor(
    protected nhanVienTiepNhanService: NhanVienTiepNhanService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ nhanVienTiepNhan }) => {
      this.updateForm(nhanVienTiepNhan);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const nhanVienTiepNhan = this.createFromForm();
    if (nhanVienTiepNhan.id !== undefined) {
      this.subscribeToSaveResponse(this.nhanVienTiepNhanService.update(nhanVienTiepNhan));
    } else {
      this.subscribeToSaveResponse(this.nhanVienTiepNhanService.create(nhanVienTiepNhan));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INhanVienTiepNhan>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(nhanVienTiepNhan: INhanVienTiepNhan): void {
    this.editForm.patchValue({
      id: nhanVienTiepNhan.id,
      maNhanVien: nhanVienTiepNhan.maNhanVien,
      tenNhanVien: nhanVienTiepNhan.tenNhanVien,
    });
  }

  protected createFromForm(): INhanVienTiepNhan {
    return {
      ...new NhanVienTiepNhan(),
      id: this.editForm.get(['id'])!.value,
      maNhanVien: this.editForm.get(['maNhanVien'])!.value,
      tenNhanVien: this.editForm.get(['tenNhanVien'])!.value,
    };
  }
}
