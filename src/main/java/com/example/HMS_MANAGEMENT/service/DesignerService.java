package com.example.HMS_MANAGEMENT.service;

import com.example.HMS_MANAGEMENT.dto.DesignerDto;
import com.example.HMS_MANAGEMENT.entity.DayOffEntity;
import com.example.HMS_MANAGEMENT.entity.DesignerEntity;
import com.example.HMS_MANAGEMENT.repository.DayOffRepo;
import com.example.HMS_MANAGEMENT.repository.DesignerRepo;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DesignerService {

    private final DayOffRepo dayOffRepo;
    private final DesignerRepo designerRepo;

    public DesignerEntity designerSave(DesignerDto dto) {
        DesignerEntity designer = new DesignerEntity();
        designer.setName(dto.getName());
        designer.setTel(dto.getTel());
        designer.setDate(dto.getDate());
        designer.setSal(dto.getSal());
        designer.setSalDate(dto.getSalDate());
        designer = designerRepo.save(designer);
        designer.setAfterTime(LocalTime.parse(dto.getAfterTime()));
        designer.setMorningTime(LocalTime.parse(dto.getMorningTime()));
        designer.setEmail(dto.getEmail());
        saveDayOffs(dto, designer);
        return designer;
    }

    private void saveDayOffs(DesignerDto dto, DesignerEntity designer) {
        List<DayOffEntity> dayOffEntities = new ArrayList<>();
        for (String day : dto.getDayOffList()) {
            DayOffEntity dayOffEntity = new DayOffEntity();
            dayOffEntity.setDay(day);
            dayOffEntity.setDesigner(designer);
            dayOffEntities.add(dayOffEntity);
        }
        dayOffRepo.saveAll(dayOffEntities);
    }

    public List<DesignerDto> getAllDesigners() {
        List<DesignerEntity> designers = designerRepo.findAll();
        return designers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<DesignerDto> getAllDesignersWithDayOff() {
        List<DesignerEntity> designers = designerRepo.findAll();
        List<DesignerDto> designerDtos = new ArrayList<>();
        for (DesignerEntity designerEntity : designers) {
            DesignerDto dto = convertToDto(designerEntity);
            List<String> dayOffs = getDayOffsByDesignerId(designerEntity.getId());
            dto.setDayOffList(dayOffs);
            designerDtos.add(dto);
        }
        return designerDtos;
    }

    public List<String> getDayOffsByDesignerId(Long designerId) {
        List<DayOffEntity> dayOffEntities = dayOffRepo.findByDesignerId(designerId);
        return dayOffEntities.stream()
                .map(DayOffEntity::getDay)
                .collect(Collectors.toList());
    }

    private DesignerDto convertToDto(DesignerEntity designerEntity) {
        DesignerDto dto = new DesignerDto();
        dto.setId(designerEntity.getId());
        dto.setName(designerEntity.getName());
        dto.setTel(designerEntity.getTel());
        dto.setDate(designerEntity.getDate());
        dto.setSal(designerEntity.getSal());
        dto.setSalDate(designerEntity.getSalDate());
        dto.setAfterTime(String.valueOf(designerEntity.getAfterTime()));
        dto.setMorningTime(String.valueOf(designerEntity.getMorningTime()));
        dto.setEmail(designerEntity.getEmail());
        return dto;
    }

    public DesignerDto getDesignerByID(Long id){
        Optional<DesignerEntity> designerEntityOptional = designerRepo.findById(id);
        if (designerEntityOptional.isPresent()) {
            DesignerEntity designerEntity = designerEntityOptional.get();
            DesignerDto designerDto = new DesignerDto();
            // 다른 필드 채우기
            designerDto.setId(designerEntity.getId());
            designerDto.setName(designerEntity.getName());
            designerDto.setTel(designerEntity.getTel());
            designerDto.setDate(designerEntity.getDate());
            designerDto.setSal(designerEntity.getSal());
            designerDto.setSalDate(designerEntity.getSalDate());
            designerDto.setMorningTime(String.valueOf(designerEntity.getMorningTime()));
            designerDto.setAfterTime(String.valueOf(designerEntity.getAfterTime()));
            designerDto.setEmail(designerEntity.getEmail());
            // dayOffList 채우기
            List<String> dayOffList = new ArrayList<>();
            for (DayOffEntity dayOffEntity : designerEntity.getDayOffs()) {
                dayOffList.add(dayOffEntity.getDay());
            }
            designerDto.setDayOffList(dayOffList);
            return designerDto;
        } else {
            // 주어진 ID의 디자이너가 없는 경우 처리
            return null;
        }
        }

}
