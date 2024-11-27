document.addEventListener("DOMContentLoaded", () => {
  const userInfoElement = document.getElementById("user-info");
  const userName = localStorage.getItem("userName");

  if (userName) {
    userInfoElement.innerHTML = `<p>Welcome, ${userName}</p>`;
  }

  const loginForm = document.getElementById("login-form");
  const signupForm = document.getElementById("signup-form");

  if (loginForm) {
    loginForm.addEventListener("submit", (e) => {
      e.preventDefault();
      const name = document.getElementById("name").value;
      localStorage.setItem("userName", name);
      window.location.href = "index.html";
    });
  }

  if (signupForm) {
    signupForm.addEventListener("submit", (e) => {
      e.preventDefault();
      const name = document.getElementById("name").value;
      localStorage.setItem("userName", name);
      window.location.href = "index.html";
    });
  }

  document.querySelectorAll(".add-to-cart").forEach((button) => {
    button.addEventListener("click", () => {
      alert("Item added to cart! Go to the cart to checkout.");
    });
  });
});
