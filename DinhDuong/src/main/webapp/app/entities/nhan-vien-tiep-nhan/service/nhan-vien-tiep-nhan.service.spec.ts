import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { INhanVienTiepNhan, NhanVienTiepNhan } from '../nhan-vien-tiep-nhan.model';

import { NhanVienTiepNhanService } from './nhan-vien-tiep-nhan.service';

describe('NhanVienTiepNhan Service', () => {
  let service: NhanVienTiepNhanService;
  let httpMock: HttpTestingController;
  let elemDefault: INhanVienTiepNhan;
  let expectedResult: INhanVienTiepNhan | INhanVienTiepNhan[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(NhanVienTiepNhanService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      maNhanVien: 0,
      tenNhanVien: 'AAAAAAA',
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

    it('should create a NhanVienTiepNhan', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new NhanVienTiepNhan()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a NhanVienTiepNhan', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          maNhanVien: 1,
          tenNhanVien: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a NhanVienTiepNhan', () => {
      const patchObject = Object.assign(
        {
          maNhanVien: 1,
        },
        new NhanVienTiepNhan()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of NhanVienTiepNhan', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          maNhanVien: 1,
          tenNhanVien: 'BBBBBB',
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

    it('should delete a NhanVienTiepNhan', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addNhanVienTiepNhanToCollectionIfMissing', () => {
      it('should add a NhanVienTiepNhan to an empty array', () => {
        const nhanVienTiepNhan: INhanVienTiepNhan = { id: 123 };
        expectedResult = service.addNhanVienTiepNhanToCollectionIfMissing([], nhanVienTiepNhan);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(nhanVienTiepNhan);
      });

      it('should not add a NhanVienTiepNhan to an array that contains it', () => {
        const nhanVienTiepNhan: INhanVienTiepNhan = { id: 123 };
        const nhanVienTiepNhanCollection: INhanVienTiepNhan[] = [
          {
            ...nhanVienTiepNhan,
          },
          { id: 456 },
        ];
        expectedResult = service.addNhanVienTiepNhanToCollectionIfMissing(nhanVienTiepNhanCollection, nhanVienTiepNhan);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a NhanVienTiepNhan to an array that doesn't contain it", () => {
        const nhanVienTiepNhan: INhanVienTiepNhan = { id: 123 };
        const nhanVienTiepNhanCollection: INhanVienTiepNhan[] = [{ id: 456 }];
        expectedResult = service.addNhanVienTiepNhanToCollectionIfMissing(nhanVienTiepNhanCollection, nhanVienTiepNhan);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(nhanVienTiepNhan);
      });

      it('should add only unique NhanVienTiepNhan to an array', () => {
        const nhanVienTiepNhanArray: INhanVienTiepNhan[] = [{ id: 123 }, { id: 456 }, { id: 23604 }];
        const nhanVienTiepNhanCollection: INhanVienTiepNhan[] = [{ id: 123 }];
        expectedResult = service.addNhanVienTiepNhanToCollectionIfMissing(nhanVienTiepNhanCollection, ...nhanVienTiepNhanArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const nhanVienTiepNhan: INhanVienTiepNhan = { id: 123 };
        const nhanVienTiepNhan2: INhanVienTiepNhan = { id: 456 };
        expectedResult = service.addNhanVienTiepNhanToCollectionIfMissing([], nhanVienTiepNhan, nhanVienTiepNhan2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(nhanVienTiepNhan);
        expect(expectedResult).toContain(nhanVienTiepNhan2);
      });

      it('should accept null and undefined values', () => {
        const nhanVienTiepNhan: INhanVienTiepNhan = { id: 123 };
        expectedResult = service.addNhanVienTiepNhanToCollectionIfMissing([], null, nhanVienTiepNhan, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(nhanVienTiepNhan);
      });

      it('should return initial array if no NhanVienTiepNhan is added', () => {
        const nhanVienTiepNhanCollection: INhanVienTiepNhan[] = [{ id: 123 }];
        expectedResult = service.addNhanVienTiepNhanToCollectionIfMissing(nhanVienTiepNhanCollection, undefined, null);
        expect(expectedResult).toEqual(nhanVienTiepNhanCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
