document.addEventListener('DOMContentLoaded', function() {
    let mainPagingButtons = Array.from(document.getElementsByClassName("join-item"));
    let categoryButtons = Array.from(document.getElementsByClassName("btn_category"));
    let weekOrDayButtons = Array.from(document.getElementsByClassName("weekOrDay"));

    let currentCategoryId = 0;  // 기본 카테고리 ID
    let currentPage = 1;        // 기본 페이지
    let currentTab = 'week';    // 기본 탭 (week or day)

    function fetchData() {
        let url;
        if (currentTab === 'week') {
            url = `/home/bestWeekBoard?categoryId=${currentCategoryId}&page=${currentPage}&pageSize=5`;
        } else {
            url = `/home/bestDailyBoard?categoryId=${currentCategoryId}&page=${currentPage}&pageSize=5`;
        }

        fetch(url)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                const contentContainer = document.getElementById('weekend-content');
                contentContainer.innerHTML = data.boardList.map(board => createBoardCard(board, data.fileWeekBestList)).join('');
            })
            .catch(error => console.error('Error fetching data:', error));
    }

    function createBoardCard(board, fileList) {
        let file = fileList.find(file => file.boardId === board.boardId);
        let imageUrl = file && file.fileNames ? file.fileNames : '/static/img/unnamed.jpg';
        return `
            <div class="home card w-40 h-25 bg-base-100 shadow-xl">
                <a href="/boards/content/${board.boardId}">
                    <figure>
                        <img src="${imageUrl}" alt="img" />
                    </figure>
                    <div class="home card-body">
                        <p>${board.title}</p>
                    </div>
                </a>
            </div>
        `;
    }

    mainPagingButtons.forEach(function(button) {
        button.addEventListener('click', function() {
            mainPagingButtons.forEach(function(btn) {
                btn.classList.remove('btn-active');
            });
            button.classList.add('btn-active');
            currentPage = parseInt(button.textContent, 10);  // 페이지 번호 업데이트
            fetchData();  // 데이터 가져오기
        });
    });

    categoryButtons.forEach(function(button) {
        button.addEventListener('click', function() {
            categoryButtons.forEach(function(btn) {
                btn.classList.remove('tab-active');
            });
            button.classList.add('tab-active');
            currentCategoryId = parseInt(button.dataset.categoryId, 10);  // 카테고리 ID 업데이트
            currentPage = 1;  // 카테고리를 변경할 때 페이지 번호를 초기화
            fetchData();  // 데이터 가져오기
        });
    });

    weekOrDayButtons.forEach(function(button) {
        button.addEventListener('click', function() {
            weekOrDayButtons.forEach(function(btn) {
                btn.classList.remove('tab-active');
            });
            button.classList.add('tab-active');
            currentTab = button.dataset.tab;  // 탭 (week or day) 업데이트
            currentPage = 1;  // 탭을 변경할 때 페이지 번호를 초기화
            fetchData();  // 데이터 가져오기
        });
    });

});