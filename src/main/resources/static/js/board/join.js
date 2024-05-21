        const uploadedImageUrls = [];
        tinymce.init({
            selector: "#tinymceTextarea",
            language: 'ko_KR',
            plugins: "paste image imagetools",
            height: 800,
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

    // 이미지 URL이 없을 때 공백 문자열 추가
    if (uploadedImageUrls.length > 0) {
        formData.append("fileUrls", uploadedImageUrls[0]);
    } else {
        formData.append("fileUrls", "");
    }

    fetch('/boards/join', {
        method: 'POST',
        body: formData
    })
        .then(response => {
            if (response.ok) {
                alert("게시글 등록에 성공했습니다.");
                window.location.href = '/boards/list';
            } else {
                alert('게시글 등록에 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('게시글 등록 중 오류 발생:', error);
        });
}