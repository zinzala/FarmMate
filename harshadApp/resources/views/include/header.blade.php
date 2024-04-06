<!-- <nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">{{config("app.name")}}</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link active" aria-current="page" href="{{route("dashboard")}}">Home</a>
                <a class="nav-link" href="{{route("products")}}">Products</a>
                <a class="nav-link" href="{{route("order.list")}}">Orders</a>
                <a class="nav-link" href="{{route("logout")}}">Logout</a>
                
            </div>
        </div>
    </div>
</nav> -->
<!-- <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Title</title>
    <style>
        /* Navbar styles */
        .navbar {
            background-color: #343a40; /* Dark background color */
            padding: 10px 0;
            border-bottom: 4px solid #17a2b8; /* Bottom border color */
        }

        /* Navbar brand styles */
        .navbar-brand {
            font-size: 24px;
            font-weight: bold;
            color: #ffffff; /* White text color */
            text-transform: uppercase; /* Uppercase text */
            letter-spacing: 2px; /* Add some letter spacing */
        }

        /* Navbar link styles */
        .navbar-nav .nav-link {
            font-size: 18px;
            color: #ffffff; /* White text color */
            margin-right: 15px;
            transition: color 0.3s; /* Smooth color transition */
        }

        /* Navbar link hover styles */
        .navbar-nav .nav-link:hover {
            color: #17a2b8; /* Change color on hover */
        }

        /* Active link styles */
        .navbar-nav .nav-link.active {
            color: #17a2b8; /* Active link color */
        }

        /* Toggler icon styles */
        .navbar-toggler {
            border: none;
            background-color: transparent; /* Transparent background */
        }

        /* Toggler icon line styles */
        .navbar-toggler-icon {
            background-color: #ffffff; /* White color for toggler icon lines */
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">{{ config("app.name") }}</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-link active" aria-current="page" href="{{ route("dashboard") }}">Home</a>
                    <a class="nav-link" href="{{ route("products") }}">Products</a>
                    <a class="nav-link" href="{{ route("order.list") }}">Orders</a>
                    <a class="nav-link" href="{{ route("logout") }}">Logout</a>
                </div>
            </div>
        </div>
    </nav>
</body>
</html> -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Title</title>
    <style>
        /* Navbar styles */
        .navbar {
            background-color: #343a40; /* Dark background color */
            padding: 10px 0;
            border-bottom: 4px solid #17a2b8; /* Bottom border color */
        }

        /* Navbar brand styles */
        .navbar-brand {
            font-size: 24px;
            font-weight: bold;
            color: #ffffff; /* White text color */
            text-transform: uppercase; /* Uppercase text */
            letter-spacing: 2px; /* Add some letter spacing */
        }

        /* Navbar link styles */
        .navbar-nav .nav-link {
            font-size: 18px;
            color: #ffffff; /* White text color */
            margin-right: 15px;
            transition: color 0.3s; /* Smooth color transition */
        }

        /* Navbar link hover styles */
        .navbar-nav .nav-link:hover {
            color: #17a2b8; /* Change color on hover */
        }

        /* Active link styles */
        .navbar-nav .nav-link.active {
            color: #17a2b8; /* Active link color */
        }

        /* Toggler icon styles */
        .navbar-toggler {
            border: none;
            background-color: transparent; /* Transparent background */
        }

        /* Toggler icon line styles */
        .navbar-toggler-icon {
            background-color: #ffffff; /* White color for toggler icon lines */
        }

        /* Customize hover color for logout link */
        .navbar-nav .nav-link[href="{{ route("logout") }}"]:hover {
            color: #ff0000; /* Red color on hover */
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">{{ config("app.name") }}</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-link active" aria-current="page" href="{{ route("dashboard") }}">Home</a>
                    <a class="nav-link" href="{{ route("products") }}">Products</a>
                    <a class="nav-link" href="{{ route("order.list") }}">Orders</a>
                    <a class="nav-link" href="{{ route("logout") }}">Logout</a>
                </div>
            </div>
        </div>
    </nav>
</body>
</html>




