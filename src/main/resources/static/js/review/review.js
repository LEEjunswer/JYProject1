const boardCommentContainer = document.getElementById('boardCommentContainer');
const boardCommentWrite = document.getElementById('boardCommentWrite');
let currentPage = 0;
const pageSize = 10;
const boardCommentList = document.getElementById('boardCommentList');
let getComments =document.getElementsByClassName('comment-value');
let loginCheck = document.getElementById('loginCheck').getAttribute('data-value');
if(document.getElementsByClassName('comment-delBtn')) {
    let deleteButtons = document.getElementsByClassName('comment-delBtn');
        for(let i = 0 ; i<deleteButtons.length; i++){
            let deleteButton = deleteButtons[i];
            let replyId =    deleteButton.getAttribute('reply-value');
             deleteButton.addEventListener('click' ,()=>{
                 deleteComment(replyId)
            });

    }

}
if(document.getElementsByClassName('comment-updateBtn')){
    let updateButtons = document.getElementsByClassName('comment-updateBtn');
    for(let i = 0; i<updateButtons.length; i++){
        let updateButton = updateButtons[i];
        let replyId = updateButton.getAttribute('reply-value');
        let getComment = getComments[i];
        updateButton.addEventListener('click',()=>{
            if(updateButton.textContent.trim() === '수정') {
                updateButton.textContent = '완료';
                getComment.contentEditable = true;
                getComment.style.backgroundColor = "grey";
            }else{
                updateComment(replyId,getComment.textContent);
                getComment.contentEditable = false;
                getComment.style.backgroundColor = "white";
                updateButton.textContent = '수정';
            }
            });

    }
}


fetchComments(boardId, currentPage,pageSize);
function fetchComments(boardId, page,size) {

    fetch(`/review/content/paging?boardId=${boardId}&page=${page}&size=${size}`, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            if (data) {
                currentPage = page;
                displayComments(data);
            console.log("진입갑체크");
            }
            createPagingBoardComment(data.totalPages, data.number,boardId)

        })
        .catch(error => console.error('Error:', error));
}

function displayComments(data) {
    const list = document.getElementById('boardCommentList');
    const boardCommentId = document.getElementById('boardId_content').getAttribute('data-value');
    list.innerHTML = '';  // Clear previous comments
    data.replyList.forEach(comment => {
        const date = new Date(comment.regDate);
        const formattedDate = `${date.getFullYear()}년 ${date.getMonth() + 1}월 ${date.getDate()}일 ${date.getHours()}시 ${date.getMinutes()}분`;

        console.log(comment);
        const commentCardElement = document.createElement('div');
        commentCardElement.className='comment card w-full bg-base-100 shadow-xl relative';
        const commentHeaderElement = document.createElement('div');
        commentHeaderElement.className='comment card-body block m-0';
        const commentTitle = document.createElement('span');
        commentTitle.className='comment-span text-sm';
        commentTitle.textContent = `작성자 : ${comment.memberInfo.nickname}  작성일 : ${formattedDate} `;
        const commentP = document.createElement('p');
        commentP.className= 'comment-value text-md';
        if(comment.deleteDate === null) {
            commentP.textContent = comment.content;
        }
        if(comment.deleteDate !== null) {
            commentP.textContent = "삭제된 게시글입니다.";
            commentP.className='text-red-800';
        }
        commentHeaderElement.appendChild(commentTitle);
        commentHeaderElement.appendChild(commentP);
        commentCardElement.appendChild(commentHeaderElement);
        if (loginCheck === comment.memberInfo.nickname || loginCheck === "admin" ) {
            const flexDiv = document.createElement('div');
            flexDiv.className = 'absolute top-0 right-0';
            const deleteButton = document.createElement('button');
            deleteButton.className='btn btn-square btn-outline';
            deleteButton.value=comment.id;
            const updateButton = document.createElement('button');
            updateButton.textContent='수정';
            updateButton.className=' absolute top-0 right-20 btn btn-square btn-outline';
            updateButton.value=comment.id;
            const updateSubmit =document.createElement('button');
            updateButton.addEventListener('click', ()=>{
                updateButton.style.display='none';
                updateSubmit.textContent='수정하기';
                updateSubmit.className='absolute top-0 right-20 w-24 btn btn-square btn-outline';
                commentP.contentEditable = true;
                commentP.className='bg-slate-200';
                updateSubmit.addEventListener('click',()=>{
                    const commentChange= commentP.textContent;
                    if(commentChange.trim()<1){
                        alert('공백 댓글을 수정하실 수 없습니다');
                        return false;
                    }
                    updateSubmit.style.display='none';
                    updateButton.style.display='block';
                    commentP.contentEditable = false;
                    commentP.className='';
                    const updateValueId = updateButton.value;

                    updateComment(updateValueId,commentChange);
                })
            });
            deleteButton.innerHTML=`  <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                        <path class="absolute top-0 right-0" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                    </svg>`;
            deleteButton.addEventListener('click', () => {
                const commentId = deleteButton.value;
                deleteComment(commentId);
            });
            flexDiv.appendChild(updateSubmit);
            flexDiv.appendChild(updateButton);
            flexDiv.appendChild(deleteButton);
            commentCardElement.appendChild(flexDiv);
        }
        list.appendChild(commentCardElement);
    });
}


function createPagingBoardComment(totalPages, currentPage, boardId) {
    const pagingButtonDiv = document.getElementById('pagingComment');
    pagingButtonDiv.innerHTML = '';

    for (let i = 0; i < totalPages; i++) {
        let pageButton = document.createElement('button');
        pageButton.className = "pagingComment";
        pageButton.textContent = i + 1;

        pageButton.onclick = function () {
            fetchComments(boardId, i+1, 10);
        };

        pagingButtonDiv.appendChild(pageButton);
    }
}

// 뼈대만 작성중 예외처리
function reviewContentJoin(){
    const commentInput = document.getElementById('contentInput').value;
    if(commentInput.trim().length < 2){
        alert('댓글 2글자 이상 입력해야합니다.');
        return;
    }
    const data = {
        content: commentInput
    };
    fetch(`/boards/content/${boardId}/reply`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }) .then(response => response.json())
        .then(data => {

                alert(data.message);
                window.location.reload();
        })

        .catch(error => {
            console.error('Error:', error);
            alert("서버에 문제가 생겼습니다. 잠시 후 다시 이용해주세요.");
        });
}

function deleteComment(commentId) {
    const data = {
        replyId : commentId,
    };

    fetch(`/review/comment/delete`, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    /*    .then(response => {
            if (response.redirected) {
                window.location.href = response.url;
            } else {
                return response.json();
            }
        })*/
        .then(response => response.json())
        .then(data => {

            alert(data.message);

        })
        .catch(error => console.error('Error:', error));
}
function updateComment(commentId, commentChange) {
    if(commentChange.trim().length < 2){
        alert("공백 또는 두 글자 이하로 수정하실 수 없습니다");
        return;
    }
    const data = {
        replyId : commentId,
        content: commentChange
    };

    fetch(`/review/comment/update`, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(data => {

            alert(data.message);

        })
        .catch(error => console.error('Error:', error));
}



// 댓글 닫기 클릭시 기능
function closeReview(){
    let closeButton = document.getElementById("close_review");
        if(closeButton.textContent === 'ㅣ 댓글닫기 ㅣ'){
            closeButton.textContent = "ㅣ 댓글열기 ㅣ";
            boardCommentContainer.style.display = 'none';
        }else{
            closeButton.textContent = 'ㅣ 댓글닫기 ㅣ';
            boardCommentContainer.style.display = 'block';
        }
}

