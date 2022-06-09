import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IToaThuoc, getToaThuocIdentifier } from '../toa-thuoc.model';

export type EntityResponseType = HttpResponse<IToaThuoc>;
export type EntityArrayResponseType = HttpResponse<IToaThuoc[]>;

@Injectable({ providedIn: 'root' })
export class ToaThuocService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/toa-thuocs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(toaThuoc: IToaThuoc): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(toaThuoc);
    return this.http
      .post<IToaThuoc>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(toaThuoc: IToaThuoc): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(toaThuoc);
    return this.http
      .put<IToaThuoc>(`${this.resourceUrl}/${getToaThuocIdentifier(toaThuoc) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(toaThuoc: IToaThuoc): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(toaThuoc);
    return this.http
      .patch<IToaThuoc>(`${this.resourceUrl}/${getToaThuocIdentifier(toaThuoc) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IToaThuoc>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IToaThuoc[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addToaThuocToCollectionIfMissing(toaThuocCollection: IToaThuoc[], ...toaThuocsToCheck: (IToaThuoc | null | undefined)[]): IToaThuoc[] {
    const toaThuocs: IToaThuoc[] = toaThuocsToCheck.filter(isPresent);
    if (toaThuocs.length > 0) {
      const toaThuocCollectionIdentifiers = toaThuocCollection.map(toaThuocItem => getToaThuocIdentifier(toaThuocItem)!);
      const toaThuocsToAdd = toaThuocs.filter(toaThuocItem => {
        const toaThuocIdentifier = getToaThuocIdentifier(toaThuocItem);
        if (toaThuocIdentifier == null || toaThuocCollectionIdentifiers.includes(toaThuocIdentifier)) {
          return false;
        }
        toaThuocCollectionIdentifiers.push(toaThuocIdentifier);
        return true;
      });
      return [...toaThuocsToAdd, ...toaThuocCollection];
    }
    return toaThuocCollection;
  }

  protected convertDateFromClient(toaThuoc: IToaThuoc): IToaThuoc {
    return Object.assign({}, toaThuoc, {
      ngayChiDinh: toaThuoc.ngayChiDinh?.isValid() ? toaThuoc.ngayChiDinh.toJSON() : undefined,
      ngayDung: toaThuoc.ngayDung?.isValid() ? toaThuoc.ngayDung.toJSON() : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.ngayChiDinh = res.body.ngayChiDinh ? dayjs(res.body.ngayChiDinh) : undefined;
      res.body.ngayDung = res.body.ngayDung ? dayjs(res.body.ngayDung) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((toaThuoc: IToaThuoc) => {
        toaThuoc.ngayChiDinh = toaThuoc.ngayChiDinh ? dayjs(toaThuoc.ngayChiDinh) : undefined;
        toaThuoc.ngayDung = toaThuoc.ngayDung ? dayjs(toaThuoc.ngayDung) : undefined;
      });
    }
    return res;
  }
}
