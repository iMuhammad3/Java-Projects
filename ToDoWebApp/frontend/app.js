let todos = [];
fetch("http://localhost:8080/todos")
    .then(res => res.json())
    .then(data => {
        data.forEach(todo => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${todo.id}</td>
                <td>${todo.todo}</td>
                <td>${todo.completed}</td>
            `
            todoList.appendChild(row);
        });
    })
    .catch(err => console.error(err));

const todoList = document.querySelector(".todoList");

console.log(todos);