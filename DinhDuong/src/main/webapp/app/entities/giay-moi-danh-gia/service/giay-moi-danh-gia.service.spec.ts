import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IGiayMoiDanhGia, GiayMoiDanhGia } from '../giay-moi-danh-gia.model';

import { GiayMoiDanhGiaService } from './giay-moi-danh-gia.service';

describe('GiayMoiDanhGia Service', () => {
  let service: GiayMoiDanhGiaService;
  let httpMock: HttpTestingController;
  let elemDefault: IGiayMoiDanhGia;
  let expectedResult: IGiayMoiDanhGia | IGiayMoiDanhGia[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(GiayMoiDanhGiaService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      maBN: 'AAAAAAA',
      maNguoiTao: 'AAAAAAA',
      thoiGianChiDinh: currentDate,
      chuanDoan: 'AAAAAAA',
      chuanDoanPhu: 'AAAAAAA',
      guiTu: 'AAAAAAA',
      guiDen: 'AAAAAAA',
      moi: 'AAAAAAA',
      noiDungHoiChuan: 'AAAAAAA',
      hoiChuanLan: 0,
      thoiGianHoiChuan: currentDate,
      trangThai: false,
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          thoiGianChiDinh: currentDate.format(DATE_TIME_FORMAT),
          thoiGianHoiChuan: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a GiayMoiDanhGia', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          thoiGianChiDinh: currentDate.format(DATE_TIME_FORMAT),
          thoiGianHoiChuan: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          thoiGianChiDinh: currentDate,
          thoiGianHoiChuan: currentDate,
        },
        returnedFromService
      );

      service.create(new GiayMoiDanhGia()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a GiayMoiDanhGia', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          maBN: 'BBBBBB',
          maNguoiTao: 'BBBBBB',
          thoiGianChiDinh: currentDate.format(DATE_TIME_FORMAT),
          chuanDoan: 'BBBBBB',
          chuanDoanPhu: 'BBBBBB',
          guiTu: 'BBBBBB',
          guiDen: 'BBBBBB',
          moi: 'BBBBBB',
          noiDungHoiChuan: 'BBBBBB',
          hoiChuanLan: 1,
          thoiGianHoiChuan: currentDate.format(DATE_TIME_FORMAT),
          trangThai: true,
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          thoiGianChiDinh: currentDate,
          thoiGianHoiChuan: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a GiayMoiDanhGia', () => {
      const patchObject = Object.assign(
        {
          maNguoiTao: 'BBBBBB',
          chuanDoan: 'BBBBBB',
        },
        new GiayMoiDanhGia()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          thoiGianChiDinh: currentDate,
          thoiGianHoiChuan: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of GiayMoiDanhGia', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          maBN: 'BBBBBB',
          maNguoiTao: 'BBBBBB',
          thoiGianChiDinh: currentDate.format(DATE_TIME_FORMAT),
          chuanDoan: 'BBBBBB',
          chuanDoanPhu: 'BBBBBB',
          guiTu: 'BBBBBB',
          guiDen: 'BBBBBB',
          moi: 'BBBBBB',
          noiDungHoiChuan: 'BBBBBB',
          hoiChuanLan: 1,
          thoiGianHoiChuan: currentDate.format(DATE_TIME_FORMAT),
          trangThai: true,
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          thoiGianChiDinh: currentDate,
          thoiGianHoiChuan: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a GiayMoiDanhGia', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addGiayMoiDanhGiaToCollectionIfMissing', () => {
      it('should add a GiayMoiDanhGia to an empty array', () => {
        const giayMoiDanhGia: IGiayMoiDanhGia = { id: 123 };
        expectedResult = service.addGiayMoiDanhGiaToCollectionIfMissing([], giayMoiDanhGia);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(giayMoiDanhGia);
      });

      it('should not add a GiayMoiDanhGia to an array that contains it', () => {
        const giayMoiDanhGia: IGiayMoiDanhGia = { id: 123 };
        const giayMoiDanhGiaCollection: IGiayMoiDanhGia[] = [
          {
            ...giayMoiDanhGia,
          },
          { id: 456 },
        ];
        expectedResult = service.addGiayMoiDanhGiaToCollectionIfMissing(giayMoiDanhGiaCollection, giayMoiDanhGia);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a GiayMoiDanhGia to an array that doesn't contain it", () => {
        const giayMoiDanhGia: IGiayMoiDanhGia = { id: 123 };
        const giayMoiDanhGiaCollection: IGiayMoiDanhGia[] = [{ id: 456 }];
        expectedResult = service.addGiayMoiDanhGiaToCollectionIfMissing(giayMoiDanhGiaCollection, giayMoiDanhGia);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(giayMoiDanhGia);
      });

      it('should add only unique GiayMoiDanhGia to an array', () => {
        const giayMoiDanhGiaArray: IGiayMoiDanhGia[] = [{ id: 123 }, { id: 456 }, { id: 94383 }];
        const giayMoiDanhGiaCollection: IGiayMoiDanhGia[] = [{ id: 123 }];
        expectedResult = service.addGiayMoiDanhGiaToCollectionIfMissing(giayMoiDanhGiaCollection, ...giayMoiDanhGiaArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const giayMoiDanhGia: IGiayMoiDanhGia = { id: 123 };
        const giayMoiDanhGia2: IGiayMoiDanhGia = { id: 456 };
        expectedResult = service.addGiayMoiDanhGiaToCollectionIfMissing([], giayMoiDanhGia, giayMoiDanhGia2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(giayMoiDanhGia);
        expect(expectedResult).toContain(giayMoiDanhGia2);
      });

      it('should accept null and undefined values', () => {
        const giayMoiDanhGia: IGiayMoiDanhGia = { id: 123 };
        expectedResult = service.addGiayMoiDanhGiaToCollectionIfMissing([], null, giayMoiDanhGia, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(giayMoiDanhGia);
      });

      it('should return initial array if no GiayMoiDanhGia is added', () => {
        const giayMoiDanhGiaCollection: IGiayMoiDanhGia[] = [{ id: 123 }];
        expectedResult = service.addGiayMoiDanhGiaToCollectionIfMissing(giayMoiDanhGiaCollection, undefined, null);
        expect(expectedResult).toEqual(giayMoiDanhGiaCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
