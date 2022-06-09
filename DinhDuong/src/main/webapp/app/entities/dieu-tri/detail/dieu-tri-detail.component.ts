import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDieuTri } from '../dieu-tri.model';

@Component({
  selector: 'jhi-dieu-tri-detail',
  templateUrl: './dieu-tri-detail.component.html',
})
export class DieuTriDetailComponent implements OnInit {
  dieuTri: IDieuTri | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dieuTri }) => {
      this.dieuTri = dieuTri;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
