import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IKetQuaCongTac } from '../ket-qua-cong-tac.model';

@Component({
  selector: 'jhi-ket-qua-cong-tac-detail',
  templateUrl: './ket-qua-cong-tac-detail.component.html',
})
export class KetQuaCongTacDetailComponent implements OnInit {
  ketQuaCongTac: IKetQuaCongTac | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ketQuaCongTac }) => {
      this.ketQuaCongTac = ketQuaCongTac;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
