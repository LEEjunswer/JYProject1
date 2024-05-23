
let idCheck = 0;
let emailCheck = 0;
let nickNameCheck = 0;
let idValue = document.getElementById().value;
let nickValue = document.getElementById().value;
let emailValue = document.getElementById().value;
const submit = document.getElementById("join-submit");

function idValidCheck(form){
if(!form.loginId.value.trim()){
    alert("아이디를 입력해주세요");
    form.loginId.focus();
    return;
}
if(!form.pw.value.trim()){
    alert("비밀번호를 입력헤주세요")
    form.pw.focus();
    return;
}
if(!form.pwCheck.value.trim()){
    alert("비밀번호 확인을 입력해주세요");
    form.pwCheck.focus();
    return;
}
if(!form.name.value.trim()){
    alert("이름을 입력해주세요")
    form.name.focus();
    return;
}
if(!form.nickname.value.trim()){
    alert("별명을 입력해주세요");
    form.nickname.focus();
    return;
}
if(!form.email.value.trim()){
    alert("이메일을 입력해주세요");
    form.email.focus();
    return;
}
if(!form.zipcode.value.trim() || !form.address.value.trim() || !form.addressDetail.value.trim()){
  alert("주소를 입력해주세요");
  form.zipcode.focus();
  return;
}
if( idCheck === 0 || emailCheck ===0 || nickNameCheck === 0 ){
    alert("아이디 or 이메일 or 닉네임 중복체크를 해주세요");
    return;
}
form.submit();
}


fetch("/members/idValidCheck"+idValue, {
    method : "post",
    body : "",
});


fetch("members/emailCheck" + emailValue,{})

fetch("members/nickValidCheck" +nickNameCheck , {

})