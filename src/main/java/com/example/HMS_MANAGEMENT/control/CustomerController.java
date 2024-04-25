package com.example.HMS_MANAGEMENT.control;

import com.example.HMS_MANAGEMENT.dto.CustomerDetailDto;
import com.example.HMS_MANAGEMENT.dto.CustomerDto;
import com.example.HMS_MANAGEMENT.dto.SalesDto;
import com.example.HMS_MANAGEMENT.entity.CustomerDetailEntity;
import com.example.HMS_MANAGEMENT.entity.CustomerEntity;
import com.example.HMS_MANAGEMENT.entity.SalesEntity;
import com.example.HMS_MANAGEMENT.repository.CustomerDetailRepo;
import com.example.HMS_MANAGEMENT.repository.SalesRepository;
import com.example.HMS_MANAGEMENT.service.CustomerService;
import com.example.HMS_MANAGEMENT.service.SalesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    SalesRepository salesRepository;

    @Autowired
    CustomerDetailRepo customerDetailRepo;

    //페이징수
    @GetMapping("/customer/cusList")
    public String cusList(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size, Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CustomerEntity> customerPage = customerService.getAllCustomer(pageable);

        model.addAttribute("customers", customerPage.getContent());
        model.addAttribute("totalPages", customerPage.getTotalPages());
        model.addAttribute("currentPage", page);

        return "customer/cusList";

    }

    // 고객정보에서 등록하기를 클릭했을때
    @GetMapping("/customer/cusReg")
    public String cusReg(Model model) {
        List<String> optionsList = customerService.getOptions();
        model.addAttribute("optionsList", optionsList);
        model.addAttribute("customerDto", new CustomerDto());
        return "customer/cusReg";

    }

    // 등록 후 고객정보로 이동 컨트롤러
    @PostMapping("/customer/cusReg")
    public String saveCustomer(@Valid @ModelAttribute("customerDto") CustomerDto customerDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("customerDto", customerDto);
            return "customer/cusReg";
        }
        customerService.saveCustomer(customerDto);
        return "redirect:/customer/cusList";
    }


    //고객정보에서 이름 클릭했을때 id값에 맞는 내용 출력하는 컨트롤
    @GetMapping("/customer/useList")
    public String useList(@RequestParam("id") Long customerId, Model model) {
        // 특정 고객 정보 조회
        CustomerEntity customer = customerService.getCustomerById(customerId);

        // 모든 고객 정보 조회
        List<CustomerEntity> customers = customerService.getAllCustomer();

        // 특정 고객의 상세 정보 리스트 조회
        List<CustomerDetailEntity> customerDetails = customerDetailRepo.findByCustomerId(customerId);

        // 모델에 고객 정보와 고객 상세 정보를 추가
        model.addAttribute("customer", customer); // 특정 고객 정보
        model.addAttribute("customers", customers); // 모든 고객 정보
        model.addAttribute("customerDetails", customerDetails); // 특정 고객의 상세 정보 리스트

        return "customer/useList";
    }


    @GetMapping("/customer/test")
    public String test(@RequestParam(value = "id", required = false) Long customerId, Model model) {
        List<String> optionsList = customerService.getOptions();
        model.addAttribute("optionsList", optionsList);
        CustomerDetailDto customerDetailDto = new CustomerDetailDto();
        customerDetailDto.setCustomerId(customerId);
        model.addAttribute("customerDetailDto", customerDetailDto);
        if (customerId != null) {
            model.addAttribute("selectedCustomerId", customerId);
        }
        return "customer/test";
    }



    @PostMapping("/customer/test")
    public String addCustomer(@Valid @ModelAttribute("customerDetailDto") CustomerDetailDto customerDetailDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("customerDetailDto", customerDetailDto);
            return "customer/test";
        }

        customerService.detailCustomer(customerDetailDto);
        return "redirect:/customer/cusList";
    }





}