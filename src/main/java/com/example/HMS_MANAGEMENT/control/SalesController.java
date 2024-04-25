package com.example.HMS_MANAGEMENT.control;

import com.example.HMS_MANAGEMENT.dto.SalesDto;
import com.example.HMS_MANAGEMENT.entity.SalesEntity;
import com.example.HMS_MANAGEMENT.service.SalesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class SalesController {

    private final SalesService salesService;

    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }
    @PostMapping("/shop/reg")
    public String registration(@ModelAttribute("salesDto") SalesDto salesDto, Model model){
        List<String> optionsList = salesService.getOptions();
        model.addAttribute("optionsList",optionsList);

        salesService.saveSales(salesDto);
        return "redirect:/shop/service";
    }
    @GetMapping("/shop/reg")
    public String reg(Model model){
        List<String> optionsList = salesService.getOptions();
        model.addAttribute("optionsList",optionsList);
        model.addAttribute("salesDto",new SalesDto());
        return "shop/reg";
    }
    @GetMapping("/shop/service")
    public String servicePage(Model model) {
        List<SalesEntity> services = salesService.getAllServices();

        Map<String, List<SalesEntity>> groupedServices = services.stream()
                .collect(Collectors.groupingBy(SalesEntity::getCut));

        model.addAttribute("groupedServices", groupedServices);
        return "shop/service";
    }

}