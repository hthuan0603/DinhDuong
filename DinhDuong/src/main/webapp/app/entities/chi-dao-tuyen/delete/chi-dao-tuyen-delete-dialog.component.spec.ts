jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ChiDaoTuyenService } from '../service/chi-dao-tuyen.service';

import { ChiDaoTuyenDeleteDialogComponent } from './chi-dao-tuyen-delete-dialog.component';

describe('ChiDaoTuyen Management Delete Component', () => {
  let comp: ChiDaoTuyenDeleteDialogComponent;
  let fixture: ComponentFixture<ChiDaoTuyenDeleteDialogComponent>;
  let service: ChiDaoTuyenService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [ChiDaoTuyenDeleteDialogComponent],
      providers: [NgbActiveModal],
    })
      .overrideTemplate(ChiDaoTuyenDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(ChiDaoTuyenDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(ChiDaoTuyenService);
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
