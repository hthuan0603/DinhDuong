import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBaoHiem } from '../bao-hiem.model';

@Component({
  selector: 'jhi-bao-hiem-detail',
  templateUrl: './bao-hiem-detail.component.html',
})
export class BaoHiemDetailComponent implements OnInit {
  baoHiem: IBaoHiem | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ baoHiem }) => {
      this.baoHiem = baoHiem;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
