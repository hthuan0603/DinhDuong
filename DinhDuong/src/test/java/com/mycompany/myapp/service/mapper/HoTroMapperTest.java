package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HoTroMapperTest {

    private HoTroMapper hoTroMapper;

    @BeforeEach
    public void setUp() {
        hoTroMapper = new HoTroMapperImpl();
    }
}
