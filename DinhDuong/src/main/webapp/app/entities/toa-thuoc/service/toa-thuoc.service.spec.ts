import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IToaThuoc, ToaThuoc } from '../toa-thuoc.model';

import { ToaThuocService } from './toa-thuoc.service';

describe('ToaThuoc Service', () => {
  let service: ToaThuocService;
  let httpMock: HttpTestingController;
  let elemDefault: IToaThuoc;
  let expectedResult: IToaThuoc | IToaThuoc[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ToaThuocService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      iCD10: 'AAAAAAA',
      maBA: 'AAAAAAA',
      maBN: 'AAAAAAA',
      loaiThuoc: 'AAAAAAA',
      doiTuong: 'AAAAAAA',
      tiLe: 0,
      soNgayHenTaiKham: 0,
      capPhieuHenKham: false,
      loiDanBacSi: 'AAAAAAA',
      ngayChiDinh: currentDate,
      ngayDung: currentDate,
      soNgaykeDon: 0,
      khoThuoc: false,
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          ngayChiDinh: currentDate.format(DATE_TIME_FORMAT),
          ngayDung: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a ToaThuoc', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          ngayChiDinh: currentDate.format(DATE_TIME_FORMAT),
          ngayDung: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          ngayChiDinh: currentDate,
          ngayDung: currentDate,
        },
        returnedFromService
      );

      service.create(new ToaThuoc()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ToaThuoc', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          iCD10: 'BBBBBB',
          maBA: 'BBBBBB',
          maBN: 'BBBBBB',
          loaiThuoc: 'BBBBBB',
          doiTuong: 'BBBBBB',
          tiLe: 1,
          soNgayHenTaiKham: 1,
          capPhieuHenKham: true,
          loiDanBacSi: 'BBBBBB',
          ngayChiDinh: currentDate.format(DATE_TIME_FORMAT),
          ngayDung: currentDate.format(DATE_TIME_FORMAT),
          soNgaykeDon: 1,
          khoThuoc: true,
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          ngayChiDinh: currentDate,
          ngayDung: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ToaThuoc', () => {
      const patchObject = Object.assign(
        {
          maBA: 'BBBBBB',
          loaiThuoc: 'BBBBBB',
          tiLe: 1,
          soNgayHenTaiKham: 1,
          capPhieuHenKham: true,
          loiDanBacSi: 'BBBBBB',
          ngayChiDinh: currentDate.format(DATE_TIME_FORMAT),
          ngayDung: currentDate.format(DATE_TIME_FORMAT),
          soNgaykeDon: 1,
          khoThuoc: true,
        },
        new ToaThuoc()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          ngayChiDinh: currentDate,
          ngayDung: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ToaThuoc', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          iCD10: 'BBBBBB',
          maBA: 'BBBBBB',
          maBN: 'BBBBBB',
          loaiThuoc: 'BBBBBB',
          doiTuong: 'BBBBBB',
          tiLe: 1,
          soNgayHenTaiKham: 1,
          capPhieuHenKham: true,
          loiDanBacSi: 'BBBBBB',
          ngayChiDinh: currentDate.format(DATE_TIME_FORMAT),
          ngayDung: currentDate.format(DATE_TIME_FORMAT),
          soNgaykeDon: 1,
          khoThuoc: true,
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          ngayChiDinh: currentDate,
          ngayDung: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a ToaThuoc', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addToaThuocToCollectionIfMissing', () => {
      it('should add a ToaThuoc to an empty array', () => {
        const toaThuoc: IToaThuoc = { id: 123 };
        expectedResult = service.addToaThuocToCollectionIfMissing([], toaThuoc);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(toaThuoc);
      });

      it('should not add a ToaThuoc to an array that contains it', () => {
        const toaThuoc: IToaThuoc = { id: 123 };
        const toaThuocCollection: IToaThuoc[] = [
          {
            ...toaThuoc,
          },
          { id: 456 },
        ];
        expectedResult = service.addToaThuocToCollectionIfMissing(toaThuocCollection, toaThuoc);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ToaThuoc to an array that doesn't contain it", () => {
        const toaThuoc: IToaThuoc = { id: 123 };
        const toaThuocCollection: IToaThuoc[] = [{ id: 456 }];
        expectedResult = service.addToaThuocToCollectionIfMissing(toaThuocCollection, toaThuoc);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(toaThuoc);
      });

      it('should add only unique ToaThuoc to an array', () => {
        const toaThuocArray: IToaThuoc[] = [{ id: 123 }, { id: 456 }, { id: 44262 }];
        const toaThuocCollection: IToaThuoc[] = [{ id: 123 }];
        expectedResult = service.addToaThuocToCollectionIfMissing(toaThuocCollection, ...toaThuocArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const toaThuoc: IToaThuoc = { id: 123 };
        const toaThuoc2: IToaThuoc = { id: 456 };
        expectedResult = service.addToaThuocToCollectionIfMissing([], toaThuoc, toaThuoc2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(toaThuoc);
        expect(expectedResult).toContain(toaThuoc2);
      });

      it('should accept null and undefined values', () => {
        const toaThuoc: IToaThuoc = { id: 123 };
        expectedResult = service.addToaThuocToCollectionIfMissing([], null, toaThuoc, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(toaThuoc);
      });

      it('should return initial array if no ToaThuoc is added', () => {
        const toaThuocCollection: IToaThuoc[] = [{ id: 123 }];
        expectedResult = service.addToaThuocToCollectionIfMissing(toaThuocCollection, undefined, null);
        expect(expectedResult).toEqual(toaThuocCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
