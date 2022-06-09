import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IToaThuoc } from '../toa-thuoc.model';

@Component({
  selector: 'jhi-toa-thuoc-detail',
  templateUrl: './toa-thuoc-detail.component.html',
})
export class ToaThuocDetailComponent implements OnInit {
  toaThuoc: IToaThuoc | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ toaThuoc }) => {
      this.toaThuoc = toaThuoc;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
