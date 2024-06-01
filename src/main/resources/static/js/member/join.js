let idCheck = 0;
let emailCheck = 0;
let nickNameCheck = 0;
let passwordCheck = 0;
let nickValue = document.getElementById("nickname");
let passwordStyle = document.getElementById('password');
let passwordConfirmStyle= document.getElementById('passwordConfirm');
const submitButton = document.getElementById('join_submit');
let selectElement = document.getElementById("selectEmail");
let emailInput = document.getElementById("emailInput");
let emailDomain = document.getElementById('emailDomain');
let emailId = document.getElementById('emailId');




// 이메일 주소의 유효성을 검사하는 정규 표현식
function isValidEmail(email) {
    var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailPattern.test(email);
}

function checkPw(){
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("passwordConfirm").value;

    if(password === confirmPassword){
        passwordCheck = 1;
        passwordStyle.style.borderColor = 'blue';
        passwordConfirmStyle.style.borderColor = 'blue';
    }else{
        passwordCheck = 0;
        passwordStyle.style.borderColor = 'red';
        passwordConfirmStyle.style.borderColor = 'red';
    }

}

function emailSelect() {
    if (selectElement.value.trim() === "") {
        emailDomain.style.display = 'inline';
        emailDomain.readOnly = false;
        emailDomain.value = "";
    }else {
        emailDomain.style.display = 'none';
        emailDomain.readOnly = true;
        emailDomain.value = selectElement.value;
    }

}

function formSubmitCheck(form){
    submitButton.disabled = true;
    form.email.value = emailId.value + "@" + emailDomain.value;
     let emailCheck = form.email.value;
    if(!form.loginId.value.trim()){
    alert("아이디를 입력해주세요");
    form.loginId.focus();
    submitButton.disabled = false;
    return;
}
else if(!form.pw.value.trim()){
    alert("비밀번호를 입력헤주세요")
    form.pw.focus();
    submitButton.disabled = false;
    return;
}

else if(!form.name.value.trim()){
    submitButton.disabled = false;
    alert("이름을 입력해주세요")
    form.name.focus();
    return;
}
else if(!form.nickname.value.trim()){
    alert("별명을 입력해주세요");
    form.nickname.focus();
    submitButton.disabled = false;
    return;
}
else if(!form.email.value.trim()){
    alert("이메일을 입력해주세요");
    form.email.focus();
    submitButton.disabled = false;
    return;
}
// 주소는 일단 안받기로함 아직 있어야할 이유가 있는지 모르겠다.
/*if(!form.zipcode.value.trim() || !form.address.value.trim() || !form.addressDetail.value.trim()){
  alert("주소를 입력해주세요");
  form.zipcode.focus();
  return;
}*/
  else if(passwordCheck === 0){
        alert("비밀번호확인을 입력해주세요");
        submitButton.disabled = false;
        return;
    }
  else if(!isValidEmail(emailCheck)){
      alert("이메일 형식이 아닙니다. 다시 확인해주세요");
        submitButton.disabled = false;
        return;
    }
  /* emailCheck ===0  나중에 이메일 중보게크랑 인증번호 보내고 할 예정 */
else if( idCheck === 0 ||nickNameCheck === 0){
    alert("아이디 or 닉네임 중복체크를 해주세요");
    submitButton.disabled = false;
    return;
}
form.submit();
}




function  idValidCheck() {
    const idInput = document.getElementById("inputLoginId").value;
    const koreanPattern = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
    const spacePattern = /\s/;
    if(koreanPattern.test(idInput) || spacePattern.test(idInput) ){
        alert("아이디는 한글을 포함 또는 공백을 사용하실 수 없습니다.");
        return;
    }
    if (idInput.length < 6 || idInput.length > 20) {
        alert("아이디는 6글자 이상 20글자 이하로 설정해야 합니다.");
        return;
    }

    fetch("/members/idValidCheck?loginId=" +idInput, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(response => response.json())
        .then(data => {

            if (!data) {
                idCheck =1;
                alert("사용 가능한 아이디입니다.")

            } else {
                idCheck = 0;
                alert("이미 사용중인 아이디입니다")
            }
        })
        .catch(error => {
            console.error("오류 발생:", error); 
        });
}


/* 역시 나중에 중복체크하고 이메일로 인증할 예정
fetch("members/emailCheck" + emailInput,{})
*/

function nickValidCheck(){
    let nickValue = document.getElementById("nickname").value;
fetch("/members/nickValidCheck?nickname=" +nickValue , {
    method: "POST",
    headers: {
        'Content-Type': 'application/json'
    },
})
    .then(response => response.json())
    .then(data => {
        console.log(data);
        if (!data) {
            nickNameCheck =1;
            alert("사용 가능한 닉네임입니다.")

        } else {
            nickNameCheck = 0;
            alert("이미 사용중인 닉네임입니다")
        }
    })
    .catch(error => {
        console.error("오류 발생:", error);
    });


}