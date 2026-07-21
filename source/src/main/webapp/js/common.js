/*--------------------------*/
/*--------ページ全体--------*/
/*--------------------------*/


/*------------------------*/
/*--------ヘッダー--------*/
/*------------------------*/

/*-- ヘッダー開閉処理 --*/

const menuButton = document.getElementById("menuButton");
const menu = document.getElementById("menu");
const closeButton = document.getElementById("closeButton");

/*-- ドロワーメニューが存在する時だけ処理を行う --*/
if (menuButton && menu && closeButton) {
	menuButton.addEventListener("click", () => {
		menu.classList.add("open");
	});

	closeButton.addEventListener("click", () => {
		menu.classList.remove("open");
	});

	document.addEventListener("click", (event) => {
		const clickedMenu = menu.contains(event.target);
		const clickedMenuButton = menuButton.contains(event.target);

		if (!clickedMenu && !clickedMenuButton) {
			menu.classList.remove("open");
		}
	});
}




/*------------------------*/
/*--------フッター--------*/
/*------------------------*/

