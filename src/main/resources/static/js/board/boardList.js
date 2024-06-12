function onSearch(event) {
    event.preventDefault();
    const form = event.target.closest('form');
    const searchValue = form.search.value.trim();

    if (searchValue.length < 2) {
        alert("두 글자 이상 검색어를 입력해주세요");
        return;
    }

    form.submit();
}