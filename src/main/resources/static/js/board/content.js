const boardId= document.getElementById("boardId_content").getAttribute('data-value');

function checkLoginAndPerformAction(action) {
console.log("boardId"+boardId);
    fetch("/boards/checkLogin")
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(isLoggedIn => {
            if (isLoggedIn) {
                action();
            } else {
                alert("로그인 후 이용할 수 있습니다.");
            }
        })
        .catch(error => {
            console.error("Error checking login status:", error);
        });
}

function disLikes() {
    checkLoginAndPerformAction(() => {
        fetch(`/boards/disLikes?boardId=${boardId}`, { method: 'POST' })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok ' + response.statusText);
                }
                return response.json();
            })
            .then(data => {
                alert(data.message);
                if(data.dislikeCount) {
                    updateDislikeCount(data.dislikeCount);

                }

                })
            .catch(error =>
                alert("서버에 문제가 생겼습니다 잠시 후 다시 이용해주세요")
            );
    });
}

function likes() {
    checkLoginAndPerformAction(() => {
        fetch(`/boards/likes?boardId=${boardId}`, { method: 'POST',
        })
            .then(response => response.json())
            .then(data => {
                alert(data.message);
                if(data.likesCount) {
                    updateLikeCount(data.likesCount);
                }
                })
            .catch(error =>
                alert("서버에 문제가 생겼습니다 잠시 후 다시 이용해주세요")
            );
                    
    });
}

function updateLikeCount(count) {
    document.getElementById("like-count").innerText = count;
}

function updateDislikeCount(count) {
    document.getElementById("dislike-count").innerText = count;
}