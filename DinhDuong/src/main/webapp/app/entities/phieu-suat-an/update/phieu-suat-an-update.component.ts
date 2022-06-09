import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IPhieuSuatAn, PhieuSuatAn } from '../phieu-suat-an.model';
import { PhieuSuatAnService } from '../service/phieu-suat-an.service';
import { IHoaDon } from 'app/entities/hoa-don/hoa-don.model';
import { HoaDonService } from 'app/entities/hoa-don/service/hoa-don.service';

@Component({
  selector: 'jhi-phieu-suat-an-update',
  templateUrl: './phieu-suat-an-update.component.html',
})
export class PhieuSuatAnUpdateComponent implements OnInit {
  isSaving = false;

  hoaDonsCollection: IHoaDon[] = [];

  editForm = this.fb.group({
    id: [],
    tenDv: [],
    maDV: [],
    maBN: [],
    maTheBHYT: [],
    tGSuDung: [],
    tGChiDinh: [],
    chuanDoan: [],
    chuanDoanKT: [],
    ghiChu: [],
    hoaDon: [],
  });

  constructor(
    protected phieuSuatAnService: PhieuSuatAnService,
    protected hoaDonService: HoaDonService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ phieuSuatAn }) => {
      if (phieuSuatAn.id === undefined) {
        const today = dayjs().startOf('day');
        phieuSuatAn.tGSuDung = today;
        phieuSuatAn.tGChiDinh = today;
      }

      this.updateForm(phieuSuatAn);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const phieuSuatAn = this.createFromForm();
    if (phieuSuatAn.id !== undefined) {
      this.subscribeToSaveResponse(this.phieuSuatAnService.update(phieuSuatAn));
    } else {
      this.subscribeToSaveResponse(this.phieuSuatAnService.create(phieuSuatAn));
    }
  }

  trackHoaDonById(_index: number, item: IHoaDon): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPhieuSuatAn>>): void {
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

  protected updateForm(phieuSuatAn: IPhieuSuatAn): void {
    this.editForm.patchValue({
      id: phieuSuatAn.id,
      tenDv: phieuSuatAn.tenDv,
      maDV: phieuSuatAn.maDV,
      maBN: phieuSuatAn.maBN,
      maTheBHYT: phieuSuatAn.maTheBHYT,
      tGSuDung: phieuSuatAn.tGSuDung ? phieuSuatAn.tGSuDung.format(DATE_TIME_FORMAT) : null,
      tGChiDinh: phieuSuatAn.tGChiDinh ? phieuSuatAn.tGChiDinh.format(DATE_TIME_FORMAT) : null,
      chuanDoan: phieuSuatAn.chuanDoan,
      chuanDoanKT: phieuSuatAn.chuanDoanKT,
      ghiChu: phieuSuatAn.ghiChu,
      hoaDon: phieuSuatAn.hoaDon,
    });

    this.hoaDonsCollection = this.hoaDonService.addHoaDonToCollectionIfMissing(this.hoaDonsCollection, phieuSuatAn.hoaDon);
  }

  protected loadRelationshipsOptions(): void {
    this.hoaDonService
      .query({ filter: 'phieusuatan-is-null' })
      .pipe(map((res: HttpResponse<IHoaDon[]>) => res.body ?? []))
      .pipe(map((hoaDons: IHoaDon[]) => this.hoaDonService.addHoaDonToCollectionIfMissing(hoaDons, this.editForm.get('hoaDon')!.value)))
      .subscribe((hoaDons: IHoaDon[]) => (this.hoaDonsCollection = hoaDons));
  }

  protected createFromForm(): IPhieuSuatAn {
    return {
      ...new PhieuSuatAn(),
      id: this.editForm.get(['id'])!.value,
      tenDv: this.editForm.get(['tenDv'])!.value,
      maDV: this.editForm.get(['maDV'])!.value,
      maBN: this.editForm.get(['maBN'])!.value,
      maTheBHYT: this.editForm.get(['maTheBHYT'])!.value,
      tGSuDung: this.editForm.get(['tGSuDung'])!.value ? dayjs(this.editForm.get(['tGSuDung'])!.value, DATE_TIME_FORMAT) : undefined,
      tGChiDinh: this.editForm.get(['tGChiDinh'])!.value ? dayjs(this.editForm.get(['tGChiDinh'])!.value, DATE_TIME_FORMAT) : undefined,
      chuanDoan: this.editForm.get(['chuanDoan'])!.value,
      chuanDoanKT: this.editForm.get(['chuanDoanKT'])!.value,
      ghiChu: this.editForm.get(['ghiChu'])!.value,
      hoaDon: this.editForm.get(['hoaDon'])!.value,
    };
  }
}
