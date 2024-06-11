const boardContent = document.getElementById("tinymceTextarea");
const categoryId = document.getElementsByName('categoryId');
const questionPointCheck = document.getElementById("question_point");
const boardTitle = document.getElementById("title_join");
const uploadedImageUrls = [];
const questionPoint = document.getElementById('questionPoint');
const myPointCheck =document.getElementById('myPointCheck').getAttribute('myPoint');

document.querySelectorAll('input[name="categoryId"]').forEach((radio) => {
    radio.addEventListener('change', function() {
        if (this.value === '6') {
            questionPointCheck.style.display= 'inline-block';

        } else {
            questionPointCheck.style.display= 'none';
            questionPoint.value=0;

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
/*

        if (!boardTitle.value.trim()) {
            alert("제목을 입력해주세요.");
            return;
        }
        let isChecked = false;
        for (let i = 0; i < categoryId.length; i++) {
            if (categoryId[i].checked) {
                isChecked = true;
                break;
            }
        }
        if (!isChecked) {
            alert("카테고리를 선택해주세요.");
            return;
        }
        if (!tinymce.get("tinymceTextarea").getContent().trim()) {
            alert("내용을 입력해주세요.");
            return;
        }
*/

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
    let categoryIdValue = null;
    let isChecked = false;
    for (let i = 0; i < categoryId.length; i++) {
        if (categoryId[i].checked) {
            isChecked = true;
            categoryIdValue = categoryId[i].value;
            break;
        }
    }
    if (!isChecked) {
        alert("카테고리를 선택해주세요.");
        return;
    }
    if (categoryIdValue === '6') {
        const questionPointValue = parseInt(questionPoint.value);
        const myPointCheckValue = parseInt(myPointCheck);
        if (myPointCheckValue < questionPointValue) {
            alert("포인트가 부족합니다. 현재 보유하신 포인트는 " + myPointCheckValue + " 입니다.");
            return;
        }
        formData.append("questionPoint", questionPoint.value);

        fetch('/question/join', { // 변경된 경로
            method: 'POST',
            body: formData
        })
            .then(response => {
                if (response.ok) {
                    alert("게시글 등록에 성공했습니다.");
                    window.location.href = '/';
                } else {
                    alert('게시글 등록에 실패했습니다.');
                }
            })
            .catch(error => {
                console.error('게시글 등록 중 오류 발생:', error);
                alert('게시글 등록 중 오류가 발생했습니다.');
            });
    } else {

        fetch('/boards/join', {
            method: 'POST',
            body: formData
        })
            .then(response => {
                if (response.ok) {
                    alert("게시글 등록에 성공했습니다.");
                    window.location.href = '/';
                } else {
                    alert('게시글 등록에 실패했습니다.');
                }
            })
            .catch(error => {
                console.error('게시글 등록 중 오류 발생:', error);
                alert('게시글 등록 중 오류가 발생했습니다.');
            });
    }
}