let userStatusList = document.querySelectorAll('.user-status');
let memberIds = document.querySelectorAll('.memberIds');
for (let i = 0; i < userStatusList.length; i++) {
    let userStatus = userStatusList[i];
    let memberId = memberIds[i].getAttribute('data-value');
    userStatus.addEventListener('change', function(event) {
        let selectValue = event.target.value;
        fetchData(selectValue,memberId);
    });
}

function fetchData(active,member) {
    fetch(`/admin/active?active=${active}&memberId=${member}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            alert(data.msg);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}