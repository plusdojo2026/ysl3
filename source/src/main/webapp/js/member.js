/*-------------------------------*/
/*--------member_list.jsp--------*/
/*-------------------------------*/

// 検索処理
const searchBtn = document.getElementById("search-button");
const searchBar = document.getElementById("search-bar");

function executeSearch() {
	
	// 検索ワードを整形
	const keyword = searchBar.value.toLowerCase().trim();
	const memberRows = document.querySelectorAll(".member-row");

	memberRows.forEach(function(row) {
		const text = row.textContent.toLowerCase();
		
		if (text.includes(keyword)) {
			row.style.display = "";
		} else {
			row.style.display = "none";
		}
	});
}

// 要素が存在する場合のみイベントを登録
if (searchBtn && searchBar) {
	
	searchBtn.addEventListener("click", function(e) {
		e.preventDefault();
		executeSearch();
	});

	// 検索バーでのエンターキー入力時の検索
	searchBar.addEventListener("keypress", function(e) {
		if (e.key === "Enter") {
			e.preventDefault();
			executeSearch();
		}
	});
}


