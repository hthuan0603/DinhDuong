import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { ITTSanglocVaDanhGiaDD, TTSanglocVaDanhGiaDD } from '../tt-sangloc-va-danh-gia-dd.model';
import { TTSanglocVaDanhGiaDDService } from '../service/tt-sangloc-va-danh-gia-dd.service';
import { IDanhGiaCanThiepDD } from 'app/entities/danh-gia-can-thiep-dd/danh-gia-can-thiep-dd.model';
import { DanhGiaCanThiepDDService } from 'app/entities/danh-gia-can-thiep-dd/service/danh-gia-can-thiep-dd.service';

@Component({
  selector: 'jhi-tt-sangloc-va-danh-gia-dd-update',
  templateUrl: './tt-sangloc-va-danh-gia-dd-update.component.html',
})
export class TTSanglocVaDanhGiaDDUpdateComponent implements OnInit {
  isSaving = false;

  danhGiaCanThiepDDSCollection: IDanhGiaCanThiepDD[] = [];

  editForm = this.fb.group({
    id: [],
    maBN: [],
    mangThai: [],
    bMI: [],
    danhGiaBMI: [],
    phanTramCanNangTrong3Thang: [],
    danhGiaCanNang: [],
    anUongTrongTuan: [],
    danhGiaAnUong: [],
    thoiGianChiDinh: [],
    nguyCoSDD: [],
    cheDoAn: [],
    danhGiaCanThiepDD: [],
  });

  constructor(
    protected tTSanglocVaDanhGiaDDService: TTSanglocVaDanhGiaDDService,
    protected danhGiaCanThiepDDService: DanhGiaCanThiepDDService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tTSanglocVaDanhGiaDD }) => {
      if (tTSanglocVaDanhGiaDD.id === undefined) {
        const today = dayjs().startOf('day');
        tTSanglocVaDanhGiaDD.thoiGianChiDinh = today;
      }

      this.updateForm(tTSanglocVaDanhGiaDD);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tTSanglocVaDanhGiaDD = this.createFromForm();
    if (tTSanglocVaDanhGiaDD.id !== undefined) {
      this.subscribeToSaveResponse(this.tTSanglocVaDanhGiaDDService.update(tTSanglocVaDanhGiaDD));
    } else {
      this.subscribeToSaveResponse(this.tTSanglocVaDanhGiaDDService.create(tTSanglocVaDanhGiaDD));
    }
  }

  trackDanhGiaCanThiepDDById(_index: number, item: IDanhGiaCanThiepDD): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITTSanglocVaDanhGiaDD>>): void {
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

  protected updateForm(tTSanglocVaDanhGiaDD: ITTSanglocVaDanhGiaDD): void {
    this.editForm.patchValue({
      id: tTSanglocVaDanhGiaDD.id,
      maBN: tTSanglocVaDanhGiaDD.maBN,
      mangThai: tTSanglocVaDanhGiaDD.mangThai,
      bMI: tTSanglocVaDanhGiaDD.bMI,
      danhGiaBMI: tTSanglocVaDanhGiaDD.danhGiaBMI,
      phanTramCanNangTrong3Thang: tTSanglocVaDanhGiaDD.phanTramCanNangTrong3Thang,
      danhGiaCanNang: tTSanglocVaDanhGiaDD.danhGiaCanNang,
      anUongTrongTuan: tTSanglocVaDanhGiaDD.anUongTrongTuan,
      danhGiaAnUong: tTSanglocVaDanhGiaDD.danhGiaAnUong,
      thoiGianChiDinh: tTSanglocVaDanhGiaDD.thoiGianChiDinh ? tTSanglocVaDanhGiaDD.thoiGianChiDinh.format(DATE_TIME_FORMAT) : null,
      nguyCoSDD: tTSanglocVaDanhGiaDD.nguyCoSDD,
      cheDoAn: tTSanglocVaDanhGiaDD.cheDoAn,
      danhGiaCanThiepDD: tTSanglocVaDanhGiaDD.danhGiaCanThiepDD,
    });

    this.danhGiaCanThiepDDSCollection = this.danhGiaCanThiepDDService.addDanhGiaCanThiepDDToCollectionIfMissing(
      this.danhGiaCanThiepDDSCollection,
      tTSanglocVaDanhGiaDD.danhGiaCanThiepDD
    );
  }

  protected loadRelationshipsOptions(): void {
    this.danhGiaCanThiepDDService
      .query({ filter: 'ttsanglocvadanhgiadd-is-null' })
      .pipe(map((res: HttpResponse<IDanhGiaCanThiepDD[]>) => res.body ?? []))
      .pipe(
        map((danhGiaCanThiepDDS: IDanhGiaCanThiepDD[]) =>
          this.danhGiaCanThiepDDService.addDanhGiaCanThiepDDToCollectionIfMissing(
            danhGiaCanThiepDDS,
            this.editForm.get('danhGiaCanThiepDD')!.value
          )
        )
      )
      .subscribe((danhGiaCanThiepDDS: IDanhGiaCanThiepDD[]) => (this.danhGiaCanThiepDDSCollection = danhGiaCanThiepDDS));
  }

  protected createFromForm(): ITTSanglocVaDanhGiaDD {
    return {
      ...new TTSanglocVaDanhGiaDD(),
      id: this.editForm.get(['id'])!.value,
      maBN: this.editForm.get(['maBN'])!.value,
      mangThai: this.editForm.get(['mangThai'])!.value,
      bMI: this.editForm.get(['bMI'])!.value,
      danhGiaBMI: this.editForm.get(['danhGiaBMI'])!.value,
      phanTramCanNangTrong3Thang: this.editForm.get(['phanTramCanNangTrong3Thang'])!.value,
      danhGiaCanNang: this.editForm.get(['danhGiaCanNang'])!.value,
      anUongTrongTuan: this.editForm.get(['anUongTrongTuan'])!.value,
      danhGiaAnUong: this.editForm.get(['danhGiaAnUong'])!.value,
      thoiGianChiDinh: this.editForm.get(['thoiGianChiDinh'])!.value
        ? dayjs(this.editForm.get(['thoiGianChiDinh'])!.value, DATE_TIME_FORMAT)
        : undefined,
      nguyCoSDD: this.editForm.get(['nguyCoSDD'])!.value,
      cheDoAn: this.editForm.get(['cheDoAn'])!.value,
      danhGiaCanThiepDD: this.editForm.get(['danhGiaCanThiepDD'])!.value,
    };
  }
}
