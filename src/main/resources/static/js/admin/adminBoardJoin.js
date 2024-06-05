const boardContent = document.getElementById("tinymceTextarea");
const boardTitle = document.getElementById("title_join");
const eventPop = document.getElementById('eventPop');
const uploadedImageUrls = [];
let radioButtons = document.querySelectorAll('input[name="category"]');
let startDateField = document.getElementById('eventStartDate');
let endDateField = document.getElementById('eventEndDate');
let eventTitle =document.getElementById('eventTitle');
let eventPoint = document.getElementById('eventPoint');
let eventPointValue  ;
eventPoint.addEventListener('input',() =>{
    if(eventPoint.value < 0){
        alert("음수를 사용하실 수 없습니다");
        eventPoint.value = 0;
    }
    eventPointValue=eventPoint.value;
    console.log(eventPointValue);
})

startDateField.min = getCurrentDateTime();
endDateField.min = getCurrentDateTime();

function getCurrentDateTime() {

    const today = new Date();
    const yyyy = today.getFullYear();
    const mm = String(today.getMonth() + 1).padStart(2, '0');
    const dd = String(today.getDate()).padStart(2, '0');
    const hh = String(today.getHours()).padStart(2, '0');
    const mi = String(today.getMinutes()).padStart(2, '0');
    return `${yyyy}-${mm}-${dd}T${hh}:${mi}`;
}


radioButtons.forEach(function(radioButton) {
    radioButton.addEventListener('change', function() {
        if (this.value === "1") {
            eventPop.style.display = 'block';
        } else {

            eventPop.style.display = 'none';
        }
    });
});

tinymce.init({
    selector: "#tinymceTextarea",
    language: 'ko_KR',
    plugins: "paste image imagetools",
    height: 600,
    width: 1200,
    toolbar: "undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | outdent indent | image",
    paste_data_images: true,
    file_picker_types: 'image',
    images_upload_handler: function(blobInfo, success, failure) {
        const file = new File([blobInfo.blob()], blobInfo.filename());

        const formData = new FormData();
        formData.append('file', file);

        fetch('/upload-image', {
            method: 'POST',
            body: formData
        })
            .then(response => response.json())
            .then(result => {
                if (result && result.fileUrl) {
                    uploadedImageUrls.push(result.fileUrl);
                    tinymce.activeEditor.dom.select(`img[src="${blobInfo.blobUri()}"]`).forEach(img => {
                        img.src = result.fileUrl;
                        img.width = 800;
                        img.height = 400;
                        img.style.maxWidth = "800px";
                        img.style.maxHeight = "400px";
                    });
                    success(result.fileUrl);
                } else {
                    failure('Image upload failed.');
                }
            })
            .catch(() => {
                failure('Image upload failed.');
            });
    },
    setup: function(editor) {
        editor.on('NodeChange', function(e) {
            const images = e.element.getElementsByTagName('img');
            for (let img of images) {
                if (img.width > 800) {
                    img.width = 800;
                }
                if (img.height > 400) {
                    img.height = 400;
                }
                img.style.maxWidth = "800px";
                img.style.maxHeight = "400px";
            }
        });
    }
});

document.getElementById('postForm').addEventListener('submit', handleSubmit);
function handleSubmit(event) {
    event.preventDefault();

    const formData = new FormData(event.target);

    const content = tinymce.get("tinymceTextarea").getContent();
    formData.set("content", content);
    if (uploadedImageUrls.length > 0) {
        formData.append("fileUrls", uploadedImageUrls.join(',')); // 여러 이미지 URL을 콤마로 구분하여 전달
    } else {
        formData.append("fileUrls", "");
    }

    // 제목, 카테고리, 내용 유효성 검사
    if (!boardTitle.value.trim()) {
        alert("제목을 입력해주세요.");
        return;
    }
    if (!tinymce.get("tinymceTextarea").getContent().trim()) {
        alert("내용을 입력해주세요.");
        return;
    }
    formData.set("startDate",  startDateField.value);
    formData.set("eventTitle", eventTitle.value);
    formData.set("endDate", endDateField.value);
    formData.set("point",  eventPointValue);


    fetch('/admin/boardJoin', {
        method: 'POST',
        body: formData
    })
        .then(response => {

                alert('게시글 등록에 성공했습니다.');
                window.location.href = '/';

        })
        .catch(error => {
            console.error('게시글 등록 중 오류 발생:', error);
            alert('게시글 등록 중 오류가 발생했습니다.');
        });
}