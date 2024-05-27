function onSearch(form){
    form.preventDefault();
    if(!form.search.value.trim()>1){
        alert("두글자 이상 검색어 입력해주세요");
         return;   
    }
    form.submit();
}