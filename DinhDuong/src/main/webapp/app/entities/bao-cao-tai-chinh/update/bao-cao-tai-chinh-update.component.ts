import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IBaoCaoTaiChinh, BaoCaoTaiChinh } from '../bao-cao-tai-chinh.model';
import { BaoCaoTaiChinhService } from '../service/bao-cao-tai-chinh.service';
import { IChiDaoTuyen } from 'app/entities/chi-dao-tuyen/chi-dao-tuyen.model';
import { ChiDaoTuyenService } from 'app/entities/chi-dao-tuyen/service/chi-dao-tuyen.service';

@Component({
  selector: 'jhi-bao-cao-tai-chinh-update',
  templateUrl: './bao-cao-tai-chinh-update.component.html',
})
export class BaoCaoTaiChinhUpdateComponent implements OnInit {
  isSaving = false;

  chiDaoTuyensCollection: IChiDaoTuyen[] = [];

  editForm = this.fb.group({
    id: [],
    maBaoCao: [],
    luuTru: [],
    tienAn: [],
    tienO: [],
    tienDiLai: [],
    taiLieu: [],
    giangDay: [],
    khac: [],
    chiDaoTuyen: [],
  });

  constructor(
    protected baoCaoTaiChinhService: BaoCaoTaiChinhService,
    protected chiDaoTuyenService: ChiDaoTuyenService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ baoCaoTaiChinh }) => {
      this.updateForm(baoCaoTaiChinh);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const baoCaoTaiChinh = this.createFromForm();
    if (baoCaoTaiChinh.id !== undefined) {
      this.subscribeToSaveResponse(this.baoCaoTaiChinhService.update(baoCaoTaiChinh));
    } else {
      this.subscribeToSaveResponse(this.baoCaoTaiChinhService.create(baoCaoTaiChinh));
    }
  }

  trackChiDaoTuyenById(_index: number, item: IChiDaoTuyen): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBaoCaoTaiChinh>>): void {
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

  protected updateForm(baoCaoTaiChinh: IBaoCaoTaiChinh): void {
    this.editForm.patchValue({
      id: baoCaoTaiChinh.id,
      maBaoCao: baoCaoTaiChinh.maBaoCao,
      luuTru: baoCaoTaiChinh.luuTru,
      tienAn: baoCaoTaiChinh.tienAn,
      tienO: baoCaoTaiChinh.tienO,
      tienDiLai: baoCaoTaiChinh.tienDiLai,
      taiLieu: baoCaoTaiChinh.taiLieu,
      giangDay: baoCaoTaiChinh.giangDay,
      khac: baoCaoTaiChinh.khac,
      chiDaoTuyen: baoCaoTaiChinh.chiDaoTuyen,
    });

    this.chiDaoTuyensCollection = this.chiDaoTuyenService.addChiDaoTuyenToCollectionIfMissing(
      this.chiDaoTuyensCollection,
      baoCaoTaiChinh.chiDaoTuyen
    );
  }

  protected loadRelationshipsOptions(): void {
    this.chiDaoTuyenService
      .query({ filter: 'baocaotaichinh-is-null' })
      .pipe(map((res: HttpResponse<IChiDaoTuyen[]>) => res.body ?? []))
      .pipe(
        map((chiDaoTuyens: IChiDaoTuyen[]) =>
          this.chiDaoTuyenService.addChiDaoTuyenToCollectionIfMissing(chiDaoTuyens, this.editForm.get('chiDaoTuyen')!.value)
        )
      )
      .subscribe((chiDaoTuyens: IChiDaoTuyen[]) => (this.chiDaoTuyensCollection = chiDaoTuyens));
  }

  protected createFromForm(): IBaoCaoTaiChinh {
    return {
      ...new BaoCaoTaiChinh(),
      id: this.editForm.get(['id'])!.value,
      maBaoCao: this.editForm.get(['maBaoCao'])!.value,
      luuTru: this.editForm.get(['luuTru'])!.value,
      tienAn: this.editForm.get(['tienAn'])!.value,
      tienO: this.editForm.get(['tienO'])!.value,
      tienDiLai: this.editForm.get(['tienDiLai'])!.value,
      taiLieu: this.editForm.get(['taiLieu'])!.value,
      giangDay: this.editForm.get(['giangDay'])!.value,
      khac: this.editForm.get(['khac'])!.value,
      chiDaoTuyen: this.editForm.get(['chiDaoTuyen'])!.value,
    };
  }
}
