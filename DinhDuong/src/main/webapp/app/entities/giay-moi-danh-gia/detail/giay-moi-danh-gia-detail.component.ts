import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGiayMoiDanhGia } from '../giay-moi-danh-gia.model';

@Component({
  selector: 'jhi-giay-moi-danh-gia-detail',
  templateUrl: './giay-moi-danh-gia-detail.component.html',
})
export class GiayMoiDanhGiaDetailComponent implements OnInit {
  giayMoiDanhGia: IGiayMoiDanhGia | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ giayMoiDanhGia }) => {
      this.giayMoiDanhGia = giayMoiDanhGia;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
