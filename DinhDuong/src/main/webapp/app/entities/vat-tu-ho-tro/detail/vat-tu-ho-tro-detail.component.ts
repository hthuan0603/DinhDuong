import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVatTuHoTro } from '../vat-tu-ho-tro.model';

@Component({
  selector: 'jhi-vat-tu-ho-tro-detail',
  templateUrl: './vat-tu-ho-tro-detail.component.html',
})
export class VatTuHoTroDetailComponent implements OnInit {
  vatTuHoTro: IVatTuHoTro | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ vatTuHoTro }) => {
      this.vatTuHoTro = vatTuHoTro;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
