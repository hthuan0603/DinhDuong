jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { BenhNhanService } from '../service/benh-nhan.service';

import { BenhNhanDeleteDialogComponent } from './benh-nhan-delete-dialog.component';

describe('BenhNhan Management Delete Component', () => {
  let comp: BenhNhanDeleteDialogComponent;
  let fixture: ComponentFixture<BenhNhanDeleteDialogComponent>;
  let service: BenhNhanService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [BenhNhanDeleteDialogComponent],
      providers: [NgbActiveModal],
    })
      .overrideTemplate(BenhNhanDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(BenhNhanDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(BenhNhanService);
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
