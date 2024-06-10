 var eventRaffleBtns = document.getElementsByClassName('eventRaffle_start');
 var  eventNums =   document.getElementsByClassName('eventNum_input');
 var eventIds =document.getElementsByClassName('eventId_value');
var eventPointChecks = document.getElementsByClassName('eventPointCheck');
var inputPoints = document.getElementsByClassName('input_point');

let isPointCheck =false;
 for(let i=0; i<eventRaffleBtns.length; i++) {
    let eventRaffleBtn = eventRaffleBtns[i];
     let eventPointCheck = eventPointChecks[i];
     let eventNum = eventNums[i].value;
     let eventId = eventIds[i].getAttribute('data-value');
     let inputPoint = inputPoints[i];
     eventRaffleBtn.addEventListener('click', () => {
        if (eventNum < 1) {
            alert(" 0명 또는 음수는 사용이 안됩니다");
            eventNum.value = 1;
            return;
        }
        if (!eventNum.trim()) {
            alert("공백은 사용이 불가능합니다.");
            return;
        }
         if (eventPointCheck.value === "" ) {
             alert("이벤트 포인트 체크여부 해주세요;");
             inputPoint.disabled = true;
             inputPoint.style.backgroundColor = 'gray';
             return;
         }
         isPointCheck = eventPointCheck.value === 'true';
         inputPoint.disabled = false;
         inputPoint.style.backgroundColor = 'white';
        fetch(`/admin/raffle/${eventNum}/${eventId}/${isPointCheck}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({})
        }).then(response => {
                alert("추첨이 완료되었습니다");
        }).catch(error => {
            console.error('Error:', error);
            alert('추첨에 실패했습니다. 다시 시도해주세요.');
        });
    });

}
