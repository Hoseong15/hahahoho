package com.example.HMS_MANAGEMENT.control;

import com.example.HMS_MANAGEMENT.dto.DesignerCalendarDto;
import com.example.HMS_MANAGEMENT.entity.DesignerCalendarEntity;
import com.example.HMS_MANAGEMENT.repository.DesignerCalendarRepo;
import com.example.HMS_MANAGEMENT.service.DesignerCalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/designer")
public class DesignerCalendarController {
    private final DesignerCalendarService calendarService;

    @Autowired
    public DesignerCalendarController(DesignerCalendarService calendarService){
        this.calendarService = calendarService;
    }

    @PostMapping("/calendarSave")
    public ResponseEntity<DesignerCalendarEntity> calendarSave(@RequestBody DesignerCalendarDto calendarDto){
        DesignerCalendarEntity savedEvent = calendarService.calendarSave(calendarDto);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    @GetMapping("/calendarUpdate")
    public ResponseEntity<DesignerCalendarEntity> calendarUpdate(@RequestBody DesignerCalendarDto calendarDto){
        DesignerCalendarEntity updatedEvent = calendarService.calendarUpdate(calendarDto);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    @GetMapping("/calendarDelete")
    public ResponseEntity<?> calendarDelete(@PathVariable Long id){
        calendarService.calendarDelete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}