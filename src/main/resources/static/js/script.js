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

const beneficiary = document.getElementById("beneficiary")
const age = document.getElementById("age")
const contact = document.getElementById("contact")
const address = document.getElementById("address")

const beneficiary1 = document.getElementById("beneficiary1")
const age1 = document.getElementById("age1")
const contact1 = document.getElementById("contact1")
const address1 = document.getElementById("address1")

const beneficiary2 = document.getElementById("beneficiary2")
const age2 = document.getElementById("age2")
const contact2 = document.getElementById("contact2")
const address2 = document.getElementById("address2")

const dependent = document.getElementById("dependent")
const age3 = document.getElementById("age3")
const contact3 = document.getElementById("contact3")
const address3 = document.getElementById("address3")

const dependent1 = document.getElementById("dependent1")
const age4 = document.getElementById("age4")
const contact4 = document.getElementById("contact4")
const address4 = document.getElementById("address4")

const dependent2 = document.getElementById("dependent2")
const age5 = document.getElementById("age5")
const contact5 = document.getElementById("contact5")
const address5 = document.getElementById("address5")


insurance_type1.addEventListener("change", function (event){
	if (event.target.value == 'Vehicle_Insurance'){
		theDive.remove();
		// theDiv.style.display = "none";
		theDive1.style.display = "block";
		theDive2.style.display = "block";
		theDive3.style.display = "none";
		beneficiary.removeAttribute("required");
		age.removeAttribute("required");
		contact.removeAttribute("required");
		address.removeAttribute("required");
		beneficiary.value = "no";
		age.value = 100;
		contact.value = "no";
		address.value = "no";
		beneficiary1.value = "no";
		age1.value = 100;
		contact1.value = "no";
		address1.value = "no";
		beneficiary2.value = "no";
		age2.value = 100;
		contact2.value = "no";
		address2.value = "no";

		dependent.value = "no";
		age3.value = 100;
		contact3.value = "no";
		address3.value = "no";
		dependent1.value = "no";
		age4.value = 100;
		contact4.value = "no";
		address4.value = "no";
		dependent2.value = "no";
		age5.value = 100;
		contact5.value = "no";
		address5.value = "no";

		beneficiary1.removeAttribute("required");
		age1.removeAttribute("required");
		contact1.removeAttribute("required");
		address1.removeAttribute("required");

		beneficiary2.removeAttribute("required");
		age2.removeAttribute("required");
		contact2.removeAttribute("required");
		address2.removeAttribute("required");

		dependent.removeAttribute("required");
		age3.removeAttribute("required");
		contact3.removeAttribute("required");
		address3.removeAttribute("required");

		dependent1.removeAttribute("required");
		age4.removeAttribute("required");
		contact4.removeAttribute("required");
		address4.removeAttribute("required");

		dependent2.removeAttribute("required");
		age5.removeAttribute("required");
		contact5.removeAttribute("required");
		address5.removeAttribute("required");

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