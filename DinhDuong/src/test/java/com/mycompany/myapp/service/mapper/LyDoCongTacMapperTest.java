package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LyDoCongTacMapperTest {

    private LyDoCongTacMapper lyDoCongTacMapper;

    @BeforeEach
    public void setUp() {
        lyDoCongTacMapper = new LyDoCongTacMapperImpl();
    }
}
