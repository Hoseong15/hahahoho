package com.example.HMS_MANAGEMENT.control;

import com.example.HMS_MANAGEMENT.dto.EmailDto;
import com.example.HMS_MANAGEMENT.util.EmailSender;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @PostMapping("/send-salary-email")
    public ResponseEntity<String> sendSalaryEmail(@RequestBody EmailDto request) {
        try {
            // 이메일 본문 생성
            String emailContent = "디자이너: " + request.getDesignerName() + "\n" +
                    "기본급: " + request.getBasicSal() + "\n" +
                    "초과근무수당: " + request.getOvertimeAllowance() + "\n" +
                    "식비: " + request.getMealAllowance() + "\n" +
                    "국민연금: " + request.getPension() + "\n" +
                    "건강보험: " + request.getHealthInsurance() + "\n" +
                    "고용보험: " + request.getEmploymentInsurance() + "\n" +
                    "근로소득세: " + request.getIncomeTax() + "\n" +
                    "실 수령액: " + request.getNetSalary();

            // 이메일 보내기
            EmailSender.sendEmail(request.getDesignerEmail(), "급여명세서", emailContent);

            return ResponseEntity.ok("이메일이 성공적으로 전송되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이메일 전송 중 오류가 발생했습니다.");
        }
    }
}