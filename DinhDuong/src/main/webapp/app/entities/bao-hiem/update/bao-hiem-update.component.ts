import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IBaoHiem, BaoHiem } from '../bao-hiem.model';
import { BaoHiemService } from '../service/bao-hiem.service';
import { IHoaDon } from 'app/entities/hoa-don/hoa-don.model';
import { HoaDonService } from 'app/entities/hoa-don/service/hoa-don.service';

@Component({
  selector: 'jhi-bao-hiem-update',
  templateUrl: './bao-hiem-update.component.html',
})
export class BaoHiemUpdateComponent implements OnInit {
  isSaving = false;

  hoaDonsCollection: IHoaDon[] = [];

  editForm = this.fb.group({
    id: [],
    maTheBHYT: [],
    doiTuong: [],
    baoHiemThanhToan: [],
    hoaDon: [],
  });

  constructor(
    protected baoHiemService: BaoHiemService,
    protected hoaDonService: HoaDonService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ baoHiem }) => {
      this.updateForm(baoHiem);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const baoHiem = this.createFromForm();
    if (baoHiem.id !== undefined) {
      this.subscribeToSaveResponse(this.baoHiemService.update(baoHiem));
    } else {
      this.subscribeToSaveResponse(this.baoHiemService.create(baoHiem));
    }
  }

  trackHoaDonById(_index: number, item: IHoaDon): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBaoHiem>>): void {
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

  protected updateForm(baoHiem: IBaoHiem): void {
    this.editForm.patchValue({
      id: baoHiem.id,
      maTheBHYT: baoHiem.maTheBHYT,
      doiTuong: baoHiem.doiTuong,
      baoHiemThanhToan: baoHiem.baoHiemThanhToan,
      hoaDon: baoHiem.hoaDon,
    });

    this.hoaDonsCollection = this.hoaDonService.addHoaDonToCollectionIfMissing(this.hoaDonsCollection, baoHiem.hoaDon);
  }

  protected loadRelationshipsOptions(): void {
    this.hoaDonService
      .query({ filter: 'baohiem-is-null' })
      .pipe(map((res: HttpResponse<IHoaDon[]>) => res.body ?? []))
      .pipe(map((hoaDons: IHoaDon[]) => this.hoaDonService.addHoaDonToCollectionIfMissing(hoaDons, this.editForm.get('hoaDon')!.value)))
      .subscribe((hoaDons: IHoaDon[]) => (this.hoaDonsCollection = hoaDons));
  }

  protected createFromForm(): IBaoHiem {
    return {
      ...new BaoHiem(),
      id: this.editForm.get(['id'])!.value,
      maTheBHYT: this.editForm.get(['maTheBHYT'])!.value,
      doiTuong: this.editForm.get(['doiTuong'])!.value,
      baoHiemThanhToan: this.editForm.get(['baoHiemThanhToan'])!.value,
      hoaDon: this.editForm.get(['hoaDon'])!.value,
    };
  }
}
