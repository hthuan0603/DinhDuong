import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILyDoCongTac } from '../ly-do-cong-tac.model';

@Component({
  selector: 'jhi-ly-do-cong-tac-detail',
  templateUrl: './ly-do-cong-tac-detail.component.html',
})
export class LyDoCongTacDetailComponent implements OnInit {
  lyDoCongTac: ILyDoCongTac | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ lyDoCongTac }) => {
      this.lyDoCongTac = lyDoCongTac;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
