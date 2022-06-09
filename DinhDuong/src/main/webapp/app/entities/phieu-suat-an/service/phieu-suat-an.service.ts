import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IPhieuSuatAn, getPhieuSuatAnIdentifier } from '../phieu-suat-an.model';

export type EntityResponseType = HttpResponse<IPhieuSuatAn>;
export type EntityArrayResponseType = HttpResponse<IPhieuSuatAn[]>;

@Injectable({ providedIn: 'root' })
export class PhieuSuatAnService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/phieu-suat-ans');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(phieuSuatAn: IPhieuSuatAn): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(phieuSuatAn);
    return this.http
      .post<IPhieuSuatAn>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(phieuSuatAn: IPhieuSuatAn): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(phieuSuatAn);
    return this.http
      .put<IPhieuSuatAn>(`${this.resourceUrl}/${getPhieuSuatAnIdentifier(phieuSuatAn) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(phieuSuatAn: IPhieuSuatAn): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(phieuSuatAn);
    return this.http
      .patch<IPhieuSuatAn>(`${this.resourceUrl}/${getPhieuSuatAnIdentifier(phieuSuatAn) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPhieuSuatAn>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPhieuSuatAn[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addPhieuSuatAnToCollectionIfMissing(
    phieuSuatAnCollection: IPhieuSuatAn[],
    ...phieuSuatAnsToCheck: (IPhieuSuatAn | null | undefined)[]
  ): IPhieuSuatAn[] {
    const phieuSuatAns: IPhieuSuatAn[] = phieuSuatAnsToCheck.filter(isPresent);
    if (phieuSuatAns.length > 0) {
      const phieuSuatAnCollectionIdentifiers = phieuSuatAnCollection.map(phieuSuatAnItem => getPhieuSuatAnIdentifier(phieuSuatAnItem)!);
      const phieuSuatAnsToAdd = phieuSuatAns.filter(phieuSuatAnItem => {
        const phieuSuatAnIdentifier = getPhieuSuatAnIdentifier(phieuSuatAnItem);
        if (phieuSuatAnIdentifier == null || phieuSuatAnCollectionIdentifiers.includes(phieuSuatAnIdentifier)) {
          return false;
        }
        phieuSuatAnCollectionIdentifiers.push(phieuSuatAnIdentifier);
        return true;
      });
      return [...phieuSuatAnsToAdd, ...phieuSuatAnCollection];
    }
    return phieuSuatAnCollection;
  }

  protected convertDateFromClient(phieuSuatAn: IPhieuSuatAn): IPhieuSuatAn {
    return Object.assign({}, phieuSuatAn, {
      tGSuDung: phieuSuatAn.tGSuDung?.isValid() ? phieuSuatAn.tGSuDung.toJSON() : undefined,
      tGChiDinh: phieuSuatAn.tGChiDinh?.isValid() ? phieuSuatAn.tGChiDinh.toJSON() : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.tGSuDung = res.body.tGSuDung ? dayjs(res.body.tGSuDung) : undefined;
      res.body.tGChiDinh = res.body.tGChiDinh ? dayjs(res.body.tGChiDinh) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((phieuSuatAn: IPhieuSuatAn) => {
        phieuSuatAn.tGSuDung = phieuSuatAn.tGSuDung ? dayjs(phieuSuatAn.tGSuDung) : undefined;
        phieuSuatAn.tGChiDinh = phieuSuatAn.tGChiDinh ? dayjs(phieuSuatAn.tGChiDinh) : undefined;
      });
    }
    return res;
  }
}
