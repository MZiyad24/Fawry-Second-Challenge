# 📚 Quantum Bookstore

**Fawry Bookstore** is a simplified and extensible Java-based simulation of an online bookstore system. It demonstrates clean object-oriented design with support for multiple book types, inventory management, and unit testing.

All console outputs are prefixed with:

```
Fawry Bookstore →
```

---

## ✨ Features

### 📦 Supported Book Types

- **📘 PaperBook**
  - Has physical stock
  - Can be shipped via a `ShippingService`

- **📄 EBook**
  - Has a file format (e.g., PDF, EPUB)
  - Delivered via a `MailService` 

- **🖼️ ShowcaseBook**
  - Not for sale
  - Exists only as a demo/showcase item

Each book includes:
- ISBN (string identifier)
- Title
- Author
- Year of publication
- Price

---

## 🛠️ Functionalities

### ➕ Add Book to Inventory
- Add books of any type to the inventory with their details
- ISBN, title, author, year, price, type

### ❌ Remove Outdated Books
- Remove and return books older than a specific number of years

### 🛒 Purchase Book

Purchase a book by providing:
- ISBN
- Quantity
- Customer email (for EBooks)
- Customer address (for PaperBooks)

✅ Behavior:
- Decreases stock for PaperBooks
- Throws error if stock is unavailable or book is not for sale
- Returns the total price paid
- Sends:
  - PaperBooks to `ShippingService`
  - EBooks to `MailService`

---

## 🔁 Design Patterns & Practices

- ✅ **Singleton Pattern**:  
  The inventory is implemented as a Singleton to ensure centralized access and consistency throughout the application.

- ✅ **Open/Closed Principle**:  
  New book types can be added without modifying existing logic.

---

## ✅ Testing

- Unit tests are written using **JUnit 5**
- Includes tests for:
  - Adding books
  - Removing outdated books
  - Buying books of different types
  - Invalid operations (e.g., buying showcase or out-of-stock books)

---

## 💻 Code Samples

- Example usage and system simulation is provided in the **`samples/`** folder
- These samples show how to add books, remove old ones, and simulate purchases

