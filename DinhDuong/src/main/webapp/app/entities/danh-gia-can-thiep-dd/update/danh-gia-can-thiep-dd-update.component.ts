import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IDanhGiaCanThiepDD, DanhGiaCanThiepDD } from '../danh-gia-can-thiep-dd.model';
import { DanhGiaCanThiepDDService } from '../service/danh-gia-can-thiep-dd.service';
import { IPhieuSuatAn } from 'app/entities/phieu-suat-an/phieu-suat-an.model';
import { PhieuSuatAnService } from 'app/entities/phieu-suat-an/service/phieu-suat-an.service';
import { IToaThuoc } from 'app/entities/toa-thuoc/toa-thuoc.model';
import { ToaThuocService } from 'app/entities/toa-thuoc/service/toa-thuoc.service';

@Component({
  selector: 'jhi-danh-gia-can-thiep-dd-update',
  templateUrl: './danh-gia-can-thiep-dd-update.component.html',
})
export class DanhGiaCanThiepDDUpdateComponent implements OnInit {
  isSaving = false;

  phieuSuatAnsCollection: IPhieuSuatAn[] = [];
  toaThuocsCollection: IToaThuoc[] = [];

  editForm = this.fb.group({
    id: [],
    maBN: [],
    chuanDoanLS: [],
    bSDieuTri: [],
    thayDoiCanNangTrong1Thang: [],
    danhGiaCN: [],
    khauPhanAn: [],
    danhGiaKPA: [],
    trieuChungTieuHoa: [],
    mucDo: [],
    danhGiaMD: [],
    giamChucNangHoatDong: [],
    danhGiaCNHD: [],
    stress: [],
    danhGiaStress: [],
    refeeding: [],
    danhGiaRefeeding: [],
    tongDiem: [],
    phieuSuatAn: [],
    toaThuoc: [],
  });

  constructor(
    protected danhGiaCanThiepDDService: DanhGiaCanThiepDDService,
    protected phieuSuatAnService: PhieuSuatAnService,
    protected toaThuocService: ToaThuocService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ danhGiaCanThiepDD }) => {
      this.updateForm(danhGiaCanThiepDD);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const danhGiaCanThiepDD = this.createFromForm();
    if (danhGiaCanThiepDD.id !== undefined) {
      this.subscribeToSaveResponse(this.danhGiaCanThiepDDService.update(danhGiaCanThiepDD));
    } else {
      this.subscribeToSaveResponse(this.danhGiaCanThiepDDService.create(danhGiaCanThiepDD));
    }
  }

  trackPhieuSuatAnById(_index: number, item: IPhieuSuatAn): number {
    return item.id!;
  }

  trackToaThuocById(_index: number, item: IToaThuoc): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDanhGiaCanThiepDD>>): void {
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

  protected updateForm(danhGiaCanThiepDD: IDanhGiaCanThiepDD): void {
    this.editForm.patchValue({
      id: danhGiaCanThiepDD.id,
      maBN: danhGiaCanThiepDD.maBN,
      chuanDoanLS: danhGiaCanThiepDD.chuanDoanLS,
      bSDieuTri: danhGiaCanThiepDD.bSDieuTri,
      thayDoiCanNangTrong1Thang: danhGiaCanThiepDD.thayDoiCanNangTrong1Thang,
      danhGiaCN: danhGiaCanThiepDD.danhGiaCN,
      khauPhanAn: danhGiaCanThiepDD.khauPhanAn,
      danhGiaKPA: danhGiaCanThiepDD.danhGiaKPA,
      trieuChungTieuHoa: danhGiaCanThiepDD.trieuChungTieuHoa,
      mucDo: danhGiaCanThiepDD.mucDo,
      danhGiaMD: danhGiaCanThiepDD.danhGiaMD,
      giamChucNangHoatDong: danhGiaCanThiepDD.giamChucNangHoatDong,
      danhGiaCNHD: danhGiaCanThiepDD.danhGiaCNHD,
      stress: danhGiaCanThiepDD.stress,
      danhGiaStress: danhGiaCanThiepDD.danhGiaStress,
      refeeding: danhGiaCanThiepDD.refeeding,
      danhGiaRefeeding: danhGiaCanThiepDD.danhGiaRefeeding,
      tongDiem: danhGiaCanThiepDD.tongDiem,
      phieuSuatAn: danhGiaCanThiepDD.phieuSuatAn,
      toaThuoc: danhGiaCanThiepDD.toaThuoc,
    });

    this.phieuSuatAnsCollection = this.phieuSuatAnService.addPhieuSuatAnToCollectionIfMissing(
      this.phieuSuatAnsCollection,
      danhGiaCanThiepDD.phieuSuatAn
    );
    this.toaThuocsCollection = this.toaThuocService.addToaThuocToCollectionIfMissing(this.toaThuocsCollection, danhGiaCanThiepDD.toaThuoc);
  }

  protected loadRelationshipsOptions(): void {
    this.phieuSuatAnService
      .query({ filter: 'danhgiacanthiepdd-is-null' })
      .pipe(map((res: HttpResponse<IPhieuSuatAn[]>) => res.body ?? []))
      .pipe(
        map((phieuSuatAns: IPhieuSuatAn[]) =>
          this.phieuSuatAnService.addPhieuSuatAnToCollectionIfMissing(phieuSuatAns, this.editForm.get('phieuSuatAn')!.value)
        )
      )
      .subscribe((phieuSuatAns: IPhieuSuatAn[]) => (this.phieuSuatAnsCollection = phieuSuatAns));

    this.toaThuocService
      .query({ filter: 'danhgiacanthiepdd-is-null' })
      .pipe(map((res: HttpResponse<IToaThuoc[]>) => res.body ?? []))
      .pipe(
        map((toaThuocs: IToaThuoc[]) =>
          this.toaThuocService.addToaThuocToCollectionIfMissing(toaThuocs, this.editForm.get('toaThuoc')!.value)
        )
      )
      .subscribe((toaThuocs: IToaThuoc[]) => (this.toaThuocsCollection = toaThuocs));
  }

  protected createFromForm(): IDanhGiaCanThiepDD {
    return {
      ...new DanhGiaCanThiepDD(),
      id: this.editForm.get(['id'])!.value,
      maBN: this.editForm.get(['maBN'])!.value,
      chuanDoanLS: this.editForm.get(['chuanDoanLS'])!.value,
      bSDieuTri: this.editForm.get(['bSDieuTri'])!.value,
      thayDoiCanNangTrong1Thang: this.editForm.get(['thayDoiCanNangTrong1Thang'])!.value,
      danhGiaCN: this.editForm.get(['danhGiaCN'])!.value,
      khauPhanAn: this.editForm.get(['khauPhanAn'])!.value,
      danhGiaKPA: this.editForm.get(['danhGiaKPA'])!.value,
      trieuChungTieuHoa: this.editForm.get(['trieuChungTieuHoa'])!.value,
      mucDo: this.editForm.get(['mucDo'])!.value,
      danhGiaMD: this.editForm.get(['danhGiaMD'])!.value,
      giamChucNangHoatDong: this.editForm.get(['giamChucNangHoatDong'])!.value,
      danhGiaCNHD: this.editForm.get(['danhGiaCNHD'])!.value,
      stress: this.editForm.get(['stress'])!.value,
      danhGiaStress: this.editForm.get(['danhGiaStress'])!.value,
      refeeding: this.editForm.get(['refeeding'])!.value,
      danhGiaRefeeding: this.editForm.get(['danhGiaRefeeding'])!.value,
      tongDiem: this.editForm.get(['tongDiem'])!.value,
      phieuSuatAn: this.editForm.get(['phieuSuatAn'])!.value,
      toaThuoc: this.editForm.get(['toaThuoc'])!.value,
    };
  }
}
