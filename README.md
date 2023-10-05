# Algeo01-22014
Tugas besar 1 Aljabar Linear dan Geometri

Program ini adalah program pengolahan matriks yang dapat mengolah sebuah matriks sehingga menghasilkan solusi sistem persamaan linier, determinan, invers, interpolasi polinom, interpolasi bicubic spline, regresi linier berganda, dan perbesaran gambar.


Anggota : <br>
Raden Rafly Hanggaraksa Budiarto - 13522014 <br>
Rayhan Fadhlan Azka - 13522095 <br>
Rayendra Althaf Taraka Noor - 13522107 <br>

## How To Run

Untuk menjalankan program ini kalian dapat,

1. Masuk kedalam folder class
```
  $ cd bin
```
2. Run file Main
```
java src.Main
```


## Disclaimer
1. Pastikan jika ingin menginput dari file, file .txt tersebut berada di dalam folder test.
2. Jika ingin print output ke file, hasil file tersebut berada dalam folder dataoutput.


## Format File Masukan

1. Setiap file masukan harus diakhiri newline
2. Untuk penggunaan program SPL,determinan, dan matriks balikan format matriks adalah matriks augmented (Ax = B) dengan kolom terakhir adalah B.
```
1 1 -1 -1 1
2 5 -7 -5 -2
2 -1 1 3 4
5 2 -4 2 6
```
3. Dalam penggunaan interpolasi polinomial. Matriks dimasukkan dengan ketentuan berikut **Jumlah Kolom 2**, **Jumlah Baris merupakan banyak titik ditambah satu (n + 1)**, **Baris terakhir merupakan nilai yang ingin diaproksimasi diikuti dengan nol**. 


Example:
Banyak Titik : 2 <br>
Nilai yang ingin diaproksimasi: 12 <br>
```
1 2
3 4
12
```

## Program Structure
