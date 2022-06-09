package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KyThuatHoTroMapperTest {

    private KyThuatHoTroMapper kyThuatHoTroMapper;

    @BeforeEach
    public void setUp() {
        kyThuatHoTroMapper = new KyThuatHoTroMapperImpl();
    }
}
