import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IGiayMoiDanhGia, GiayMoiDanhGia } from '../giay-moi-danh-gia.model';
import { GiayMoiDanhGiaService } from '../service/giay-moi-danh-gia.service';

@Component({
  selector: 'jhi-giay-moi-danh-gia-update',
  templateUrl: './giay-moi-danh-gia-update.component.html',
})
export class GiayMoiDanhGiaUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    maBN: [],
    maNguoiTao: [],
    thoiGianChiDinh: [],
    chuanDoan: [],
    chuanDoanPhu: [],
    guiTu: [],
    guiDen: [],
    moi: [],
    noiDungHoiChuan: [],
    hoiChuanLan: [],
    thoiGianHoiChuan: [],
    trangThai: [],
  });

  constructor(
    protected giayMoiDanhGiaService: GiayMoiDanhGiaService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ giayMoiDanhGia }) => {
      if (giayMoiDanhGia.id === undefined) {
        const today = dayjs().startOf('day');
        giayMoiDanhGia.thoiGianChiDinh = today;
        giayMoiDanhGia.thoiGianHoiChuan = today;
      }

      this.updateForm(giayMoiDanhGia);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const giayMoiDanhGia = this.createFromForm();
    if (giayMoiDanhGia.id !== undefined) {
      this.subscribeToSaveResponse(this.giayMoiDanhGiaService.update(giayMoiDanhGia));
    } else {
      this.subscribeToSaveResponse(this.giayMoiDanhGiaService.create(giayMoiDanhGia));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGiayMoiDanhGia>>): void {
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

  protected updateForm(giayMoiDanhGia: IGiayMoiDanhGia): void {
    this.editForm.patchValue({
      id: giayMoiDanhGia.id,
      maBN: giayMoiDanhGia.maBN,
      maNguoiTao: giayMoiDanhGia.maNguoiTao,
      thoiGianChiDinh: giayMoiDanhGia.thoiGianChiDinh ? giayMoiDanhGia.thoiGianChiDinh.format(DATE_TIME_FORMAT) : null,
      chuanDoan: giayMoiDanhGia.chuanDoan,
      chuanDoanPhu: giayMoiDanhGia.chuanDoanPhu,
      guiTu: giayMoiDanhGia.guiTu,
      guiDen: giayMoiDanhGia.guiDen,
      moi: giayMoiDanhGia.moi,
      noiDungHoiChuan: giayMoiDanhGia.noiDungHoiChuan,
      hoiChuanLan: giayMoiDanhGia.hoiChuanLan,
      thoiGianHoiChuan: giayMoiDanhGia.thoiGianHoiChuan ? giayMoiDanhGia.thoiGianHoiChuan.format(DATE_TIME_FORMAT) : null,
      trangThai: giayMoiDanhGia.trangThai,
    });
  }

  protected createFromForm(): IGiayMoiDanhGia {
    return {
      ...new GiayMoiDanhGia(),
      id: this.editForm.get(['id'])!.value,
      maBN: this.editForm.get(['maBN'])!.value,
      maNguoiTao: this.editForm.get(['maNguoiTao'])!.value,
      thoiGianChiDinh: this.editForm.get(['thoiGianChiDinh'])!.value
        ? dayjs(this.editForm.get(['thoiGianChiDinh'])!.value, DATE_TIME_FORMAT)
        : undefined,
      chuanDoan: this.editForm.get(['chuanDoan'])!.value,
      chuanDoanPhu: this.editForm.get(['chuanDoanPhu'])!.value,
      guiTu: this.editForm.get(['guiTu'])!.value,
      guiDen: this.editForm.get(['guiDen'])!.value,
      moi: this.editForm.get(['moi'])!.value,
      noiDungHoiChuan: this.editForm.get(['noiDungHoiChuan'])!.value,
      hoiChuanLan: this.editForm.get(['hoiChuanLan'])!.value,
      thoiGianHoiChuan: this.editForm.get(['thoiGianHoiChuan'])!.value
        ? dayjs(this.editForm.get(['thoiGianHoiChuan'])!.value, DATE_TIME_FORMAT)
        : undefined,
      trangThai: this.editForm.get(['trangThai'])!.value,
    };
  }
}
