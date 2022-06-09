package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.ChiDaoTuyen;
import com.mycompany.myapp.repository.ChiDaoTuyenRepository;
import com.mycompany.myapp.service.ChiDaoTuyenService;
import com.mycompany.myapp.service.dto.ChiDaoTuyenDTO;
import com.mycompany.myapp.service.mapper.ChiDaoTuyenMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ChiDaoTuyen}.
 */
@Service
@Transactional
public class ChiDaoTuyenServiceImpl implements ChiDaoTuyenService {

    private final Logger log = LoggerFactory.getLogger(ChiDaoTuyenServiceImpl.class);

    private final ChiDaoTuyenRepository chiDaoTuyenRepository;

    private final ChiDaoTuyenMapper chiDaoTuyenMapper;

    public ChiDaoTuyenServiceImpl(ChiDaoTuyenRepository chiDaoTuyenRepository, ChiDaoTuyenMapper chiDaoTuyenMapper) {
        this.chiDaoTuyenRepository = chiDaoTuyenRepository;
        this.chiDaoTuyenMapper = chiDaoTuyenMapper;
    }

    @Override
    public ChiDaoTuyenDTO save(ChiDaoTuyenDTO chiDaoTuyenDTO) {
        log.debug("Request to save ChiDaoTuyen : {}", chiDaoTuyenDTO);
        ChiDaoTuyen chiDaoTuyen = chiDaoTuyenMapper.toEntity(chiDaoTuyenDTO);
        chiDaoTuyen = chiDaoTuyenRepository.save(chiDaoTuyen);
        return chiDaoTuyenMapper.toDto(chiDaoTuyen);
    }

    @Override
    public ChiDaoTuyenDTO update(ChiDaoTuyenDTO chiDaoTuyenDTO) {
        log.debug("Request to save ChiDaoTuyen : {}", chiDaoTuyenDTO);
        ChiDaoTuyen chiDaoTuyen = chiDaoTuyenMapper.toEntity(chiDaoTuyenDTO);
        chiDaoTuyen = chiDaoTuyenRepository.save(chiDaoTuyen);
        return chiDaoTuyenMapper.toDto(chiDaoTuyen);
    }

    @Override
    public Optional<ChiDaoTuyenDTO> partialUpdate(ChiDaoTuyenDTO chiDaoTuyenDTO) {
        log.debug("Request to partially update ChiDaoTuyen : {}", chiDaoTuyenDTO);

        return chiDaoTuyenRepository
            .findById(chiDaoTuyenDTO.getId())
            .map(existingChiDaoTuyen -> {
                chiDaoTuyenMapper.partialUpdate(existingChiDaoTuyen, chiDaoTuyenDTO);

                return existingChiDaoTuyen;
            })
            .map(chiDaoTuyenRepository::save)
            .map(chiDaoTuyenMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ChiDaoTuyenDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ChiDaoTuyens");
        return chiDaoTuyenRepository.findAll(pageable).map(chiDaoTuyenMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ChiDaoTuyenDTO> findOne(Long id) {
        log.debug("Request to get ChiDaoTuyen : {}", id);
        return chiDaoTuyenRepository.findById(id).map(chiDaoTuyenMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ChiDaoTuyen : {}", id);
        chiDaoTuyenRepository.deleteById(id);
    }
}
