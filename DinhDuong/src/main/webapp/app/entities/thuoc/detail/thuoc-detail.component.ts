import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IThuoc } from '../thuoc.model';

@Component({
  selector: 'jhi-thuoc-detail',
  templateUrl: './thuoc-detail.component.html',
})
export class ThuocDetailComponent implements OnInit {
  thuoc: IThuoc | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ thuoc }) => {
      this.thuoc = thuoc;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
