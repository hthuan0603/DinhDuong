import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHoTro } from '../ho-tro.model';

@Component({
  selector: 'jhi-ho-tro-detail',
  templateUrl: './ho-tro-detail.component.html',
})
export class HoTroDetailComponent implements OnInit {
  hoTro: IHoTro | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ hoTro }) => {
      this.hoTro = hoTro;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
