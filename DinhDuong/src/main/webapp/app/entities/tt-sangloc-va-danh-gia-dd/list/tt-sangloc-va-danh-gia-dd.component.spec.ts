import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { TTSanglocVaDanhGiaDDService } from '../service/tt-sangloc-va-danh-gia-dd.service';

import { TTSanglocVaDanhGiaDDComponent } from './tt-sangloc-va-danh-gia-dd.component';

describe('TTSanglocVaDanhGiaDD Management Component', () => {
  let comp: TTSanglocVaDanhGiaDDComponent;
  let fixture: ComponentFixture<TTSanglocVaDanhGiaDDComponent>;
  let service: TTSanglocVaDanhGiaDDService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [TTSanglocVaDanhGiaDDComponent],
    })
      .overrideTemplate(TTSanglocVaDanhGiaDDComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TTSanglocVaDanhGiaDDComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(TTSanglocVaDanhGiaDDService);

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
    expect(comp.tTSanglocVaDanhGiaDDS?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});
