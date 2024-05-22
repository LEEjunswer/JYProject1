const header_query = document.getElementById("header_query");
const search_button = document.getElementById('search_button');
const search_form = document.getElementById('searchForm');
search_button.addEventListener("click",()=>{
    searchFunction();
})
header_query.addEventListener("keyup" ,(event)=>{
    if(event.key==="Enter"){
    searchFunction();
    }
})


function searchFunction(){
    const query = header_query.value.trim();
    console.log(query);
if(!query){
    alert("검색어를 입력하세요");
    return;
}
search_form.action = '/boards/search/' + encodeURIComponent(query);
search_form.submit();
}