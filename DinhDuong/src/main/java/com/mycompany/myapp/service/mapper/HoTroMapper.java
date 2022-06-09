package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.ChiDaoTuyen;
import com.mycompany.myapp.domain.HoTro;
import com.mycompany.myapp.service.dto.ChiDaoTuyenDTO;
import com.mycompany.myapp.service.dto.HoTroDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link HoTro} and its DTO {@link HoTroDTO}.
 */
@Mapper(componentModel = "spring")
public interface HoTroMapper extends EntityMapper<HoTroDTO, HoTro> {
    @Mapping(target = "chiDaoTuyen", source = "chiDaoTuyen", qualifiedByName = "chiDaoTuyenId")
    HoTroDTO toDto(HoTro s);

    @Named("chiDaoTuyenId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ChiDaoTuyenDTO toDtoChiDaoTuyenId(ChiDaoTuyen chiDaoTuyen);
}
