<% HttpSession sess=request.getSession(false); if (sess==null || sess.getAttribute("loggedInUser")==null) {
    response.sendRedirect("index.jsp"); return; } %>


    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Ποιότητα στην εκπαίδευση</title>
                <style>
                    @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400&display=swap');
                </style>
                <!-- Bootstrap 5.3 CSS -->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

                <script src="external/index.js"></script>
                <link rel="shortcut icon" href="external/logo.png">
                <link rel="stylesheet" href="external/index.css">
                <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css">

            </head>

            <body>
                <div class="container">

                    <div class="inner-container">
                        <div>
                            <h1 id="h12"><b>Ποιότητα στην εκπαίδευση</b></h1>
                            <br>
                            <div class="cta-grid">
                                <div class="cta" onclick="selectCTA(this); searchAppear()">Αναζήτηση εκπαιδευτικού</div>
                                <div class="cta" onclick="selectCTA(this); insertAppear()">Προσθήκη νέου εκπαιδευτικού
                                </div>
                                <div class="cta" onclick="selectCTA(this); searchToModifyAppear()">Επεξεργασία στοιχείων
                                    εκπαιδευτικού
                                </div>
                                <div class="cta" onclick="selectCTA(this); deletionAppear()">Διαγραφή εκπαιδευτικού
                                </div>
                            </div>

                            <br>

                            <!--SEARCH-->
                            <form class="search" method="get" action="searchController"
                                onsubmit="return handleSearch(event)">
                                <div style="display: none" id="searchFailAlert"
                                    class="alert alert-danger alert-dismissible fade show" role="alert">
                                    <span id="alertMessage"></span>
                                    <button type="button" class="btn-close" data-bs-dismiss="alert"
                                        aria-label="Close"></button>
                                </div>
                                <div class="search-row">
                                    <input class="no-symbols" type="text" id="search" name="search"
                                        placeholder="Αναγνωριστικό ή επώνυμο εκπαιδευτικού" required>
                                    <input type="submit" value="Αναζήτηση">
                                </div>

                                <div class="search-results" id="resultsContainer" style="display: none">
                                    <br>
                                    <div class="table-container">
                                    <table class="table table-hover table-striped" id="resultsTable">
                                        <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">Αναγνωριστικό</th>
                                                <th scope="col">Όνομα</th>
                                                <th scope="col">Επώνυμο</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <!-- Dynamic results -->
                                        </tbody>
                                    </table></div>
                                </div>

                            </form>

                            <!--INSERT-->
                            <form id="insertForm" class="insert" method="post" onsubmit="return handleInsert(event)">
                                <div id="insertAlert" class="alert fade show" style="display: none;" role="alert">
                                    <span id="insertAlertMessage"></span>
                                    <button type="button" class="btn-close" data-bs-dismiss="alert"
                                        aria-label="Close"></button>
                                </div>

                                <label for="in_id">Αναγνωριστικό εκπαιδευτικού</label>
                                <input type="text" name="in_id" id="in_id" placeholder="" required class="only-numbers">

                                <label for="in_fname">Όνομα</label>
                                <input class="only-letters" type="text" name="in_fname" id="in_fname" placeholder=" "
                                    required>

                                <label for="in_sname">Επώνυμο</label>
                                <input class="only-letters" type="text" name="in_sname" id="in_sname" placeholder=" "
                                    required>

                                <div style="text-align: center;">
                                    <input type="submit" value="Αποθήκευση">
                                </div>
                            </form>


                            <!--MODIFY-->
                            <form class="search-to-modify" id="searching" method="get"
                                onsubmit="return showModify(event)">

                                <div style="display: none" id="notFoundMessage"
                                    class="alert alert-danger alert-dismissible fade show" role="alert">
                                    <span id="notFoundMessageText"></span>
                                    <button type="button" class="btn-close" data-bs-dismiss="alert"
                                        aria-label="Close"></button>
                                </div>
                                <div id="modifyAlert" class="alert alert-dismissible fade show" role="alert"
                                    style="display: none;">
                                    <span id="modifyAlertMessage"></span>
                                    <button type="button" class="btn-close" data-bs-dismiss="alert"
                                        aria-label="Close"></button>
                                </div>

                                <div class="search-row">
                                    <input type="text" id="search-id" name="search-id"
                                        placeholder="Αναγνωριστικό εκπαιδευτικού προς επεξεργασία" required
                                        class="only-numbers">
                                    <input type="submit" value="Αναζήτηση">
                                </div>
                            </form>
                            <br>

                            <form class="search-to-modify modifying" id="modifying" method="post"
                                action="modifyController" onsubmit="return handleModify(event)">
                                <input type="hidden" id="search-id-hidden" name="search-id">
                                <label for="fname">Όνομα</label>
                                <div class="form-group modify-field input-with-icon">
                                    <input class="only-letters" type="text" id="fname" name="fname" placeholder="Όνομα"
                                        value="${fname}" required>
                                    <span class="icon fas fa-pen"></span>
                                </div>
                                <br>
                                <label for="sname">Επώνυμο</label>
                                <div class="form-group modify-field input-with-icon">
                                    <input class="only-letters" type="text" id="sname" name="sname"
                                        placeholder="Επώνυμο" value="${sname}" required>
                                    <span class="icon fas fa-pen"></span>
                                </div>
                                <br>
                                <div style="text-align: center;">
                                    <input type="submit" value="Αποθήκευση">
                                </div>
                            </form>

                            <!--DELETE-->
                            <form class="delete" id="deleteForm" method="post" action="deleteController"
                                onsubmit="return showConfirmModal(event)">
                                <div id="alertContainer"></div>
                                <div class="search-row">
                                    <input type="text" name="de_lid" id="de_lid"
                                        placeholder="Αναγνωριστικό εκπαιδευτικού προς διαγραφή" required
                                        class="only-numbers">
                                    <input type="submit" value="Διαγραφή">
                                </div>
                            </form>



                        </div>


                    </div>
                    <br><br>

                </div>


                <!-- Delete Modal -->
                <div class="modal fade" id="confirmModal" tabindex="-1" aria-labelledby="confirmModalLabel"
                    aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="confirmModalLabel">Επιβεβαίωση Διαγραφής</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Κλείσιμο"></button>
                            </div>
                            <div class="modal-body">
                                Είστε σίγουρος/η ότι θέλετε να προχωρήσετε σε διαγραφή;
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-light" onclick="submitDeleteForm()">Ναι</button>
                                <button type="button" class="btn" style="background-color: #115629; color: #FFFFFF"
                                    data-bs-dismiss="modal">Όχι</button>
                            </div>
                        </div>
                    </div>
                </div>


            </body>

            <!--Validations on input-->
            <script>
                document.addEventListener('DOMContentLoaded', function () {
                    enforceOnlyLetters('.only-letters');
                    enforceOnlyNumbers('.only-numbers');
                    enforceOnlyLettersAndNumbers('.no-symbols')
                });
            </script>