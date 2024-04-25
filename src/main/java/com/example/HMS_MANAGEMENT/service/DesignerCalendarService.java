package com.example.HMS_MANAGEMENT.service;

import com.example.HMS_MANAGEMENT.dto.DesignerCalendarDto;
import com.example.HMS_MANAGEMENT.entity.DesignerCalendarEntity;
import com.example.HMS_MANAGEMENT.repository.DesignerCalendarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class DesignerCalendarService {
    private final DesignerCalendarRepo designerCalendarRepo;

    @Autowired
    public DesignerCalendarService(DesignerCalendarRepo designerCalendarRepo){
        this.designerCalendarRepo = designerCalendarRepo;
    }

    public DesignerCalendarEntity calendarSave(DesignerCalendarDto calendarDto){
        DesignerCalendarEntity calendar = new DesignerCalendarEntity();
        calendar.setTitle(calendarDto.getTitle());
        calendar.setStart(calendarDto.getStart());
        calendar.setEnd(calendarDto.getEnd());
        calendar.setAllDay(calendarDto.isAllDay());
        calendar.setEventType(calendarDto.getEventType()); // eventType 설정
        return designerCalendarRepo.save(calendar);
    }

    public DesignerCalendarEntity calendarUpdate(DesignerCalendarDto calendarDto) {
        Long eventId = calendarDto.getId();

        Optional<DesignerCalendarEntity> optionalEvent = designerCalendarRepo.findById(eventId);

        if (!optionalEvent.isPresent()) {
            throw new EntityNotFoundException("해당 ID에 해당하는 이벤트를 찾을 수 없습니다: " + eventId);
        }

        DesignerCalendarEntity event = optionalEvent.get();
        event.setTitle(calendarDto.getTitle());
        event.setStart(calendarDto.getStart());
        event.setEnd(calendarDto.getEnd());
        event.setAllDay(calendarDto.isAllDay());
        event.setEventType(calendarDto.getEventType()); // eventType 설정

        // 업데이트된 이벤트를 저장하고 반환합니다.
        return designerCalendarRepo.save(event);
    }

    public void calendarDelete(Long id) {

        Optional<DesignerCalendarEntity> optionalEvent = designerCalendarRepo.findById(id);

        if (!optionalEvent.isPresent()) {
            throw new EntityNotFoundException("해당 ID에 해당하는 이벤트를 찾을 수 없습니다: " + id);
        }

        designerCalendarRepo.deleteById(id);
    }
}