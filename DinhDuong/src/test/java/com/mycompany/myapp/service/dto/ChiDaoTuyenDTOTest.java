package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ChiDaoTuyenDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChiDaoTuyenDTO.class);
        ChiDaoTuyenDTO chiDaoTuyenDTO1 = new ChiDaoTuyenDTO();
        chiDaoTuyenDTO1.setId(1L);
        ChiDaoTuyenDTO chiDaoTuyenDTO2 = new ChiDaoTuyenDTO();
        assertThat(chiDaoTuyenDTO1).isNotEqualTo(chiDaoTuyenDTO2);
        chiDaoTuyenDTO2.setId(chiDaoTuyenDTO1.getId());
        assertThat(chiDaoTuyenDTO1).isEqualTo(chiDaoTuyenDTO2);
        chiDaoTuyenDTO2.setId(2L);
        assertThat(chiDaoTuyenDTO1).isNotEqualTo(chiDaoTuyenDTO2);
        chiDaoTuyenDTO1.setId(null);
        assertThat(chiDaoTuyenDTO1).isNotEqualTo(chiDaoTuyenDTO2);
    }
}
