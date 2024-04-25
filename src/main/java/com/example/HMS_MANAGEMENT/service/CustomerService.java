package com.example.HMS_MANAGEMENT.service;

import com.example.HMS_MANAGEMENT.dto.CustomerDetailDto;
import com.example.HMS_MANAGEMENT.dto.CustomerDto;
import com.example.HMS_MANAGEMENT.entity.CustomerDetailEntity;
import com.example.HMS_MANAGEMENT.entity.CustomerEntity;
import com.example.HMS_MANAGEMENT.entity.DesignerCalendarEntity;
import com.example.HMS_MANAGEMENT.entity.SalesEntity;
import com.example.HMS_MANAGEMENT.repository.CustomerDetailRepo;
import com.example.HMS_MANAGEMENT.repository.CustomerRepository;
import com.example.HMS_MANAGEMENT.repository.DesignerCalendarRepo;
import com.example.HMS_MANAGEMENT.repository.SalesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SalesRepository salesRepository;
    @Autowired
    private CustomerDetailRepo customerDetailRepo;
    @Autowired
    private DesignerCalendarRepo designerCalendarRepo;

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);


    public void saveCustomer(CustomerDto customerDto) {
        String formatPhoneNumber = formatPhoneNumber(customerDto.getTel());
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customerDto.getName());
        customerEntity.setFirstVisit(customerDto.getFirstVisit());
        customerEntity.setTel(formatPhoneNumber);
        customerEntity.setRecord(customerDto.getRecord());
        customerEntity.setFrequentDesigner(customerDto.getFrequentDesigner());

        logger.debug("Searching for SalesEntity with type: {}", customerDto.getRecord());
        List<SalesEntity> salesEntities = salesRepository.findByType(customerDto.getRecord());
        if (salesEntities.isEmpty()) {
            logger.error("해당 타입의 판매 정보를 찾을 수 없음: {}", customerDto.getRecord());
        } else {
            SalesEntity selectedSale = salesEntities.get(0);
            customerEntity.setSales(selectedSale);
            customerEntity.setRecord(selectedSale.getType());
            customerEntity.setCusCost(selectedSale.getCost());
        }
        customerRepository.save(customerEntity);


        CustomerDetailEntity customerDetailEntity = new CustomerDetailEntity();
        customerDetailEntity.setName(customerEntity.getName());
        customerDetailEntity.setVisit(customerEntity.getFirstVisit());
        customerDetailEntity.setCutType(customerDto.getRecord());
        customerDetailEntity.setDesigner(customerEntity.getFrequentDesigner());
        customerDetailEntity.setCost(customerEntity.getCusCost());

        customerDetailEntity.setSales(customerEntity.getSales());
        customerDetailEntity.setCustomer(customerEntity);
        customerDetailRepo.save(customerDetailEntity);
    }

    //고객정보 페이징
    public Page<CustomerEntity> getAllCustomer(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    //휴대폰 번호 하이푼 코드
    public String formatPhoneNumber(String phoneNumber) {
        String digits = phoneNumber.replaceAll("\\D", "");
        if (digits.length() < 10) {
            throw new IllegalArgumentException("유효한 전화번호가 아닙니다.");
        }
        String areaCode = digits.substring(0, 3);
        String firstPart = digits.substring(3, 7);
        String secondPart = digits.substring(7, 11);
        return String.format("%s-%s-%s", areaCode, firstPart, secondPart);
    }

    //고객정보에서 정보 보여주기
    public List<CustomerDto> getCustomer() {
        List<CustomerEntity> customerEntities = customerRepository.findAll();
        List<CustomerDto> customer = new ArrayList<>();

        for (CustomerEntity entity : customerEntities) {
            CustomerDto dto = new CustomerDto();
            dto.setName(entity.getName());
            dto.setFrequentDesigner(entity.getFrequentDesigner());
            dto.setRecord(entity.getRecord());
            customer.add(dto);
        }
        return customer;
    }


    public List<CustomerEntity> getAllCustomer() {
        return customerRepository.findAll();
    }

    //고객정보 아이디
    public CustomerEntity getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("id" + customerId));
    }


    // 서비스 아이디
    public SalesEntity getSalesId(Long salesId, String cut) {
        return salesRepository.findById(salesId)
                .orElseThrow(() -> new NoSuchElementException("id" + salesId));
    }

    //시술종류 리스트
    public List<String> getOptions() {
        return Arrays.asList("남성커트", "여성커트", "남성포인트펌", "여성일반펌", "셋팅펌", "뿌리염색", "전체염색", "두피케어");
    }


    public void detailCustomer(CustomerDetailDto customerDetailDto) {

        CustomerDetailEntity customerDetailEntity = new CustomerDetailEntity();

        List<SalesEntity> salesEntities = salesRepository.findByType(customerDetailDto.getCutType());
        if (salesEntities.isEmpty()) {
            logger.error("해당 타입의 판매 정보를 찾을 수 없음: {}", customerDetailDto.getCutType());
        } else {
            SalesEntity selectedSale = salesEntities.get(0);
            customerDetailEntity.setSales(selectedSale);
            customerDetailEntity.setCutType(selectedSale.getType());
            customerDetailEntity.setCost(selectedSale.getCost());
        }
        CustomerEntity customerEntity = customerRepository.findById(customerDetailDto.getCustomerId()).get();
        customerDetailEntity.setCustomer(customerEntity);

        customerDetailEntity.setVisit(customerDetailDto.getVisit());
        customerDetailEntity.setCutType(customerDetailDto.getCutType());
        customerDetailEntity.setDesigner(customerDetailDto.getDesigner());
        customerDetailEntity.setCost(customerDetailEntity.getCost());
        customerDetailEntity.setName(customerEntity.getName());

        customerDetailRepo.save(customerDetailEntity);

        DesignerCalendarEntity designerCalendarEntity = new DesignerCalendarEntity();
        designerCalendarEntity.setWhoAmI("고객");
        designerCalendarEntity.setName(customerDetailEntity.getName());
        designerCalendarEntity.setEventType("예약");
        designerCalendarEntity.setAllDay(true);
        designerCalendarEntity.setTitle(customerDetailEntity.getName()+"-"+customerDetailDto.getCutType()+"("+customerDetailDto.getDesigner()+")");
        designerCalendarEntity.setStart(customerDetailDto.getVisit().atStartOfDay());
        designerCalendarEntity.setEnd(customerDetailDto.getVisit().atTime(8,59));
        designerCalendarRepo.save(designerCalendarEntity);
    }
}