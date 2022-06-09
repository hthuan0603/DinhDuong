import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDanhGiaCanThiepDD } from '../danh-gia-can-thiep-dd.model';

@Component({
  selector: 'jhi-danh-gia-can-thiep-dd-detail',
  templateUrl: './danh-gia-can-thiep-dd-detail.component.html',
})
export class DanhGiaCanThiepDDDetailComponent implements OnInit {
  danhGiaCanThiepDD: IDanhGiaCanThiepDD | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ danhGiaCanThiepDD }) => {
      this.danhGiaCanThiepDD = danhGiaCanThiepDD;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
