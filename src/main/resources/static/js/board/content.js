const boardId= document.getElementById("boardId_content").getAttribute('data-value');

function checkLoginAndPerformAction(action) {
    fetch("/boards/checkLogin")
        .then(response => response.json())
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
        fetch('/boards/dislike', { method: 'POST' })
            .then(response => response.json())
            .then(data => {
                updateDislikeCount(data.dislikeCount);
            })
            .catch(error => console.error('Error:', error));
    });
}

function likes() {
    checkLoginAndPerformAction(() => {
        fetch('/boards/like', { method: 'POST' })
            .then(response => response.json())
            .then(data => {
                updateLikeCount(data.likeCount);
            })
            .catch(error => console.error('Error:', error));
    });
}

function updateLikeCount(count) {
    document.getElementById("like-count").innerText = count;
}

function updateDislikeCount(count) {
    document.getElementById("dislike-count").innerText = count;
}