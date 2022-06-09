import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IHoaDon, HoaDon } from '../hoa-don.model';
import { HoaDonService } from '../service/hoa-don.service';

@Component({
  selector: 'jhi-hoa-don-update',
  templateUrl: './hoa-don-update.component.html',
})
export class HoaDonUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    noiDung: [],
    maBN: [],
    maTheBHYT: [],
    maDV: [],
    tamUng: [],
    daNop: [],
    tong: [],
  });

  constructor(protected hoaDonService: HoaDonService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ hoaDon }) => {
      this.updateForm(hoaDon);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const hoaDon = this.createFromForm();
    if (hoaDon.id !== undefined) {
      this.subscribeToSaveResponse(this.hoaDonService.update(hoaDon));
    } else {
      this.subscribeToSaveResponse(this.hoaDonService.create(hoaDon));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHoaDon>>): void {
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

  protected updateForm(hoaDon: IHoaDon): void {
    this.editForm.patchValue({
      id: hoaDon.id,
      noiDung: hoaDon.noiDung,
      maBN: hoaDon.maBN,
      maTheBHYT: hoaDon.maTheBHYT,
      maDV: hoaDon.maDV,
      tamUng: hoaDon.tamUng,
      daNop: hoaDon.daNop,
      tong: hoaDon.tong,
    });
  }

  protected createFromForm(): IHoaDon {
    return {
      ...new HoaDon(),
      id: this.editForm.get(['id'])!.value,
      noiDung: this.editForm.get(['noiDung'])!.value,
      maBN: this.editForm.get(['maBN'])!.value,
      maTheBHYT: this.editForm.get(['maTheBHYT'])!.value,
      maDV: this.editForm.get(['maDV'])!.value,
      tamUng: this.editForm.get(['tamUng'])!.value,
      daNop: this.editForm.get(['daNop'])!.value,
      tong: this.editForm.get(['tong'])!.value,
    };
  }
}
