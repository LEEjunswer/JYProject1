if (document.getElementById('apply-btn')) {
    document.getElementById('apply-btn').addEventListener('click', () => {
        document.getElementById('apply-btn').disabled = true;
        const getEventId = document.getElementById('event_id').getAttribute('data-value');
        const eventPoint = document.getElementById('event_point').getAttribute('event-value');
        const  myPoint = document.getElementById('myPoint').getAttribute('data-myPoint');
       const  dataId =document.getElementById('data_id').getAttribute('data-id');
        if(!dataId){
           alert("로그인 이후 응모해주세요.");
            document.getElementById('apply-btn').disabled=false;
           return;

       }
        if( myPoint < eventPoint ){
            alert("응모포인트가 부족합니다");
            document.getElementById('apply-btn').disabled=false;
            return;
        }

        const data = {
            eventId : getEventId,
        };

        fetch(`/notice/applyRaffle`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(data => {
                alert(data.message);
                location.reload();
            })
            .catch(error => console.error('Error:', error));
    });
}
const refreshBtn = document.getElementById('refresh-btn');
refreshBtn.addEventListener('click',()=>{
    location.reload();
})
