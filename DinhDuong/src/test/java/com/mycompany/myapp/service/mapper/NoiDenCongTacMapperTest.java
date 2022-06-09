package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NoiDenCongTacMapperTest {

    private NoiDenCongTacMapper noiDenCongTacMapper;

    @BeforeEach
    public void setUp() {
        noiDenCongTacMapper = new NoiDenCongTacMapperImpl();
    }
}
