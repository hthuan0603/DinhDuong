import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IVatTuHoTro, VatTuHoTro } from '../vat-tu-ho-tro.model';

import { VatTuHoTroService } from './vat-tu-ho-tro.service';

describe('VatTuHoTro Service', () => {
  let service: VatTuHoTroService;
  let httpMock: HttpTestingController;
  let elemDefault: IVatTuHoTro;
  let expectedResult: IVatTuHoTro | IVatTuHoTro[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(VatTuHoTroService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      maVatTu: 0,
      tenVatTu: 'AAAAAAA',
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

    it('should create a VatTuHoTro', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new VatTuHoTro()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a VatTuHoTro', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          maVatTu: 1,
          tenVatTu: 'BBBBBB',
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

    it('should partial update a VatTuHoTro', () => {
      const patchObject = Object.assign({}, new VatTuHoTro());

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of VatTuHoTro', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          maVatTu: 1,
          tenVatTu: 'BBBBBB',
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

    it('should delete a VatTuHoTro', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addVatTuHoTroToCollectionIfMissing', () => {
      it('should add a VatTuHoTro to an empty array', () => {
        const vatTuHoTro: IVatTuHoTro = { id: 123 };
        expectedResult = service.addVatTuHoTroToCollectionIfMissing([], vatTuHoTro);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(vatTuHoTro);
      });

      it('should not add a VatTuHoTro to an array that contains it', () => {
        const vatTuHoTro: IVatTuHoTro = { id: 123 };
        const vatTuHoTroCollection: IVatTuHoTro[] = [
          {
            ...vatTuHoTro,
          },
          { id: 456 },
        ];
        expectedResult = service.addVatTuHoTroToCollectionIfMissing(vatTuHoTroCollection, vatTuHoTro);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a VatTuHoTro to an array that doesn't contain it", () => {
        const vatTuHoTro: IVatTuHoTro = { id: 123 };
        const vatTuHoTroCollection: IVatTuHoTro[] = [{ id: 456 }];
        expectedResult = service.addVatTuHoTroToCollectionIfMissing(vatTuHoTroCollection, vatTuHoTro);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(vatTuHoTro);
      });

      it('should add only unique VatTuHoTro to an array', () => {
        const vatTuHoTroArray: IVatTuHoTro[] = [{ id: 123 }, { id: 456 }, { id: 90548 }];
        const vatTuHoTroCollection: IVatTuHoTro[] = [{ id: 123 }];
        expectedResult = service.addVatTuHoTroToCollectionIfMissing(vatTuHoTroCollection, ...vatTuHoTroArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const vatTuHoTro: IVatTuHoTro = { id: 123 };
        const vatTuHoTro2: IVatTuHoTro = { id: 456 };
        expectedResult = service.addVatTuHoTroToCollectionIfMissing([], vatTuHoTro, vatTuHoTro2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(vatTuHoTro);
        expect(expectedResult).toContain(vatTuHoTro2);
      });

      it('should accept null and undefined values', () => {
        const vatTuHoTro: IVatTuHoTro = { id: 123 };
        expectedResult = service.addVatTuHoTroToCollectionIfMissing([], null, vatTuHoTro, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(vatTuHoTro);
      });

      it('should return initial array if no VatTuHoTro is added', () => {
        const vatTuHoTroCollection: IVatTuHoTro[] = [{ id: 123 }];
        expectedResult = service.addVatTuHoTroToCollectionIfMissing(vatTuHoTroCollection, undefined, null);
        expect(expectedResult).toEqual(vatTuHoTroCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
