import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPhieuSuatAn } from '../phieu-suat-an.model';

@Component({
  selector: 'jhi-phieu-suat-an-detail',
  templateUrl: './phieu-suat-an-detail.component.html',
})
export class PhieuSuatAnDetailComponent implements OnInit {
  phieuSuatAn: IPhieuSuatAn | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ phieuSuatAn }) => {
      this.phieuSuatAn = phieuSuatAn;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
