import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IThuoc, Thuoc } from '../thuoc.model';
import { ThuocService } from '../service/thuoc.service';

@Component({
  selector: 'jhi-thuoc-update',
  templateUrl: './thuoc-update.component.html',
})
export class ThuocUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    tenThuoc: [],
    duongDung: [],
    soLuong: [],
    cachDung: [],
    hoatChat: [],
    dVT: [],
    donGia: [],
    thanhTien: [],
    loaiTTCu: [],
    loaiTTMoi: [],
    lieuDung: [],
    kS: [],
  });

  constructor(protected thuocService: ThuocService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ thuoc }) => {
      this.updateForm(thuoc);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const thuoc = this.createFromForm();
    if (thuoc.id !== undefined) {
      this.subscribeToSaveResponse(this.thuocService.update(thuoc));
    } else {
      this.subscribeToSaveResponse(this.thuocService.create(thuoc));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IThuoc>>): void {
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

  protected updateForm(thuoc: IThuoc): void {
    this.editForm.patchValue({
      id: thuoc.id,
      tenThuoc: thuoc.tenThuoc,
      duongDung: thuoc.duongDung,
      soLuong: thuoc.soLuong,
      cachDung: thuoc.cachDung,
      hoatChat: thuoc.hoatChat,
      dVT: thuoc.dVT,
      donGia: thuoc.donGia,
      thanhTien: thuoc.thanhTien,
      loaiTTCu: thuoc.loaiTTCu,
      loaiTTMoi: thuoc.loaiTTMoi,
      lieuDung: thuoc.lieuDung,
      kS: thuoc.kS,
    });
  }

  protected createFromForm(): IThuoc {
    return {
      ...new Thuoc(),
      id: this.editForm.get(['id'])!.value,
      tenThuoc: this.editForm.get(['tenThuoc'])!.value,
      duongDung: this.editForm.get(['duongDung'])!.value,
      soLuong: this.editForm.get(['soLuong'])!.value,
      cachDung: this.editForm.get(['cachDung'])!.value,
      hoatChat: this.editForm.get(['hoatChat'])!.value,
      dVT: this.editForm.get(['dVT'])!.value,
      donGia: this.editForm.get(['donGia'])!.value,
      thanhTien: this.editForm.get(['thanhTien'])!.value,
      loaiTTCu: this.editForm.get(['loaiTTCu'])!.value,
      loaiTTMoi: this.editForm.get(['loaiTTMoi'])!.value,
      lieuDung: this.editForm.get(['lieuDung'])!.value,
      kS: this.editForm.get(['kS'])!.value,
    };
  }
}
