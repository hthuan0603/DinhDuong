import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IKyThuatHoTro, KyThuatHoTro } from '../ky-thuat-ho-tro.model';

import { KyThuatHoTroService } from './ky-thuat-ho-tro.service';

describe('KyThuatHoTro Service', () => {
  let service: KyThuatHoTroService;
  let httpMock: HttpTestingController;
  let elemDefault: IKyThuatHoTro;
  let expectedResult: IKyThuatHoTro | IKyThuatHoTro[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(KyThuatHoTroService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      maKyThuat: 0,
      tenKyThuat: 'AAAAAAA',
      thuTuSX: 0,
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign({}, elemDefault);

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a KyThuatHoTro', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new KyThuatHoTro()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a KyThuatHoTro', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          maKyThuat: 1,
          tenKyThuat: 'BBBBBB',
          thuTuSX: 1,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a KyThuatHoTro', () => {
      const patchObject = Object.assign(
        {
          thuTuSX: 1,
        },
        new KyThuatHoTro()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of KyThuatHoTro', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          maKyThuat: 1,
          tenKyThuat: 'BBBBBB',
          thuTuSX: 1,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a KyThuatHoTro', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addKyThuatHoTroToCollectionIfMissing', () => {
      it('should add a KyThuatHoTro to an empty array', () => {
        const kyThuatHoTro: IKyThuatHoTro = { id: 123 };
        expectedResult = service.addKyThuatHoTroToCollectionIfMissing([], kyThuatHoTro);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(kyThuatHoTro);
      });

      it('should not add a KyThuatHoTro to an array that contains it', () => {
        const kyThuatHoTro: IKyThuatHoTro = { id: 123 };
        const kyThuatHoTroCollection: IKyThuatHoTro[] = [
          {
            ...kyThuatHoTro,
          },
          { id: 456 },
        ];
        expectedResult = service.addKyThuatHoTroToCollectionIfMissing(kyThuatHoTroCollection, kyThuatHoTro);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a KyThuatHoTro to an array that doesn't contain it", () => {
        const kyThuatHoTro: IKyThuatHoTro = { id: 123 };
        const kyThuatHoTroCollection: IKyThuatHoTro[] = [{ id: 456 }];
        expectedResult = service.addKyThuatHoTroToCollectionIfMissing(kyThuatHoTroCollection, kyThuatHoTro);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(kyThuatHoTro);
      });

      it('should add only unique KyThuatHoTro to an array', () => {
        const kyThuatHoTroArray: IKyThuatHoTro[] = [{ id: 123 }, { id: 456 }, { id: 34886 }];
        const kyThuatHoTroCollection: IKyThuatHoTro[] = [{ id: 123 }];
        expectedResult = service.addKyThuatHoTroToCollectionIfMissing(kyThuatHoTroCollection, ...kyThuatHoTroArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const kyThuatHoTro: IKyThuatHoTro = { id: 123 };
        const kyThuatHoTro2: IKyThuatHoTro = { id: 456 };
        expectedResult = service.addKyThuatHoTroToCollectionIfMissing([], kyThuatHoTro, kyThuatHoTro2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(kyThuatHoTro);
        expect(expectedResult).toContain(kyThuatHoTro2);
      });

      it('should accept null and undefined values', () => {
        const kyThuatHoTro: IKyThuatHoTro = { id: 123 };
        expectedResult = service.addKyThuatHoTroToCollectionIfMissing([], null, kyThuatHoTro, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(kyThuatHoTro);
      });

      it('should return initial array if no KyThuatHoTro is added', () => {
        const kyThuatHoTroCollection: IKyThuatHoTro[] = [{ id: 123 }];
        expectedResult = service.addKyThuatHoTroToCollectionIfMissing(kyThuatHoTroCollection, undefined, null);
        expect(expectedResult).toEqual(kyThuatHoTroCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
