import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBaoCaoTaiChinh } from '../bao-cao-tai-chinh.model';

@Component({
  selector: 'jhi-bao-cao-tai-chinh-detail',
  templateUrl: './bao-cao-tai-chinh-detail.component.html',
})
export class BaoCaoTaiChinhDetailComponent implements OnInit {
  baoCaoTaiChinh: IBaoCaoTaiChinh | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ baoCaoTaiChinh }) => {
      this.baoCaoTaiChinh = baoCaoTaiChinh;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
