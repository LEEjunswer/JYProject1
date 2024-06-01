let checkPassword = 0;
let confirmPassword = document.getElementById('passwordConfirm');
let password = document.getElementById('password');
let changeNickname = document.getElementById('changeNick');
let nickname =document.getElementById('nickname');
let headerNickname = document.getElementById('user_nickname');
// 나중에 4글자 이상만 수정하게 변경할 예정
function  changeCheckPw(){
    if(password.value === confirmPassword.value){
        checkPassword = 1;
        password.style.borderColor = 'blue';
    confirmPassword.style.borderColor = 'blue';
    }else{
        checkPassword = 0;
        password.style.borderColor = 'red';
        confirmPassword.style.borderColor = 'red';
    }
}
function changePassword(){
    if(checkPassword !== 1){
        alert("비밀번호가 틀립니다 다시 확인해주세요");
        return;
    }else if(password.value.trim() < 3){
        alert("비밀번호 4글자 이상 적어주세요");
        return;
    }
    fetch(`/members/updatePassword?password=${password.value}`, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(response => response.json())
        .then(data => {
            alert(data.message);
        })
        .catch(error => {
            console.error("오류 발생:", error);
        });
    }
    
    // 닉네임 변경을 좀 복잡하게 만들엇다.. 수정을 좀더 해봐야겟다
function nicknameChange(){
    if(changeNickname.value.trim() < 1){
        alert("닉네임은 두 글자 이상 가능합니다");
        return;
    }
    fetch(`/members/nickValidCheck?nickname=${changeNickname.value}` , {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            if (!data) {

                if (confirm(changeNickname.value +"은(는) 사용 가능한 닉네임입니다. 닉네임을 변경하시겠습니까?")) {
                    ChangePostNickName(changeNickname.value);
                    nickname.value=changeNickname.value;
                    headerNickname.innerHTML=changeNickname.value;
                }
            } else {

                alert("이미 사용중인 닉네임입니다")
            }
        })
        .catch(error => {
            console.error("오류 발생:", error);
        });


}

function ChangePostNickName(nickname){
    fetch(`/members/updateNickname?nickname=${changeNickname.value}`, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(response => response.json())
        .then(data => {
            alert(data.message);
        })
        .catch(error => {
            console.error("오류 발생:", error);
        });

}