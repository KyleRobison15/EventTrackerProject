window.addEventListener('load', function(e){
	console.log('script.js loaded');
	init();
});

function clearTables(){
	let h1 = document.getElementById('error');
	h1.textContent = '';

	let clearables = document.getElementsByClassName('clear');

	for(let i = 0; i < clearables.length; i++){
		clearables[i].textContent = '';
	}
}

//////////////////////////////////////////  EVENT LISTENERS ///////////////////////////////////////////////////////

function init(){

	/////////////////// GET ALL ORDERS //////////////////////
	let allOrdersButton = document.getElementById('allOrders');

	allOrdersButton.addEventListener('click', function(e){
		e.preventDefault();
		getReqs();

	});

	/////////////////// GET ORDER BY ID //////////////////////
	let orderByIdButton = document.getElementById('orderById');

	orderByIdButton.addEventListener('click', function(e){
		e.preventDefault();
		let form =  document.getElementById('orderIdForm');
		getReqById(form.id.value);

	});

	/////////////////// GET ORDERS BY PRODUCT ID //////////////////////
	let orderByProdIdButton = document.getElementById('orderByProdId');

	orderByProdIdButton.addEventListener('click', function(e){
		e.preventDefault();
		let form =  document.getElementById('orderProdIdForm');
		getProdReqs(form.id.value);

	});

	/////////////////// GET ORDERS BY CUSTOMER ID //////////////////////
	let orderByCustIdButton = document.getElementById('orderByCustId');

	orderByCustIdButton.addEventListener('click', function(e){
		e.preventDefault();
		let form =  document.getElementById('orderCustIdForm');
		getCustReqs(form.id.value);

	});

	/////////////////// GET ALL CUSTOMERS //////////////////////
	let allCustomers = document.getElementById('allCustomers');

	allCustomers.addEventListener('click', function(e){
		e.preventDefault();
		getCustomers();

	});

	/////////////////// GET ALL PRODUCTS //////////////////////
	let allProducts = document.getElementById('allProducts');

	allProducts.addEventListener('click', function(e){
		e.preventDefault();
		getProducts();

	});

	/////////////////// ADD NEW ORDER //////////////////////
	let addOrder = document.getElementById('addOrder');
	
	addOrder.addEventListener('click', function(e){
		e.preventDefault();
		generateCustomerSelect();
	});

	let addOrderButton = document.getElementById('addOrderModalButton');
	addOrderButton.addEventListener('click', function(e){
		e.preventDefault();
		let form =  document.getElementById('addOrderForm');

		let reqObject = {

			dueDate: form.dueDate.value,

		};


		addReq(reqObject);

	});

	/////////////////// ADD NEW CUSTOMER //////////////////////
	let addCustomerButton = document.getElementById('addCustomerModalButton');

	addCustomerButton.addEventListener('click', function(e){
		e.preventDefault();
		let form =  document.getElementById('addCustomerForm');

		let custObj = {
			firstName: form.firstName.value,
			lastName: form.lastName.value,
			email: form.email.value,
			phone: form.phone.value,
			street: form.street.value,
			city: form.city.value,
			stateAbbreviation: form.stateAbbreviation.value,
			postalCode: form.postalCode.value
		};

		addCustomer(custObj);

	});

	/////////////////// ADD NEW PRODUCT //////////////////////
	let addProductButton = document.getElementById('addProductModalButton');

	addProductButton.addEventListener('click', function(e){
		e.preventDefault();
		let form =  document.getElementById('addProductForm');

		let prodObj = {
			name: form.name.value,
			unitQuantity: form.unitQuantity.value,
			unitPrice: form.unitPrice.value,
			imageUrl: form.imageUrl.value,
		};

		addProduct(prodObj);

	});

}

//////////////////////////////////////////  AJAX REQUESTS ///////////////////////////////////////////////////////

/////////////////// GET ALL ORDERS //////////////////////
function getReqs() {

		let xhr = new XMLHttpRequest();
		xhr.open('GET', 'api/reqs');
		xhr.onreadystatechange = function () {
			if(xhr.readyState === 4){
				if (xhr.status === 200) {
					let reqs = JSON.parse(xhr.responseText);
					displayReqs(reqs);
				}else{
					displayError('No orders found.');
				}
			}
		}
		xhr.send();
}
/////////////////// GET ORDERS BY PRODUCT //////////////////////
function getProdReqs(id) {

		let xhr = new XMLHttpRequest();
		xhr.open('GET', `api/products/${id}/reqs`);
		xhr.onreadystatechange = function () {
			if(xhr.readyState === 4){
				if (xhr.status === 200) {
					let reqs = JSON.parse(xhr.responseText);
					displayReqs(reqs);
				}else{
					displayError('No orders found for this product.');
				}
			}
		}
		xhr.send();
}
/////////////////// GET ORDERS BY CUSTOMER //////////////////////
function getCustReqs(id) {

		let xhr = new XMLHttpRequest();
		xhr.open('GET', `api/customers/${id}/reqs`);
		xhr.onreadystatechange = function () {
			if(xhr.readyState === 4){
				if (xhr.status === 200) {
					let reqs = JSON.parse(xhr.responseText);
					displayReqs(reqs);
				} else{
					displayError('No orders found for this customer.');
				}
			}
		}
		xhr.send();
}

/////////////////// GET ORDER BY ID //////////////////////
function getReqById(id) {
	let xhr = new XMLHttpRequest();
	xhr.open('GET', `api/reqs/${id}`);
	xhr.onreadystatechange = function () {
		if(xhr.readyState === 4){
			if (xhr.status === 200) {
				let req = JSON.parse(xhr.responseText);
				displaySingleReq(req);
			} else{
				displayError('No order found.');
			}
		}
	}
	xhr.send();
}

/////////////////// GET ALL CUSTOMERS //////////////////////
function getCustomers() {
	let xhr = new XMLHttpRequest();
	xhr.open('GET', `api/customers`);
	xhr.onreadystatechange = function () {
		if(xhr.readyState === 4){
			if (xhr.status === 200) {
				let customers = JSON.parse(xhr.responseText);
				displayCustomers(customers);
			} else{
				displayError('No customers found.');
			}
		}
	}
	xhr.send();
}


/////////////////// ADD NEW CUSTOMER //////////////////////
function addCustomer(custObj) {
	let xhr = new XMLHttpRequest();
	xhr.open('POST', `api/customers`);
	xhr.onreadystatechange = function () {
		if(xhr.readyState === 4){
			if (xhr.status === 201) {
				let newCust = JSON.parse(xhr.responseText);
				console.log(newCust);
				displaySingleCustomer(newCust);
			} else{
				displayError(`Error creating customer: ${xhr.status}`);
			}
		}
	}

	xhr.setRequestHeader("Content-type", "application/json");
	var custObjJson = JSON.stringify(custObj);
	xhr.send(custObjJson);
}

/////////////////// ADD NEW PRODUCT //////////////////////
function addProduct(prodObj) {
	let xhr = new XMLHttpRequest();
	xhr.open('POST', `api/products`);
	xhr.onreadystatechange = function () {
		if(xhr.readyState === 4){
			if (xhr.status === 201) {
				let newProd = JSON.parse(xhr.responseText);
				console.log(newProd);
				displaySingleProduct(newProd);
			} else{
				displayError(`Error creating product: ${xhr.status}`);
			}
		}
	}

	xhr.setRequestHeader("Content-type", "application/json");
	var prodObjJson = JSON.stringify(prodObj);
	xhr.send(prodObjJson);
}

/////////////////// GET ALL PRODUCTS //////////////////////
function getProducts() {
	let xhr = new XMLHttpRequest();
	xhr.open('GET', `api/products`);
	xhr.onreadystatechange = function () {
		if(xhr.readyState === 4){
			if (xhr.status === 200) {
				let products = JSON.parse(xhr.responseText);
				displayProducts(products);
			} else{
				displayError('No products found.');
			}
		}
	}
	xhr.send();
}

//////////////////////////////////////////  DOM MANIPULATION ///////////////////////////////////////////////////////
/////////////////// DISPLAY ORDERS //////////////////////
function displayError(msg){
	clearTables();
	let h1 = document.getElementById('error');
	h1.textContent = msg;

}

/////////////////// DISPLAY ORDERS //////////////////////
function displayReqs(reqs){
	clearTables();

	// Build Table Header Row
	let headers = ['Date Placed', 'Due Date', 'First Name', 'Last Name', 'Products'];
	for(const header of headers){
		let th = document.createElement('th');
		th.textContent = header;
		allReqsHeaderRow.appendChild(th);
	};

	// Populate Table Data
	for(let i=0; i < reqs.length ; i++){
		let tr = document.createElement('tr');
		orderTableBody.appendChild(tr);

		let datePlaced = document.createElement('td');
		datePlaced.textContent = reqs[i].datePlaced;
		tr.appendChild(datePlaced);

		let dueDate = document.createElement('td');
		dueDate.textContent = reqs[i].dueDate;
		tr.appendChild(dueDate);
		
		let firstName = document.createElement('td');
		firstName.textContent = reqs[i].customer.firstName;
		tr.appendChild(firstName);
		
		let lastName = document.createElement('td');
		lastName.textContent = reqs[i].customer.lastName;
		tr.appendChild(lastName);

		let prods = document.createElement('td');

			for (let prod of reqs[i].products){
				prods.textContent += `${prod.name}, `;
			}
		tr.appendChild(prods);
	}
}

/////////////////// DISPLAY SINGLE ORDER //////////////////////
function displaySingleReq(req){
	clearTables();

	// Build Order Table Header Row
	let headers = ['Date Placed', 'Due Date', 'First Name', 'Last Name', 'Email', 'Phone', 'Street', 
	'City', 'State', 'ZIP'];

	for(const header of headers){
		let th = document.createElement('th');
		th.textContent = header;
		singleReqHeaderRow.appendChild(th);
	};

	// Build Product Table Header Row
	let pHeaders = ['Product', 'Unit Qty', 'Unit Price', 'Units Purchased', 'Total Cost']

	for(const pHeader of pHeaders){
		let th = document.createElement('th');
		th.textContent = pHeader;
		singleReqProdHeaders.appendChild(th);
	};

	// Populate Table Data
	let tr = document.createElement('tr');
	singleReqTableBody.appendChild(tr);

	let datePlaced = document.createElement('td');
	datePlaced.textContent = req.datePlaced;
	tr.appendChild(datePlaced);

	let dueDate = document.createElement('td');
	dueDate.textContent = req.dueDate;
	tr.appendChild(dueDate);

	let firstName = document.createElement('td');
	firstName.textContent = req.customer.firstName;
	tr.appendChild(firstName);
	
	let lastName = document.createElement('td');
	lastName.textContent = req.customer.lastName;
	tr.appendChild(lastName);

	let email = document.createElement('td');
	email.textContent = req.customer.email;
	tr.appendChild(email);
	
	let phone = document.createElement('td');
	phone.textContent = req.customer.phone;
	tr.appendChild(phone);

	let street = document.createElement('td');
	street.textContent = req.customer.street;
	tr.appendChild(street);

	let city = document.createElement('td');
	city.textContent = req.customer.city;
	tr.appendChild(city);

	let state = document.createElement('td');
	state.textContent = req.customer.state;
	tr.appendChild(state);

	let postalCode = document.createElement('td');
	postalCode.textContent = req.customer.postalCode;
	tr.appendChild(postalCode);

	for (let prod of req.products){
		let row = document.createElement('tr');
		prodTableBody.appendChild(row);

		let name = document.createElement('td');
		name.textContent = `${prod.name}`;
		row.appendChild(name);

		let qty = document.createElement('td');
		qty.textContent = `${prod.unitQuantity}`;
		row.appendChild(qty);

		let price = document.createElement('td');
		price.textContent = `${prod.unitPrice}`;
		row.appendChild(price);

		let purch = document.createElement('td');
		purch.textContent = 'TODO';
		row.appendChild(purch);

		let totalCost = document.createElement('td');
		totalCost.textContent = 'TODO';
		row.appendChild(totalCost);

	}
}
function displayCustomers(custs){

	clearTables();

	// Build Customers Table Header Row
	let headers = ['First Name', 'Last Name', 'Email', 'Phone', 'Street', 
	'City', 'State', 'ZIP'];

	for(const header of headers){
		let th = document.createElement('th');
		th.textContent = header;
		customersHeaderRow.appendChild(th);
	};

	// Populate Table Data
	for(let i=0; i < custs.length ; i++){
		let tr = document.createElement('tr');
		customersTableBody.appendChild(tr);

		let firstName = document.createElement('td');
		firstName.textContent = custs[i].firstName;
		tr.appendChild(firstName);

		let lastName = document.createElement('td');
		lastName.textContent = custs[i].lastName;
		tr.appendChild(lastName);
		
		let email = document.createElement('td');
		email.textContent = custs[i].email;
		tr.appendChild(email);

		let phone = document.createElement('td');
		phone.textContent = custs[i].phone;
		tr.appendChild(phone);

		let street = document.createElement('td');
		street.textContent = custs[i].street;
		tr.appendChild(street);

		let city = document.createElement('td');
		city.textContent = custs[i].city;
		tr.appendChild(city);

		let stateAbbreviation = document.createElement('td');
		stateAbbreviation.textContent = custs[i].stateAbbreviation;
		tr.appendChild(stateAbbreviation);

		let postalCode = document.createElement('td');
		postalCode.textContent = custs[i].postalCode;
		tr.appendChild(postalCode);

	} 
}

function displayProducts(prods){

	clearTables();

	// Build Customers Table Header Row
	let headers = ['Name', 'Unit Qty', 'Unit Price'];

	for(const header of headers){
		let th = document.createElement('th');
		th.textContent = header;
		productsHeaderRow.appendChild(th);
	};

	// Populate Table Data
	for(let i=0; i < prods.length ; i++){
		let tr = document.createElement('tr');
		productsTableBody.appendChild(tr);

		let name = document.createElement('td');
		name.textContent = prods[i].name;
		tr.appendChild(name);

		let unitQuantity = document.createElement('td');
		unitQuantity.textContent = prods[i].unitQuantity;
		tr.appendChild(unitQuantity);

		let unitPrice = document.createElement('td');
		unitPrice.textContent = prods[i].unitPrice;
		tr.appendChild(unitPrice);

	} 
}

function displaySingleCustomer(cust){

	clearTables();
	let h1 = document.getElementById('custAddSuccess');
	h1.textContent = 'Customer Added: ';

	// Build Customers Table Header Row
	let headers = ['First Name', 'Last Name', 'Email', 'Phone', 'Street', 
	'City', 'State', 'ZIP'];

	for(const header of headers){
		let th = document.createElement('th');
		th.textContent = header;
		customersHeaderRow.appendChild(th);
	};

	// Populate Table Data
		let tr = document.createElement('tr');

		customersTableBody.appendChild(tr);
		let firstName = document.createElement('td');
		firstName.textContent = cust.firstName;
		tr.appendChild(firstName);

		let lastName = document.createElement('td');
		lastName.textContent = cust.lastName;
		tr.appendChild(lastName);
		
		let email = document.createElement('td');
		email.textContent = cust.email;
		tr.appendChild(email);

		let phone = document.createElement('td');
		phone.textContent = cust.phone;
		tr.appendChild(phone);

		let street = document.createElement('td');
		street.textContent = cust.street;
		tr.appendChild(street);

		let city = document.createElement('td');
		city.textContent = cust.city;
		tr.appendChild(city);

		let stateAbbreviation = document.createElement('td');
		stateAbbreviation.textContent = cust.stateAbbreviation;
		tr.appendChild(stateAbbreviation);

		let postalCode = document.createElement('td');
		postalCode.textContent = cust.postalCode;
		tr.appendChild(postalCode);

}

function displaySingleProduct(prod){
	let h1 = document.getElementById('prodAddSuccess');
	h1.textContent = 'Product Added: ';

	clearTables();

	// Build Customers Table Header Row
	let headers = ['Name', 'Unit Qty', 'Unit Price'];

	for(const header of headers){
		let th = document.createElement('th');
		th.textContent = header;
		productsHeaderRow.appendChild(th);
	};

	// Populate Table Data
		let tr = document.createElement('tr');
		productsTableBody.appendChild(tr);

		let name = document.createElement('td');
		name.textContent = prod.name;
		tr.appendChild(name);

		let unitQuantity = document.createElement('td');
		unitQuantity.textContent = prod.unitQuantity;
		tr.appendChild(unitQuantity);

		let unitPrice = document.createElement('td');
		unitPrice.textContent = prod.unitPrice;
		tr.appendChild(unitPrice);
}

function generateCustomerSelect(){
	clearTables();
	let custSelect = document.getElementById('custSelect');
	let selectedOpt = document.createElement('option');

	selectedOpt.classList.add('clear');
	selectedOpt.textContent = 'Select A Customer';
	selectedOpt.selected = true;
	custSelect.appendChild(selectedOpt);

	let custs = null;

	let xhr = new XMLHttpRequest();
	xhr.open('GET', `api/customers`);
	xhr.onreadystatechange = function () {
		if(xhr.readyState === 4){
			if (xhr.status === 200) {
				custs = JSON.parse(xhr.responseText);

				
				for(const cust of custs){

					let custOpt = document.createElement('option');
					custSelect.appendChild(custOpt);
					custOpt.textContent = `${cust.firstName} ${cust.lastName}`;
					custOpt.classList.add('clear');
					custOpt.value = cust.id;
			
				}
			}
		}
	}
	xhr.send();

}