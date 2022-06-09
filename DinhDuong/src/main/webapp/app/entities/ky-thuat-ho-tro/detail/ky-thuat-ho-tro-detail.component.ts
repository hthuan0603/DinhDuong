import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IKyThuatHoTro } from '../ky-thuat-ho-tro.model';

@Component({
  selector: 'jhi-ky-thuat-ho-tro-detail',
  templateUrl: './ky-thuat-ho-tro-detail.component.html',
})
export class KyThuatHoTroDetailComponent implements OnInit {
  kyThuatHoTro: IKyThuatHoTro | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ kyThuatHoTro }) => {
      this.kyThuatHoTro = kyThuatHoTro;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
