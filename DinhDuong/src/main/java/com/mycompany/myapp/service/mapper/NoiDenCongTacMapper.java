package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.ChiDaoTuyen;
import com.mycompany.myapp.domain.NoiDenCongTac;
import com.mycompany.myapp.service.dto.ChiDaoTuyenDTO;
import com.mycompany.myapp.service.dto.NoiDenCongTacDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NoiDenCongTac} and its DTO {@link NoiDenCongTacDTO}.
 */
@Mapper(componentModel = "spring")
public interface NoiDenCongTacMapper extends EntityMapper<NoiDenCongTacDTO, NoiDenCongTac> {
    @Mapping(target = "chiDaoTuyen", source = "chiDaoTuyen", qualifiedByName = "chiDaoTuyenId")
    NoiDenCongTacDTO toDto(NoiDenCongTac s);

    @Named("chiDaoTuyenId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ChiDaoTuyenDTO toDtoChiDaoTuyenId(ChiDaoTuyen chiDaoTuyen);
}
