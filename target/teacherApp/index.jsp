<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
                    <h1 id="h12"><b>Καλως ήρθατε!</b></h1>
                    <br>

                    <% String status1=(String) request.getAttribute("status"); String message1=(String)
                        request.getAttribute("message"); if (status1=="success" && message1 !=null) { %>
                        <div class="alert alert-success alert-dismissible fade show" role="alert">
                            <%= message1 %>
                                <button type="button" class="btn-close" data-bs-dismiss="alert"
                                    aria-label="Close"></button>
                        </div>

                        <% } %>

                            <% String status2=(String) request.getAttribute("status"); String message2=(String)
                                request.getAttribute("message"); if (status2=="fail" && message2 !=null) { %>
                                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                    <%= message2 %>
                                        <button type="button" class="btn-close" data-bs-dismiss="alert"
                                            aria-label="Close"></button>
                                </div>

                                <% } %>



                                    <div style="background-color: #FFFFFF; padding: 20px; border-radius: 5px">
                                        <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                                            <li class="nav-item">
                                                <a class="nav-link active" id="login" data-bs-toggle="pill"
                                                    href="#pills-login" role="tab" aria-controls="pills-login"
                                                    aria-selected="true">Σύνδεση</a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" id="register" data-bs-toggle="pill"
                                                    href="#pills-register" role="tab" aria-controls="pills-register"
                                                    aria-selected="false">Εγγραφή</a>
                                            </li>
                                        </ul>

                                        <div class="tab-content" id="pills-tabContent">
                                            <div class="tab-pane fade show active" id="pills-login" role="tabpanel"
                                                aria-labelledby="login">
                                                <form method="get" action="loginController">
                                                    <label for="username">Όνομα χρήστη</label>
                                                    <input type="text" name="username" id="username" placeholder=""
                                                        required>

                                                    <label for="password">Κωδικός πρόσβασης</label>
                                                    <input type="text" name="password" id="password" placeholder=" "
                                                        required onkeyup>

                                                    <div style="text-align: center;">
                                                        <input type="submit" value="Σύνδεση">
                                                    </div>

                                                </form>
                                            </div>
                                            <div class="tab-pane fade" id="pills-register" role="tabpanel"
                                                aria-labelledby="register">
                                                <form style="background-color: #FFFFFF" method="post"
                                                    action="registerController">

                                                    <label for="username-register">Όνομα χρήστη</label>
                                                    <input type="text" name="username-register" id="username-register"
                                                        placeholder="" required class="only-numbers">

                                                    <label for="password-register">Κωδικός πρόσβασης</label>
                                                    <input class="only-letters" type="text" name="password-register"
                                                        id="password-register" placeholder=" " required onkeyup>

                                                    <label for="confirm-password">Επιβεβαίωση κωδικού</label>
                                                    <input class="only-letters" type="text" name="confirm-password"
                                                        id="confirm-password" placeholder=" " required onkeyup>

                                                    <div style="text-align: center;">
                                                        <input type="submit" value="Εγγραφή">
                                                    </div>

                                                </form>
                                            </div>
                                        </div>
                                    </div>
                </div>
            </div>
            <br><br>

        </div>



    </body>


    <script>

    </script>