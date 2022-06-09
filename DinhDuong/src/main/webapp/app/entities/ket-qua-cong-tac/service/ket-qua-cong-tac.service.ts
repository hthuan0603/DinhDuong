import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IKetQuaCongTac, getKetQuaCongTacIdentifier } from '../ket-qua-cong-tac.model';

export type EntityResponseType = HttpResponse<IKetQuaCongTac>;
export type EntityArrayResponseType = HttpResponse<IKetQuaCongTac[]>;

@Injectable({ providedIn: 'root' })
export class KetQuaCongTacService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/ket-qua-cong-tacs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(ketQuaCongTac: IKetQuaCongTac): Observable<EntityResponseType> {
    return this.http.post<IKetQuaCongTac>(this.resourceUrl, ketQuaCongTac, { observe: 'response' });
  }

  update(ketQuaCongTac: IKetQuaCongTac): Observable<EntityResponseType> {
    return this.http.put<IKetQuaCongTac>(`${this.resourceUrl}/${getKetQuaCongTacIdentifier(ketQuaCongTac) as number}`, ketQuaCongTac, {
      observe: 'response',
    });
  }

  partialUpdate(ketQuaCongTac: IKetQuaCongTac): Observable<EntityResponseType> {
    return this.http.patch<IKetQuaCongTac>(`${this.resourceUrl}/${getKetQuaCongTacIdentifier(ketQuaCongTac) as number}`, ketQuaCongTac, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IKetQuaCongTac>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IKetQuaCongTac[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addKetQuaCongTacToCollectionIfMissing(
    ketQuaCongTacCollection: IKetQuaCongTac[],
    ...ketQuaCongTacsToCheck: (IKetQuaCongTac | null | undefined)[]
  ): IKetQuaCongTac[] {
    const ketQuaCongTacs: IKetQuaCongTac[] = ketQuaCongTacsToCheck.filter(isPresent);
    if (ketQuaCongTacs.length > 0) {
      const ketQuaCongTacCollectionIdentifiers = ketQuaCongTacCollection.map(
        ketQuaCongTacItem => getKetQuaCongTacIdentifier(ketQuaCongTacItem)!
      );
      const ketQuaCongTacsToAdd = ketQuaCongTacs.filter(ketQuaCongTacItem => {
        const ketQuaCongTacIdentifier = getKetQuaCongTacIdentifier(ketQuaCongTacItem);
        if (ketQuaCongTacIdentifier == null || ketQuaCongTacCollectionIdentifiers.includes(ketQuaCongTacIdentifier)) {
          return false;
        }
        ketQuaCongTacCollectionIdentifiers.push(ketQuaCongTacIdentifier);
        return true;
      });
      return [...ketQuaCongTacsToAdd, ...ketQuaCongTacCollection];
    }
    return ketQuaCongTacCollection;
  }
}
