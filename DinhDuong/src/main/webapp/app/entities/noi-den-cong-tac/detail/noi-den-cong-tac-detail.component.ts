import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INoiDenCongTac } from '../noi-den-cong-tac.model';

@Component({
  selector: 'jhi-noi-den-cong-tac-detail',
  templateUrl: './noi-den-cong-tac-detail.component.html',
})
export class NoiDenCongTacDetailComponent implements OnInit {
  noiDenCongTac: INoiDenCongTac | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ noiDenCongTac }) => {
      this.noiDenCongTac = noiDenCongTac;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
