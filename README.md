# Algeo01-22014
Tugas besar 1 Aljabar Linear dan Geometri

Program ini adalah program pengolahan matriks yang dapat mengolah sebuah matriks sehingga menghasilkan solusi sistem persamaan linier, determinan, invers, interpolasi polinom, interpolasi bicubic spline, regresi linier berganda, dan perbesaran gambar.


Anggota : <br>
Raden Rafly Hanggaraksa Budiarto - 13522014 <br>
Rayhan Fadhlan Azka - 13522095 <br>
Rayendra Althaf Taraka Noor - 13522107 <br>

## How To Run

Untuk menjalankan program ini kalian dapat,

1. Masuk kedalam folder bin
```
  $ cd bin
```
2. Run file Main
```
  $ java src.Main
```


## Disclaimer
1. Pastikan jika ingin menginput dari file, file .txt tersebut berada di dalam folder test.
2. Jika ingin print output ke file, hasil file tersebut berada dalam folder dataoutput.


## Format File Masukan

1. Untuk penggunaan program SPL input adalah Matriks Augmented. Untuk SPL, Determinan, dan Matriks balikan input pada file seperti:
```
1 1 -1 -1 1
2 5 -7 -5 -2
2 -1 1 3 4
5 2 -4 2 6
```
2. Dalam penggunaan interpolasi polinomial. Matriks dimasukkan dengan ketentuan berikut **Jumlah Kolom 2**, **Jumlah Baris merupakan banyak titik ditambah satu (n + 1)**, **Baris terakhir merupakan nilai yang ingin diaproksimasi diikuti dengan nol**. 


Example:
Banyak Titik : 2 <br>
Nilai yang ingin diaproksimasi: 12 <br>
```
1 2
3 4
12
```

3. Sub program interpolasi bicubic digunakan dengan cara memasukkan matriks berukuran 4 x 4 dengan nilai f(x,y), fx(x,y), fy(x,y), dan fxy(x,y) di titik (0,0),
   (0,1), (1,0), dan (1,1). Matrik kemudian diikuti 2 nilai yang merupakan nilai x dan y yang akan ditaksir dari hasil interpolasi bicubic.

   Format masukan:
   ```
   f(0,0) f(1,0) f(0,1) f(1,1)
   fx(0,0) fx(1,0) fx(0,1) fx(1,1)
   fy(0,0) fy(1,0) fy(0,1) fy(1,1)
   fxy(0,0) fxy(1,0) fxy(0,1) fxy(1,1)
   tx ty
   ```
4. Sub program regresi linear akan menerima 1 atau 2 matriks masukan (diambil secara terpisah). Matriks pertama akan menerima data yang akan dijadikan acuan untuk membentuk persamaan regresi.
   Kemudian jika ada nilai yang ingin ditaksir, akan diambil matriks kedua yang berukuran 1 x n-1 dimana n adalah jumlah kolom dari matriks pertama.
   
   Contoh:
   matriks pertama
   ```
   1 2 3 4
   4 21 5 6
   2 1 6 7
   12 32 1 2
   31 2 4 120
   ```
   matriks kedua
   ```
   1 2 3
   ```
## INPUT / OUTPUT FILE
1. Input file
   Input file akan mengambil file dalam folder test. Sebagai contoh, masukan lokasi di bawah
   ini akan mengambil file test\tc.txt
   
   ```
   tc.txt
   ```
3. Output file
   Input file akan membuat file tipe txt dalam folder dataoutput dan jika file sudah ada maka file tersebut akan ditulis ulang.Pemberian nama juga tidak perlu disertai
   jenis file yang dalam kasus ini adalah ".txt". Sebagai contoh, masukan lokasi di bawah ini akan mebuat file dataoutput\result.txt

   ```
   result
   ```
