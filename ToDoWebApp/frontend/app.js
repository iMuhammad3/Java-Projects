let todos = [];
fetch("http://localhost:8080/app/todos")
    .then(res => res.json())
    .then(data => handleData(data))
    .catch(err => console.error(err));

document
    .getElementById("createForm")
    .addEventListener("submit", function (event) {
        // Prevent the default form submission behavior
        event.preventDefault();

        // Manually submit the form
        var form = event.target;
        var formData = new FormData(form);
        var xhr = new XMLHttpRequest();
        xhr.open(form.method, form.action, true);
        xhr.onload = function () {
            // Check if the request was successful
            if (xhr.status >= 200 && xhr.status < 300) {
                // Redirect back to the previous page
                window.location.href = document.referrer;
            }
        };
        xhr.send(formData);
        setTimeout(() => {
            window.location.reload();
        }, 100);
    });

const handleData = data => {
    const todoList = document.querySelector(".todoList");
    data.forEach(todo => {
        const row = document.createElement("tr");
        row.setAttribute("data-id", todo.id);
        row.innerHTML = `
            <td>${todo.id}</td>
            <td>${todo.todo}</td>
            <td>${todo.completed}</td>
            <td class="buttons"><button class="editBtn">Edit</button><button class="deleteBtn">Delete</button></td>
        `;
        row.addEventListener("click", e => {
            const element = e.target
            switch (element.className) {
                case "editBtn": {
                    alert("Doesn't work yet! 🙃")
                    break;
                }
                case "deleteBtn": {
                    element.closest("tr").remove();
                    const id = element.closest('tr').getAttribute("data-id")
                    fetch(`http://localhost:8080/app/delete?id=${id}`, {
                        method: "DELETE",
                    });
                }
            }
        });
        todoList.appendChild(row);
    });
}

const addTodoBtn = document.getElementById("addTodoBtn")
const formContainer = document.getElementById("form-container")

addTodoBtn.addEventListener('click', () => {
    formContainer.style.display = "flex"
    formContainer.querySelector("#createForm").classList.remove("hide")
})

formContainer.addEventListener("click", e => {
    if (e.target.id === "form-container" || e.target.id === "submitBtn") {
        formContainer.style.display = "none"
    }
})