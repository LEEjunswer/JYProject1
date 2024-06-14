document.addEventListener("DOMContentLoaded", function() {
    var textarea = document.getElementById('filterTextArea');
    filters.forEach(
        function (filter) {
            textarea.value += filter + "\n";
        })
});
function addContent() {
    var newContent = document.getElementById('newFilterWord').value;
    var textarea = document.getElementById('filterTextArea');
    var currentContent = textarea.value.split("\n").map(item => item.trim());
    if (currentContent.includes(newContent)) {
      alert("이미 등록된 필터 단어가 존재합니다.");
    }else if(newContent.trim() !== "") {
        textarea.value += newContent + "\n";
        document.getElementById('newFilterWord').value = "";
        addFilterFetch(newContent.trim());
    }
    else {
        alert("공백은 등록하실 수 없습니다.");
    }
}
function deleteContent() {
    var deleteContent = document.getElementById('deleteFilterWord').value;
    var textarea = document.getElementById('filterTextArea');
    var currentContent = textarea.value.split("\n").map(item => item.trim());
    if (currentContent.includes(deleteContent)) {
        currentContent = currentContent.filter(item => item !== deleteContent);
        textarea.value = currentContent.join("\n") + "\n";
        document.getElementById('deleteFilterWord').value = "";
        deleteFilterFetch(deleteContent);
    }  else {
        alert("단어가 존재하지않습니다.");
    }
}
function addFilterFetch(filter) {
    fetch(`/admin/addFilter?filter=${filter}`, {
        method: "Post",
        headers: {
            'Content-Type': 'application/json'
        },

    })
        .then(response => response.json())
        .then(data => {
            alert(data.msg);
        })
        .catch(error => console.error('Error:', error));
}
function deleteFilterFetch(filter) {
    fetch(`/admin/deleteFilter?filter=${filter}`, {
        method: "Post",
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(response => response.json())
        .then(data => {
            alert(data.msg);
        })
        .catch(error => console.error('Error:', error));

}