
package com.example.HMS_MANAGEMENT.service;

import com.example.HMS_MANAGEMENT.constent.InvenStatus;
import com.example.HMS_MANAGEMENT.dto.InvenDto;
import com.example.HMS_MANAGEMENT.entity.InvenEntity;
import com.example.HMS_MANAGEMENT.repository.InvenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class InvenService {

    private final InvenRepo invenRepo;


    public InvenEntity invenSave(InvenDto dto){

        InvenEntity inven = new InvenEntity();
        inven.setIdCode(dto.getIdCode());
        inven.setCount(dto.getCount());
        inven.setItemL(dto.getItemL());
        inven.setIdClass(dto.getIdClass());
        inven.setItemNm(dto.getItemNm());
        inven.setInvenStatus(dto.getInvenStatus());
        inven.setDate(LocalDate.now());
        inven.setTime(LocalTime.now());
        inven.setCash(dto.getCash());
        inven.setBuyCash(dto.getBuyCash());
        inven.setSellCash(dto.getSellCash());
        inven = invenRepo.save(inven);

        return inven;

    }

    public void processBasic(InvenDto invenDto){
        if (invenDto != null && invenDto.getInvenStatus() != null) {

            if( invenDto.getInvenStatus() == InvenStatus.BASIC){
                invenSave(invenDto);
            }else{
                InvenEntity inven = invenRepo.findById(invenDto.getId()).get();

                switch (invenDto.getInvenStatus()) {
                    case BUY:
                        inven.setCount(inven.getCount()+invenDto.getCount());
                        inven.setBuyCash(inven.getCount()*inven.getCash());
                        break;
                    case SELL:
                        inven.setCount(inven.getCount()-invenDto.getCount());
                        inven.setSellCash(invenDto.getCount()*inven.getCash());
                        break;
                    default:
                        // 처리할 수 없는 상태입니다. 오류 처리 로직 추가
                        break;
                }


                invenSave(invenDto);
            }

        } else {
            // 유효하지 않은 입력입니다. 오류 처리 로직 추가

        }

    }

    public List<InvenDto> getAllBasicItems() {
        List<InvenEntity> basicItems = invenRepo.findAllByInvenStatusOrderByTimeDesc(InvenStatus.BASIC);
        return basicItems.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<InvenDto> getAllBuyItems() {
        List<InvenEntity> buyItems = invenRepo.findAllByInvenStatusOrderByTimeDesc(InvenStatus.BUY);
        return buyItems.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<InvenDto> getAllSellItems() {
        List<InvenEntity> sellItems = invenRepo.findAllByInvenStatusOrderByTimeDesc(InvenStatus.SELL);
        return sellItems.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private InvenDto convertToDto(InvenEntity entity) {
        InvenDto dto = new InvenDto();
        dto.setId(entity.getId());
        dto.setItemNm(entity.getItemNm());
        dto.setInvenStatus(entity.getInvenStatus());
        dto.setIdCode(entity.getIdCode());
        dto.setIdClass(entity.getIdClass());
        dto.setItemL(entity.getItemL());
        dto.setCount(entity.getCount());
        dto.setDate(entity.getDate());
        dto.setTime(entity.getTime());
        dto.setCash(entity.getCash());
        dto.setBuyCash(entity.getBuyCash());
        dto.setSellCash(entity.getSellCash());
        return dto;
    }

    public void deleteInven(Long invenId){
        InvenEntity invenEntity = invenRepo.findById(invenId).get();
        invenRepo.delete(invenEntity);
    }


}