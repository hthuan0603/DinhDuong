import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IGiayMoiDanhGia, getGiayMoiDanhGiaIdentifier } from '../giay-moi-danh-gia.model';

export type EntityResponseType = HttpResponse<IGiayMoiDanhGia>;
export type EntityArrayResponseType = HttpResponse<IGiayMoiDanhGia[]>;

@Injectable({ providedIn: 'root' })
export class GiayMoiDanhGiaService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/giay-moi-danh-gias');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(giayMoiDanhGia: IGiayMoiDanhGia): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(giayMoiDanhGia);
    return this.http
      .post<IGiayMoiDanhGia>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(giayMoiDanhGia: IGiayMoiDanhGia): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(giayMoiDanhGia);
    return this.http
      .put<IGiayMoiDanhGia>(`${this.resourceUrl}/${getGiayMoiDanhGiaIdentifier(giayMoiDanhGia) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(giayMoiDanhGia: IGiayMoiDanhGia): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(giayMoiDanhGia);
    return this.http
      .patch<IGiayMoiDanhGia>(`${this.resourceUrl}/${getGiayMoiDanhGiaIdentifier(giayMoiDanhGia) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IGiayMoiDanhGia>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IGiayMoiDanhGia[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addGiayMoiDanhGiaToCollectionIfMissing(
    giayMoiDanhGiaCollection: IGiayMoiDanhGia[],
    ...giayMoiDanhGiasToCheck: (IGiayMoiDanhGia | null | undefined)[]
  ): IGiayMoiDanhGia[] {
    const giayMoiDanhGias: IGiayMoiDanhGia[] = giayMoiDanhGiasToCheck.filter(isPresent);
    if (giayMoiDanhGias.length > 0) {
      const giayMoiDanhGiaCollectionIdentifiers = giayMoiDanhGiaCollection.map(
        giayMoiDanhGiaItem => getGiayMoiDanhGiaIdentifier(giayMoiDanhGiaItem)!
      );
      const giayMoiDanhGiasToAdd = giayMoiDanhGias.filter(giayMoiDanhGiaItem => {
        const giayMoiDanhGiaIdentifier = getGiayMoiDanhGiaIdentifier(giayMoiDanhGiaItem);
        if (giayMoiDanhGiaIdentifier == null || giayMoiDanhGiaCollectionIdentifiers.includes(giayMoiDanhGiaIdentifier)) {
          return false;
        }
        giayMoiDanhGiaCollectionIdentifiers.push(giayMoiDanhGiaIdentifier);
        return true;
      });
      return [...giayMoiDanhGiasToAdd, ...giayMoiDanhGiaCollection];
    }
    return giayMoiDanhGiaCollection;
  }

  protected convertDateFromClient(giayMoiDanhGia: IGiayMoiDanhGia): IGiayMoiDanhGia {
    return Object.assign({}, giayMoiDanhGia, {
      thoiGianChiDinh: giayMoiDanhGia.thoiGianChiDinh?.isValid() ? giayMoiDanhGia.thoiGianChiDinh.toJSON() : undefined,
      thoiGianHoiChuan: giayMoiDanhGia.thoiGianHoiChuan?.isValid() ? giayMoiDanhGia.thoiGianHoiChuan.toJSON() : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.thoiGianChiDinh = res.body.thoiGianChiDinh ? dayjs(res.body.thoiGianChiDinh) : undefined;
      res.body.thoiGianHoiChuan = res.body.thoiGianHoiChuan ? dayjs(res.body.thoiGianHoiChuan) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((giayMoiDanhGia: IGiayMoiDanhGia) => {
        giayMoiDanhGia.thoiGianChiDinh = giayMoiDanhGia.thoiGianChiDinh ? dayjs(giayMoiDanhGia.thoiGianChiDinh) : undefined;
        giayMoiDanhGia.thoiGianHoiChuan = giayMoiDanhGia.thoiGianHoiChuan ? dayjs(giayMoiDanhGia.thoiGianHoiChuan) : undefined;
      });
    }
    return res;
  }
}
