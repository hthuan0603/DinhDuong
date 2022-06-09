import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBenhNhan } from '../benh-nhan.model';

@Component({
  selector: 'jhi-benh-nhan-detail',
  templateUrl: './benh-nhan-detail.component.html',
})
export class BenhNhanDetailComponent implements OnInit {
  benhNhan: IBenhNhan | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ benhNhan }) => {
      this.benhNhan = benhNhan;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
