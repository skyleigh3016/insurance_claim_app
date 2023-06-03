const allSideMenu = document.querySelectorAll('#sidebar .side-menu.top li a');

allSideMenu.forEach(item=> {
	const li = item.parentElement;

	item.addEventListener('click', function () {
		allSideMenu.forEach(i=> {
			i.parentElement.classList.remove('active');
		})
		li.classList.add('active');
	})
});

const initNavBar = () => {
	const menusEl = document.querySelectorAll('#sidebar .side-menu.top li a')
	menusEl.forEach(menu => menu.addEventListener('click', ()=> {
		const menuActiveEl = document.querySelector('.side-bar ul li.active')
		menuActiveEl.classList.remove('active')
		menu.classList.add('active')
	}))
}
initNavBar()
function checkdelete()
{
	return confirm('Are you sure you want to delete this record?');
}








// TOGGLE SIDEBAR
const menuBar = document.querySelector('#content nav .bx.bx-menu');
const sidebar = document.getElementById('sidebar');

menuBar.addEventListener('click', function () {
	sidebar.classList.toggle('hide');
})

const insurance_type = document.getElementById("insurance_type")
const theDiv = document.getElementById("theDiv")
const theDiv1 = document.getElementById("theDiv1")
const theDiv2 = document.getElementById("theDiv2")
var  inputElement = document.getElementById("age_coverage")

insurance_type.addEventListener("change", function (event){
	if (event.target.value == 'Vehicle_Insurance'){
		theDiv.style.display = "none";
		theDiv1.style.display = "block";
		theDiv2.style.display = "block";
		inputElement.value = 18;
	}else {
		theDiv.style.display = "block";
		theDiv1.style.display = "none";
		theDiv2.style.display = "none";
	}
})

const insurance_type1 = document.getElementById("insurance_type")
const theDive = document.getElementById("theDive")
const theDive1 = document.getElementById("theDive1")
const theDive2 = document.getElementById("theDive2")
const theDive3 = document.getElementById("theDive3")


insurance_type1.addEventListener("change", function (event){
	if (event.target.value == 'Vehicle_Insurance'){
		theDive.remove();
		// theDiv.style.display = "none";
		theDive1.style.display = "block";
		theDive2.style.display = "block";
		theDive3.remove();

	}
	else {
		theDive.restore();
		// theDiv.style.display = "block";
		theDive1.style.display = "none";
		// theDive2.style.display = "none";

	}
})






const searchButton = document.querySelector('#content nav form .form-input button');
const searchButtonIcon = document.querySelector('#content nav form .form-input button .bx');
const searchForm = document.querySelector('#content nav form');

searchButton.addEventListener('click', function (e) {
	if(window.innerWidth < 576) {
		e.preventDefault();
		searchForm.classList.toggle('show');
		if(searchForm.classList.contains('show')) {
			searchButtonIcon.classList.replace('bx-search', 'bx-x');
		} else {
			searchButtonIcon.classList.replace('bx-x', 'bx-search');
		}
	}
})





if(window.innerWidth < 768) {
	sidebar.classList.add('hide');
} else if(window.innerWidth > 576) {
	searchButtonIcon.classList.replace('bx-x', 'bx-search');
	searchForm.classList.remove('show');
}


window.addEventListener('resize', function () {
	if(this.innerWidth > 576) {
		searchButtonIcon.classList.replace('bx-x', 'bx-search');
		searchForm.classList.remove('show');
	}
})



const switchMode = document.getElementById('switch-mode');

switchMode.addEventListener('change', function () {
	if(this.checked) {
		document.body.classList.add('dark');
	} else {
		document.body.classList.remove('dark');
	}
})