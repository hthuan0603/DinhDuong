import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IHoTro, getHoTroIdentifier } from '../ho-tro.model';

export type EntityResponseType = HttpResponse<IHoTro>;
export type EntityArrayResponseType = HttpResponse<IHoTro[]>;

@Injectable({ providedIn: 'root' })
export class HoTroService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ho-tros');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(hoTro: IHoTro): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(hoTro);
    return this.http
      .post<IHoTro>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(hoTro: IHoTro): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(hoTro);
    return this.http
      .put<IHoTro>(`${this.resourceUrl}/${getHoTroIdentifier(hoTro) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(hoTro: IHoTro): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(hoTro);
    return this.http
      .patch<IHoTro>(`${this.resourceUrl}/${getHoTroIdentifier(hoTro) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IHoTro>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IHoTro[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addHoTroToCollectionIfMissing(hoTroCollection: IHoTro[], ...hoTrosToCheck: (IHoTro | null | undefined)[]): IHoTro[] {
    const hoTros: IHoTro[] = hoTrosToCheck.filter(isPresent);
    if (hoTros.length > 0) {
      const hoTroCollectionIdentifiers = hoTroCollection.map(hoTroItem => getHoTroIdentifier(hoTroItem)!);
      const hoTrosToAdd = hoTros.filter(hoTroItem => {
        const hoTroIdentifier = getHoTroIdentifier(hoTroItem);
        if (hoTroIdentifier == null || hoTroCollectionIdentifiers.includes(hoTroIdentifier)) {
          return false;
        }
        hoTroCollectionIdentifiers.push(hoTroIdentifier);
        return true;
      });
      return [...hoTrosToAdd, ...hoTroCollection];
    }
    return hoTroCollection;
  }

  protected convertDateFromClient(hoTro: IHoTro): IHoTro {
    return Object.assign({}, hoTro, {
      ngayTao: hoTro.ngayTao?.isValid() ? hoTro.ngayTao.toJSON() : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.ngayTao = res.body.ngayTao ? dayjs(res.body.ngayTao) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((hoTro: IHoTro) => {
        hoTro.ngayTao = hoTro.ngayTao ? dayjs(hoTro.ngayTao) : undefined;
      });
    }
    return res;
  }
}
