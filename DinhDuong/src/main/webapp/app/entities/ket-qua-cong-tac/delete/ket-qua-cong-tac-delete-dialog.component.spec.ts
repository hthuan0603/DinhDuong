jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { KetQuaCongTacService } from '../service/ket-qua-cong-tac.service';

import { KetQuaCongTacDeleteDialogComponent } from './ket-qua-cong-tac-delete-dialog.component';

describe('KetQuaCongTac Management Delete Component', () => {
  let comp: KetQuaCongTacDeleteDialogComponent;
  let fixture: ComponentFixture<KetQuaCongTacDeleteDialogComponent>;
  let service: KetQuaCongTacService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [KetQuaCongTacDeleteDialogComponent],
      providers: [NgbActiveModal],
    })
      .overrideTemplate(KetQuaCongTacDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(KetQuaCongTacDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(KetQuaCongTacService);
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
