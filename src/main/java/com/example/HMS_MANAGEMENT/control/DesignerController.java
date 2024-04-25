package com.example.HMS_MANAGEMENT.control;

import com.example.HMS_MANAGEMENT.dto.DayOffDto;
import com.example.HMS_MANAGEMENT.dto.DesignerCalendarDto;
import com.example.HMS_MANAGEMENT.dto.DesignerDto;
import com.example.HMS_MANAGEMENT.entity.DesignerCalendarEntity;
import com.example.HMS_MANAGEMENT.entity.DesignerEntity;
import com.example.HMS_MANAGEMENT.repository.DesignerCalendarRepo;
import com.example.HMS_MANAGEMENT.repository.DesignerRepo;
import com.example.HMS_MANAGEMENT.service.DesignerCalendarService;
import com.example.HMS_MANAGEMENT.service.DesignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class DesignerController {

    private final DesignerService designerService;

    @Autowired
    public DesignerController(DesignerService designerService){
        this.designerService = designerService;
    }

    @GetMapping("/designer")
    public String designerMain(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, Model model){

        List<DesignerDto> designerDtoList = designerService.getAllDesigners();
        Pageable pageable = PageRequest.of(page, 8);
        List<DesignerDto> designerDtoListWithDayOff = designerService.getAllDesignersWithDayOff();
        int maxPage = (int)Math.ceil((double) designerDtoListWithDayOff.size()/8);
        model.addAttribute("designerDtoList", designerDtoListWithDayOff.subList(page * 8, Math.min((page + 1) * 8, designerDtoListWithDayOff.size())));
        model.addAttribute("designerDto", new DesignerDto());
        model.addAttribute("maxPage",maxPage);
        model.addAttribute("currentPage", page);


        return "designer/designerMain";
    }

    @GetMapping("/designer/scheduleList")
    public String scheduleList(){

        return "designer/scheduleList";
    }

    @GetMapping("/designer/salaryList")
    public String salaryList(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, Model model){
        List<DesignerDto> designerDtoList = designerService.getAllDesigners();
        Pageable pageable = PageRequest.of(page, 8);
        int maxPage = (int)Math.ceil((double) designerDtoList.size()/8);
        model.addAttribute("designerDtoList", designerDtoList.subList(page * 8, Math.min((page + 1) * 8, designerDtoList.size())));
        model.addAttribute("maxPage",maxPage);
        model.addAttribute("currentPage", page);

        return "designer/salaryList";
    }

    @GetMapping("/designer/commuteList")
    public String commuteList(Model model){

        List<DesignerDto> designerDto = designerService.getAllDesigners();
        model.addAttribute("designerDto", designerDto);
        model.addAttribute("localDate", LocalDate.now());

        return "designer/commuteList";
    }

    @GetMapping("/designer/detailPage/{id}")
    public String detailPage(@PathVariable Long id, Model model){

        DesignerDto designerDto = designerService.getDesignerByID(id);

        model.addAttribute("designerDto",designerDto);

        return "designer/detailPage";
    }

    @GetMapping("/designer/regPage")
    public String regPage(Model model){

        model.addAttribute("designerDto", new DesignerDto());

        return "designer/designerReg";
    }

    @GetMapping("/designer/salaryPage/{id}")
    public String salaryPage(@PathVariable Long id, Model model){

        DesignerDto dto = designerService.getDesignerByID(id);

        model.addAttribute("designer",dto);

        return "designer/salaryPage";
    }

    @PostMapping("/designer/reg")
    public String designerChk(@ModelAttribute("designerDto") @Valid DesignerDto designerDto, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()) {
            model.addAttribute("errorMsg",designerDto);
            return "designer/designerReg";
        }
        List<String> selectedDayOffList = designerDto.getDayOffList().stream()
                .filter(dayOff -> !dayOff.isEmpty())
                .collect(Collectors.toList());
        designerDto.setDayOffList(selectedDayOffList);

        designerDto.setSalDate(designerDto.getSalDate());
        designerService.designerSave(designerDto);

        return "redirect:/designer";
    }



}
