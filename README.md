- [What is Thymeleaf?](#-what-is-thymeleaf)
- [Thymeleaf Standard Expressions](#-thymeleaf-standard-expressions)
- [Variable Expressions](#-variable-expressions-)
- [Selection Expressions](#-selection-expressions-)
- [Message Expressions](#-message-expressions-)
- [Link Expressions](#-link-expressions-)
- [Fragment Expressions](#-fragment-expressions-)


## 🌿 What is **Thymeleaf**?

**Thymeleaf** is a **server-side Java template engine** used for building dynamic web pages.
It allows you to write HTML pages that can display **data from your Java code (Spring Boot, for example)** using simple expressions.

➡️ It’s often used with **Spring Boot** as the default template engine for rendering **views** in MVC (Model-View-Controller) applications.

---

## 💡 Why use Thymeleaf?

✅ **1. Natural HTML Templates**
Thymeleaf templates are valid HTML files — they can be opened and previewed directly in a browser, even without running the backend.

✅ **2. Easy Integration with Spring Boot**
It works seamlessly with Spring MVC’s model attributes (`Model`, `ModelMap`, etc.).

✅ **3. Expression Language (EL)**
You can easily bind backend data (Java objects) to your HTML views.

✅ **4. Dynamic Rendering**
Supports iteration, conditionals, text replacement, form binding, URL rewriting, and more.

✅ **5. Fragment Reusability**
You can reuse page components (headers, footers, navbars) easily using fragments.

---

## 🧩 Thymeleaf Standard Expressions

| **Expression Type**           | **Syntax** | **Used For**                                                         | **Example**                                                 |
| ----------------------------- | ---------- | -------------------------------------------------------------------- | ----------------------------------------------------------- |
| **1. Variable Expressions**   | `${...}`   | Access variables from the **model** (Spring controller)              | `<p>Name: ${student.name}</p>`                              |
| **2. Selection Expressions**  | `*{...}`   | Access properties of a **selected object** (used inside `th:object`) | `<input th:field="*{email}" />`                             |
| **3. Message Expressions**    | `#{...}`   | Retrieve messages from **properties files** (for i18n)               | `<p th:text="#{welcome.message}"></p>`                      |
| **4. Link (URL) Expressions** | `@{...}`   | Build **dynamic URLs**                                               | `<a th:href="@{/students/{id}(id=${student.id})}">View</a>` |
| **5. Fragment Expressions**   | `~{...}`   | Include or reference **fragments** (HTML parts)                      | `<div th:replace="~{fragments/header :: header}"></div>`    |

---

### 🌐 Example (Spring + Thymeleaf)

**Controller:**

```java
@GetMapping("/students")
public String listStudents(Model model) {
    model.addAttribute("students", studentService.getAllStudents());
    return "students"; // refers to students.html
}
```

**HTML (students.html):**

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <title>Students</title>
</head>
<body>
  <h2>Student List</h2>
  <ul>
    <li th:each="student : ${students}">
      <span th:text="${student.name}">Name</span> -
      <a th:href="@{/students/{id}(id=${student.id})}">View</a>
    </li>
  </ul>
</body>
</html>
```
---
Perfect 👍 — here’s a clear and detailed explanation you can use in your notes 👇

---

## 🧮 **Variable Expressions (`${...}`)**

Variable expressions are the **most commonly used expressions** in Thymeleaf templates.
They allow you to **access and display data** stored in the **model (template context)** that the controller sends to the view.

---

### 🧩 **Syntax**

```html
${variableName}
```

This expression fetches the value of `variableName` from the **model** and inserts it into the rendered HTML page.

---

### 🧠 **Concept**

When a Spring Controller adds data to the model:

```java
model.addAttribute("studentName", "Lalit");
```

This value becomes available inside the Thymeleaf template as `${studentName}`.

---

### 💻 **Example**

**Controller (Java):**

```java
@GetMapping("/welcome")
public String showWelcomePage(Model model) {
    model.addAttribute("studentName", "Lalit");
    model.addAttribute("course", "Spring Boot");
    return "welcome"; // Refers to welcome.html
}
```

**Template (welcome.html):**

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <title>Welcome Page</title>
</head>
<body>
  <h2 th:text="'Welcome, ' + ${studentName} + '!'"></h2>
  <p th:text="'You are learning ' + ${course} + '.'"></p>
</body>
</html>
```

**Rendered Output (in browser):**

```html
<h2>Welcome, Lalit!</h2>
<p>You are learning Spring Boot.</p>
```

---

### 🔍 **Usage Examples**

| Purpose                | Example                     | Description                               |
| ---------------------- | --------------------------- | ----------------------------------------- |
| Access single variable | `${studentName}`            | Displays the variable value               |
| Access object property | `${student.name}`           | Gets the `name` field of `student` object |
| Inside `th:text`       | `th:text="${student.age}"`  | Displays dynamic text                     |
| Inside `th:each`       | `th:each="s : ${students}"` | Iterates over a list from the model       |

    
---

## 🎯 **Selection Expressions (`*{...}`)**

### 📘 **Definition**

**Selection expressions** are used to access the **fields (properties)** of a **previously selected object** — instead of referring to the entire model.

They are typically used inside a tag that defines a **`th:object`**, which specifies the current object in context.
Once that object is set, you can use `*{...}` to refer to its fields directly.

---

### 🧩 **Syntax**

```html
*{propertyName}
```

➡️ Works **similar to `${object.propertyName}`**,
but `${...}` accesses data from the whole **model**,
while `*{...}` accesses data from the **selected object** defined by `th:object`.

---

### 🧠 **Example 1: Basic Example**

**HTML:**

```html
<p>Name: <span th:text="*{firstName}">Sebastian</span>.</p>
```

This will display the value of `firstName` from the selected object.

---

### 💻 **Example 2: With Selected Object**

**Suppose you have an object in your model:**

```java
model.addAttribute("sessionUser", new User("Pepper", "Saturn"));
```

**User class:**

```java
public class User {
    private String lastName;
    private String nationality;

    // Constructor, Getters, Setters
    public User(String lastName, String nationality) {
        this.lastName = lastName;
        this.nationality = nationality;
    }
    public String getLastName() { return lastName; }
    public String getNationality() { return nationality; }
}
```

**Thymeleaf Template:**

```html
<div th:object="${sessionUser}">
  <p>Surname: <span th:text="*{lastName}">Pepper</span>.</p>
  <p>Nationality: <span th:text="*{nationality}">Saturn</span>.</p>
</div>
```

✅ **Rendered Output:**

```
Surname: Pepper.
Nationality: Saturn.
```

---

### 💡 **When to Use `*{...}`**

* Inside forms or components where a **single object** is being handled (like `Student`, `User`, etc.).
* Makes templates **cleaner and shorter** because you don’t need to repeat the object name every time.

---

### 💬 **Comparison:**

| Expression | Works On                      | Example           | Description                       |
| ---------- | ----------------------------- | ----------------- | --------------------------------- |
| `${...}`   | Entire Model                  | `${student.name}` | Accesses variable from model      |
| `*{...}`   | Selected Object (`th:object`) | `*{name}`         | Accesses field of selected object |

---

### 🧾 **Example: Form with Selection Expression**

```html
<form th:object="${student}">
  <label>Name:</label>
  <input type="text" th:field="*{name}" />
  
  <label>Email:</label>
  <input type="email" th:field="*{email}" />

  <button type="submit">Save</button>
</form>
```

Here, all `*{...}` expressions refer to fields of the selected object `${student}`.
---

## 💬 **Message Expressions (`#{...}`)**

### 📘 **Definition**

**Message expressions** in Thymeleaf are used to **read messages from properties files** (like `messages.properties`).
They allow you to **externalize common text or labels**, making your application easier to **maintain** and **translate** (internationalization – *i18n*).

---

### 🧩 **Syntax**

```html
#{message.key}
```

➡️ The `message.key` refers to a key defined inside a `.properties` file.
➡️ Thymeleaf replaces it with the corresponding message text at runtime.

---

### 📁 **Example: messages.properties**

```properties
welcome.message=Welcome to the Student Portal!
student.name.label=Student Name
student.email.label=Student Email
```

---

### 💻 **Example: Using in Thymeleaf Template**

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <title>Message Example</title>
</head>
<body>
  <h2 th:text="#{welcome.message}">Default Welcome Message</h2>

  <form>
    <label th:text="#{student.name.label}">Name:</label>
    <input type="text" name="name" />

    <br><br>

    <label th:text="#{student.email.label}">Email:</label>
    <input type="email" name="email" />
  </form>
</body>
</html>
```

✅ **Rendered Output:**

```
Welcome to the Student Portal!
Student Name: [__________]
Student Email: [__________]
```

---

### 🧠 **How It Works**

1. Thymeleaf looks for a file named `messages.properties` (or language-specific like `messages_en.properties`).
2. It finds the key written inside `#{...}`.
3. The key is replaced by its value from the properties file.

---

### 🌐 **Internationalization Example (Optional)**

If you have multiple language files like:

* `messages_en.properties`
* `messages_fr.properties`

Then, based on the user’s locale, Thymeleaf automatically picks the correct message.

---

### 💬 **Summary Table**

| Expression | Purpose                        | Example              | Output                         |
| ---------- | ------------------------------ | -------------------- | ------------------------------ |
| `#{...}`   | Read text from properties file | `#{welcome.message}` | Welcome to the Student Portal! |

---

## 🔗 **Link Expressions (`@{...}`)**

### 📘 **Definition**

**Link Expressions** in Thymeleaf are used to **generate URLs dynamically** within your HTML templates.
They make sure your URLs are always **context-aware**, meaning they automatically include the correct **application context path**.

---

### 🧩 **Syntax**

```html
@{...}
```

➡️ The `@{}` syntax tells Thymeleaf to build a **URL**.
You can use it for:

* Links (`href`)
* Form actions (`action`)
* Image sources (`src`)
* Stylesheets or script references

---

### 💻 **Example 1: Static Resource (CSS File)**

```html
<head>
  <meta charset="UTF-8">
  <title>Link Expression Demo</title>
  <link rel="stylesheet" th:href="@{/css/style.css}">
</head>
```

✅ **Explanation:**

* `@{/css/style.css}` → Automatically resolves to `/yourAppName/css/style.css`
* Works even if the app is deployed in a subdirectory.

---

### 💻 **Example 2: Dynamic URL with Parameters**

```html
<a th:href="@{/editUser/{id}(id=${user.id})}">Edit</a>
```

✅ **Explanation:**

* `{id}` is a **path variable** placeholder.
* `(id=${user.id})` passes the actual value dynamically.

If `${user.id}` = `101`,
the rendered output becomes:

```html
<a href="/editUser/101">Edit</a>
```

---

### 💻 **Complete Example:**

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="UTF-8">
  <title>Link Expression Demo</title>
  <link rel="stylesheet" th:href="@{/css/style.css}">
</head>
<body>
  <h1 class="red-heading">Link Expression Demo</h1>

  <table border="1">
    <thead>
      <tr>
        <td>Name</td>
        <td>Contact</td>
        <td>Action</td>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>Pankaj</td>
        <td>2937492739</td>
        <td><a th:href="@{/editUser/{id}(id=${id})}">Edit</a></td>
      </tr>
    </tbody>
  </table>
</body>
</html>
```

✅ **Rendered Output:**

```html
<a href="/editUser/1">Edit</a>
```

---

### 💡 **Common Use Cases**

| Purpose            | Example                               | Description          |
| ------------------ | ------------------------------------- | -------------------- |
| Link to static CSS | `th:href="@{/css/style.css}"`         | Load CSS file        |
| Link to static JS  | `th:src="@{/js/app.js}"`              | Load JavaScript file |
| Path variable      | `th:href="@{/user/{id}(id=${u.id})}"` | Dynamic link         |
| Query parameter    | `th:href="@{/search(query=${q})}"`    | Adds `?query=value`  |
| Form action        | `th:action="@{/save}"`                | Submit to controller |

---

### 🧠 **Why Use Link Expressions**

* Automatically **handles context paths** (no need to hardcode `/appname`).
* Easier to maintain links.
* Works perfectly across environments (local, staging, production).

---

## 🧩 **Fragment Expressions (`~{...}`)**

### 📘 **Definition**

**Fragment Expressions** in Thymeleaf are used to **define and reuse parts of HTML templates** — such as headers, footers, or navigation bars.

They make your web pages more **modular, reusable, and maintainable** by allowing you to include the same fragment across multiple pages.

---

### 🧩 **Syntax**

```html
~{fragmentName}
```

You can also reference fragments defined in another template:

```html
~{templateName :: fragmentName}
```

---

### ⚙️ **How to Use Fragment Expressions**

There are **three main ways** to include fragments:

| Method       | Description                                         | Example                                                  |
| ------------ | --------------------------------------------------- | -------------------------------------------------------- |
| `th:insert`  | Inserts the fragment *inside* the current tag       | `<div th:insert="~{fragments/header :: header}"></div>`  |
| `th:replace` | Replaces the *entire current tag* with the fragment | `<div th:replace="~{fragments/header :: header}"></div>` |
| `th:include` | *(Deprecated)* Older syntax — avoid using           | `<div th:include="~{fragments/header :: header}"></div>` |

---

### 🧱 **Example 1: Defining a Fragment**

**📄 File:** `fragments/header.html`

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
  <body>
    <div th:fragment="header">
      <h1>Welcome to Student Portal</h1>
      <hr/>
    </div>
  </body>
</html>
```

---

### 🧱 **Example 2: Using a Fragment in Another Template**

**📄 File:** `students.html`

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
  <title>Students List</title>
</head>
<body>

  <!-- Include Header Fragment -->
  <div th:replace="~{fragments/header :: header}"></div>

  <h2>Student Details</h2>
  <p>List of students will appear here...</p>

</body>
</html>
```

✅ **Rendered Output:**

```html
<h1>Welcome to Student Portal</h1>
<hr>
<h2>Student Details</h2>
<p>List of students will appear here...</p>
```

---

### 🧠 **When to Use Which**

| Use Case                                                    | Recommended Attribute     |
| ----------------------------------------------------------- | ------------------------- |
| When you want to include content **inside** an existing tag | `th:insert`               |
| When you want to **replace** the tag entirely               | `th:replace`              |
| When working with **legacy** templates                      | `th:include` (deprecated) |

---

### 💡 **Benefits of Using Fragments**

* Avoids repeating common HTML code (header, footer, sidebar).
* Keeps templates **clean and organized**.
* Makes maintenance and updates much easier.

---


