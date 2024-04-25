package com.example.HMS_MANAGEMENT.control;

import com.example.HMS_MANAGEMENT.constent.InvenStatus;
import com.example.HMS_MANAGEMENT.dto.InvenDto;
import com.example.HMS_MANAGEMENT.service.InvenService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class InvenController {

    private final InvenService invenService;

    public InvenController(InvenService invenService) {
        this.invenService = invenService;
    }

    @GetMapping("/inven")
    public String invenMain(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, Model model){

        List<InvenDto> invenList = invenService.getAllBasicItems();
        Pageable pageable = PageRequest.of(page,10);
        int maxPage = (int)Math.ceil((double) invenList.size()/10);
        model.addAttribute("maxPage",maxPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("invenList", invenList.subList(page * 10, Math.min((page + 1) * 10, invenList.size())));
        return "inven/invenMain";
    }

    @GetMapping("/inven/invenList")
    public String invenListPage(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, @RequestParam(name = "search", required = false) String search, Model model){
        List<InvenDto> invenList = invenService.getAllBasicItems();
        int maxPage = (int)Math.ceil((double) invenList.size()/10);
        model.addAttribute("maxPage",maxPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("invenList", invenList.subList(page * 10, Math.min((page + 1) * 10, invenList.size())));
        model.addAttribute("invenDto", new InvenDto());
        return "inven/invenList";
    }

    @PostMapping("/inven/process")
    public String processOperation(@ModelAttribute InvenDto invenDto, BindingResult bindingResult, Model model) {
        invenService.processBasic(invenDto);

        if(bindingResult.hasErrors()) {

            return "/inven/invenList";
        }
        return "redirect:/inven/invenList";
    }

    @GetMapping("/inven/buyList")
    public String buyList(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, Model model){
        List<InvenDto> invenList = invenService.getAllBuyItems();
        int maxPage = (int)Math.ceil((double) invenList.size()/10);
        model.addAttribute("maxPage",maxPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("invenList", invenList.subList(page * 10, Math.min((page + 1) * 10, invenList.size())));
        model.addAttribute("invenDto", new InvenDto());
        return "inven/buyList";
    }

    @GetMapping("inven/sortList")
    public String sortList(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page, Model model){
        List<InvenDto> invenList = invenService.getAllSellItems();
        int maxPage = (int)Math.ceil((double) invenList.size()/10);
        model.addAttribute("maxPage",maxPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("invenList", invenList.subList(page * 10, Math.min((page + 1) * 10, invenList.size())));
        model.addAttribute("invenDto", new InvenDto());
        return "inven/sortList";
    }

    @DeleteMapping("inven/deleteList")
    public @ResponseBody ResponseEntity deleteInven(@PathVariable("invenId") Long idCode, Principal principal){

        invenService.deleteInven(idCode);

        return new ResponseEntity<Long>(idCode, HttpStatus.OK);
    }

}
