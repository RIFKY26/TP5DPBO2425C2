-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 17 Okt 2025 pada 15.57
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_product`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `product`
--

CREATE TABLE `product` (
  `id` varchar(255) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `harga` double NOT NULL,
  `kategori` varchar(255) NOT NULL,
  `rating` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `product`
--

INSERT INTO `product` (`id`, `nama`, `harga`, `kategori`, `rating`) VALUES
('B001', 'Laskar Pelangi', 85000, 'Novel', 5),
('B002', 'Atomic Habits', 125000, 'Self-Improvement', 4),
('B003', 'Pemrograman Java', 98000, 'Teknologi', 5),
('B004', 'Bumi', 90000, 'Fiksi', 4),
('B005', 'Sapienss', 150000, 'Sejarah', 5),
('B006', 'Chainsaw Man Vol 2', 40000, 'Komik', 5),
('B007', 'Sakamoto Days Vol. 1', 45000, 'Komik', 5),
('B008', 'Doraemon Vol. 10', 35000, 'Komik', 4),
('B009', 'One Piece Vol. 100', 40000, 'Komik', 5),
('B010', 'Jujutsu Kaisen Vol. 20', 42000, 'Komik', 5),
('B011', '20th Century Boys Vol. 1', 55000, 'Komik', 5),
('B012', 'One-Punch Man Vol. 25', 40000, 'Komik', 4),
('B013', 'Naruto Vol. 72', 38000, 'Komik', 4),
('B014', 'Dragon Ball Super Vol. 15', 40000, 'Komik', 4),
('B015', 'Attack on Titan Vol. 34', 45000, 'Komik', 5);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
