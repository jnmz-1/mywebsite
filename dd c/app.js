

function addToCart(name, price) {
  const existingItem = cart.find(item => item.name === name);
  if (existingItem) {
    existingItem.quantity++;
  } else {
    cart.push({ name, price, quantity: 1 });
  }
  localStorage.setItem("cart", JSON.stringify(cart));
  alert(`${name} added to the cart!`);
}

function updateCartDisplay() {
  // Use this function to dynamically update cart.html
}

// Cart Data
let cart = JSON.parse(localStorage.getItem("cart")) || [];

// Add to Cart
function addToCart(name, price) {
  const existingItem = cart.find(item => item.name === name);
  if (existingItem) {
    existingItem.quantity++;
  } else {
    cart.push({ name, price, quantity: 1 });
  }
  localStorage.setItem("cart", JSON.stringify(cart));
  alert(`${name} added to the cart!`);
}

// Display Cart Items
function displayCart() {
  const cartItems = document.getElementById("cart-items");
  const finalTotal = document.getElementById("final-total");

  cartItems.innerHTML = ""; // Clear existing items
  let total = 0;

  cart.forEach((item, index) => {
    const itemTotal = item.price * item.quantity;
    total += itemTotal;

    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${item.name}</td>
      <td>$${item.price.toFixed(2)}</td>
      <td>
        <input type="number" value="${item.quantity}" min="1" 
               onchange="updateQuantity(${index}, this.value)">
      </td>
      <td>$${itemTotal.toFixed(2)}</td>
      <td><button onclick="removeFromCart(${index})">Remove</button></td>
    `;
    cartItems.appendChild(row);
  });

  finalTotal.textContent = total.toFixed(2);
  document.getElementById("checkout-total").textContent = total.toFixed(2);
}

// Update Quantity
function updateQuantity(index, newQuantity) {
  cart[index].quantity = parseInt(newQuantity);
  localStorage.setItem("cart", JSON.stringify(cart));
  displayCart();
}

// Remove Item
function removeFromCart(index) {
  cart.splice(index, 1);
  localStorage.setItem("cart", JSON.stringify(cart));
  displayCart();
}

// Checkout Popup
document.getElementById("checkout-btn").addEventListener("click", () => {
  document.getElementById("checkout-popup").style.display = "block";
});

document.getElementById("close-popup").addEventListener("click", () => {
  document.getElementById("checkout-popup").style.display = "none";
});

// Initialize Cart Display
if (window.location.pathname.includes("cart.html")) {
  displayCart();
}
