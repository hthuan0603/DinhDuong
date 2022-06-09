import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IDieuTri, DieuTri } from '../dieu-tri.model';
import { DieuTriService } from '../service/dieu-tri.service';
import { IHoaDon } from 'app/entities/hoa-don/hoa-don.model';
import { HoaDonService } from 'app/entities/hoa-don/service/hoa-don.service';

@Component({
  selector: 'jhi-dieu-tri-update',
  templateUrl: './dieu-tri-update.component.html',
})
export class DieuTriUpdateComponent implements OnInit {
  isSaving = false;

  hoaDonsCollection: IHoaDon[] = [];

  editForm = this.fb.group({
    id: [],
    maDieuTri: [],
    hoTen: [],
    maBenhAn: [],
    tenBSDieuTri: [],
    nguoiNha: [],
    giuong: [],
    maTheBHYT: [],
    ngayVaoKhoa: [],
    ngayRaVien: [],
    bVChuyen: [],
    ketQuaDieuTri: [],
    lichSuChuyenKhoa: [],
    hoaDon: [],
  });

  constructor(
    protected dieuTriService: DieuTriService,
    protected hoaDonService: HoaDonService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dieuTri }) => {
      if (dieuTri.id === undefined) {
        const today = dayjs().startOf('day');
        dieuTri.ngayVaoKhoa = today;
        dieuTri.ngayRaVien = today;
      }

      this.updateForm(dieuTri);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const dieuTri = this.createFromForm();
    if (dieuTri.id !== undefined) {
      this.subscribeToSaveResponse(this.dieuTriService.update(dieuTri));
    } else {
      this.subscribeToSaveResponse(this.dieuTriService.create(dieuTri));
    }
  }

  trackHoaDonById(_index: number, item: IHoaDon): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDieuTri>>): void {
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

  protected updateForm(dieuTri: IDieuTri): void {
    this.editForm.patchValue({
      id: dieuTri.id,
      maDieuTri: dieuTri.maDieuTri,
      hoTen: dieuTri.hoTen,
      maBenhAn: dieuTri.maBenhAn,
      tenBSDieuTri: dieuTri.tenBSDieuTri,
      nguoiNha: dieuTri.nguoiNha,
      giuong: dieuTri.giuong,
      maTheBHYT: dieuTri.maTheBHYT,
      ngayVaoKhoa: dieuTri.ngayVaoKhoa ? dieuTri.ngayVaoKhoa.format(DATE_TIME_FORMAT) : null,
      ngayRaVien: dieuTri.ngayRaVien ? dieuTri.ngayRaVien.format(DATE_TIME_FORMAT) : null,
      bVChuyen: dieuTri.bVChuyen,
      ketQuaDieuTri: dieuTri.ketQuaDieuTri,
      lichSuChuyenKhoa: dieuTri.lichSuChuyenKhoa,
      hoaDon: dieuTri.hoaDon,
    });

    this.hoaDonsCollection = this.hoaDonService.addHoaDonToCollectionIfMissing(this.hoaDonsCollection, dieuTri.hoaDon);
  }

  protected loadRelationshipsOptions(): void {
    this.hoaDonService
      .query({ filter: 'dieutri-is-null' })
      .pipe(map((res: HttpResponse<IHoaDon[]>) => res.body ?? []))
      .pipe(map((hoaDons: IHoaDon[]) => this.hoaDonService.addHoaDonToCollectionIfMissing(hoaDons, this.editForm.get('hoaDon')!.value)))
      .subscribe((hoaDons: IHoaDon[]) => (this.hoaDonsCollection = hoaDons));
  }

  protected createFromForm(): IDieuTri {
    return {
      ...new DieuTri(),
      id: this.editForm.get(['id'])!.value,
      maDieuTri: this.editForm.get(['maDieuTri'])!.value,
      hoTen: this.editForm.get(['hoTen'])!.value,
      maBenhAn: this.editForm.get(['maBenhAn'])!.value,
      tenBSDieuTri: this.editForm.get(['tenBSDieuTri'])!.value,
      nguoiNha: this.editForm.get(['nguoiNha'])!.value,
      giuong: this.editForm.get(['giuong'])!.value,
      maTheBHYT: this.editForm.get(['maTheBHYT'])!.value,
      ngayVaoKhoa: this.editForm.get(['ngayVaoKhoa'])!.value
        ? dayjs(this.editForm.get(['ngayVaoKhoa'])!.value, DATE_TIME_FORMAT)
        : undefined,
      ngayRaVien: this.editForm.get(['ngayRaVien'])!.value ? dayjs(this.editForm.get(['ngayRaVien'])!.value, DATE_TIME_FORMAT) : undefined,
      bVChuyen: this.editForm.get(['bVChuyen'])!.value,
      ketQuaDieuTri: this.editForm.get(['ketQuaDieuTri'])!.value,
      lichSuChuyenKhoa: this.editForm.get(['lichSuChuyenKhoa'])!.value,
      hoaDon: this.editForm.get(['hoaDon'])!.value,
    };
  }
}
