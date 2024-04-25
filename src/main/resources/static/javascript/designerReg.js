function formatTime(time) {
    var hour = parseInt(time.split(":")[0]);
    var minute = parseInt(time.split(":")[1]);
    return padWithZero(hour) + ":" + padWithZero(minute);
}

// 시간을 10분 단위로 조정하는 함수
function padWithZero(number) {
    return number < 10 ? "0" + number : number;
}

// 오전과 오후 시간 선택 범위 설정 함수
function setSelectOptions(selectElement, startHour, endHour) {
    for (var i = startHour; i <= endHour; i++) {
        for (var j = 0; j < 60; j += 10) {
            var hour = padWithZero(i);
            var minute = padWithZero(j);
            if (!selectElement) return;
            selectElement.options[selectElement.options.length] = new Option(hour + ":" + minute + (i < 12 ? " 오전" : " 오후"), hour + ":" + minute);
        }
    }
}

// 문서 로딩 후 실행되는 부분
$(document).ready(function() {
    // 오전 시간 분 단위 설정
    setSelectOptions(document.getElementById("morningTime"), 6, 11);

    // 오후 시간 분 단위 설정
    setSelectOptions(document.getElementById("afterTime"), 12, 23);

    // 오후 시간 선택 제한
    $("#afternoonTime").on("change", function () {
        var value = $(this).val();
        if (value < "12:00" || value > "23:50") {
            $(this).val("12:00");
        }
    });
});

function submitForm(){

    var checkDay = [];
    $(".dayBt:checked").each(function(){
        if($(this).val() !== '')
            checkDay.push($(this).val());
    });

    if (checkDay.length === 0) {
        alert("휴무 요일을 선택해주세요.");
        return;
    }

    $("#designerDto_free").val(checkDay);

    $("#designerForm input[type=hidden]").val('');

    // 저장하기 전에 쉼표 제거
    removeCommasBeforeSave("tel");
    removeCommasBeforeSave("sal");

    $("#designerForm").submit();
}

// 쉼표를 제거하여 값 설정
function removeCommas(x) {
    return x.toString().replace(/,/g, "");
}

// 입력 필드에 쉼표 추가하여 값 설정
function formatInputValue(inputId) {
    var input = document.getElementById(inputId);
    var value = input.value;

    // 전화번호인 경우
    if (inputId === "tel") {
        // 입력된 값에서 숫자만 남기고 나머지는 제거합니다.
        value = value.replace(/\D/g, "");
        // 전화번호 형식에 맞게 하이픈을 삽입합니다.
        value = value.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
    } else if (inputId === "sal") {
        // 월급 입력 필드인 경우
        // 쉼표 제거 후 적용합니다.
        value = removeCommas(value);
        // 숫자가 3개씩 반복되어 입력될 때마다 쉼표를 추가합니다.
        value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }

    input.value = value;
}

// 저장 전 쉼표 제거하여 값 설정
function removeCommasBeforeSave(inputId) {
    var input = document.getElementById(inputId);
    var value = input.value;

    if(inputId === "tel"){
        value = value.toString().replace(/-/g,"");
    }else{
        value = removeCommas(value); // 쉼표를 제거하여 값 설정
    }

    input.value = value; // 쉼표가 제거된 값으로 입력 필드 업데이트
}

