@extends('layout')
@section('content')
    <!-- <main class="container my-5" style="max-width: 900px">
        <div class="row">
            <div class="col-12">
                <div class="fs-5 fw-bold mb-2 text-decoration-underline">List of all item:</div>
                <table class="table table-bordered border-secondary">
                    <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Order address</th>
                        <th>Order items</th>
                        <th>Order status</th>
                    </tr>
                    </thead>
                    <tbody>
                    @foreach($orders as $order)
                        <tr>
                            <td>{{$order->id}}</td>
                            <td>{{$order->destination_address}}</td>
                            <td>
                                @foreach($order->item_details as $item_details)
                                    <div>{{$item_details->name}}</div>
                                @endforeach
                            </td>
                            <td>{{$order->status}}</td>
                        </tr>
                    @endforeach
                    </tbody>
                </table>
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
    <style>
        /* Custom CSS for order.blade.php */
        .order-table {
            border-collapse: collapse;
            width: 100%;
            margin-bottom: 20px;
        }
        .order-table th, .order-table td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        .order-table th {
            background-color: #f2f2f2;
        }
        .order-table tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .order-table tr:hover {
            background-color: #ddd;
        }
        .order-table th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #04AA6D;
            color: white;
        }
        .order-table td {
            padding-top: 12px;
            padding-bottom: 12px;
        }
    </style>
</head>
<body>

    <!-- Your HTML content here -->
    <main class="container my-5" style="max-width: 900px">
        <div class="row">
            <div class="col-12">
                <div class="fs-5 fw-bold mb-2 text-decoration-underline"> All Orders:</div>
                <table class="table table-bordered border-secondary order-table">
                    <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Order address</th>
                        <th>Order items</th>
                        <th>Order status</th>
                    </tr>
                    </thead>
                    <tbody>
                    @foreach($orders as $order)
                        <tr>
                            <td>{{$order->id}}</td>
                            <td>{{$order->destination_address}}</td>
                            <td>
                                @foreach($order->item_details as $item_details)
                                    <div>{{$item_details->name}}</div>
                                @endforeach
                            </td>
                            <td>{{$order->status}}</td>
                        </tr>
                    @endforeach
                    </tbody>
                </table>
            </div>
        </div>
    </main>

</body>
</html>


@endsection
