import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IKyThuatHoTro, KyThuatHoTro } from '../ky-thuat-ho-tro.model';
import { KyThuatHoTroService } from '../service/ky-thuat-ho-tro.service';
import { IHoTro } from 'app/entities/ho-tro/ho-tro.model';
import { HoTroService } from 'app/entities/ho-tro/service/ho-tro.service';

@Component({
  selector: 'jhi-ky-thuat-ho-tro-update',
  templateUrl: './ky-thuat-ho-tro-update.component.html',
})
export class KyThuatHoTroUpdateComponent implements OnInit {
  isSaving = false;

  hoTrosCollection: IHoTro[] = [];

  editForm = this.fb.group({
    id: [],
    maKyThuat: [],
    tenKyThuat: [],
    thuTuSX: [],
    hoTro: [],
  });

  constructor(
    protected kyThuatHoTroService: KyThuatHoTroService,
    protected hoTroService: HoTroService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ kyThuatHoTro }) => {
      this.updateForm(kyThuatHoTro);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const kyThuatHoTro = this.createFromForm();
    if (kyThuatHoTro.id !== undefined) {
      this.subscribeToSaveResponse(this.kyThuatHoTroService.update(kyThuatHoTro));
    } else {
      this.subscribeToSaveResponse(this.kyThuatHoTroService.create(kyThuatHoTro));
    }
  }

  trackHoTroById(_index: number, item: IHoTro): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IKyThuatHoTro>>): void {
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

  protected updateForm(kyThuatHoTro: IKyThuatHoTro): void {
    this.editForm.patchValue({
      id: kyThuatHoTro.id,
      maKyThuat: kyThuatHoTro.maKyThuat,
      tenKyThuat: kyThuatHoTro.tenKyThuat,
      thuTuSX: kyThuatHoTro.thuTuSX,
      hoTro: kyThuatHoTro.hoTro,
    });

    this.hoTrosCollection = this.hoTroService.addHoTroToCollectionIfMissing(this.hoTrosCollection, kyThuatHoTro.hoTro);
  }

  protected loadRelationshipsOptions(): void {
    this.hoTroService
      .query({ filter: 'kythuathotro-is-null' })
      .pipe(map((res: HttpResponse<IHoTro[]>) => res.body ?? []))
      .pipe(map((hoTros: IHoTro[]) => this.hoTroService.addHoTroToCollectionIfMissing(hoTros, this.editForm.get('hoTro')!.value)))
      .subscribe((hoTros: IHoTro[]) => (this.hoTrosCollection = hoTros));
  }

  protected createFromForm(): IKyThuatHoTro {
    return {
      ...new KyThuatHoTro(),
      id: this.editForm.get(['id'])!.value,
      maKyThuat: this.editForm.get(['maKyThuat'])!.value,
      tenKyThuat: this.editForm.get(['tenKyThuat'])!.value,
      thuTuSX: this.editForm.get(['thuTuSX'])!.value,
      hoTro: this.editForm.get(['hoTro'])!.value,
    };
  }
}
