import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IHoTro, HoTro } from '../ho-tro.model';

import { HoTroService } from './ho-tro.service';

describe('HoTro Service', () => {
  let service: HoTroService;
  let httpMock: HttpTestingController;
  let elemDefault: IHoTro;
  let expectedResult: IHoTro | IHoTro[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(HoTroService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      maNoiDung: 0,
      ngayTao: currentDate,
      nhanVienCV: 'AAAAAAA',
      kyThuatHoTro: 'AAAAAAA',
      vatTuHoTro: 'AAAAAAA',
      soBnKhamDieuTri: 0,
      soBnPhauThuat: 0,
      soCanBoChuyenGiao: 0,
      ketQuaCongTac: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          ngayTao: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a HoTro', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          ngayTao: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          ngayTao: currentDate,
        },
        returnedFromService
      );

      service.create(new HoTro()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a HoTro', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          maNoiDung: 1,
          ngayTao: currentDate.format(DATE_TIME_FORMAT),
          nhanVienCV: 'BBBBBB',
          kyThuatHoTro: 'BBBBBB',
          vatTuHoTro: 'BBBBBB',
          soBnKhamDieuTri: 1,
          soBnPhauThuat: 1,
          soCanBoChuyenGiao: 1,
          ketQuaCongTac: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          ngayTao: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a HoTro', () => {
      const patchObject = Object.assign(
        {
          maNoiDung: 1,
          ngayTao: currentDate.format(DATE_TIME_FORMAT),
          vatTuHoTro: 'BBBBBB',
          ketQuaCongTac: 'BBBBBB',
        },
        new HoTro()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          ngayTao: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of HoTro', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          maNoiDung: 1,
          ngayTao: currentDate.format(DATE_TIME_FORMAT),
          nhanVienCV: 'BBBBBB',
          kyThuatHoTro: 'BBBBBB',
          vatTuHoTro: 'BBBBBB',
          soBnKhamDieuTri: 1,
          soBnPhauThuat: 1,
          soCanBoChuyenGiao: 1,
          ketQuaCongTac: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          ngayTao: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a HoTro', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addHoTroToCollectionIfMissing', () => {
      it('should add a HoTro to an empty array', () => {
        const hoTro: IHoTro = { id: 123 };
        expectedResult = service.addHoTroToCollectionIfMissing([], hoTro);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(hoTro);
      });

      it('should not add a HoTro to an array that contains it', () => {
        const hoTro: IHoTro = { id: 123 };
        const hoTroCollection: IHoTro[] = [
          {
            ...hoTro,
          },
          { id: 456 },
        ];
        expectedResult = service.addHoTroToCollectionIfMissing(hoTroCollection, hoTro);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a HoTro to an array that doesn't contain it", () => {
        const hoTro: IHoTro = { id: 123 };
        const hoTroCollection: IHoTro[] = [{ id: 456 }];
        expectedResult = service.addHoTroToCollectionIfMissing(hoTroCollection, hoTro);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(hoTro);
      });

      it('should add only unique HoTro to an array', () => {
        const hoTroArray: IHoTro[] = [{ id: 123 }, { id: 456 }, { id: 71292 }];
        const hoTroCollection: IHoTro[] = [{ id: 123 }];
        expectedResult = service.addHoTroToCollectionIfMissing(hoTroCollection, ...hoTroArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const hoTro: IHoTro = { id: 123 };
        const hoTro2: IHoTro = { id: 456 };
        expectedResult = service.addHoTroToCollectionIfMissing([], hoTro, hoTro2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(hoTro);
        expect(expectedResult).toContain(hoTro2);
      });

      it('should accept null and undefined values', () => {
        const hoTro: IHoTro = { id: 123 };
        expectedResult = service.addHoTroToCollectionIfMissing([], null, hoTro, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(hoTro);
      });

      it('should return initial array if no HoTro is added', () => {
        const hoTroCollection: IHoTro[] = [{ id: 123 }];
        expectedResult = service.addHoTroToCollectionIfMissing(hoTroCollection, undefined, null);
        expect(expectedResult).toEqual(hoTroCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
