function showAlertOnSubmit() {
    let hasErrors = false;

    const form = document.querySelector('form');

    const name = form.querySelector('#name').value;
    const phone = form.querySelector('#phone').value;
    const firstVisit = form.querySelector('#firstVisit').value;

    if (!name || name.trim() === '') {
        hasErrors = true;
        alert('이름을 입력해야 합니다.');
    }

    const phonePattern = /^010\d{4}\d{4}$/;
    if (!phone.match(phonePattern)) {
        hasErrors = true;
        alert('유효한 연락처 형식을 입력해야 합니다. 예: 01012345678');
    }

    if (!firstVisit || firstVisit.trim() === '') {
        hasErrors = true;
        alert('첫 방문일을 입력해야 합니다.');
    }

    return !hasErrors;
}