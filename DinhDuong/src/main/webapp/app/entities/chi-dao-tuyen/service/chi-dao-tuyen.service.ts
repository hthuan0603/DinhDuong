import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IChiDaoTuyen, getChiDaoTuyenIdentifier } from '../chi-dao-tuyen.model';

export type EntityResponseType = HttpResponse<IChiDaoTuyen>;
export type EntityArrayResponseType = HttpResponse<IChiDaoTuyen[]>;

@Injectable({ providedIn: 'root' })
export class ChiDaoTuyenService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/chi-dao-tuyens');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(chiDaoTuyen: IChiDaoTuyen): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(chiDaoTuyen);
    return this.http
      .post<IChiDaoTuyen>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(chiDaoTuyen: IChiDaoTuyen): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(chiDaoTuyen);
    return this.http
      .put<IChiDaoTuyen>(`${this.resourceUrl}/${getChiDaoTuyenIdentifier(chiDaoTuyen) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(chiDaoTuyen: IChiDaoTuyen): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(chiDaoTuyen);
    return this.http
      .patch<IChiDaoTuyen>(`${this.resourceUrl}/${getChiDaoTuyenIdentifier(chiDaoTuyen) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IChiDaoTuyen>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IChiDaoTuyen[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addChiDaoTuyenToCollectionIfMissing(
    chiDaoTuyenCollection: IChiDaoTuyen[],
    ...chiDaoTuyensToCheck: (IChiDaoTuyen | null | undefined)[]
  ): IChiDaoTuyen[] {
    const chiDaoTuyens: IChiDaoTuyen[] = chiDaoTuyensToCheck.filter(isPresent);
    if (chiDaoTuyens.length > 0) {
      const chiDaoTuyenCollectionIdentifiers = chiDaoTuyenCollection.map(chiDaoTuyenItem => getChiDaoTuyenIdentifier(chiDaoTuyenItem)!);
      const chiDaoTuyensToAdd = chiDaoTuyens.filter(chiDaoTuyenItem => {
        const chiDaoTuyenIdentifier = getChiDaoTuyenIdentifier(chiDaoTuyenItem);
        if (chiDaoTuyenIdentifier == null || chiDaoTuyenCollectionIdentifiers.includes(chiDaoTuyenIdentifier)) {
          return false;
        }
        chiDaoTuyenCollectionIdentifiers.push(chiDaoTuyenIdentifier);
        return true;
      });
      return [...chiDaoTuyensToAdd, ...chiDaoTuyenCollection];
    }
    return chiDaoTuyenCollection;
  }

  protected convertDateFromClient(chiDaoTuyen: IChiDaoTuyen): IChiDaoTuyen {
    return Object.assign({}, chiDaoTuyen, {
      ngayQuyetDinh: chiDaoTuyen.ngayQuyetDinh?.isValid() ? chiDaoTuyen.ngayQuyetDinh.toJSON() : undefined,
      ngayHD: chiDaoTuyen.ngayHD?.isValid() ? chiDaoTuyen.ngayHD.toJSON() : undefined,
      ngayBatDau: chiDaoTuyen.ngayBatDau?.isValid() ? chiDaoTuyen.ngayBatDau.toJSON() : undefined,
      ngayKetThuc: chiDaoTuyen.ngayKetThuc?.isValid() ? chiDaoTuyen.ngayKetThuc.toJSON() : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.ngayQuyetDinh = res.body.ngayQuyetDinh ? dayjs(res.body.ngayQuyetDinh) : undefined;
      res.body.ngayHD = res.body.ngayHD ? dayjs(res.body.ngayHD) : undefined;
      res.body.ngayBatDau = res.body.ngayBatDau ? dayjs(res.body.ngayBatDau) : undefined;
      res.body.ngayKetThuc = res.body.ngayKetThuc ? dayjs(res.body.ngayKetThuc) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((chiDaoTuyen: IChiDaoTuyen) => {
        chiDaoTuyen.ngayQuyetDinh = chiDaoTuyen.ngayQuyetDinh ? dayjs(chiDaoTuyen.ngayQuyetDinh) : undefined;
        chiDaoTuyen.ngayHD = chiDaoTuyen.ngayHD ? dayjs(chiDaoTuyen.ngayHD) : undefined;
        chiDaoTuyen.ngayBatDau = chiDaoTuyen.ngayBatDau ? dayjs(chiDaoTuyen.ngayBatDau) : undefined;
        chiDaoTuyen.ngayKetThuc = chiDaoTuyen.ngayKetThuc ? dayjs(chiDaoTuyen.ngayKetThuc) : undefined;
      });
    }
    return res;
  }
}
