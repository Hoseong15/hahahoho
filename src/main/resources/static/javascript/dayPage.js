function changeDate(change) {
    const dateDisplay = document.getElementById('dateDisplay');
    let currentDate = new Date(dateDisplay.innerText);

    currentDate.setDate(currentDate.getDate() + change);

    const year = currentDate.getFullYear();
    const month = ("0" + (currentDate.getMonth() + 1)).slice(-2);
    const day = ("0" + currentDate.getDate()).slice(-2);

    dateDisplay.innerText = `${month}-${day}`;
}