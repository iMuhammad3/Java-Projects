let todos = [];
fetch("http://localhost:8080/todos")
    .then(res => res.json())
    .then(data => {
        data.forEach(todo => {
            const li = document.createElement('li');
            li.textContent = todo.todo;
            todoList.appendChild(li);
        });
    })
    .catch(err => console.error(err));

const todoList = document.querySelector(".todoList");

console.log(todos);