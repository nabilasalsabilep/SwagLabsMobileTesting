# Swag Labs Mobile QA Simulation: Ensuring Seamless Shopping Experiences

This repository contains automated test scripts developed for the Swag Labs demo retail application using Katalon Studio. The project simulates real-world e-commerce interactions, focusing on validating functionality, UI behavior, and performance for core mobile features.

## About the Project 🔍

Swag Labs is a demo shopping app designed to emulate the user journey of a modern retail experience. This testing initiative aimed to:

- Ensure smooth and bug-free navigation across critical flows
- Enhance the reliability and stability of mobile features
- Deliver a seamless, intuitive mobile shopping experience

Functional and UI tests were implemented for the following key areas:

- ✅ Login
- 🔍 Scan QR Code
- 🛒 Add to Cart
- 🔄 Update Cart
- 💳 Checkout
- 🚪 Logout

## Key Features Tested 🚀

Below is a breakdown of the primary test cases executed during the project:

### ✅ Test 1: Successful Login

Objective: Validate that users with valid credentials can log in successfully.  

Steps:
1. Launch the app
2. Enter valid username and password
3. Tap the login button
4. Verify successful navigation to the Products page

### ❌ Test 2: Failed Login

Objective: Ensure the app properly handles login attempts with invalid credentials.  

Steps:
1. Launch the app
2. Enter incorrect username or password
3. Tap the login button
4. Verify that an appropriate error message is displayed and access is denied

### 📷 Test 3: Scan QR Code

Objective: Ensure the app accurately processes product information from a QR code scan.  

Steps:
1. Navigate to the QR code scanner
2. Scan a valid product QR code
3. Confirm that the corresponding URL appear on the screen

### 🛒 Test 4: Add Product to Cart

Objective: Confirm that products can be added to the shopping cart from the product detail page.  

Steps:
1. Browse or scan a product
2. Tap "Add to Cart"
3. Verify that the item appears in the cart with correct details

### 🔄 Test 5: Update Cart

Objective: Ensure users can modify the cart by changing quantities or removing items.  

Steps:
1. Open the cart
2. Remove 2 items from the cart by swiping right to remove, and tapping the Remove button
3. Verify that the cart updates accordingly and reflects changes

### 💳 Test 6: Checkout Process

Objective: Validate that users can complete purchases through the checkout flow.  

Steps:
1. Add products to the cart
2. Proceed to checkout
3. Enter or confirm delivery and payment details
4. Verify order summary and successful transaction completion

### 🚪 Test 7: Successful Logout

Objective: Ensure users can securely log out of the application.  

Steps:
1. Tap on the logout button/menu
2. Verify redirection to the login screen

## Objectives 🎯

Through this testing project, the primary goals were to:

- ✔️ Improve functional coverage across critical app flows
- ⚙️ Validate UI consistency and responsiveness
- 📲 Ensure a robust and user-friendly mobile shopping experience
- 🧰 Strengthen automation skills using Katalon Studio for mobile testing
