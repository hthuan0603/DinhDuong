import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IPhieuSuatAn, PhieuSuatAn } from '../phieu-suat-an.model';

import { PhieuSuatAnService } from './phieu-suat-an.service';

describe('PhieuSuatAn Service', () => {
  let service: PhieuSuatAnService;
  let httpMock: HttpTestingController;
  let elemDefault: IPhieuSuatAn;
  let expectedResult: IPhieuSuatAn | IPhieuSuatAn[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(PhieuSuatAnService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      tenDv: 'AAAAAAA',
      maDV: 'AAAAAAA',
      maBN: 'AAAAAAA',
      maTheBHYT: 'AAAAAAA',
      tGSuDung: currentDate,
      tGChiDinh: currentDate,
      chuanDoan: 'AAAAAAA',
      chuanDoanKT: 'AAAAAAA',
      ghiChu: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          tGSuDung: currentDate.format(DATE_TIME_FORMAT),
          tGChiDinh: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a PhieuSuatAn', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          tGSuDung: currentDate.format(DATE_TIME_FORMAT),
          tGChiDinh: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          tGSuDung: currentDate,
          tGChiDinh: currentDate,
        },
        returnedFromService
      );

      service.create(new PhieuSuatAn()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a PhieuSuatAn', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          tenDv: 'BBBBBB',
          maDV: 'BBBBBB',
          maBN: 'BBBBBB',
          maTheBHYT: 'BBBBBB',
          tGSuDung: currentDate.format(DATE_TIME_FORMAT),
          tGChiDinh: currentDate.format(DATE_TIME_FORMAT),
          chuanDoan: 'BBBBBB',
          chuanDoanKT: 'BBBBBB',
          ghiChu: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          tGSuDung: currentDate,
          tGChiDinh: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a PhieuSuatAn', () => {
      const patchObject = Object.assign(
        {
          tenDv: 'BBBBBB',
          maDV: 'BBBBBB',
          maBN: 'BBBBBB',
          tGChiDinh: currentDate.format(DATE_TIME_FORMAT),
          chuanDoan: 'BBBBBB',
          chuanDoanKT: 'BBBBBB',
        },
        new PhieuSuatAn()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          tGSuDung: currentDate,
          tGChiDinh: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of PhieuSuatAn', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          tenDv: 'BBBBBB',
          maDV: 'BBBBBB',
          maBN: 'BBBBBB',
          maTheBHYT: 'BBBBBB',
          tGSuDung: currentDate.format(DATE_TIME_FORMAT),
          tGChiDinh: currentDate.format(DATE_TIME_FORMAT),
          chuanDoan: 'BBBBBB',
          chuanDoanKT: 'BBBBBB',
          ghiChu: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          tGSuDung: currentDate,
          tGChiDinh: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a PhieuSuatAn', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addPhieuSuatAnToCollectionIfMissing', () => {
      it('should add a PhieuSuatAn to an empty array', () => {
        const phieuSuatAn: IPhieuSuatAn = { id: 123 };
        expectedResult = service.addPhieuSuatAnToCollectionIfMissing([], phieuSuatAn);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(phieuSuatAn);
      });

      it('should not add a PhieuSuatAn to an array that contains it', () => {
        const phieuSuatAn: IPhieuSuatAn = { id: 123 };
        const phieuSuatAnCollection: IPhieuSuatAn[] = [
          {
            ...phieuSuatAn,
          },
          { id: 456 },
        ];
        expectedResult = service.addPhieuSuatAnToCollectionIfMissing(phieuSuatAnCollection, phieuSuatAn);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a PhieuSuatAn to an array that doesn't contain it", () => {
        const phieuSuatAn: IPhieuSuatAn = { id: 123 };
        const phieuSuatAnCollection: IPhieuSuatAn[] = [{ id: 456 }];
        expectedResult = service.addPhieuSuatAnToCollectionIfMissing(phieuSuatAnCollection, phieuSuatAn);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(phieuSuatAn);
      });

      it('should add only unique PhieuSuatAn to an array', () => {
        const phieuSuatAnArray: IPhieuSuatAn[] = [{ id: 123 }, { id: 456 }, { id: 92222 }];
        const phieuSuatAnCollection: IPhieuSuatAn[] = [{ id: 123 }];
        expectedResult = service.addPhieuSuatAnToCollectionIfMissing(phieuSuatAnCollection, ...phieuSuatAnArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const phieuSuatAn: IPhieuSuatAn = { id: 123 };
        const phieuSuatAn2: IPhieuSuatAn = { id: 456 };
        expectedResult = service.addPhieuSuatAnToCollectionIfMissing([], phieuSuatAn, phieuSuatAn2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(phieuSuatAn);
        expect(expectedResult).toContain(phieuSuatAn2);
      });

      it('should accept null and undefined values', () => {
        const phieuSuatAn: IPhieuSuatAn = { id: 123 };
        expectedResult = service.addPhieuSuatAnToCollectionIfMissing([], null, phieuSuatAn, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(phieuSuatAn);
      });

      it('should return initial array if no PhieuSuatAn is added', () => {
        const phieuSuatAnCollection: IPhieuSuatAn[] = [{ id: 123 }];
        expectedResult = service.addPhieuSuatAnToCollectionIfMissing(phieuSuatAnCollection, undefined, null);
        expect(expectedResult).toEqual(phieuSuatAnCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
