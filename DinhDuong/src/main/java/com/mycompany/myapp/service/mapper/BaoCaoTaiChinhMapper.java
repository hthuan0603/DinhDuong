package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.BaoCaoTaiChinh;
import com.mycompany.myapp.domain.ChiDaoTuyen;
import com.mycompany.myapp.service.dto.BaoCaoTaiChinhDTO;
import com.mycompany.myapp.service.dto.ChiDaoTuyenDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BaoCaoTaiChinh} and its DTO {@link BaoCaoTaiChinhDTO}.
 */
@Mapper(componentModel = "spring")
public interface BaoCaoTaiChinhMapper extends EntityMapper<BaoCaoTaiChinhDTO, BaoCaoTaiChinh> {
    @Mapping(target = "chiDaoTuyen", source = "chiDaoTuyen", qualifiedByName = "chiDaoTuyenId")
    BaoCaoTaiChinhDTO toDto(BaoCaoTaiChinh s);

    @Named("chiDaoTuyenId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ChiDaoTuyenDTO toDtoChiDaoTuyenId(ChiDaoTuyen chiDaoTuyen);
}
