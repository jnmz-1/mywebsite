document.addEventListener("DOMContentLoaded", () => {
    const userInfoElement = document.getElementById("user-info");
    const userName = localStorage.getItem("userName");
  
    // Display the user's name if logged in
    if (userName) {
      userInfoElement.innerHTML = `<p>Welcome, ${userName}</p>`;
    }
  
    // Handle login form submission
    const loginForm = document.getElementById("login-form");
    if (loginForm) {
      loginForm.addEventListener("submit", (e) => {
        e.preventDefault();
        const name = document.getElementById("name").value;
        localStorage.setItem("userName", name);
        alert(`Welcome, ${name}!`);
        window.location.href = "index.html";
      });
    }
  
    // Handle signup form submission
    const signupForm = document.getElementById("signup-form");
    if (signupForm) {
      signupForm.addEventListener("submit", (e) => {
        e.preventDefault();
        const name = document.getElementById("name").value;
        localStorage.setItem("userName", name);
        alert(`Account created for ${name}!`);
        window.location.href = "index.html";
      });
    }
  
    // Handle adding items to cart
    document.querySelectorAll(".add-to-cart").forEach((button) => {
      button.addEventListener("click", () => {
        alert("Item added to cart!");
      });
    });
  });
  document.addEventListener("DOMContentLoaded", () => {
    const checkoutButton = document.getElementById("checkout-button");
    const paymentModal = document.getElementById("payment-modal");
    const closeButton = document.querySelector(".close-button");
    const paymentForm = document.getElementById("payment-form");
  
    // Show the modal when "Proceed to Checkout" is clicked
    checkoutButton.addEventListener("click", () => {
      paymentModal.style.display = "block";
    });
  
    // Close the modal when the close button is clicked
    closeButton.addEventListener("click", () => {
      paymentModal.style.display = "none";
    });
  
    // Close the modal when clicking outside the modal content
    window.addEventListener("click", (event) => {
      if (event.target === paymentModal) {
        paymentModal.style.display = "none";
      }
    });
  
    // Handle payment form submission
    paymentForm.addEventListener("submit", (e) => {
      e.preventDefault();
      const selectedPaymentMethod = document.querySelector(
        'input[name="payment-method"]:checked'
      ).value;
      alert(`Payment confirmed with ${selectedPaymentMethod}. Thank you!`);
      paymentModal.style.display = "none";
    });
  });

  document.addEventListener("DOMContentLoaded", () => {
    const cartKey = "cartItems"; // Key for storing cart in localStorage
  
    // Fetch cart from localStorage or initialize an empty array
    let cart = JSON.parse(localStorage.getItem(cartKey)) || [];
  
    // Update cart in localStorage
    function updateCartStorage() {
      localStorage.setItem(cartKey, JSON.stringify(cart));
    }
  
    // Add item to cart
    const addToCartButtons = document.querySelectorAll(".add-to-cart");
    addToCartButtons.forEach((button) => {
      button.addEventListener("click", (e) => {
        const itemName = e.target.dataset.name;
        const itemPrice = parseFloat(e.target.dataset.price);
        const existingItem = cart.find((item) => item.name === itemName);
  
        if (existingItem) {
          existingItem.quantity += 1; // Increase quantity if item already exists
        } else {
          cart.push({ name: itemName, price: itemPrice, quantity: 1 });
        }
  
        updateCartStorage();
        alert(`${itemName} added to cart!`);
      });
    });
  
    // Display cart items on the cart page
    const cartItemsContainer = document.getElementById("cart-items");
    const cartSubtotalElement = document.getElementById("cart-subtotal");
  
    if (cartItemsContainer) {
      function renderCartItems() {
        cartItemsContainer.innerHTML = ""; // Clear existing items
        let subtotal = 0;
  
        cart.forEach((item, index) => {
          const total = item.price * item.quantity;
          subtotal += total;
  
          const row = document.createElement("tr");
          row.innerHTML = `
            <td>${item.name}</td>
            <td>$${item.price.toFixed(2)}</td>
            <td>
              <button class="decrease-qty" data-index="${index}">-</button>
              ${item.quantity}
              <button class="increase-qty" data-index="${index}">+</button>
            </td>
            <td>$${total.toFixed(2)}</td>
            <td><button class="remove-item" data-index="${index}">Remove</button></td>
          `;
          cartItemsContainer.appendChild(row);
        });
  
        cartSubtotalElement.textContent = subtotal.toFixed(2);
      }
  
      renderCartItems();
  
      // Handle quantity updates and item removal
      cartItemsContainer.addEventListener("click", (e) => {
        const index = e.target.dataset.index;
  
        if (e.target.classList.contains("increase-qty")) {
          cart[index].quantity += 1;
        } else if (e.target.classList.contains("decrease-qty") && cart[index].quantity > 1) {
          cart[index].quantity -= 1;
        } else if (e.target.classList.contains("remove-item")) {
          cart.splice(index, 1); // Remove item
        }
  
        updateCartStorage();
        renderCartItems();
      });
    }
  });

  


  document.addEventListener("DOMContentLoaded", () => {
    const cartKey = "cartItems"; // Key for storing cart in localStorage
    let cart = JSON.parse(localStorage.getItem(cartKey)) || []; // Initialize cart
  
    // Save cart to localStorage
    const saveCart = () => {
      localStorage.setItem(cartKey, JSON.stringify(cart));
    };
  
    // Add item to cart
    const addToCart = (name, price) => {
      const existingItem = cart.find((item) => item.name === name);
  
      if (existingItem) {
        existingItem.quantity += 1; // Increase quantity if item exists
      } else {
        cart.push({ name, price, quantity: 1 }); // Add new item
      }
  
      saveCart();
      alert(`${name} has been added to your cart!`);
    };
  
    // Render cart items in cart.html
    const renderCart = () => {
      const cartItemsContainer = document.getElementById("cart-items");
      const cartSubtotalElement = document.getElementById("cart-subtotal");
      if (!cartItemsContainer || !cartSubtotalElement) return;
  
      cartItemsContainer.innerHTML = ""; // Clear previous items
      let subtotal = 0;
  
      cart.forEach((item, index) => {
        const total = item.price * item.quantity;
        subtotal += total;
  
        const row = document.createElement("tr");
        row.innerHTML = `
          <td>${item.name}</td>
          <td>$${item.price.toFixed(2)}</td>
          <td>
            <button class="decrease-qty" data-index="${index}">-</button>
            ${item.quantity}
            <button class="increase-qty" data-index="${index}">+</button>
          </td>
          <td>$${total.toFixed(2)}</td>
          <td><button class="remove-item" data-index="${index}">Remove</button></td>
        `;
        cartItemsContainer.appendChild(row);
      });
  
      cartSubtotalElement.textContent = subtotal.toFixed(2);
    };
  
    // Update cart item quantity or remove item
    const updateCart = (action, index) => {
      if (action === "increase") {
        cart[index].quantity += 1;
      } else if (action === "decrease" && cart[index].quantity > 1) {
        cart[index].quantity -= 1;
      } else if (action === "remove") {
        cart.splice(index, 1);
      }
  
      saveCart();
      renderCart();
    };
  
    // Event listeners for cart updates
    const cartItemsContainer = document.getElementById("cart-items");
    if (cartItemsContainer) {
      cartItemsContainer.addEventListener("click", (event) => {
        const index = event.target.dataset.index;
  
        if (event.target.classList.contains("increase-qty")) {
          updateCart("increase", index);
        } else if (event.target.classList.contains("decrease-qty")) {
          updateCart("decrease", index);
        } else if (event.target.classList.contains("remove-item")) {
          updateCart("remove", index);
        }
      });
    }
  
    // Attach add to cart functionality on product buttons
    const productButtons = document.querySelectorAll(".add-to-cart");
    productButtons.forEach((button) => {
      button.addEventListener("click", () => {
        const name = button.dataset.name;
        const price = parseFloat(button.dataset.price);
        addToCart(name, price);
      });
    });
  
    // Render cart on load if on cart.html
    renderCart();

  });



  /* General Reset */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: Arial, sans-serif;
  background-color: #f0f8ff; /* Light blue background */
  color: #333;
  line-height: 1.6;
}

header {
  background-color: #0077b6; /* Blue nav bar */
  color: white;
  padding: 1rem 0;
}

header .navbar {
  display: flex;
  justify-content: space-around;
  align-items: center;
  list-style: none;
}

header .navbar li {
  margin: 0 10px;
}

header .navbar a {
  color: white;
  text-decoration: none;
  font-weight: bold;
}

header .navbar a:hover {
  text-decoration: underline;
}

header .user-name {
  color: white;
  font-size: 1rem;
  font-weight: bold;
}

main {
  padding: 20px;
}

h1, h2 {
  color: #00509e; /* Darker blue for headings */
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

table th, table td {
  border: 1px solid #ccc;
  padding: 10px;
  text-align: left;
}

table th {
  background-color: #0077b6;
  color: white;
}

button {
  background-color: #0077b6;
  color: white;
  border: none;
  padding: 10px 20px;
  cursor: pointer;
  border-radius: 5px;
}

button:hover {
  background-color: #00509e;
}

footer {
  background-color: #0077b6;
  color: white;
  text-align: center;
  padding: 10px 0;
  position: fixed;
  bottom: 0;
  width: 100%;
}

input[type="text"], input[type="email"], input[type="number"] {
  width: 100%;
  padding: 10px;
  margin: 10px 0;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button.checkout {
  margin-top: 20px;
}

/* Specific Styling */
.home-banner {
  background: url('images/home-banner.jpg') no-repeat center center/cover;
  height: 300px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.home-banner h1 {
  color: white;
  font-size: 3rem;
  background-color: rgba(0, 0, 0, 0.5);
  padding: 20px;
  border-radius: 10px;
}

.cart-table {
  margin-top: 20px;
}

.cart-total {
  font-size: 1.5rem;
  margin-top: 20px;
}

.modal {
  display: none; /* Hidden by default */
  position: fixed;
  z-index: 1000;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.6); /* Semi-transparent background */
  justify-content: center;
  align-items: center;
}

.modal-content {
  background-color: #fff;
  padding: 30px; /* Increased padding */
  border-radius: 10px;
  width: 400px; /* Increased width */
  max-width: 90%;
  text-align: center;
  position: relative;
}

.modal-content h2 {
  color: #0077b6;
  margin-bottom: 20px;
}

.modal-content label {
  font-size: 1.2rem;
  display: block;
  margin: 10px 0;
}

.modal-content .final-total {
  font-size: 1.5rem;
  margin: 20px 0;
  color: #00509e;
}

.modal-content button {
  margin-top: 10px;
  width: 100%;
  background-color: #0077b6;
  color: white;
  padding: 10px 15px;
  font-size: 1.2rem;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.modal-content button:hover {
  background-color: #00509e;
}

.modal-close {
  color: #aaa;
  position: absolute;
  top: 10px;
  right: 20px;
  font-size: 1.5rem;
  cursor: pointer;
}

.modal-close:hover {
  color: black;
}


body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  background-color: #f0f8ff; 
}


.navbar {
  background-color: #007acc; 
  display: flex;
  justify-content: space-between;
  align-items: center;
  list-style: none;
}

.navbar li {
  display: inline;
  margin-right: 15px;
}

.navbar a {
  color: white;
  text-decoration: none;
  font-size: 16px;
}

.user-name {
  font-size: 16px;
  color: white;
  font-weight: bold;
}


.hero {
  height: 100vh;
  background: url("../images/background.jpg") center/cover no-repeat;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: white;
  text-shadow: 2px 2px 4px black;
}

.hero h1 {
  font-size: 3rem;
}

.hero p {
  font-size: 1.5rem;
}


footer {
  text-align: center;
  padding: 10px;
  background-color: #007acc;
  color: white;
  position: relative;
  bottom: 0;
  width: 100%;
}

.products img {
  width: 250px;
  height: auto;
 
}


/* General Styling */
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  background-color: #f4f4f4;
}

header {
  background-color: #0077b6;
  color: white;
  padding: 10px;
}

.navbar {
  display: flex;
  justify-content: space-around;
}

.navbar a {
  color: white;
  text-decoration: none;
  font-size: 1.1rem;
}

main {
  padding: 20px;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin: 20px 0;
}

th, td {
  border: 1px solid #ddd;
  padding: 10px;
  text-align: center;
}

#cart-summary {
  margin-top: 20px;
  text-align: right;
}

#checkout-btn {
  background-color: #0077b6;
  color: white;
  border: none;
  padding: 10px 20px;
  cursor: pointer;
  font-size: 1rem;
}

#checkout-btn:hover {
  background-color: #00509e;
}

/* Modal Styling */
.modal {
  display: none; /* Hidden by default */
  position: fixed;
  z-index: 1000;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.6); /* Dimmed background */
  justify-content: center;
  align-items: center;
}

.modal-content {
  background-color: #fff;
  padding: 30px;
  border-radius: 10px;
  width: 400px;
  text-align: center;
}

.modal-content h2 {
  margin-bottom: 20px;
}

.modal-close {
  position: absolute;
  top: 10px;
  right: 20px;
  cursor: pointer;
  font-size: 1.5rem;
  color: #aaa;
}

.modal-close:hover {
  color: black;
}

button {
  background-color: #0077b6;
  color: white;
  border: none;
  padding: 10px;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #00509e;
}





document.addEventListener("DOMContentLoaded", () => {
  const cartKey = "cartItems";
  const userNameKey = "userName";

  let cart = JSON.parse(localStorage.getItem(cartKey)) || [];
  const userNameDisplay = document.getElementById("user-name");

  // Display user's name if logged in
  const savedUserName = localStorage.getItem(userNameKey);
  if (savedUserName) {
    userNameDisplay.textContent = `Hello, ${savedUserName}`;
  }

  // Add to Cart Functionality
  document.querySelectorAll(".add-to-cart").forEach((button) => {
    button.addEventListener("click", (e) => {
      const name = button.dataset.name;
      const price = parseFloat(button.dataset.price);

      const existingItem = cart.find((item) => item.name === name);
      if (existingItem) {
        existingItem.quantity += 1;
      } else {
        cart.push({ name, price, quantity: 1 });
      }

      localStorage.setItem(cartKey, JSON.stringify(cart));
      alert(`${name} added to cart!`);
    });
  });

  // Checkout Button
  const checkoutButton = document.getElementById("checkout");
  if (checkoutButton) {
    checkoutButton.addEventListener("click", () => {
      const total = cart.reduce((sum, item) => sum + item.price * item.quantity, 0);
      alert(`Checkout - Total: $${total}\nChoose payment option:\n- Apple Pay\n- Google Pay\n- PayPal\n- Credit Card`);
    });
  }
});

 

let cart = JSON.parse(localStorage.getItem("cart")) || [];

// Function to update cart table
function updateCartTable() {
  const cartItems = document.getElementById("cart-items");
  const subtotalElement = document.getElementById("subtotal");
  cartItems.innerHTML = "";
  let subtotal = 0;

  cart.forEach((item, index) => {
    const total = item.price * item.quantity;
    subtotal += total;

    cartItems.innerHTML += `
      <tr>
        <td>${item.name}</td>
        <td>$${item.price.toFixed(2)}</td>
        <td>${item.quantity}</td>
        <td>$${total.toFixed(2)}</td>
      </tr>
    `;
  });

  subtotalElement.textContent = `Subtotal: $${subtotal.toFixed(2)}`;
  document.getElementById("final-total").textContent = `$${subtotal.toFixed(2)}`;
}

// Add to cart (from products page)
function addToCart(name, price) {
  const existingItem = cart.find(item => item.name === name);
  if (existingItem) {
    existingItem.quantity++;
  } else {
    cart.push({ name, price, quantity: 1 });
  }
  localStorage.setItem("cart", JSON.stringify(cart));
  alert("Item added to cart!");
}

// Open checkout modal
function openCheckoutModal() {
  const modal = document.getElementById("checkout-modal");
  modal.style.display = "flex";
}

// Close checkout modal
function closeCheckoutModal() {
  const modal = document.getElementById("checkout-modal");
  modal.style.display = "none";
}

// Submit payment
function submitPayment(event) {
  event.preventDefault();
  const paymentMethod = document.querySelector('input[name="payment-method"]:checked');
  if (paymentMethod) {
    alert(`Payment successful with ${paymentMethod.value}!`);
    localStorage.clear();
    cart = [];
    updateCartTable();
    closeCheckoutModal();
  } else {
    alert("Please select a payment method.");
  }
}

// Load cart on page load
document.addEventListener("DOMContentLoaded", updateCartTable);