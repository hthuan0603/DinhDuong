package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NhanVienTiepNhanMapperTest {

    private NhanVienTiepNhanMapper nhanVienTiepNhanMapper;

    @BeforeEach
    public void setUp() {
        nhanVienTiepNhanMapper = new NhanVienTiepNhanMapperImpl();
    }
}
