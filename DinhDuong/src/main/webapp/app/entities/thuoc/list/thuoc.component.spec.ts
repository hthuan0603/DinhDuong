import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { ThuocService } from '../service/thuoc.service';

import { ThuocComponent } from './thuoc.component';

describe('Thuoc Management Component', () => {
  let comp: ThuocComponent;
  let fixture: ComponentFixture<ThuocComponent>;
  let service: ThuocService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [ThuocComponent],
    })
      .overrideTemplate(ThuocComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ThuocComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(ThuocService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.thuocs?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
