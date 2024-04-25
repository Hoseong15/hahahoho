package com.example.HMS_MANAGEMENT.service;

import com.example.HMS_MANAGEMENT.dto.MonthChartDto;
import com.example.HMS_MANAGEMENT.dto.WeekChartDto;
import com.example.HMS_MANAGEMENT.entity.*;
import com.example.HMS_MANAGEMENT.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;


@Service
public class DayChartService {
    @Autowired
    private DayChartRepo dayChartRepo;
    @Autowired
    private DesignerRepo designerRepo;
    @Autowired
    private CustomerDetailRepo customerDetailRepo;
    @Autowired
    private InvenRepo invenRepo;
    @Autowired
    private CustomerRepository customerRepository;

//    public int[][] getChartDayData(String nowDate){
//        LocalDate date = LocalDate.parse(nowDate);
//        int yearValue = date.getYear();
//        int monthValue = date.getMonthValue(); // 현재 월의 숫자 값 (1부터 12까지)
//        int lastDayOfMonth = date.lengthOfMonth(); // 이번 달의 마지막 일
//        WeekFields weekFields = WeekFields.of(Locale.getDefault());
//        LocalDate start = LocalDate.of(yearValue, monthValue, 1);
//        LocalDate end = LocalDate.of(yearValue, monthValue, start.lengthOfMonth());
//
//        // 해당 월의 첫 번째 주의 첫 번째 날과 마지막 주의 마지막 날을 찾음
//        LocalDate firstDayOfFirstWeek = start.with(DayOfWeek.MONDAY);
//        LocalDate lastDayOfLastWeek = end.with(DayOfWeek.SUNDAY);
//
//        // 첫 번째 주와 마지막 주 사이의 주 수 계산
//        int weeksInMonth = (int) ((lastDayOfLastWeek.toEpochDay() - firstDayOfFirstWeek.toEpochDay()) / 7) + 1;
//
//        List<CustomerEntity> findIncome = customerRepository.findByFirstVisitBetween(start ,end , Sort.by(Sort.Direction.ASC,"firstVisit"));
//        List<CustomerEntity> findYearIncome = customerRepository.findByFirstVisitBetween(LocalDate.of(yearValue,1,1),
//                LocalDate.of(yearValue,12,31),Sort.by(Sort.Direction.ASC,"firstVisit"));
//
//        int[][] income = new int[3][];
//        int[] dayIncome = new int[lastDayOfMonth];
//        int[] weekIncome = new int[weeksInMonth];
//        int[] monthIncome = new int[12];
//        for(CustomerEntity customerEntity : findIncome) {
//            int idx=customerEntity.getFirstVisit().getDayOfMonth()-1;
//            int weekNumber = customerEntity.getFirstVisit().get(weekFields.weekOfMonth());
//            dayIncome[idx] += customerEntity.getCusCost();
//            weekIncome[weekNumber-1] +=customerEntity.getCusCost();
//        }
//        for(CustomerEntity customerEntity: findYearIncome){
//            int idx = customerEntity.getFirstVisit().getMonthValue();
//            monthIncome[idx-1] += customerEntity.getCusCost();
//        }
//
//        income[0]=dayIncome;
//        income[1]=weekIncome;
//        income[2]=monthIncome;
//        return income;
//    }





    // 알별그래프 상세보기 클릭하면 일별 데이터부분
    public Map<String, Integer> ServiceIncome(LocalDate date) {
        DayChartEntity dayChartEntity = new DayChartEntity();

        Integer service = customerDetailRepo.totalCustomCost(date); // 서비스 금액
        Integer salary = designerRepo.totalExpense(date); // 월급 금액
        Integer productSell = invenRepo.totalSellProduct(date); // 상품판매수입
        Integer productBuy = invenRepo.totalBuyProduct(date); // 상품구입 지출

        if (service == null) {
            service = 0;
        }
        if (salary == null) {
            salary = 0;
        }
        if (productSell == null) {
            productSell = 0;
        }
        if (productBuy == null) {
            productBuy = 0;
        }


        dayChartEntity.setDate(date); // 날짜
        dayChartEntity.setServiceIncome(service); // 서비스 금액
        dayChartEntity.setSalaryExpense(salary); // 월급 금액
        dayChartEntity.setProductSales(productSell); // 상품판매수입
        dayChartEntity.setProductPurchase(productBuy); // 상품구입 지출


        int serviceCost = dayChartEntity.getServiceIncome();
        int salaryCost = dayChartEntity.getSalaryExpense(); // 음수 표현하기 위해서
        salaryCost = -Math.abs(salaryCost);
        int productSellCost = dayChartEntity.getProductSales();
        int productBuyCost = dayChartEntity.getProductPurchase();
        productBuyCost = -Math.abs(productBuyCost);

        int totalIncomeCost = serviceCost + productSellCost;
        int totalExpenseCost = salaryCost + productBuyCost;

        dayChartEntity.setServiceIncome(totalIncomeCost); // 수입합계
        dayChartEntity.setTotalExpense(totalExpenseCost); // 지출합계

        dayChartRepo.save(dayChartEntity);

        Map<String, Integer> resultMap = new HashMap<>();

        resultMap.put("serviceCost", serviceCost);
        resultMap.put("salaryCost", salaryCost);
        resultMap.put("productSellCost", productSellCost);
        resultMap.put("productBuyCost", productBuyCost);
        resultMap.put("totalIncomeCost", totalIncomeCost);
        resultMap.put("totalExpenseCost", totalExpenseCost);

        return resultMap;
    }

    // 주간 그래프에서 상세보기 클릭하면 주간별 데이터부분
    public Map<LocalDate, Map<String, Integer>> dailyServiceIncomeAndExpenseDetails(LocalDate startDate, LocalDate endDate) {
        Map<LocalDate, Map<String, Integer>> dailyIncomeAndExpenseDetails = new LinkedHashMap<>();
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            Map<String, Integer> dailyResult = ServiceIncome(date);


            Map<String, Integer> incomeAndExpenseResult = new HashMap<>();
            incomeAndExpenseResult.put("totalIncomeCost", dailyResult.getOrDefault("totalIncomeCost", 0)); // 수입합계
            incomeAndExpenseResult.put("totalExpenseCost", dailyResult.getOrDefault("totalExpenseCost", 0)); // 지출합계

            dailyIncomeAndExpenseDetails.put(date, incomeAndExpenseResult);
        }
        return dailyIncomeAndExpenseDetails;
    }


    //월별 그래프에서 상세보기 클릭하면 월별 데이터부분
    public List<MonthChartDto> getMonthlyIncomeAndExpenseDetails(int year) {
        List<MonthChartDto> monthChartDtos = new ArrayList<>();

        for (int month = 1; month <= 12; month++) {
            MonthChartDto monthChartDto = new MonthChartDto();

            int totalMonthIncome = 0;
            int totalMonthExpense = 0;

            monthChartDto.setMonth(month);
            monthChartDto.setTotalIncome(totalMonthIncome);
            monthChartDto.setTotalExpense(totalMonthExpense);
            monthChartDtos.add(monthChartDto);
        }

        return monthChartDtos;
    }

    public int[][] getChartDayData(String nowDate){
        LocalDate date = LocalDate.parse(nowDate);
        int yearValue = date.getYear();
        int monthValue = date.getMonthValue(); // 현재 월의 숫자 값 (1부터 12까지)
        int lastDayOfMonth = date.lengthOfMonth(); // 이번 달의 마지막 일
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        LocalDate start = LocalDate.of(yearValue, monthValue, 1);
        LocalDate end = LocalDate.of(yearValue, monthValue, start.lengthOfMonth());

        // 해당 월의 첫 번째 주의 첫 번째 날과 마지막 주의 마지막 날을 찾음
        LocalDate firstDayOfFirstWeek = start.with(DayOfWeek.MONDAY);
        LocalDate lastDayOfLastWeek = end.with(DayOfWeek.SUNDAY);

        // 첫 번째 주와 마지막 주 사이의 주 수 계산
        int weeksInMonth = (int) ((lastDayOfLastWeek.toEpochDay() - firstDayOfFirstWeek.toEpochDay()) / 7) + 1;

//        List<CustomerDetailEntity> findIncome = customerDetailRepo.findByVisitBetween(start ,end , Sort.by(Sort.Direction.ASC,"visit"));
//        List<CustomerDetailEntity> findYearIncome = customerDetailRepo.findByVisitBetween(LocalDate.of(yearValue,1,1),
//                LocalDate.of(yearValue,12,31),Sort.by(Sort.Direction.ASC,"visit"));
//
//
//        List<DesignerEntity> findIncome1 = designerRepo.findByDateBetween(start ,end , Sort.by(Sort.Direction.ASC,"date"));
//        List<DesignerEntity> findYearIncome1 = designerRepo.findByDateBetween(LocalDate.of(yearValue,1,1),
//                LocalDate.of(yearValue,12,31),Sort.by(Sort.Direction.ASC,"date"));


        List<InvenEntity> findIncome2 = invenRepo.findByDateBetween(start ,end , Sort.by(Sort.Direction.ASC,"date"));
        List<InvenEntity> findYearIncome2 = invenRepo.findByDateBetween(LocalDate.of(yearValue,1,1),
                LocalDate.of(yearValue,12,31),Sort.by(Sort.Direction.ASC,"date"));




        int[][] income = new int[3][];
        int[] dayIncome = new int[lastDayOfMonth];
        int[] weekIncome = new int[weeksInMonth];
        int[] monthIncome = new int[12];

//        for(CustomerDetailEntity customerDetailEntity : findIncome) {
//            int idx=customerDetailEntity.getVisit().getDayOfMonth()-1;
//            int weekNumber = customerDetailEntity.getVisit().get(weekFields.weekOfMonth());
//            dayIncome[idx] += customerDetailEntity.getCost();
//            weekIncome[weekNumber-1] +=customerDetailEntity.getCost();
//        }
//        for(CustomerDetailEntity customerDetailEntity: findYearIncome){
//            int idx = customerDetailEntity.getVisit().getMonthValue();
//            monthIncome[idx-1] += customerDetailEntity.getCost();
//        }
//
//        for(DesignerEntity designerEntity : findIncome1) {
//            int idx=designerEntity.getDate().getDayOfMonth()-1;
//            int weekNumber = designerEntity.getDate().get(weekFields.weekOfMonth());
//            dayIncome[idx] += -1 * designerEntity.getSal();
//            weekIncome[weekNumber-1] +=designerEntity.getSal();
//        }
//        for(DesignerEntity designerEntity: findYearIncome1){
//            int idx = designerEntity.getDate().getMonthValue();
//            monthIncome[idx-1] += designerEntity.getSal();
//        }


        for(InvenEntity invenEntity : findIncome2) {
            int idx=invenEntity.getDate().getDayOfMonth()-1;
            int weekNumber = invenEntity.getDate().get(weekFields.weekOfMonth());
            dayIncome[idx] += invenEntity.getBuyCash();
            weekIncome[weekNumber-1] +=invenEntity.getBuyCash();
        }
        for(InvenEntity invenEntity: findYearIncome2){
            int idx = invenEntity.getDate().getMonthValue();
            monthIncome[idx-1] += invenEntity.getBuyCash();
        }

        income[0]=dayIncome;
        income[1]=weekIncome;
        income[2]=monthIncome;
        return income;
    }



}


//    public List<MonthChartDto> getMonthlyIncomeAndExpenseDetails(int year) {
//        List<MonthChartDto> monthChartDtos = new ArrayList<>();
//
//        // 각 월에 대해 반복
//        for (int month = 1; month <= 12; month++) {
//            LocalDate startDate = LocalDate.of(year, month, 1);
//            LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());
//
//            // 해당 월의 일별 수입과 지출 합계를 계산
//            Map<LocalDate, Map<String, Integer>> dailyDetails = dailyServiceIncomeAndExpenseDetails(startDate, endDate);
//
//            int totalIncome = 0;
//            int totalExpense = 0;
//
//            // 일별 수입과 지출 합계를 월별 합계로 집계
//            for (Map.Entry<LocalDate, Map<String, Integer>> entry : dailyDetails.entrySet()) {
//                Map<String, Integer> dailyResult = entry.getValue();
//                totalIncome += dailyResult.getOrDefault("totalIncomeCost", 0);
//                totalExpense += dailyResult.getOrDefault("totalExpenseCost", 0);
//            }
//
//            // MonthChartDto 객체 생성 및 리스트에 추가
//            MonthChartDto monthChartDto = new MonthChartDto();
//            monthChartDto.setMonth(month);
//            monthChartDto.setTotalIncome(totalIncome);
//            monthChartDto.setTotalExpense(totalExpense);
//            monthChartDtos.add(monthChartDto);
//        }
//
//        return monthChartDtos;
//    }