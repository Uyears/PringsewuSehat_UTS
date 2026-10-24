package com.uts.pringsewusehat.data

import com.uts.pringsewusehat.data.model.Faskes

/**
 * Datasource provides the hardcoded list of healthcare facilities in Pringsewu Regency.
 * Complies with the exam requirement: "Aplikasi tidak perlu terhubung ke internet atau database.
 * Seluruh data boleh disimpan langsung di kode (hardcoded), misalnya dalam List berisi data class."
 */
object Datasource {

    fun loadFaskesList(): List<Faskes> {
        return listOf(
            Faskes(
                id = 1,
                name = "RSUD Pringsewu",
                category = "Rumah Sakit",
                address = "Jl. Fatahillah No. 1, Pringsewu Selatan, Kec. Pringsewu, Kab. Pringsewu",
                phone = "(0729) 21118",
                operationalHours = "24 Jam (IGD) | 08.00 - 14.00 WIB (Poli Rawat Jalan)",
                doctorSchedule = "• dr. Bambang Priyanto, Sp.PD (Spesialis Penyakit Dalam): Senin - Kamis 09.00 - 13.00\n• dr. Siti Rahmawati, Sp.A (Spesialis Anak): Senin - Jumat 08.30 - 12.30\n• dr. Hendra Wijaya, Sp.B (Spesialis Bedah): Selasa & Kamis 10.00 - 14.00\n• drg. Rina Marlina (Dokter Gigi): Setiap Hari Kerja 08.00 - 13.00",
                facilities = "IGD 24 Jam, ICU & NICU, Kamar Operasi, Laboratorium Patologi, Radiologi & Rontgen, Rawat Inap Kelas I/II/III/VIP, Ambulans 24 Jam, Apotek Terpadu.",
                description = "Rumah Sakit Umum Daerah kebanggaan masyarakat Pringsewu dengan standar pelayanan medis bermutu tinggi, melayani pasien BPJS Kesehatan, KIS, dan Umum.",
                rating = 4.8
            ),
            Faskes(
                id = 2,
                name = "RS Mitra Husada Pringsewu",
                category = "Rumah Sakit",
                address = "Jl. Jenderal Ahmad Yani No. 128, Pringsewu Utara, Kec. Pringsewu",
                phone = "(0729) 22350",
                operationalHours = "24 Jam (IGD & Rawat Inap) | 08.00 - 20.00 WIB (Poli Spesialis)",
                doctorSchedule = "• dr. Antonius, Sp.OG (Spesialis Kandungan): Senin - Sabtu 16.00 - 20.00\n• dr. Fitri Handayani, Sp.M (Spesialis Mata): Senin, Rabu, Jumat 09.00 - 13.00\n• dr. Wahyu Eko, Sp.JP (Spesialis Jantung): Selasa & Kamis 14.00 - 18.00",
                facilities = "Instalasi Gawat Darurat (IGD), Kamar Bersalin, Hemodialisa (Cuci Darah), Fisioterapi, Medical Check-Up, Poliklinik Eksekutif.",
                description = "Rumah sakit swasta tipe C terkemuka dengan keunggulan fasilitas persalinan modern, layanan cuci darah, dan tim dokter spesialis berpengalaman.",
                rating = 4.7
            ),
            Faskes(
                id = 3,
                name = "Puskesmas Pringsewu",
                category = "Puskesmas",
                address = "Jl. KH. Ghalib No. 34, Pringsewu Barat, Kec. Pringsewu",
                phone = "(0729) 21456",
                operationalHours = "Senin - Sabtu: 07.30 - 14.00 WIB (Layanan Persalinan 24 Jam)",
                doctorSchedule = "• dr. Nurul Hidayah (Dokter Umum): Senin - Sabtu 08.00 - 13.00\n• dr. Eko Prasetyo (Poli Umum): Senin - Kamis 08.00 - 13.00\n• Bidan Dwi Astuti, S.Tr.Keb (Poli KIA): Senin - Sabtu 08.00 - 14.00",
                facilities = "Poli Umum, Poli Gigi, Poli KIA & KB, Imunisasi Anak, Laboratorium Sederhana, Pojok Gizi, Ruang Bersalin (PONED 24 Jam).",
                description = "Pusat Kesehatan Masyarakat perkotaan Pringsewu yang melayani pemeriksaan kesehatan primer, program pencegahan stunting, dan rujukan BPJS Faskes Tingkat Pertama (FKTP).",
                rating = 4.5
            ),
            Faskes(
                id = 4,
                name = "Puskesmas Gadingrejo",
                category = "Puskesmas",
                address = "Jl. Raya Gadingrejo No. 88, Kec. Gadingrejo, Kab. Pringsewu",
                phone = "(0729) 23101",
                operationalHours = "24 Jam (Pelayanan Rawat Inap & UGD) | 08.00 - 13.30 WIB (Poli)",
                doctorSchedule = "• dr. Rizky Kurniawan (Dokter Umum): Senin - Sabtu 08.00 - 13.30\n• drg. Maya Safitri (Dokter Gigi): Selasa, Kamis, Sabtu 08.30 - 12.30\n• dr. Dian Anggraini (Poli Lansia): Rabu & Jumat 09.00 - 12.00",
                facilities = "Rawat Inap 24 Jam, UGD Darurat, Poli Kesehatan Ibu & Anak, Farmasi Obat Gratis BPJS, Ambulans Siaga Desa.",
                description = "Puskesmas rawat inap utama di wilayah timur Pringsewu dengan layanan unggulan rawat inap persalinan, poli gigi, dan penanganan kegawatdaruratan dasar.",
                rating = 4.6
            ),
            Faskes(
                id = 5,
                name = "Puskesmas Sukoharjo",
                category = "Puskesmas",
                address = "Jl. Waringinsari No. 12, Sukoharjo III, Kec. Sukoharjo, Kab. Pringsewu",
                phone = "(0729) 24512",
                operationalHours = "24 Jam (UGD & Bersalin) | 08.00 - 13.00 WIB (Poli Rawat Jalan)",
                doctorSchedule = "• dr. Agus Sudrajat (Dokter Kepala): Senin - Jumat 08.00 - 13.00\n• dr. Tari Lestari (Poli Umum & Anak): Senin - Sabtu 08.00 - 13.00\n• Bidan Kusmiati (Poli Kandungan & KB): Setiap Hari Kerja 08.00 - 14.00",
                facilities = "Unit Gawat Darurat, Poli Umum, Poli Lansia, Pelayanan TB Terpadu, Pojok ASI, Ruang Farmasi, Ruang Bersalin.",
                description = "Melayani masyarakat Sukoharjo dan sekitarnya dengan pelayanan ramah, ramah anak, serta aktif dalam program posyandu balita dan lansia.",
                rating = 4.4
            ),
            Faskes(
                id = 6,
                name = "Puskesmas Ambarawa",
                category = "Puskesmas",
                address = "Jl. Sapuberani No. 05, Kec. Ambarawa, Kab. Pringsewu",
                phone = "(0729) 22890",
                operationalHours = "Senin - Sabtu: 07.30 - 13.30 WIB",
                doctorSchedule = "• dr. Arif Wicaksono (Dokter Umum): Senin - Sabtu 08.00 - 13.00\n• Bidan Ratna Dewi (Poli KIA): Senin - Sabtu 08.00 - 13.30",
                facilities = "Pemeriksaan Umum, Konsultasi Sanitasi Lingkungan, Pelayanan Lansia, Laboratorium Darah & Urine Sederhana.",
                description = "Puskesmas dengan dedikasi pencegahan penyakit menular dan pembinaan desa sehat di Kecamatan Ambarawa.",
                rating = 4.3
            ),
            Faskes(
                id = 7,
                name = "Puskesmas Pardasuka",
                category = "Puskesmas",
                address = "Jl. Raya Pardasuka No. 44, Kec. Pardasuka, Kab. Pringsewu",
                phone = "(0729) 25114",
                operationalHours = "24 Jam (UGD & PONED) | 08.00 - 13.00 WIB (Poli)",
                doctorSchedule = "• dr. M. Faisal (Poli Umum): Senin - Jumat 08.00 - 13.00\n• drg. Annisa Pratiwi (Poli Gigi): Senin, Rabu, Kamis 08.30 - 12.00",
                facilities = "Pelayanan Obstetri Neonatal Emergensi Dasar (PONED), UGD 24 Jam, Poli MTBS (Anak Sakit), Rawat Inap Tingkat Pertama.",
                description = "Puskesmas rujukan utama di bagian barat daya Pringsewu dengan kesiapsiagaan ambulans dan penanganan persalinan berisiko tinggi.",
                rating = 4.5
            ),
            Faskes(
                id = 8,
                name = "Klinik Pratama Surya Mandiri",
                category = "Klinik",
                address = "Jl. Veteran No. 20, Pringsewu Timur, Kec. Pringsewu",
                phone = "(0729) 21980",
                operationalHours = "Setiap Hari: 07.00 - 21.00 WIB",
                doctorSchedule = "• dr. Surya Pratama (Dokter Umum): Senin - Minggu 07.00 - 14.00\n• dr. Melati Indah (Dokter Umum): Senin - Minggu 14.00 - 21.00\n• drg. Fajar Nugroho: Senin, Rabu, Jumat 16.00 - 20.00",
                facilities = "Pemeriksaan Dokter Umum & Gigi, Tes Laboratorium Darah Cepat, Farmasi Lengkap, Surat Keterangan Sehat, Layanan Khitan Modern.",
                description = "Klinik pratama swasta dengan waktu tunggu singkat, tempat nyaman, serta dokter umum dan gigi yang siap melayani hingga malam hari.",
                rating = 4.7
            ),
            Faskes(
                id = 9,
                name = "Klinik Medika Utama Pringsewu",
                category = "Klinik",
                address = "Jl. Jenderal Sudirman No. 71, Pringsewu",
                phone = "(0729) 22005",
                operationalHours = "Senin - Sabtu: 08.00 - 20.00 WIB",
                doctorSchedule = "• dr. Kevin Sanjaya: Senin - Sabtu 08.00 - 14.00\n• dr. Anita Wijaya: Senin - Sabtu 14.00 - 20.00",
                facilities = "Poli Umum, Apotek 24 Jam, Cek Kolesterol, Gula Darah, Asam Urat, Nebulizer (Uap Asma).",
                description = "Klinik keluarga terpercaya di pusat Kota Pringsewu dengan pelayanan ramah dan harga terjangkau untuk seluruh lapisan masyarakat.",
                rating = 4.6
            )
        )
    }
}
