import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IChiDaoTuyen } from '../chi-dao-tuyen.model';

@Component({
  selector: 'jhi-chi-dao-tuyen-detail',
  templateUrl: './chi-dao-tuyen-detail.component.html',
})
export class ChiDaoTuyenDetailComponent implements OnInit {
  chiDaoTuyen: IChiDaoTuyen | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ chiDaoTuyen }) => {
      this.chiDaoTuyen = chiDaoTuyen;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
