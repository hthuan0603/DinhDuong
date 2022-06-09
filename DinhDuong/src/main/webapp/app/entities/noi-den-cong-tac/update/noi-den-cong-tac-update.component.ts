import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { INoiDenCongTac, NoiDenCongTac } from '../noi-den-cong-tac.model';
import { NoiDenCongTacService } from '../service/noi-den-cong-tac.service';
import { IChiDaoTuyen } from 'app/entities/chi-dao-tuyen/chi-dao-tuyen.model';
import { ChiDaoTuyenService } from 'app/entities/chi-dao-tuyen/service/chi-dao-tuyen.service';

@Component({
  selector: 'jhi-noi-den-cong-tac-update',
  templateUrl: './noi-den-cong-tac-update.component.html',
})
export class NoiDenCongTacUpdateComponent implements OnInit {
  isSaving = false;

  chiDaoTuyensCollection: IChiDaoTuyen[] = [];

  editForm = this.fb.group({
    id: [],
    maNoiDen: [],
    tenNoiDen: [],
    thuTuSX: [],
    chiDaoTuyen: [],
  });

  constructor(
    protected noiDenCongTacService: NoiDenCongTacService,
    protected chiDaoTuyenService: ChiDaoTuyenService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ noiDenCongTac }) => {
      this.updateForm(noiDenCongTac);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const noiDenCongTac = this.createFromForm();
    if (noiDenCongTac.id !== undefined) {
      this.subscribeToSaveResponse(this.noiDenCongTacService.update(noiDenCongTac));
    } else {
      this.subscribeToSaveResponse(this.noiDenCongTacService.create(noiDenCongTac));
    }
  }

  trackChiDaoTuyenById(_index: number, item: IChiDaoTuyen): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INoiDenCongTac>>): void {
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

  protected updateForm(noiDenCongTac: INoiDenCongTac): void {
    this.editForm.patchValue({
      id: noiDenCongTac.id,
      maNoiDen: noiDenCongTac.maNoiDen,
      tenNoiDen: noiDenCongTac.tenNoiDen,
      thuTuSX: noiDenCongTac.thuTuSX,
      chiDaoTuyen: noiDenCongTac.chiDaoTuyen,
    });

    this.chiDaoTuyensCollection = this.chiDaoTuyenService.addChiDaoTuyenToCollectionIfMissing(
      this.chiDaoTuyensCollection,
      noiDenCongTac.chiDaoTuyen
    );
  }

  protected loadRelationshipsOptions(): void {
    this.chiDaoTuyenService
      .query({ filter: 'noidencongtac-is-null' })
      .pipe(map((res: HttpResponse<IChiDaoTuyen[]>) => res.body ?? []))
      .pipe(
        map((chiDaoTuyens: IChiDaoTuyen[]) =>
          this.chiDaoTuyenService.addChiDaoTuyenToCollectionIfMissing(chiDaoTuyens, this.editForm.get('chiDaoTuyen')!.value)
        )
      )
      .subscribe((chiDaoTuyens: IChiDaoTuyen[]) => (this.chiDaoTuyensCollection = chiDaoTuyens));
  }

  protected createFromForm(): INoiDenCongTac {
    return {
      ...new NoiDenCongTac(),
      id: this.editForm.get(['id'])!.value,
      maNoiDen: this.editForm.get(['maNoiDen'])!.value,
      tenNoiDen: this.editForm.get(['tenNoiDen'])!.value,
      thuTuSX: this.editForm.get(['thuTuSX'])!.value,
      chiDaoTuyen: this.editForm.get(['chiDaoTuyen'])!.value,
    };
  }
}
