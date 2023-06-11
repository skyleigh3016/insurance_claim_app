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

// to stay the data in html input field

// Function to save form data to localStorage
// function saveFormData() {
// 	var nameInput = document.getElementById("age_coverage");
// 	var nameValue = nameInput.value;
// 	localStorage.setItem("formNameValue", nameValue);
// }
//
// // Function to populate form fields with saved data
// window.onload = function() {
// 	var savedNameValue = localStorage.getItem("formNameValue");
// 	if (savedNameValue) {
// 		var nameInput = document.getElementById("age_coverage");
// 		if (nameInput) {
// 			nameInput.value = savedNameValue;
// 		}
// 	}
// };

// function saveFormData() {
// 	var nameInput = document.getElementById('age_coverage');
// 	localStorage.setItem('formDataName', nameInput.value);
//
// 	// Add more code to save other form fields if needed
// }
//
// // Populate input fields with stored form data on page load
// window.onload = function() {
// 	var nameInput = document.getElementById('age_coverage');
// 	var storedName = localStorage.getItem('formDataName');
// 	if (storedName) {
// 		nameInput.value = storedName;
// 	}
//
// 	// Add more code to populate other form fields if needed
// };
// 		function saveData() {
// 	var inputField = document.getElementById("age_coverage");
// 	// var inputField1 = document.getElementById("tenure");
// 	// var inputField2 = document.getElementById("annual_premium");
// 	// var inputField3 = document.getElementById("monthly_premium");
// 	// var inputField4 = document.getElementById("amount_insured");
// 	var value = inputField.value;
// 	// var value1 = inputField1.value;
// 	// var value2 = inputField2.value;
// 	// var value3 = inputField3.value;
// 	// var value4 = inputField4.value;
// 	        localStorage.setItem("savedInput", value);
// 			// localStorage.setItem("savedInput1", value1);
// 			// localStorage.setItem("savedInput2", value2);
// 			// localStorage.setItem("savedInput3", value3);
// 			// localStorage.setItem("savedInput4", value4);
// }
//
// 	window.onload = function() {
// 	var savedValue = localStorage.getItem("savedInput");
// 		// var savedValue1 = localStorage.getItem("savedInput1");
// 		// var savedValue2 = localStorage.getItem("savedInput2");
// 		// var savedValue3 = localStorage.getItem("savedInput3");
// 		// var savedValue4 = localStorage.getItem("savedInput4");
// 	if (savedValue) {
// 	var inputField = document.getElementById("age_coverage");
// 		// var inputField1 = document.getElementById("tenure");
// 		// var inputField2 = document.getElementById("annual_premium");
// 		// var inputField3 = document.getElementById("monthly_premium");
// 		// var inputField4 = document.getElementById("amount_insured");
// 	inputField.value = savedValue;
// 	// inputField1.value = savedValue1;
// 	// inputField2.value = savedValue2;
// 	// inputField3.value = savedValue3;
// 	// inputField4.value = savedValue4;
// }
// };


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
const vehicle_type = document.getElementById("vehicle_type")

insurance_type.addEventListener("change", function (event){
	if (event.target.value == 'Vehicle_Insurance'){
		theDiv.style.display = "none";
		theDiv1.style.display = "block";
		theDiv2.style.display = "block";
		inputElement.value = 18;
		// theDiv1.setAttribute("data-custom-attribute", "required");
		vehicle_type.required = true;
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