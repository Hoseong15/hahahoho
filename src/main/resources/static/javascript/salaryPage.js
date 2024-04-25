function calculateSalary() {
    var basicSal = parseInt(document.querySelector(".basic_sal span").innerText.replace(/[^0-9.-]+/g, "")) || 0;
    var overtimeAllowance = parseInt(document.getElementById("overtimeAllowanceInput").value.replace(/[^0-9.-]+/g, "")) || 0;
    var mealAllowance = parseInt(document.getElementById("mealAllowanceInput").value.replace(/[^0-9.-]+/g, "")) || 0;
    var pension = parseInt(document.getElementById("pensionInput").value.replace(/[^0-9.-]+/g, "")) || 0;
    var healthInsurance = parseInt(document.getElementById("healthInsuranceInput").value.replace(/[^0-9.-]+/g, "")) || 0;
    var employmentInsurance = parseInt(document.getElementById("employmentInsuranceInput").value.replace(/[^0-9.-]+/g, "")) || 0;
    var incomeTax = parseInt(document.getElementById("incomeTaxInput").value.replace(/[^0-9.-]+/g, "")) || 0;

    var totalIncome = basicSal + overtimeAllowance + mealAllowance;
    var totalDeductions = pension + healthInsurance + employmentInsurance + incomeTax;
    var netSalary = totalIncome - totalDeductions;

    document.getElementById("netSalary").innerText = numberWithCommas(netSalary) + "원";
}

function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

// 입력값이 변경될 때 쉼표를 추가하고 계산을 수행하는 함수
function handleInputChange(inputId) {
    var input = document.getElementById(inputId);
    var value = input.value.replace(/,/g, ""); // 쉼표 제거
    var formattedValue = numberWithCommas(value); // 쉼표 추가
    input.value = formattedValue; // 쉼표가 추가된 값으로 입력 필드 업데이트
    calculateSalary(); // 계산 수행
}

function sendSalaryStatement() {
    // 디자이너 정보 및 급여 관련 데이터 가져오기
    var designerName = document.querySelector(".salary_cont_title span").innerText;
    var designerEmail = document.querySelector("#designerEmail").textContent;
    var basicSal = document.querySelector(".basic_sal span").innerText;
    var overtimeAllowance = document.getElementById("overtimeAllowanceInput").value;
    var mealAllowance = document.getElementById("mealAllowanceInput").value;
    var pension = document.getElementById("pensionInput").value;
    var healthInsurance = document.getElementById("healthInsuranceInput").value;
    var employmentInsurance = document.getElementById("employmentInsuranceInput").value;
    var incomeTax = document.getElementById("incomeTaxInput").value;
    var netSalary = document.getElementById("netSalary").innerText;

    // 이메일 본문 생성
    var emailContent = "디자이너: " + designerName + "\n" +
        "기본급: " + basicSal + "\n" +
        "초과근무수당: " + overtimeAllowance + "\n" +
        "식비: " + mealAllowance + "\n" +
        "국민연금: " + pension + "\n" +
        "건강보험: " + healthInsurance + "\n" +
        "고용보험: " + employmentInsurance + "\n" +
        "근로소득세: " + incomeTax + "\n" +
        "실 수령액: " + netSalary;

    // 서버로 요청을 보내기 위한 설정
    var requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            designerName: designerName,
            designerEmail: designerEmail,
            emailContent: emailContent
        })
    };

    // 서버로 요청 보내기
    fetch('/send-salary-email', requestOptions)
        .then(response => {
            if (response.ok) {
                alert("급여명세서가 이메일로 전송되었습니다.");
            } else {
                throw new Error('이메일 전송에 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('이메일 전송 오류:', error);
            alert("이메일 전송에 실패했습니다.");
        });
}