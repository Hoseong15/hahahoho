
$(function() {
    $(".modal_bt").hide();

    $("#modal_wrap").hide();
    $(".writeBt, .buyBt, .sortBt, .modifyBt").on("click", function() {
        var operationType = $(this).text().trim();
        $("#modal_wrap").show().data("operationType", operationType);
        $(".modal_text").text(operationType);
        $("#operationType").val(operationType); // hidden input에 운영 유형 설정

        // 작성, 구매, 판매 중 어느 버튼을 클릭했는지에 따라 작성완료 버튼 표시 여부 결정
        if (operationType === "작성" || operationType === "구매" || operationType === "판매") {
            // 작성완료 버튼 숨기기
            $(".modal_bt").hide();
        }

        var invenStatusSelect = $(".inven_status_select");
        invenStatusSelect.find("option").show(); // 모든 옵션을 먼저 표시
        var invenCodeSelect = $(".code_select_box");
        invenCodeSelect.find("option").show();

        if (operationType === "구매") {
            invenStatusSelect.val("BUY");
            invenStatusSelect.find("option:not(:selected)").hide();
            $(".code_select_box option[value*='직접 입력']").hide();
            $(".basic_text").hide();
        } else if (operationType === "판매") {
            invenStatusSelect.val("SELL");
            invenStatusSelect.find("option:not(:selected)").hide();
            $(".code_select_box option[value*='직접 입력']").hide();
            $(".basic_text").hide();
        } else if (operationType === "작성") {
            invenStatusSelect.val("BASIC");
            invenStatusSelect.find("option:not(:selected)").hide();
            $(".code_select_box option").hide();
            $(".code_select_box option[value*='직접 입력']").show();
            $(".basic_text").show();
        }
    });

    // 각 필드에 입력이 완료되면 작성완료 버튼을 보이게 함
    $(".inven_input_box").on("keyup", function() {
        var allFieldsFilled = true;
        $(".inven_input_box").each(function() {
            if ($(this).val() === "") {
                allFieldsFilled = false;
                return false; // 반복문 탈출
            }
        });
        // 모든 필드에 입력이 완료되면 작성완료 버튼 보이기
        if (allFieldsFilled) {
            $(".modal_bt").show();
        } else {
            $(".modal_bt").hide();
        }
    });

    $(".modal_bt").on("click", function() {
        $("#modal_wrap").hide();
        $(".basic_text").hide();
    });

    $(".deleteBt").on("click",function(){
        $(".list_chk").show();
        $(".buyBt, .sortBt, .editBt").hide();
        $(".backBt").show();

        $(".all_chk").on("click",function(){
            $(".list_chk").prop("checked" , $(this).prop("checked"));

        });
    });

    var allCheck = $(".all_chk");
    var listCheck = $(".list_content_text .list_chk");

    allCheck.on("click", function() {
        if($(this).is(":checked")) {
            listCheck.prop("checked", true);

        }else{
            listCheck.prop("checked", false);
        }
    });
    listCheck.on("click", function() {
        var allCheckedBox = true;
        listCheck.each(function(){
            if (!$(this).is(":checked")) {
                allCheckedBox = false;
                return false;
            }
        });
        allCheck.prop("checked", allCheckedBox);
    });

    $(".backBt").on("click",function(){
        if(confirm("취소 하시겠습니까?")){
            alert("취소 되었습니다.");
            $(".buyBt, .sortBt, .editBt").show();
            $(".list_chk, .backBt").hide();
        }
    });

    $(".deleteGo").on("click",function(){
        if($(".list_chk:checked").length > 0){
            if(confirm("정말 삭제 하시겠습니까?")){
                $(this).parents('.list_content_text').remove();
                alert("삭제 되었습니다.");
                $(".buyBt, .sortBt, .editBt").show();
                $(".list_chk").hide();
            }
        }else{
            alert("삭제할 데이터를 체크 해주세요.");
        }

    });

    $(".modal_back_bt").on("click",function (){
        $(".inven_class input").val('');
        $(".inven_l input").val('');
        $(".result_n input").val('');
        $(".inven_nm input").val('');
        $(".code_select_box").val($(".code_select_box option:first").val());
        $(".selectInput").hide();
        $(".status_s select").val($(".status_s option:first").val());
        $("#modal_wrap").hide();
    });



});

function handleCodeSelection(select){
    var selectInput = select.nextElementSibling;
    var selectedCode = select.value;
    var idx=0;
    if(selectedCode !== "직접 입력"){
    $(".idCode-list").each(function(i,v){
        if ( $(this).text() === $(".code_select_box").val() ){
            idx=i;
            return false;
        }
    });
        $("#id").val( $(".idClass-list").eq(idx).attr("data-id"));
        $("#itemNm").val($(".itemNm-list").eq(idx).text());
        $("#idClass").val($(".idClass-list").eq(idx).text());
        $("#itemL").val($(".itemL-list").eq(idx).text());
        $("#count").val($(".count-list")).focus();
        $("#idCode").val(selectedCode);

        $("#count").on("blur",function(){
            if($("#operationType").text()==='판매') {
                var itemNowCount = $(".count-list").eq(idx).text();
                if (itemNowCount === 0)
                    alert("수량이 0");
                else if(itemNowCount < parseInt($(this).val()))
                    alert("재고부족");
            }
        });

        selectInput.style.display = "none";
    }else{
        selectInput.style.display = "block";
        $(".inven_class input").val('');
        $(".inven_l input").val('');
        $(".result_n input").val('');
        $(".inven_nm input").val('').focus();
        $(".selectInput").val('');
    }


}


