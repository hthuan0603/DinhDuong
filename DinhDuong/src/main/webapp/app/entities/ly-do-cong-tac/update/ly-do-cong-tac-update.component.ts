import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ILyDoCongTac, LyDoCongTac } from '../ly-do-cong-tac.model';
import { LyDoCongTacService } from '../service/ly-do-cong-tac.service';

@Component({
  selector: 'jhi-ly-do-cong-tac-update',
  templateUrl: './ly-do-cong-tac-update.component.html',
})
export class LyDoCongTacUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    maLyDo: [],
    tenLyDo: [],
    thuTuSX: [],
  });

  constructor(protected lyDoCongTacService: LyDoCongTacService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ lyDoCongTac }) => {
      this.updateForm(lyDoCongTac);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const lyDoCongTac = this.createFromForm();
    if (lyDoCongTac.id !== undefined) {
      this.subscribeToSaveResponse(this.lyDoCongTacService.update(lyDoCongTac));
    } else {
      this.subscribeToSaveResponse(this.lyDoCongTacService.create(lyDoCongTac));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILyDoCongTac>>): void {
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

  protected updateForm(lyDoCongTac: ILyDoCongTac): void {
    this.editForm.patchValue({
      id: lyDoCongTac.id,
      maLyDo: lyDoCongTac.maLyDo,
      tenLyDo: lyDoCongTac.tenLyDo,
      thuTuSX: lyDoCongTac.thuTuSX,
    });
  }

  protected createFromForm(): ILyDoCongTac {
    return {
      ...new LyDoCongTac(),
      id: this.editForm.get(['id'])!.value,
      maLyDo: this.editForm.get(['maLyDo'])!.value,
      tenLyDo: this.editForm.get(['tenLyDo'])!.value,
      thuTuSX: this.editForm.get(['thuTuSX'])!.value,
    };
  }
}
