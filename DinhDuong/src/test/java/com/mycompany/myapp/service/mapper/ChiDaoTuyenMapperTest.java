package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChiDaoTuyenMapperTest {

    private ChiDaoTuyenMapper chiDaoTuyenMapper;

    @BeforeEach
    public void setUp() {
        chiDaoTuyenMapper = new ChiDaoTuyenMapperImpl();
    }
}
