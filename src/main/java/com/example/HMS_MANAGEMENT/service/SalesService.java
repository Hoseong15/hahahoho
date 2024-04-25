
package com.example.HMS_MANAGEMENT.service;

import com.example.HMS_MANAGEMENT.dto.SalesDto;
import com.example.HMS_MANAGEMENT.entity.SalesEntity;
import com.example.HMS_MANAGEMENT.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SalesService {
    @Autowired
    private SalesRepository salesRepository;


    public void saveSales(SalesDto salesDto){
        SalesEntity salesEntity = new SalesEntity();
        salesEntity.setCut(salesDto.getCut());
        salesEntity.setType(salesDto.getType());
        salesEntity.setCost(salesDto.getCost());
        salesRepository.save(salesEntity);
    }

    public List<SalesEntity> getAllServices(){
        return salesRepository.findAll();
    }
    public List<SalesDto> getService(){
        List<SalesEntity> salesEntities = salesRepository.findAll();
        List<SalesDto> services = new ArrayList<>();

        for(SalesEntity entity : salesEntities){
            SalesDto dto = new SalesDto();
            dto.setCut(entity.getCut());
            dto.setType(entity.getType());
            dto.setCost(entity.getCost());
        }
        return services;
    }

    public List<String> getOptions() {
        return Arrays.asList("남성커트", "여성커트", "남성포인트펌", "여성일반펌", "셋팅펌", "뿌리염색", "전체염색", "두피케어");
    }


}
