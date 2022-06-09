import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IChiDaoTuyen, ChiDaoTuyen } from '../chi-dao-tuyen.model';
import { ChiDaoTuyenService } from '../service/chi-dao-tuyen.service';
import { ILyDoCongTac } from 'app/entities/ly-do-cong-tac/ly-do-cong-tac.model';
import { LyDoCongTacService } from 'app/entities/ly-do-cong-tac/service/ly-do-cong-tac.service';
import { INhanVienTiepNhan } from 'app/entities/nhan-vien-tiep-nhan/nhan-vien-tiep-nhan.model';
import { NhanVienTiepNhanService } from 'app/entities/nhan-vien-tiep-nhan/service/nhan-vien-tiep-nhan.service';

@Component({
  selector: 'jhi-chi-dao-tuyen-update',
  templateUrl: './chi-dao-tuyen-update.component.html',
})
export class ChiDaoTuyenUpdateComponent implements OnInit {
  isSaving = false;

  lyDoCongTacsSharedCollection: ILyDoCongTac[] = [];
  nhanVienTiepNhansSharedCollection: INhanVienTiepNhan[] = [];

  editForm = this.fb.group({
    id: [],
    maCdt: [],
    soQuyetDinh: [],
    ngayQuyetDinh: [],
    soHD: [],
    ngayHD: [],
    lyDoCT: [],
    noiDung: [],
    noiCongTac: [],
    ngayBatDau: [],
    ngayKetThuc: [],
    ghiChu: [],
    noiDungHoTro: [],
    baoCaoTaiChinh: [],
    lyDoCongTac: [],
    nhanVienTiepNhan: [],
  });

  constructor(
    protected chiDaoTuyenService: ChiDaoTuyenService,
    protected lyDoCongTacService: LyDoCongTacService,
    protected nhanVienTiepNhanService: NhanVienTiepNhanService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ chiDaoTuyen }) => {
      if (chiDaoTuyen.id === undefined) {
        const today = dayjs().startOf('day');
        chiDaoTuyen.ngayQuyetDinh = today;
        chiDaoTuyen.ngayHD = today;
        chiDaoTuyen.ngayBatDau = today;
        chiDaoTuyen.ngayKetThuc = today;
      }

      this.updateForm(chiDaoTuyen);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const chiDaoTuyen = this.createFromForm();
    if (chiDaoTuyen.id !== undefined) {
      this.subscribeToSaveResponse(this.chiDaoTuyenService.update(chiDaoTuyen));
    } else {
      this.subscribeToSaveResponse(this.chiDaoTuyenService.create(chiDaoTuyen));
    }
  }

  trackLyDoCongTacById(_index: number, item: ILyDoCongTac): number {
    return item.id!;
  }

  trackNhanVienTiepNhanById(_index: number, item: INhanVienTiepNhan): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IChiDaoTuyen>>): void {
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

  protected updateForm(chiDaoTuyen: IChiDaoTuyen): void {
    this.editForm.patchValue({
      id: chiDaoTuyen.id,
      maCdt: chiDaoTuyen.maCdt,
      soQuyetDinh: chiDaoTuyen.soQuyetDinh,
      ngayQuyetDinh: chiDaoTuyen.ngayQuyetDinh ? chiDaoTuyen.ngayQuyetDinh.format(DATE_TIME_FORMAT) : null,
      soHD: chiDaoTuyen.soHD,
      ngayHD: chiDaoTuyen.ngayHD ? chiDaoTuyen.ngayHD.format(DATE_TIME_FORMAT) : null,
      lyDoCT: chiDaoTuyen.lyDoCT,
      noiDung: chiDaoTuyen.noiDung,
      noiCongTac: chiDaoTuyen.noiCongTac,
      ngayBatDau: chiDaoTuyen.ngayBatDau ? chiDaoTuyen.ngayBatDau.format(DATE_TIME_FORMAT) : null,
      ngayKetThuc: chiDaoTuyen.ngayKetThuc ? chiDaoTuyen.ngayKetThuc.format(DATE_TIME_FORMAT) : null,
      ghiChu: chiDaoTuyen.ghiChu,
      noiDungHoTro: chiDaoTuyen.noiDungHoTro,
      baoCaoTaiChinh: chiDaoTuyen.baoCaoTaiChinh,
      lyDoCongTac: chiDaoTuyen.lyDoCongTac,
      nhanVienTiepNhan: chiDaoTuyen.nhanVienTiepNhan,
    });

    this.lyDoCongTacsSharedCollection = this.lyDoCongTacService.addLyDoCongTacToCollectionIfMissing(
      this.lyDoCongTacsSharedCollection,
      chiDaoTuyen.lyDoCongTac
    );
    this.nhanVienTiepNhansSharedCollection = this.nhanVienTiepNhanService.addNhanVienTiepNhanToCollectionIfMissing(
      this.nhanVienTiepNhansSharedCollection,
      chiDaoTuyen.nhanVienTiepNhan
    );
  }

  protected loadRelationshipsOptions(): void {
    this.lyDoCongTacService
      .query()
      .pipe(map((res: HttpResponse<ILyDoCongTac[]>) => res.body ?? []))
      .pipe(
        map((lyDoCongTacs: ILyDoCongTac[]) =>
          this.lyDoCongTacService.addLyDoCongTacToCollectionIfMissing(lyDoCongTacs, this.editForm.get('lyDoCongTac')!.value)
        )
      )
      .subscribe((lyDoCongTacs: ILyDoCongTac[]) => (this.lyDoCongTacsSharedCollection = lyDoCongTacs));

    this.nhanVienTiepNhanService
      .query()
      .pipe(map((res: HttpResponse<INhanVienTiepNhan[]>) => res.body ?? []))
      .pipe(
        map((nhanVienTiepNhans: INhanVienTiepNhan[]) =>
          this.nhanVienTiepNhanService.addNhanVienTiepNhanToCollectionIfMissing(
            nhanVienTiepNhans,
            this.editForm.get('nhanVienTiepNhan')!.value
          )
        )
      )
      .subscribe((nhanVienTiepNhans: INhanVienTiepNhan[]) => (this.nhanVienTiepNhansSharedCollection = nhanVienTiepNhans));
  }

  protected createFromForm(): IChiDaoTuyen {
    return {
      ...new ChiDaoTuyen(),
      id: this.editForm.get(['id'])!.value,
      maCdt: this.editForm.get(['maCdt'])!.value,
      soQuyetDinh: this.editForm.get(['soQuyetDinh'])!.value,
      ngayQuyetDinh: this.editForm.get(['ngayQuyetDinh'])!.value
        ? dayjs(this.editForm.get(['ngayQuyetDinh'])!.value, DATE_TIME_FORMAT)
        : undefined,
      soHD: this.editForm.get(['soHD'])!.value,
      ngayHD: this.editForm.get(['ngayHD'])!.value ? dayjs(this.editForm.get(['ngayHD'])!.value, DATE_TIME_FORMAT) : undefined,
      lyDoCT: this.editForm.get(['lyDoCT'])!.value,
      noiDung: this.editForm.get(['noiDung'])!.value,
      noiCongTac: this.editForm.get(['noiCongTac'])!.value,
      ngayBatDau: this.editForm.get(['ngayBatDau'])!.value ? dayjs(this.editForm.get(['ngayBatDau'])!.value, DATE_TIME_FORMAT) : undefined,
      ngayKetThuc: this.editForm.get(['ngayKetThuc'])!.value
        ? dayjs(this.editForm.get(['ngayKetThuc'])!.value, DATE_TIME_FORMAT)
        : undefined,
      ghiChu: this.editForm.get(['ghiChu'])!.value,
      noiDungHoTro: this.editForm.get(['noiDungHoTro'])!.value,
      baoCaoTaiChinh: this.editForm.get(['baoCaoTaiChinh'])!.value,
      lyDoCongTac: this.editForm.get(['lyDoCongTac'])!.value,
      nhanVienTiepNhan: this.editForm.get(['nhanVienTiepNhan'])!.value,
    };
  }
}
