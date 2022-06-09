import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { ITTSanglocVaDanhGiaDD, TTSanglocVaDanhGiaDD } from '../tt-sangloc-va-danh-gia-dd.model';

import { TTSanglocVaDanhGiaDDService } from './tt-sangloc-va-danh-gia-dd.service';

describe('TTSanglocVaDanhGiaDD Service', () => {
  let service: TTSanglocVaDanhGiaDDService;
  let httpMock: HttpTestingController;
  let elemDefault: ITTSanglocVaDanhGiaDD;
  let expectedResult: ITTSanglocVaDanhGiaDD | ITTSanglocVaDanhGiaDD[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(TTSanglocVaDanhGiaDDService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      maBN: 'AAAAAAA',
      mangThai: false,
      bMI: 0,
      danhGiaBMI: 0,
      phanTramCanNangTrong3Thang: 0,
      danhGiaCanNang: 0,
      anUongTrongTuan: 'AAAAAAA',
      danhGiaAnUong: 0,
      thoiGianChiDinh: currentDate,
      nguyCoSDD: false,
      cheDoAn: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          thoiGianChiDinh: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a TTSanglocVaDanhGiaDD', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          thoiGianChiDinh: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          thoiGianChiDinh: currentDate,
        },
        returnedFromService
      );

      service.create(new TTSanglocVaDanhGiaDD()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a TTSanglocVaDanhGiaDD', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          maBN: 'BBBBBB',
          mangThai: true,
          bMI: 1,
          danhGiaBMI: 1,
          phanTramCanNangTrong3Thang: 1,
          danhGiaCanNang: 1,
          anUongTrongTuan: 'BBBBBB',
          danhGiaAnUong: 1,
          thoiGianChiDinh: currentDate.format(DATE_TIME_FORMAT),
          nguyCoSDD: true,
          cheDoAn: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          thoiGianChiDinh: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a TTSanglocVaDanhGiaDD', () => {
      const patchObject = Object.assign(
        {
          maBN: 'BBBBBB',
          mangThai: true,
          phanTramCanNangTrong3Thang: 1,
          danhGiaCanNang: 1,
          anUongTrongTuan: 'BBBBBB',
          thoiGianChiDinh: currentDate.format(DATE_TIME_FORMAT),
          nguyCoSDD: true,
          cheDoAn: 'BBBBBB',
        },
        new TTSanglocVaDanhGiaDD()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          thoiGianChiDinh: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of TTSanglocVaDanhGiaDD', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          maBN: 'BBBBBB',
          mangThai: true,
          bMI: 1,
          danhGiaBMI: 1,
          phanTramCanNangTrong3Thang: 1,
          danhGiaCanNang: 1,
          anUongTrongTuan: 'BBBBBB',
          danhGiaAnUong: 1,
          thoiGianChiDinh: currentDate.format(DATE_TIME_FORMAT),
          nguyCoSDD: true,
          cheDoAn: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          thoiGianChiDinh: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a TTSanglocVaDanhGiaDD', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addTTSanglocVaDanhGiaDDToCollectionIfMissing', () => {
      it('should add a TTSanglocVaDanhGiaDD to an empty array', () => {
        const tTSanglocVaDanhGiaDD: ITTSanglocVaDanhGiaDD = { id: 123 };
        expectedResult = service.addTTSanglocVaDanhGiaDDToCollectionIfMissing([], tTSanglocVaDanhGiaDD);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(tTSanglocVaDanhGiaDD);
      });

      it('should not add a TTSanglocVaDanhGiaDD to an array that contains it', () => {
        const tTSanglocVaDanhGiaDD: ITTSanglocVaDanhGiaDD = { id: 123 };
        const tTSanglocVaDanhGiaDDCollection: ITTSanglocVaDanhGiaDD[] = [
          {
            ...tTSanglocVaDanhGiaDD,
          },
          { id: 456 },
        ];
        expectedResult = service.addTTSanglocVaDanhGiaDDToCollectionIfMissing(tTSanglocVaDanhGiaDDCollection, tTSanglocVaDanhGiaDD);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a TTSanglocVaDanhGiaDD to an array that doesn't contain it", () => {
        const tTSanglocVaDanhGiaDD: ITTSanglocVaDanhGiaDD = { id: 123 };
        const tTSanglocVaDanhGiaDDCollection: ITTSanglocVaDanhGiaDD[] = [{ id: 456 }];
        expectedResult = service.addTTSanglocVaDanhGiaDDToCollectionIfMissing(tTSanglocVaDanhGiaDDCollection, tTSanglocVaDanhGiaDD);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(tTSanglocVaDanhGiaDD);
      });

      it('should add only unique TTSanglocVaDanhGiaDD to an array', () => {
        const tTSanglocVaDanhGiaDDArray: ITTSanglocVaDanhGiaDD[] = [{ id: 123 }, { id: 456 }, { id: 8565 }];
        const tTSanglocVaDanhGiaDDCollection: ITTSanglocVaDanhGiaDD[] = [{ id: 123 }];
        expectedResult = service.addTTSanglocVaDanhGiaDDToCollectionIfMissing(tTSanglocVaDanhGiaDDCollection, ...tTSanglocVaDanhGiaDDArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const tTSanglocVaDanhGiaDD: ITTSanglocVaDanhGiaDD = { id: 123 };
        const tTSanglocVaDanhGiaDD2: ITTSanglocVaDanhGiaDD = { id: 456 };
        expectedResult = service.addTTSanglocVaDanhGiaDDToCollectionIfMissing([], tTSanglocVaDanhGiaDD, tTSanglocVaDanhGiaDD2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(tTSanglocVaDanhGiaDD);
        expect(expectedResult).toContain(tTSanglocVaDanhGiaDD2);
      });

      it('should accept null and undefined values', () => {
        const tTSanglocVaDanhGiaDD: ITTSanglocVaDanhGiaDD = { id: 123 };
        expectedResult = service.addTTSanglocVaDanhGiaDDToCollectionIfMissing([], null, tTSanglocVaDanhGiaDD, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(tTSanglocVaDanhGiaDD);
      });

      it('should return initial array if no TTSanglocVaDanhGiaDD is added', () => {
        const tTSanglocVaDanhGiaDDCollection: ITTSanglocVaDanhGiaDD[] = [{ id: 123 }];
        expectedResult = service.addTTSanglocVaDanhGiaDDToCollectionIfMissing(tTSanglocVaDanhGiaDDCollection, undefined, null);
        expect(expectedResult).toEqual(tTSanglocVaDanhGiaDDCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
