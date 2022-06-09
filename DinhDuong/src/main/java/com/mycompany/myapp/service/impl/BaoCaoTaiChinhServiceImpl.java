package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.BaoCaoTaiChinh;
import com.mycompany.myapp.repository.BaoCaoTaiChinhRepository;
import com.mycompany.myapp.service.BaoCaoTaiChinhService;
import com.mycompany.myapp.service.dto.BaoCaoTaiChinhDTO;
import com.mycompany.myapp.service.mapper.BaoCaoTaiChinhMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link BaoCaoTaiChinh}.
 */
@Service
@Transactional
public class BaoCaoTaiChinhServiceImpl implements BaoCaoTaiChinhService {

    private final Logger log = LoggerFactory.getLogger(BaoCaoTaiChinhServiceImpl.class);

    private final BaoCaoTaiChinhRepository baoCaoTaiChinhRepository;

    private final BaoCaoTaiChinhMapper baoCaoTaiChinhMapper;

    public BaoCaoTaiChinhServiceImpl(BaoCaoTaiChinhRepository baoCaoTaiChinhRepository, BaoCaoTaiChinhMapper baoCaoTaiChinhMapper) {
        this.baoCaoTaiChinhRepository = baoCaoTaiChinhRepository;
        this.baoCaoTaiChinhMapper = baoCaoTaiChinhMapper;
    }

    @Override
    public BaoCaoTaiChinhDTO save(BaoCaoTaiChinhDTO baoCaoTaiChinhDTO) {
        log.debug("Request to save BaoCaoTaiChinh : {}", baoCaoTaiChinhDTO);
        BaoCaoTaiChinh baoCaoTaiChinh = baoCaoTaiChinhMapper.toEntity(baoCaoTaiChinhDTO);
        baoCaoTaiChinh = baoCaoTaiChinhRepository.save(baoCaoTaiChinh);
        return baoCaoTaiChinhMapper.toDto(baoCaoTaiChinh);
    }

    @Override
    public BaoCaoTaiChinhDTO update(BaoCaoTaiChinhDTO baoCaoTaiChinhDTO) {
        log.debug("Request to save BaoCaoTaiChinh : {}", baoCaoTaiChinhDTO);
        BaoCaoTaiChinh baoCaoTaiChinh = baoCaoTaiChinhMapper.toEntity(baoCaoTaiChinhDTO);
        baoCaoTaiChinh = baoCaoTaiChinhRepository.save(baoCaoTaiChinh);
        return baoCaoTaiChinhMapper.toDto(baoCaoTaiChinh);
    }

    @Override
    public Optional<BaoCaoTaiChinhDTO> partialUpdate(BaoCaoTaiChinhDTO baoCaoTaiChinhDTO) {
        log.debug("Request to partially update BaoCaoTaiChinh : {}", baoCaoTaiChinhDTO);

        return baoCaoTaiChinhRepository
            .findById(baoCaoTaiChinhDTO.getId())
            .map(existingBaoCaoTaiChinh -> {
                baoCaoTaiChinhMapper.partialUpdate(existingBaoCaoTaiChinh, baoCaoTaiChinhDTO);

                return existingBaoCaoTaiChinh;
            })
            .map(baoCaoTaiChinhRepository::save)
            .map(baoCaoTaiChinhMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BaoCaoTaiChinhDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BaoCaoTaiChinhs");
        return baoCaoTaiChinhRepository.findAll(pageable).map(baoCaoTaiChinhMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BaoCaoTaiChinhDTO> findOne(Long id) {
        log.debug("Request to get BaoCaoTaiChinh : {}", id);
        return baoCaoTaiChinhRepository.findById(id).map(baoCaoTaiChinhMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BaoCaoTaiChinh : {}", id);
        baoCaoTaiChinhRepository.deleteById(id);
    }
}
