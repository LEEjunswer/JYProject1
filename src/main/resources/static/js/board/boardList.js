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


let currentPage = 1;
const pageSize = 10;


fetchBoards(0, currentPage, pageSize);
const categoryLinks = document.querySelectorAll('#board_category a');
categoryLinks.forEach(link => {
    link.addEventListener('click', (event) => {
        event.preventDefault();
        const categoryId = event.target.getAttribute('data-category');
        fetchBoards(categoryId, currentPage, pageSize);
    });
});


function createPagingBoard(totalPages, currentPage, categoryId) {
    const pagingButtonDiv = document.getElementById('pagingBoard');
    pagingButtonDiv.innerHTML = '';

    for (let i = 0; i < totalPages; i++) {
        let pageButton = document.createElement('button');
        pageButton.className = "pagingComment";
        pageButton.textContent = i + 1;


        pageButton.onclick = function () {
            fetchBoards(categoryId, i + 1, 10);
        };

        pagingButtonDiv.appendChild(pageButton);
    }
}

function fetchBoards(categoryId, page, size) {
    fetch(`/boards/list/paging?categoryId=${categoryId}&page=${page}&size=${size}`, {
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
            }
            createPagingBoard(data.totalPages, data.number, categoryId);
        })
        .catch(error => console.error('Error:', error));
}

function displayComments(data) {
    const tbody = document.querySelector('.board_list tbody');
    tbody.innerHTML = '';

    if (data.boardList && data.boardList.length > 0) {
        data.boardList.forEach(list => {
            const tr = document.createElement('tr');

            const categoryTd = document.createElement('td');
            categoryTd.textContent = list.categoryInfo.categoryName;
            tr.appendChild(categoryTd);

            const titleTd = document.createElement('td');
            titleTd.textContent = list.title;
            titleTd.onclick = () => { location.href = `/boards/content/${list.boardId}`; };
            tr.appendChild(titleTd);

            const memberTd = document.createElement('td');
            memberTd.textContent = list.memberInfo.nickname;
            tr.appendChild(memberTd);

            const viewCntTd = document.createElement('td');
            viewCntTd.textContent = list.viewCnt;
            tr.appendChild(viewCntTd);
            const likesTd = document.createElement('td');
            const likes = list.likes != null ? list.likes : 0;
            const dislikes = list.dislikes != null ? list.dislikes : 0;

            if (likes > dislikes) {
                likesTd.textContent = likes - dislikes;
            } else {
                likesTd.textContent = -(dislikes - likes);
            }
            tr.appendChild(likesTd);

            const regDateTd = document.createElement('td');
            regDateTd.style.fontSize = '12px';
            const date = new Date(list.regDate);
            const formattedDate = `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()} ${date.getHours()}:${date.getMinutes()}`;
            regDateTd.textContent =  `${formattedDate}`;
                tr.appendChild(regDateTd);

            tbody.appendChild(tr);
        });
    } else {
        const tr = document.createElement('tr');
        const td = document.createElement('td');
        td.setAttribute('colspan', '6');
        td.textContent = '조회된 결과가 없습니다.';
        tr.appendChild(td);
        tbody.appendChild(tr);
    }
}


