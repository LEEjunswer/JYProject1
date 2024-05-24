const profile = document.getElementById("profile_open");``
const myModal = document.getElementById("myModal");
const modalClose = document.getElementById("modalBtn-close");
const profileImg = document.getElementsByClassName("profile_img");
const profileUpload = document.getElementById("profile_upload");
const profileSubmit= document.getElementById("photo_submit");
const  modalCloseTwo =document.getElementById("photo_reset");
profile.addEventListener("click", ()=>{
    myModal.style.display="block";
    myModal.style.opacity="1";
    disableClickOutside();
})


modalClose.addEventListener("click",() =>{
    myModal.style.display="none";
    myModal.style.opacity="0";
    enableClickOutside();
})
modalCloseTwo.addEventListener("click" , ()=>{
    myModal.style.display='none';
    myModal.style.opacity='0';
    enableClickOutside();
})

profileUpload.onchange =function (){
    loadFile(this);
}

function loadFile(input) {
    const file = input.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
            document.getElementById('profile_img').src = e.target.result;
        }
        reader.readAsDataURL(file);
    }
}

// 다른창 클릭 막기
function disableClickOutside() {
    document.querySelectorAll('body > *:not(#myModal)').forEach(function (element) {
        element.style.pointerEvents = "none";
    });
   myModal.style.pointerEvents = "auto";
}

function enableClickOutside() {
    document.querySelectorAll('header > *:not(#myModal)').forEach(function (element) {
        element.style.pointerEvents = "auto";
    });
}
profileSubmit.addEventListener("click",()=> {

    console.log("체크 투")
    const fileInput = document.getElementById('profile_upload');
    const loginId = document.getElementById("loginId").value;
    const file = fileInput.files[0];
    if (file) {
        const formData = new FormData();
        formData.append('profileImage', file);
        formData.append('loginId', loginId);
        fetch('/members/updateProfile', {
            method: 'POST',
            body: formData
        })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
                myModal.style.display = "none";
                myModal.style.opacity = "0";
                enableClickOutside();
                alert("성공적으로 프로필 변경이 완료되었습니다.");
            })
            .catch(error => {
                console.error('Error:', error);
            });
    } else {
        alert('파일을 선택하세요.');
    }

});
