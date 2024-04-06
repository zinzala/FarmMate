@extends('layout')
@section('content')
<!-- <main class="container my-5" style="max-width: 900px">
        <div class="row">
            @if ($errors->any())
                <div class="col-12">
                    @foreach ($errors->all() as $error)
                        <div class="alert alert-danger">{{$error}}</div>
                    @endforeach
                </div>
            @endif
            @if(session()->has('success'))
                <div class="alert alert-success alert-dismissible">
                    {{session('success')}}
                </div>
            @endif
            @if(session()->has('error'))
                <div class="alert alert-danger alert-dismissible">
                    {{session('error')}}
                </div>
            @endif
            <div class="col-6 col-md-6">
                <div class="fs-5 fw-bold mb-2">Add new item:</div>
                <form method="POST" action="{{route("product.add")}}">
                    @csrf
                    <div class="mb-3">
                        <label class="form-label">Enter name of item</label>
                        <input type="text" name="name" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Enter item description</label>
                        <input type="text" name="description" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Enter item price</label>
                        <input type="text" name="price" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Enter item image url</label>
                        <input type="text" name="image" class="form-control">
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
            <div class="col-12 col-md-6">
                <div class="fs-5 fw-bold mb-2 text-decoration-underline">List of all item:</div>
                <ul class="list-group">
                    @foreach($products as $product)
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-8">
                                    <div class="fw-bold">{{$product->name}} | Price: ₹{{$product->price}}</div>
                                    <small>{{$product->description}}</small>
                                </div>
                                <div class="col-4">
                                    <img src="{{$product->image}}" width="100%" height="auto">
                                    <a href="{{route("product.delete")}}?id={{$product->id}}">Delete</a>
                                </div>
                            </div>
                        </li>
                    @endforeach
                </ul>
            </div>
        </div>
    </main> -->
    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css">
    <!-- <style>
        /* Custom CSS for product.blade.php */
        .product-form {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 10px;
        }

        .product-list {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .product-list-item {
            border: none;
            border-radius: 10px;
            margin-bottom: 15px;
        }

        .product-list-item img {
            border-radius: 10px;
            max-width: 100%;
            height: auto;
        }

        .delete-link {
            color: red;
            text-decoration: none;
            font-weight: bold;
            margin-left: 10px;
        }

        .delete-link:hover {
            text-decoration: underline;
        }
    </style> -->
    <style>
        /* Custom CSS for product.blade.php */
        .alert-dismissible {
            position: relative;
            padding-right: 4rem;
        }
        .alert-dismissible .close {
            position: absolute;
            top: 0;
            right: 0;
            padding: 1rem;
        }
        .product-form {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .product-list {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .product-list-item {
            border: none;
            border-radius: 10px;
            margin-bottom: 15px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            padding: 15px;
        }

        .product-list-item img {
            border-radius: 10px;
            max-width: 100%;
            height: auto;
        }

        .delete-link {
            color: red;
            text-decoration: none;
            font-weight: bold;
            margin-left: 10px;
        }

        .delete-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

    <!-- Your HTML content here -->
    <main class="container my-5" style="max-width: 900px">
        <div class="row">
        @if ($errors->any())
                <div class="col-12">
                    @foreach ($errors->all() as $error)
                        <div class="alert alert-danger">{{$error}}</div>
                    @endforeach
                </div>
            @endif
            @if(session()->has('success'))
                <div class="alert alert-success alert-dismissible">
                    {{session('success')}}
                </div>
            @endif
            @if(session()->has('error'))
                <div class="alert alert-danger alert-dismissible">
                    {{session('error')}}
                </div>
            @endif
            <div class="col-6 col-md-6 product-form">
                <div class="fs-5 fw-bold mb-2">Add new item:</div>
                <form method="POST" action="{{route("product.add")}}">
                    @csrf
                    <div class="mb-3">
                        <label class="form-label">Enter name of item</label>
                        <input type="text" name="name" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Enter item description</label>
                        <input type="text" name="description" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Enter item price</label>
                        <input type="text" name="price" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Enter item image url</label>
                        <input type="text" name="image" class="form-control">
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>

            <!-- List of all items -->
            <div class="col-12 col-md-6 product-list">
                <div class="fs-5 fw-bold mb-2 text-decoration-underline">All Products</div>
                <ul class="list-group">
                @foreach($products as $product)
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-8">
                                    <div class="fw-bold">{{$product->name}} | Price: ₹{{$product->price}}</div>
                                    <small>{{$product->description}}</small>
                                </div>
                                <div class="col-4">
                                    <img src="{{$product->image}}" width="100%" height="auto">
                                    <a href="{{route("product.delete")}}?id={{$product->id}}">Delete</a>
                                </div>
                            </div>
                        </li>
                    @endforeach
                </ul>
            </div>
        </div>
    </main>

</body>
</html>
@endsection
