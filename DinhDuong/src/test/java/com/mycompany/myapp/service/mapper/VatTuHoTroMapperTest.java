package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VatTuHoTroMapperTest {

    private VatTuHoTroMapper vatTuHoTroMapper;

    @BeforeEach
    public void setUp() {
        vatTuHoTroMapper = new VatTuHoTroMapperImpl();
    }
}
