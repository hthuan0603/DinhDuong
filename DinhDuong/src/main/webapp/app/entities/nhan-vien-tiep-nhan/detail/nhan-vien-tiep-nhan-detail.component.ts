import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INhanVienTiepNhan } from '../nhan-vien-tiep-nhan.model';

@Component({
  selector: 'jhi-nhan-vien-tiep-nhan-detail',
  templateUrl: './nhan-vien-tiep-nhan-detail.component.html',
})
export class NhanVienTiepNhanDetailComponent implements OnInit {
  nhanVienTiepNhan: INhanVienTiepNhan | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ nhanVienTiepNhan }) => {
      this.nhanVienTiepNhan = nhanVienTiepNhan;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
