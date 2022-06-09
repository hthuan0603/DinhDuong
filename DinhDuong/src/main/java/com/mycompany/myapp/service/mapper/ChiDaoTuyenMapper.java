package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.ChiDaoTuyen;
import com.mycompany.myapp.domain.LyDoCongTac;
import com.mycompany.myapp.domain.NhanVienTiepNhan;
import com.mycompany.myapp.service.dto.ChiDaoTuyenDTO;
import com.mycompany.myapp.service.dto.LyDoCongTacDTO;
import com.mycompany.myapp.service.dto.NhanVienTiepNhanDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ChiDaoTuyen} and its DTO {@link ChiDaoTuyenDTO}.
 */
@Mapper(componentModel = "spring")
public interface ChiDaoTuyenMapper extends EntityMapper<ChiDaoTuyenDTO, ChiDaoTuyen> {
    @Mapping(target = "lyDoCongTac", source = "lyDoCongTac", qualifiedByName = "lyDoCongTacId")
    @Mapping(target = "nhanVienTiepNhan", source = "nhanVienTiepNhan", qualifiedByName = "nhanVienTiepNhanId")
    ChiDaoTuyenDTO toDto(ChiDaoTuyen s);

    @Named("lyDoCongTacId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    LyDoCongTacDTO toDtoLyDoCongTacId(LyDoCongTac lyDoCongTac);

    @Named("nhanVienTiepNhanId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    NhanVienTiepNhanDTO toDtoNhanVienTiepNhanId(NhanVienTiepNhan nhanVienTiepNhan);
}
