jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { BaoCaoTaiChinhService } from '../service/bao-cao-tai-chinh.service';

import { BaoCaoTaiChinhDeleteDialogComponent } from './bao-cao-tai-chinh-delete-dialog.component';

describe('BaoCaoTaiChinh Management Delete Component', () => {
  let comp: BaoCaoTaiChinhDeleteDialogComponent;
  let fixture: ComponentFixture<BaoCaoTaiChinhDeleteDialogComponent>;
  let service: BaoCaoTaiChinhService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [BaoCaoTaiChinhDeleteDialogComponent],
      providers: [NgbActiveModal],
    })
      .overrideTemplate(BaoCaoTaiChinhDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(BaoCaoTaiChinhDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(BaoCaoTaiChinhService);
    mockActiveModal = TestBed.inject(NgbActiveModal);
  });

  describe('confirmDelete', () => {
    it('Should call delete service on confirmDelete', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(service, 'delete').mockReturnValue(of(new HttpResponse({ body: {} })));

        // WHEN
        comp.confirmDelete(123);
        tick();

        // THEN
        expect(service.delete).toHaveBeenCalledWith(123);
        expect(mockActiveModal.close).toHaveBeenCalledWith('deleted');
      })
    ));

    it('Should not call delete service on clear', () => {
      // GIVEN
      jest.spyOn(service, 'delete');

      // WHEN
      comp.cancel();

      // THEN
      expect(service.delete).not.toHaveBeenCalled();
      expect(mockActiveModal.close).not.toHaveBeenCalled();
      expect(mockActiveModal.dismiss).toHaveBeenCalled();
    });
  });
});
