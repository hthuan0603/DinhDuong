import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IBenhNhan, BenhNhan } from '../benh-nhan.model';
import { BenhNhanService } from '../service/benh-nhan.service';
import { IBaoHiem } from 'app/entities/bao-hiem/bao-hiem.model';
import { BaoHiemService } from 'app/entities/bao-hiem/service/bao-hiem.service';
import { IDieuTri } from 'app/entities/dieu-tri/dieu-tri.model';
import { DieuTriService } from 'app/entities/dieu-tri/service/dieu-tri.service';
import { ITTSanglocVaDanhGiaDD } from 'app/entities/tt-sangloc-va-danh-gia-dd/tt-sangloc-va-danh-gia-dd.model';
import { TTSanglocVaDanhGiaDDService } from 'app/entities/tt-sangloc-va-danh-gia-dd/service/tt-sangloc-va-danh-gia-dd.service';
import { IHoaDon } from 'app/entities/hoa-don/hoa-don.model';
import { HoaDonService } from 'app/entities/hoa-don/service/hoa-don.service';
import { IDanhGiaCanThiepDD } from 'app/entities/danh-gia-can-thiep-dd/danh-gia-can-thiep-dd.model';
import { DanhGiaCanThiepDDService } from 'app/entities/danh-gia-can-thiep-dd/service/danh-gia-can-thiep-dd.service';
import { IPhieuSuatAn } from 'app/entities/phieu-suat-an/phieu-suat-an.model';
import { PhieuSuatAnService } from 'app/entities/phieu-suat-an/service/phieu-suat-an.service';
import { IGiayMoiDanhGia } from 'app/entities/giay-moi-danh-gia/giay-moi-danh-gia.model';
import { GiayMoiDanhGiaService } from 'app/entities/giay-moi-danh-gia/service/giay-moi-danh-gia.service';

@Component({
  selector: 'jhi-benh-nhan-update',
  templateUrl: './benh-nhan-update.component.html',
})
export class BenhNhanUpdateComponent implements OnInit {
  isSaving = false;

  baoHiemsCollection: IBaoHiem[] = [];
  dieuTrisCollection: IDieuTri[] = [];
  tTSanglocVaDanhGiaDDSCollection: ITTSanglocVaDanhGiaDD[] = [];
  hoaDonsCollection: IHoaDon[] = [];
  danhGiaCanThiepDDSCollection: IDanhGiaCanThiepDD[] = [];
  phieuSuatAnsCollection: IPhieuSuatAn[] = [];
  giayMoiDanhGiasCollection: IGiayMoiDanhGia[] = [];

  editForm = this.fb.group({
    id: [],
    hoTen: [],
    maBN: [],
    ngaySinh: [],
    gioiTinh: [],
    diaChi: [],
    ngheNghiep: [],
    chieuCao: [],
    canHT: [],
    canTC: [],
    vongCT: [],
    bMI: [],
    maTheBHYT: [],
    sDT: [],
    baoHiem: [],
    dieuTri: [],
    tTSanglocVaDanhGiaDD: [],
    hoaDon: [],
    danhGiaCanThiepDD: [],
    phieuSuatAn: [],
    giayMoiDanhGia: [],
  });

  constructor(
    protected benhNhanService: BenhNhanService,
    protected baoHiemService: BaoHiemService,
    protected dieuTriService: DieuTriService,
    protected tTSanglocVaDanhGiaDDService: TTSanglocVaDanhGiaDDService,
    protected hoaDonService: HoaDonService,
    protected danhGiaCanThiepDDService: DanhGiaCanThiepDDService,
    protected phieuSuatAnService: PhieuSuatAnService,
    protected giayMoiDanhGiaService: GiayMoiDanhGiaService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ benhNhan }) => {
      if (benhNhan.id === undefined) {
        const today = dayjs().startOf('day');
        benhNhan.ngaySinh = today;
      }

      this.updateForm(benhNhan);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const benhNhan = this.createFromForm();
    if (benhNhan.id !== undefined) {
      this.subscribeToSaveResponse(this.benhNhanService.update(benhNhan));
    } else {
      this.subscribeToSaveResponse(this.benhNhanService.create(benhNhan));
    }
  }

  trackBaoHiemById(_index: number, item: IBaoHiem): number {
    return item.id!;
  }

  trackDieuTriById(_index: number, item: IDieuTri): number {
    return item.id!;
  }

  trackTTSanglocVaDanhGiaDDById(_index: number, item: ITTSanglocVaDanhGiaDD): number {
    return item.id!;
  }

  trackHoaDonById(_index: number, item: IHoaDon): number {
    return item.id!;
  }

  trackDanhGiaCanThiepDDById(_index: number, item: IDanhGiaCanThiepDD): number {
    return item.id!;
  }

  trackPhieuSuatAnById(_index: number, item: IPhieuSuatAn): number {
    return item.id!;
  }

  trackGiayMoiDanhGiaById(_index: number, item: IGiayMoiDanhGia): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBenhNhan>>): void {
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

  protected updateForm(benhNhan: IBenhNhan): void {
    this.editForm.patchValue({
      id: benhNhan.id,
      hoTen: benhNhan.hoTen,
      maBN: benhNhan.maBN,
      ngaySinh: benhNhan.ngaySinh ? benhNhan.ngaySinh.format(DATE_TIME_FORMAT) : null,
      gioiTinh: benhNhan.gioiTinh,
      diaChi: benhNhan.diaChi,
      ngheNghiep: benhNhan.ngheNghiep,
      chieuCao: benhNhan.chieuCao,
      canHT: benhNhan.canHT,
      canTC: benhNhan.canTC,
      vongCT: benhNhan.vongCT,
      bMI: benhNhan.bMI,
      maTheBHYT: benhNhan.maTheBHYT,
      sDT: benhNhan.sDT,
      baoHiem: benhNhan.baoHiem,
      dieuTri: benhNhan.dieuTri,
      tTSanglocVaDanhGiaDD: benhNhan.tTSanglocVaDanhGiaDD,
      hoaDon: benhNhan.hoaDon,
      danhGiaCanThiepDD: benhNhan.danhGiaCanThiepDD,
      phieuSuatAn: benhNhan.phieuSuatAn,
      giayMoiDanhGia: benhNhan.giayMoiDanhGia,
    });

    this.baoHiemsCollection = this.baoHiemService.addBaoHiemToCollectionIfMissing(this.baoHiemsCollection, benhNhan.baoHiem);
    this.dieuTrisCollection = this.dieuTriService.addDieuTriToCollectionIfMissing(this.dieuTrisCollection, benhNhan.dieuTri);
    this.tTSanglocVaDanhGiaDDSCollection = this.tTSanglocVaDanhGiaDDService.addTTSanglocVaDanhGiaDDToCollectionIfMissing(
      this.tTSanglocVaDanhGiaDDSCollection,
      benhNhan.tTSanglocVaDanhGiaDD
    );
    this.hoaDonsCollection = this.hoaDonService.addHoaDonToCollectionIfMissing(this.hoaDonsCollection, benhNhan.hoaDon);
    this.danhGiaCanThiepDDSCollection = this.danhGiaCanThiepDDService.addDanhGiaCanThiepDDToCollectionIfMissing(
      this.danhGiaCanThiepDDSCollection,
      benhNhan.danhGiaCanThiepDD
    );
    this.phieuSuatAnsCollection = this.phieuSuatAnService.addPhieuSuatAnToCollectionIfMissing(
      this.phieuSuatAnsCollection,
      benhNhan.phieuSuatAn
    );
    this.giayMoiDanhGiasCollection = this.giayMoiDanhGiaService.addGiayMoiDanhGiaToCollectionIfMissing(
      this.giayMoiDanhGiasCollection,
      benhNhan.giayMoiDanhGia
    );
  }

  protected loadRelationshipsOptions(): void {
    this.baoHiemService
      .query({ filter: 'benhnhan-is-null' })
      .pipe(map((res: HttpResponse<IBaoHiem[]>) => res.body ?? []))
      .pipe(
        map((baoHiems: IBaoHiem[]) => this.baoHiemService.addBaoHiemToCollectionIfMissing(baoHiems, this.editForm.get('baoHiem')!.value))
      )
      .subscribe((baoHiems: IBaoHiem[]) => (this.baoHiemsCollection = baoHiems));

    this.dieuTriService
      .query({ filter: 'benhnhan-is-null' })
      .pipe(map((res: HttpResponse<IDieuTri[]>) => res.body ?? []))
      .pipe(
        map((dieuTris: IDieuTri[]) => this.dieuTriService.addDieuTriToCollectionIfMissing(dieuTris, this.editForm.get('dieuTri')!.value))
      )
      .subscribe((dieuTris: IDieuTri[]) => (this.dieuTrisCollection = dieuTris));

    this.tTSanglocVaDanhGiaDDService
      .query({ filter: 'benhnhan-is-null' })
      .pipe(map((res: HttpResponse<ITTSanglocVaDanhGiaDD[]>) => res.body ?? []))
      .pipe(
        map((tTSanglocVaDanhGiaDDS: ITTSanglocVaDanhGiaDD[]) =>
          this.tTSanglocVaDanhGiaDDService.addTTSanglocVaDanhGiaDDToCollectionIfMissing(
            tTSanglocVaDanhGiaDDS,
            this.editForm.get('tTSanglocVaDanhGiaDD')!.value
          )
        )
      )
      .subscribe((tTSanglocVaDanhGiaDDS: ITTSanglocVaDanhGiaDD[]) => (this.tTSanglocVaDanhGiaDDSCollection = tTSanglocVaDanhGiaDDS));

    this.hoaDonService
      .query({ filter: 'benhnhan-is-null' })
      .pipe(map((res: HttpResponse<IHoaDon[]>) => res.body ?? []))
      .pipe(map((hoaDons: IHoaDon[]) => this.hoaDonService.addHoaDonToCollectionIfMissing(hoaDons, this.editForm.get('hoaDon')!.value)))
      .subscribe((hoaDons: IHoaDon[]) => (this.hoaDonsCollection = hoaDons));

    this.danhGiaCanThiepDDService
      .query({ filter: 'benhnhan-is-null' })
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

    this.phieuSuatAnService
      .query({ filter: 'benhnhan-is-null' })
      .pipe(map((res: HttpResponse<IPhieuSuatAn[]>) => res.body ?? []))
      .pipe(
        map((phieuSuatAns: IPhieuSuatAn[]) =>
          this.phieuSuatAnService.addPhieuSuatAnToCollectionIfMissing(phieuSuatAns, this.editForm.get('phieuSuatAn')!.value)
        )
      )
      .subscribe((phieuSuatAns: IPhieuSuatAn[]) => (this.phieuSuatAnsCollection = phieuSuatAns));

    this.giayMoiDanhGiaService
      .query({ filter: 'benhnhan-is-null' })
      .pipe(map((res: HttpResponse<IGiayMoiDanhGia[]>) => res.body ?? []))
      .pipe(
        map((giayMoiDanhGias: IGiayMoiDanhGia[]) =>
          this.giayMoiDanhGiaService.addGiayMoiDanhGiaToCollectionIfMissing(giayMoiDanhGias, this.editForm.get('giayMoiDanhGia')!.value)
        )
      )
      .subscribe((giayMoiDanhGias: IGiayMoiDanhGia[]) => (this.giayMoiDanhGiasCollection = giayMoiDanhGias));
  }

  protected createFromForm(): IBenhNhan {
    return {
      ...new BenhNhan(),
      id: this.editForm.get(['id'])!.value,
      hoTen: this.editForm.get(['hoTen'])!.value,
      maBN: this.editForm.get(['maBN'])!.value,
      ngaySinh: this.editForm.get(['ngaySinh'])!.value ? dayjs(this.editForm.get(['ngaySinh'])!.value, DATE_TIME_FORMAT) : undefined,
      gioiTinh: this.editForm.get(['gioiTinh'])!.value,
      diaChi: this.editForm.get(['diaChi'])!.value,
      ngheNghiep: this.editForm.get(['ngheNghiep'])!.value,
      chieuCao: this.editForm.get(['chieuCao'])!.value,
      canHT: this.editForm.get(['canHT'])!.value,
      canTC: this.editForm.get(['canTC'])!.value,
      vongCT: this.editForm.get(['vongCT'])!.value,
      bMI: this.editForm.get(['bMI'])!.value,
      maTheBHYT: this.editForm.get(['maTheBHYT'])!.value,
      sDT: this.editForm.get(['sDT'])!.value,
      baoHiem: this.editForm.get(['baoHiem'])!.value,
      dieuTri: this.editForm.get(['dieuTri'])!.value,
      tTSanglocVaDanhGiaDD: this.editForm.get(['tTSanglocVaDanhGiaDD'])!.value,
      hoaDon: this.editForm.get(['hoaDon'])!.value,
      danhGiaCanThiepDD: this.editForm.get(['danhGiaCanThiepDD'])!.value,
      phieuSuatAn: this.editForm.get(['phieuSuatAn'])!.value,
      giayMoiDanhGia: this.editForm.get(['giayMoiDanhGia'])!.value,
    };
  }
}
