
async function auth() {
    let res = await fetch('http://localhost:8081/admin/api/auth');
    return await res.json();
}

document.addEventListener('DOMContentLoaded', () => {
    refreshPage();
});

$(async function() {
    refreshPage();
    EditModal();
    DeleteModal();
    newUser();
})


//Нажатие на кнопку Edit в таблице юзеров
function onEditButton(button) {
    button.addEventListener('click', (e) => {
        e.preventDefault();
        const tr = button.parentNode.parentNode;
        document.querySelector('#editId').value = tr.children[0].innerHTML;
        document.querySelector('#editFirstName').value = tr.children[1].innerHTML;
        document.querySelector('#editLastName').value = tr.children[2].innerHTML;
        document.querySelector('#editAge').value = tr.children[3].innerHTML;
        document.querySelector('#editEmail').value = tr.children[4].innerHTML;
        document.querySelector('#editPassword').value = '';
        document.querySelector('#editForm').ariaModal = 'show';
    });
}

//Нажатие на кнопку Delete в таблице юзеров
function onDeleteButton(button) {
    button.addEventListener('click', (e) => {
        e.preventDefault();
        document.getElementById('deleteRoles').innerHTML = '';

        const tr = button.parentNode.parentNode;
        document.querySelector('#deleteId').value = tr.children[0].innerHTML;
        document.querySelector('#deleteFirstName').value = tr.children[1].innerHTML;
        document.querySelector('#deleteLastName').value = tr.children[2].innerHTML;
        document.querySelector('#deleteAge').value = tr.children[3].innerHTML;
        document.querySelector('#deleteEmail').value = tr.children[4].innerHTML;


        let roles = Array.from(tr.children[5].children).map(role => role.innerHTML);
        roles.forEach(role => {
            let option = document.createElement('option');
            option.text = role;
            document.querySelector('#deleteRoles').appendChild(option);
        });
        document.querySelector('#deleteForm').ariaModal = 'show';
    });
}

//Модальное окно Edit
async function EditModal() {
    let roles = await fetch("http://localhost:8081/admin/api/roles");
    roles = await roles.json();
    roles.forEach(role => {
        if (document.querySelector('#editRoles').children.length < 3) {
            let option = document.createElement("option");
            option.value = role.id;
            option.text = role.name.substring(5, role.name.length);
            document.querySelector('#editRoles').appendChild(option);
        }
    });

    document.querySelector('#editBtnSubmit').addEventListener('click', async (e) => {
        e.preventDefault();
        const url = `http://localhost:8081/admin/api/edit/${document.querySelector('#editId').value}`;
        await fetch(url, {
            method: "PATCH",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: document.querySelector('#editId').value,
                firstName: document.querySelector('#editFirstName').value,
                lastName: document.querySelector('#editLastName').value,
                age: document.querySelector('#editAge').value,
                email: document.querySelector('#editEmail').value,
                password: document.querySelector('#editPassword').value,
                roles: listOfRoles(document.querySelector('#editRoles').value)
            })
        });
        await refreshPage();
        document.querySelector('#editForm').reset();
    });
}

//Модальное окно Delete
function DeleteModal() {
    document.querySelector('.deleteSubmit').addEventListener('click', async (e) => {
        e.preventDefault();
        let url = `http://localhost:8081/admin/api/delete/${document.querySelector('#deleteId').value}`;
        await fetch(url, {
            method: "DELETE"
        });
        await refreshPage();
        document.querySelector('#deleteForm').reset();
    });
}

//Таб нового юзера
async function newUser() {
    /*if (!document.querySelector('#warning').classList.contains('d-none')) {
        document.querySelector('#warning').classList.add('d-none');
    }*/

    let roles = await fetch('http://localhost:8081/admin/api/roles');
    roles = await roles.json();
    roles.forEach(role => {
        if (document.querySelector('#roles').children.length < 3) {
            let option = document.createElement("option");
            option.value = role.id;
            option.text = role.name.substring(5, role.name.length);
            document.querySelector('#roles').appendChild(option);
        }
    });

    document.querySelector('#newUserForm').addEventListener('submit', async (e) => {
        e.preventDefault();
        const url = 'http://localhost:8081/admin/api/new';
        let response = await fetch(url,{
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                firstName: document.querySelector('#firstName').value,
                lastName: document.querySelector('#lastName').value,
                age: document.querySelector('#age').value,
                email: document.querySelector('#email').value,
                password: document.querySelector('#password').value,
                roles: listOfRoles(document.querySelector('#roles').value)
            })
        });
        if (response.status === 406) {
            document.querySelector('#warning').classList.remove('d-none');
        } else {
            await refreshPage();
            document.querySelector('a.allUsers').classList.add('active');
            document.querySelector('a.newUser').classList.remove('active');
            document.querySelector('#allUsers').classList.add('active');
            document.querySelector('#newUser').classList.remove('active');
            document.querySelector('#newUserForm').reset();
        }
    });
}

//Заполнение верхней панели
async function upperPanel() {
    let user = await auth();
    document.getElementById('adminUsername').textContent = user.email;
    let roles = "";
    user.roles.forEach(role => {
        roles += role.name.substring(5, role.name.length) + " ";
    });
    document.getElementById('adminRoles').textContent = roles;
}

//Преобразование выбранных option-ов в массив ролей
function listOfRoles(options) {
    let res = [];
    for (let i = 0; i < options.length; i++) {
        if (options[i].selected) {
            res.push({id: options[i].value, role: options[i].text});
        }
    }
    return res;
}

//Обновление списка юзеров
async function refreshPage() {
    let response = await fetch("http://localhost:8081/admin/api");
    let users = await response.json();
    document.getElementById('allUsersTBody').innerHTML = '';
    users.forEach(user => {
        let table = "";
        let roles = user.roles.map(role => role.name.substring(5, role.name.length));
        let rolesInTable = '';
        roles.forEach(role => {rolesInTable += `<div>${role}</div>`;});
        table += `<tr id="tr${user.id}">
            <td class="align-middle">${user.id}</td>
            <td class="align-middle">${user.firstName}</td>
            <td class="align-middle">${user.lastName}</td>
            <td class="align-middle">${user.age}</td>
            <td class="align-middle">${user.email}</td>
            <td class="align-middle">${roles}</td>
            <td class="align-middle"><button class="btn btn-primary btn-sm editBtn" data-bs-toggle="modal" data-bs-target="#editModal">Edit</button></td>
            <td class="align-middle"><button class="btn btn-danger btn-sm deleteBtn" data-bs-toggle="modal" data-bs-target="#deleteModal">Delete</button></td>
            </tr>`;
        document.getElementById('allUsersTBody').innerHTML += table;
    });
    document.querySelectorAll('.editBtn').forEach(btn => {
        onEditButton(btn);
    });
    document.querySelectorAll('.deleteBtn').forEach(btn => {
        onDeleteButton(btn);
    });
    await upperPanel();
    await refreshUserPanel();
}

//Обновление панели юзера
async function refreshUserPanel() {
    const tbody = document.getElementById('userTBody');

    let user = await auth();
    let roles = user.roles.map(role => role.name.substring(5, role.name.length));
    let rolesInTable = '';
    roles.forEach(role => {rolesInTable += `<div>${role}</div>`;});

    tbody.innerHTML = `<tr>
            <td class="align-middle">${user.id}</td>
            <td class="align-middle">${user.firstName}</td>
            <td class="align-middle">${user.lastName}</td>
            <td class="align-middle">${user.age}</td>
            <td class="align-middle">${user.address}</td>
            <td class="align-middle">${user.email}</td>
            <td class="align-middle">${roles}</td>
            </tr>`;
}