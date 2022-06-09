import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { INoiDenCongTac, getNoiDenCongTacIdentifier } from '../noi-den-cong-tac.model';

export type EntityResponseType = HttpResponse<INoiDenCongTac>;
export type EntityArrayResponseType = HttpResponse<INoiDenCongTac[]>;

@Injectable({ providedIn: 'root' })
export class NoiDenCongTacService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/noi-den-cong-tacs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(noiDenCongTac: INoiDenCongTac): Observable<EntityResponseType> {
    return this.http.post<INoiDenCongTac>(this.resourceUrl, noiDenCongTac, { observe: 'response' });
  }

  update(noiDenCongTac: INoiDenCongTac): Observable<EntityResponseType> {
    return this.http.put<INoiDenCongTac>(`${this.resourceUrl}/${getNoiDenCongTacIdentifier(noiDenCongTac) as number}`, noiDenCongTac, {
      observe: 'response',
    });
  }

  partialUpdate(noiDenCongTac: INoiDenCongTac): Observable<EntityResponseType> {
    return this.http.patch<INoiDenCongTac>(`${this.resourceUrl}/${getNoiDenCongTacIdentifier(noiDenCongTac) as number}`, noiDenCongTac, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<INoiDenCongTac>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INoiDenCongTac[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addNoiDenCongTacToCollectionIfMissing(
    noiDenCongTacCollection: INoiDenCongTac[],
    ...noiDenCongTacsToCheck: (INoiDenCongTac | null | undefined)[]
  ): INoiDenCongTac[] {
    const noiDenCongTacs: INoiDenCongTac[] = noiDenCongTacsToCheck.filter(isPresent);
    if (noiDenCongTacs.length > 0) {
      const noiDenCongTacCollectionIdentifiers = noiDenCongTacCollection.map(
        noiDenCongTacItem => getNoiDenCongTacIdentifier(noiDenCongTacItem)!
      );
      const noiDenCongTacsToAdd = noiDenCongTacs.filter(noiDenCongTacItem => {
        const noiDenCongTacIdentifier = getNoiDenCongTacIdentifier(noiDenCongTacItem);
        if (noiDenCongTacIdentifier == null || noiDenCongTacCollectionIdentifiers.includes(noiDenCongTacIdentifier)) {
          return false;
        }
        noiDenCongTacCollectionIdentifiers.push(noiDenCongTacIdentifier);
        return true;
      });
      return [...noiDenCongTacsToAdd, ...noiDenCongTacCollection];
    }
    return noiDenCongTacCollection;
  }
}
