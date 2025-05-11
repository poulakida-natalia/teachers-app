// Field validations
function enforceOnlyLetters(selector) {
    document.querySelectorAll(selector).forEach(function (input) {
        input.addEventListener('input', function () {
            this.value = this.value.replace(/[^a-zA-Z\u0370-\u03FF\u1F00-\u1FFF\s]/g, '');
        });
    });
}

function enforceOnlyLettersAndNumbers(selector) {
    document.querySelectorAll(selector).forEach(function (input) {
        input.addEventListener('input', function () {
            this.value = this.value.replace(/[^a-zA-Z\u0370-\u03FF\u1F00-\u1FFF0-9]/g, '');
        });
    });
}

function enforceOnlyNumbers(selector) {
    document.querySelectorAll(selector).forEach(function (input) {
        input.addEventListener('input', function () {
            this.value = this.value.replace(/[^0-9]/g, '');
        });
    });
}


// CTAs
function selectCTA(selectedElement) {
    const allCTAs = document.querySelectorAll('.cta');
    allCTAs.forEach(cta => {
        cta.classList.remove('active');
        cta.classList.add('faded');
    });

    selectedElement.classList.add('active');
    selectedElement.classList.remove('faded');
}


// Show search form
function searchAppear() {
    let searchForm = document.querySelector('.search');
    let insertForm = document.querySelector('.insert');
    let searchToModifyForm = document.querySelector('.search-to-modify')
    let deleteForm = document.querySelector('.delete');
    let modifyForm = document.querySelector('.search-to-modify.modifying');
    searchForm.style.display = 'flex';
    insertForm.style.display = 'none';
    searchToModifyForm.style.display = 'none';
    deleteForm.style.display = 'none';
    modifyForm.style.display = 'none';
}


// Show insert form
function insertAppear() {
    let searchForm = document.querySelector('.search');
    let insertForm = document.querySelector('.insert');
    let searchToModifyForm = document.querySelector('.search-to-modify')
    let deleteForm = document.querySelector('.delete');
    let modifyForm = document.querySelector('.search-to-modify.modifying');
    searchForm.style.display = 'none';
    insertForm.style.display = 'block';
    searchToModifyForm.style.display = 'none';
    modifyForm.style.display = 'none';
    deleteForm.style.display = 'none';
}


// Show modify form
function searchToModifyAppear() {
    let searchForm = document.querySelector('.search');
    let insertForm = document.querySelector('.insert');
    let searchToModifyForm = document.querySelector('.search-to-modify')
    let modifyForm = document.querySelector('.search-to-modify.modifying');
    let deleteForm = document.querySelector('.delete');
    searchForm.style.display = 'none';
    insertForm.style.display = 'none';
    searchToModifyForm.style.display = 'flex';
    modifyForm.style.display = 'none';
    deleteForm.style.display = 'none';
}


// Show deletion form
function deletionAppear() {
    let searchForm = document.querySelector('.search');
    let insertForm = document.querySelector('.insert');
    let deleteForm = document.querySelector('.delete');
    let modifyForm = document.querySelector('.search-to-modify.modifying');
    searchForm.style.display = 'none';
    insertForm.style.display = 'none';
    deleteForm.style.display = 'block';
    let searchToModifyForm = document.querySelector('.search-to-modify')
    searchToModifyForm.style.display = 'none';
    modifyForm.style.display = 'none';
}


// Insert new teacher
document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("insertForm");

    form.addEventListener("submit", handleInsert);
});

function handleInsert(event) {
    event.preventDefault();

    const in_id = document.getElementById("in_id").value;
    const in_fname = document.getElementById("in_fname").value;
    const in_sname = document.getElementById("in_sname").value;

    console.log('ID:', in_id);
    console.log('First Name:', in_fname);
    console.log('Last Name:', in_sname);

    const data = `in_id=${encodeURIComponent(in_id)}&in_fname=${encodeURIComponent(in_fname)}&in_sname=${encodeURIComponent(in_sname)}`;

    fetch('insertController', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: data
    })
        .then(response => response.json())
        .then(responseData => {
            const alertDiv = document.getElementById("insertAlert");
            const alertMessage = document.getElementById("insertAlertMessage");

            if (responseData.status === "insert-success") {
                alertDiv.classList.add("alert-success");
                alertDiv.classList.remove("alert-danger");
                alertMessage.textContent = responseData.message;
            } else if (responseData.status === "insert-fail") {
                alertDiv.classList.add("alert-danger");
                alertDiv.classList.remove("alert-success");
                alertMessage.textContent = responseData.message;
            }

            alertDiv.style.display = "block";
        })
        .catch(error => {
            console.error("Error:", error);
            alert("There was an error with the request.");
        });
}




// Deletion of teacher + Show modal before submission

let deleteFormReference = null;

function showConfirmModal(event) {
    event.preventDefault();
    deleteFormReference = event.target;
    let modal = new bootstrap.Modal(document.getElementById('confirmModal'));
    modal.show();
    return false;
}

function submitDeleteForm() {
    if (deleteFormReference) {
        const deLidElement = deleteFormReference.querySelector("#de_lid");
        const de_lid = deLidElement.value;

        const formData = new URLSearchParams();
        formData.append("de_lid", de_lid);

        fetch("deleteController", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
            body: formData.toString()
        })
            .then(res => res.json())
            .then(responseData => {
                // ✅ Close the modal
                const modalElement = document.getElementById("confirmModal");
                const modalInstance = bootstrap.Modal.getInstance(modalElement);
                if (modalInstance) {
                    modalInstance.hide();
                }

                const alertContainer = document.getElementById('alertContainer');
                const alertDiv = document.createElement('div');
                alertDiv.classList.add("alert", "alert-dismissible", "fade", "show");

                if (responseData.status === "delete-success") {
                    alertDiv.classList.add("alert-success");
                } else {
                    alertDiv.classList.add("alert-danger");
                }

                alertDiv.innerHTML = `
                ${responseData.message}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            `;

                alertContainer.innerHTML = "";
                alertContainer.appendChild(alertDiv);
            })
            .catch(err => {
                console.error("Error:", err);
                alert("Κάτι πήγε στραβά.");
            });
    }
}


// Show search results
function handleSearch(event) {
    event.preventDefault();

    const query = document.getElementById("search").value;
    const tableBody = document.querySelector("#resultsTable tbody");
    const alertBox = document.getElementById("searchFailAlert");
    const alertMessage = document.getElementById("alertMessage");
    const resultsContainer = document.getElementById("resultsContainer");

    console.log("resultsContainer:", resultsContainer);
    console.log("alertBox:", alertBox);

    tableBody.innerHTML = "";

    if (resultsContainer) {
        resultsContainer.style.display = "none";
    }

    if (alertBox) {
        alertBox.style.display = "none";
    }

    fetch(`searchController?search=${encodeURIComponent(query)}`)
        .then(response => {
            if (!response.ok) {
                return response.json().then(err => {
                    throw new Error(err.error || "An error occurred while fetching the data.");
                });
            }
            return response.json();
        })
        .then(data => {
            const results = Array.isArray(data) ? data : [data];

            tableBody.innerHTML = "";

            results.forEach((teacher, index) => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <th scope="row">${index + 1}</th>
                    <td>${teacher.id}</td>
                    <td>${teacher.fname}</td>
                    <td>${teacher.sname}</td>
                `;
                tableBody.appendChild(row);
            });

            if (resultsContainer) {
                resultsContainer.style.display = "block";
            }
        })
        .catch(error => {
            if (alertMessage) {
                alertMessage.innerText = error.message || "Σφάλμα κατά την αναζήτηση.";
            }
            if (alertBox) {
                alertBox.style.display = "block";
            }
        });

    return false;
}


document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form.search");
    if (form) {
        form.addEventListener("submit", handleSearch);
    }
});



// Show modify form if search to modify is successful
function showModify(event) {
    event.preventDefault(); // Prevent page load

    const id = document.getElementById("search-id").value.trim();
    const notFoundMessage = document.getElementById("notFoundMessage");
    const modifyForm = document.getElementById("modifying");

    fetch(`searchToModifyController?search-id=${encodeURIComponent(id)}`)
        .then(async response => {
            const data = await response.json();
            if (!response.ok) {
                throw new Error(data.error || "Unknown error");
            }
            return data;
        })
        .then(data => {
            document.getElementById("fname").value = data.fname;
            document.getElementById("sname").value = data.sname;
            document.getElementById("search-id-hidden").value = id;

            modifyForm.style.display = 'block';
            modifyForm.removeAttribute("hidden");
            notFoundMessage.style.display = "none";
        })
        .catch(err => {
            modifyForm.style.display = 'none';
            notFoundMessage.innerText = err.message;
            notFoundMessage.style.display = "block";
        });

    return false;
}


// Modify teacher details handling
function handleModify(event) {
    event.preventDefault();

    const id = document.getElementById("search-id-hidden").value;
    const fname = document.getElementById("fname").value;
    const sname = document.getElementById("sname").value;

    const formData = new URLSearchParams();
    formData.append("search-id", id);
    formData.append("fname", fname);
    formData.append("sname", sname);

    fetch("modifyController", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
            "Accept": "application/json"
        },
        body: formData.toString()
    })
        .then(response => response.json())
        .then(data => {
            const alertDiv = document.getElementById("modifyAlert");
            const alertMessage = document.getElementById("modifyAlertMessage");

            if (data.status === "modify-success") {
                alertDiv.classList.remove("alert-danger");
                alertDiv.classList.add("alert-success");
            } else {
                alertDiv.classList.remove("alert-success");
                alertDiv.classList.add("alert-danger");
            }

            alertMessage.textContent = data.message;
            alertDiv.style.display = "block";
        })
        .catch(error => {
            console.error("Error:", error);
            alert("Κάτι πήγε στραβά.");
        });
}
