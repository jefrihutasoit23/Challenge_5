challenge 5

1) Menambahkan CRUD dari masing-masing entity dan membuat API nya di controller.

2) Requirement sesuai deskripsi yang sudah terdapat dalam API selain CRUD antara lain:
    a)Menambahkan merchant, edit status merchant buka/tutup, menampilkan merchant yang sedang buka
    b)Menambahkan produk baru, mengupdate detail produk, menghapus produk, menampilkan produk yang tersedia 
    c)Menambahkan user, mengupdate user, menghapus user
    d)Membuat pesanan, menampilkan pesanan

3) Membuat RESTful API 
    a) Merchant
        Add (POST)
        Update (PUT), List dan filter merchant(GET)
        Get id merchant(GET), Delete Merchant(DELETE)

    b) Product Berdasarkan Merchant
        Add(POST)
        Update(PUT), List dan filter (GET)
        Get id merchant(GET), Delete Merchant(DELETE)

    c) User
        Add(POST)
        Update(PUT) List dan filter (GET)
        Get id merchant(GET), Delete Merchant(DELETE)

    d) Menampilkan detail order dari user yang melakukan order makanan.

    e) Menampilkan reporting merchant dan menghitung pendapatan merchant

4) Tiap service dan responnya sudah menggunakan responEntity dan dibungkus try catch

5) untuk requirement generate file PDF yaitu generate invoice order dan generate report penjualan merchant
 terdapat dalam InvoiceService dan implementasinya ada di OrderController dengan method POST.
