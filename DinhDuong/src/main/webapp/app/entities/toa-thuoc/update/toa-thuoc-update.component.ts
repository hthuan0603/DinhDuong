import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IToaThuoc, ToaThuoc } from '../toa-thuoc.model';
import { ToaThuocService } from '../service/toa-thuoc.service';
import { IThuoc } from 'app/entities/thuoc/thuoc.model';
import { ThuocService } from 'app/entities/thuoc/service/thuoc.service';
import { IHoaDon } from 'app/entities/hoa-don/hoa-don.model';
import { HoaDonService } from 'app/entities/hoa-don/service/hoa-don.service';
import { IBenhNhan } from 'app/entities/benh-nhan/benh-nhan.model';
import { BenhNhanService } from 'app/entities/benh-nhan/service/benh-nhan.service';

@Component({
  selector: 'jhi-toa-thuoc-update',
  templateUrl: './toa-thuoc-update.component.html',
})
export class ToaThuocUpdateComponent implements OnInit {
  isSaving = false;

  thuocsCollection: IThuoc[] = [];
  hoaDonsCollection: IHoaDon[] = [];
  benhNhansSharedCollection: IBenhNhan[] = [];

  editForm = this.fb.group({
    id: [],
    iCD10: [],
    maBA: [],
    maBN: [],
    loaiThuoc: [],
    doiTuong: [],
    tiLe: [],
    soNgayHenTaiKham: [],
    capPhieuHenKham: [],
    loiDanBacSi: [],
    ngayChiDinh: [],
    ngayDung: [],
    soNgaykeDon: [],
    khoThuoc: [],
    thuoc: [],
    hoaDon: [],
    benhNhan: [],
  });

  constructor(
    protected toaThuocService: ToaThuocService,
    protected thuocService: ThuocService,
    protected hoaDonService: HoaDonService,
    protected benhNhanService: BenhNhanService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ toaThuoc }) => {
      if (toaThuoc.id === undefined) {
        const today = dayjs().startOf('day');
        toaThuoc.ngayChiDinh = today;
        toaThuoc.ngayDung = today;
      }

      this.updateForm(toaThuoc);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const toaThuoc = this.createFromForm();
    if (toaThuoc.id !== undefined) {
      this.subscribeToSaveResponse(this.toaThuocService.update(toaThuoc));
    } else {
      this.subscribeToSaveResponse(this.toaThuocService.create(toaThuoc));
    }
  }

  trackThuocById(_index: number, item: IThuoc): number {
    return item.id!;
  }

  trackHoaDonById(_index: number, item: IHoaDon): number {
    return item.id!;
  }

  trackBenhNhanById(_index: number, item: IBenhNhan): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IToaThuoc>>): void {
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

  protected updateForm(toaThuoc: IToaThuoc): void {
    this.editForm.patchValue({
      id: toaThuoc.id,
      iCD10: toaThuoc.iCD10,
      maBA: toaThuoc.maBA,
      maBN: toaThuoc.maBN,
      loaiThuoc: toaThuoc.loaiThuoc,
      doiTuong: toaThuoc.doiTuong,
      tiLe: toaThuoc.tiLe,
      soNgayHenTaiKham: toaThuoc.soNgayHenTaiKham,
      capPhieuHenKham: toaThuoc.capPhieuHenKham,
      loiDanBacSi: toaThuoc.loiDanBacSi,
      ngayChiDinh: toaThuoc.ngayChiDinh ? toaThuoc.ngayChiDinh.format(DATE_TIME_FORMAT) : null,
      ngayDung: toaThuoc.ngayDung ? toaThuoc.ngayDung.format(DATE_TIME_FORMAT) : null,
      soNgaykeDon: toaThuoc.soNgaykeDon,
      khoThuoc: toaThuoc.khoThuoc,
      thuoc: toaThuoc.thuoc,
      hoaDon: toaThuoc.hoaDon,
      benhNhan: toaThuoc.benhNhan,
    });

    this.thuocsCollection = this.thuocService.addThuocToCollectionIfMissing(this.thuocsCollection, toaThuoc.thuoc);
    this.hoaDonsCollection = this.hoaDonService.addHoaDonToCollectionIfMissing(this.hoaDonsCollection, toaThuoc.hoaDon);
    this.benhNhansSharedCollection = this.benhNhanService.addBenhNhanToCollectionIfMissing(
      this.benhNhansSharedCollection,
      toaThuoc.benhNhan
    );
  }

  protected loadRelationshipsOptions(): void {
    this.thuocService
      .query({ filter: 'toathuoc-is-null' })
      .pipe(map((res: HttpResponse<IThuoc[]>) => res.body ?? []))
      .pipe(map((thuocs: IThuoc[]) => this.thuocService.addThuocToCollectionIfMissing(thuocs, this.editForm.get('thuoc')!.value)))
      .subscribe((thuocs: IThuoc[]) => (this.thuocsCollection = thuocs));

    this.hoaDonService
      .query({ filter: 'toathuoc-is-null' })
      .pipe(map((res: HttpResponse<IHoaDon[]>) => res.body ?? []))
      .pipe(map((hoaDons: IHoaDon[]) => this.hoaDonService.addHoaDonToCollectionIfMissing(hoaDons, this.editForm.get('hoaDon')!.value)))
      .subscribe((hoaDons: IHoaDon[]) => (this.hoaDonsCollection = hoaDons));

    this.benhNhanService
      .query()
      .pipe(map((res: HttpResponse<IBenhNhan[]>) => res.body ?? []))
      .pipe(
        map((benhNhans: IBenhNhan[]) =>
          this.benhNhanService.addBenhNhanToCollectionIfMissing(benhNhans, this.editForm.get('benhNhan')!.value)
        )
      )
      .subscribe((benhNhans: IBenhNhan[]) => (this.benhNhansSharedCollection = benhNhans));
  }

  protected createFromForm(): IToaThuoc {
    return {
      ...new ToaThuoc(),
      id: this.editForm.get(['id'])!.value,
      iCD10: this.editForm.get(['iCD10'])!.value,
      maBA: this.editForm.get(['maBA'])!.value,
      maBN: this.editForm.get(['maBN'])!.value,
      loaiThuoc: this.editForm.get(['loaiThuoc'])!.value,
      doiTuong: this.editForm.get(['doiTuong'])!.value,
      tiLe: this.editForm.get(['tiLe'])!.value,
      soNgayHenTaiKham: this.editForm.get(['soNgayHenTaiKham'])!.value,
      capPhieuHenKham: this.editForm.get(['capPhieuHenKham'])!.value,
      loiDanBacSi: this.editForm.get(['loiDanBacSi'])!.value,
      ngayChiDinh: this.editForm.get(['ngayChiDinh'])!.value
        ? dayjs(this.editForm.get(['ngayChiDinh'])!.value, DATE_TIME_FORMAT)
        : undefined,
      ngayDung: this.editForm.get(['ngayDung'])!.value ? dayjs(this.editForm.get(['ngayDung'])!.value, DATE_TIME_FORMAT) : undefined,
      soNgaykeDon: this.editForm.get(['soNgaykeDon'])!.value,
      khoThuoc: this.editForm.get(['khoThuoc'])!.value,
      thuoc: this.editForm.get(['thuoc'])!.value,
      hoaDon: this.editForm.get(['hoaDon'])!.value,
      benhNhan: this.editForm.get(['benhNhan'])!.value,
    };
  }
}
