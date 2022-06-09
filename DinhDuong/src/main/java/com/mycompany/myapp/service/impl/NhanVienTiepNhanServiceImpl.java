package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.NhanVienTiepNhan;
import com.mycompany.myapp.repository.NhanVienTiepNhanRepository;
import com.mycompany.myapp.service.NhanVienTiepNhanService;
import com.mycompany.myapp.service.dto.NhanVienTiepNhanDTO;
import com.mycompany.myapp.service.mapper.NhanVienTiepNhanMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NhanVienTiepNhan}.
 */
@Service
@Transactional
public class NhanVienTiepNhanServiceImpl implements NhanVienTiepNhanService {

    private final Logger log = LoggerFactory.getLogger(NhanVienTiepNhanServiceImpl.class);

    private final NhanVienTiepNhanRepository nhanVienTiepNhanRepository;

    private final NhanVienTiepNhanMapper nhanVienTiepNhanMapper;

    public NhanVienTiepNhanServiceImpl(
        NhanVienTiepNhanRepository nhanVienTiepNhanRepository,
        NhanVienTiepNhanMapper nhanVienTiepNhanMapper
    ) {
        this.nhanVienTiepNhanRepository = nhanVienTiepNhanRepository;
        this.nhanVienTiepNhanMapper = nhanVienTiepNhanMapper;
    }

    @Override
    public NhanVienTiepNhanDTO save(NhanVienTiepNhanDTO nhanVienTiepNhanDTO) {
        log.debug("Request to save NhanVienTiepNhan : {}", nhanVienTiepNhanDTO);
        NhanVienTiepNhan nhanVienTiepNhan = nhanVienTiepNhanMapper.toEntity(nhanVienTiepNhanDTO);
        nhanVienTiepNhan = nhanVienTiepNhanRepository.save(nhanVienTiepNhan);
        return nhanVienTiepNhanMapper.toDto(nhanVienTiepNhan);
    }

    @Override
    public NhanVienTiepNhanDTO update(NhanVienTiepNhanDTO nhanVienTiepNhanDTO) {
        log.debug("Request to save NhanVienTiepNhan : {}", nhanVienTiepNhanDTO);
        NhanVienTiepNhan nhanVienTiepNhan = nhanVienTiepNhanMapper.toEntity(nhanVienTiepNhanDTO);
        nhanVienTiepNhan = nhanVienTiepNhanRepository.save(nhanVienTiepNhan);
        return nhanVienTiepNhanMapper.toDto(nhanVienTiepNhan);
    }

    @Override
    public Optional<NhanVienTiepNhanDTO> partialUpdate(NhanVienTiepNhanDTO nhanVienTiepNhanDTO) {
        log.debug("Request to partially update NhanVienTiepNhan : {}", nhanVienTiepNhanDTO);

        return nhanVienTiepNhanRepository
            .findById(nhanVienTiepNhanDTO.getId())
            .map(existingNhanVienTiepNhan -> {
                nhanVienTiepNhanMapper.partialUpdate(existingNhanVienTiepNhan, nhanVienTiepNhanDTO);

                return existingNhanVienTiepNhan;
            })
            .map(nhanVienTiepNhanRepository::save)
            .map(nhanVienTiepNhanMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NhanVienTiepNhanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NhanVienTiepNhans");
        return nhanVienTiepNhanRepository.findAll(pageable).map(nhanVienTiepNhanMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<NhanVienTiepNhanDTO> findOne(Long id) {
        log.debug("Request to get NhanVienTiepNhan : {}", id);
        return nhanVienTiepNhanRepository.findById(id).map(nhanVienTiepNhanMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete NhanVienTiepNhan : {}", id);
        nhanVienTiepNhanRepository.deleteById(id);
    }
}
