import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITTSanglocVaDanhGiaDD } from '../tt-sangloc-va-danh-gia-dd.model';

@Component({
  selector: 'jhi-tt-sangloc-va-danh-gia-dd-detail',
  templateUrl: './tt-sangloc-va-danh-gia-dd-detail.component.html',
})
export class TTSanglocVaDanhGiaDDDetailComponent implements OnInit {
  tTSanglocVaDanhGiaDD: ITTSanglocVaDanhGiaDD | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tTSanglocVaDanhGiaDD }) => {
      this.tTSanglocVaDanhGiaDD = tTSanglocVaDanhGiaDD;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
