import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IThuoc, Thuoc } from '../thuoc.model';

import { ThuocService } from './thuoc.service';

describe('Thuoc Service', () => {
  let service: ThuocService;
  let httpMock: HttpTestingController;
  let elemDefault: IThuoc;
  let expectedResult: IThuoc | IThuoc[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ThuocService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      tenThuoc: 'AAAAAAA',
      duongDung: 'AAAAAAA',
      soLuong: 0,
      cachDung: 'AAAAAAA',
      hoatChat: 'AAAAAAA',
      dVT: 'AAAAAAA',
      donGia: 0,
      thanhTien: 0,
      loaiTTCu: 'AAAAAAA',
      loaiTTMoi: 'AAAAAAA',
      lieuDung: 'AAAAAAA',
      kS: 'AAAAAAA',
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

    it('should create a Thuoc', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new Thuoc()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Thuoc', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          tenThuoc: 'BBBBBB',
          duongDung: 'BBBBBB',
          soLuong: 1,
          cachDung: 'BBBBBB',
          hoatChat: 'BBBBBB',
          dVT: 'BBBBBB',
          donGia: 1,
          thanhTien: 1,
          loaiTTCu: 'BBBBBB',
          loaiTTMoi: 'BBBBBB',
          lieuDung: 'BBBBBB',
          kS: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Thuoc', () => {
      const patchObject = Object.assign(
        {
          tenThuoc: 'BBBBBB',
          soLuong: 1,
          hoatChat: 'BBBBBB',
          dVT: 'BBBBBB',
          donGia: 1,
          loaiTTCu: 'BBBBBB',
          loaiTTMoi: 'BBBBBB',
          lieuDung: 'BBBBBB',
        },
        new Thuoc()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Thuoc', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          tenThuoc: 'BBBBBB',
          duongDung: 'BBBBBB',
          soLuong: 1,
          cachDung: 'BBBBBB',
          hoatChat: 'BBBBBB',
          dVT: 'BBBBBB',
          donGia: 1,
          thanhTien: 1,
          loaiTTCu: 'BBBBBB',
          loaiTTMoi: 'BBBBBB',
          lieuDung: 'BBBBBB',
          kS: 'BBBBBB',
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

    it('should delete a Thuoc', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addThuocToCollectionIfMissing', () => {
      it('should add a Thuoc to an empty array', () => {
        const thuoc: IThuoc = { id: 123 };
        expectedResult = service.addThuocToCollectionIfMissing([], thuoc);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(thuoc);
      });

      it('should not add a Thuoc to an array that contains it', () => {
        const thuoc: IThuoc = { id: 123 };
        const thuocCollection: IThuoc[] = [
          {
            ...thuoc,
          },
          { id: 456 },
        ];
        expectedResult = service.addThuocToCollectionIfMissing(thuocCollection, thuoc);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Thuoc to an array that doesn't contain it", () => {
        const thuoc: IThuoc = { id: 123 };
        const thuocCollection: IThuoc[] = [{ id: 456 }];
        expectedResult = service.addThuocToCollectionIfMissing(thuocCollection, thuoc);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(thuoc);
      });

      it('should add only unique Thuoc to an array', () => {
        const thuocArray: IThuoc[] = [{ id: 123 }, { id: 456 }, { id: 41995 }];
        const thuocCollection: IThuoc[] = [{ id: 123 }];
        expectedResult = service.addThuocToCollectionIfMissing(thuocCollection, ...thuocArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const thuoc: IThuoc = { id: 123 };
        const thuoc2: IThuoc = { id: 456 };
        expectedResult = service.addThuocToCollectionIfMissing([], thuoc, thuoc2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(thuoc);
        expect(expectedResult).toContain(thuoc2);
      });

      it('should accept null and undefined values', () => {
        const thuoc: IThuoc = { id: 123 };
        expectedResult = service.addThuocToCollectionIfMissing([], null, thuoc, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(thuoc);
      });

      it('should return initial array if no Thuoc is added', () => {
        const thuocCollection: IThuoc[] = [{ id: 123 }];
        expectedResult = service.addThuocToCollectionIfMissing(thuocCollection, undefined, null);
        expect(expectedResult).toEqual(thuocCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
