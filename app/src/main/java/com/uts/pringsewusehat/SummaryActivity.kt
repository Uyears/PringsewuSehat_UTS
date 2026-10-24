package com.uts.pringsewusehat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.uts.pringsewusehat.databinding.ActivitySummaryBinding

/**
 * SummaryActivity: Menampilkan bukti pendaftaran antrean dan nomor antrean digital.
 * Memenuhi kriteria:
 * - Menerima data dari putExtra Intent
 * - ViewBinding
 * - Lifecycle Logging
 * - Navigasi Intent explicit kembali ke MainActivity
 * - Fitur tambahan: Intent implicit untuk membagikan bukti antrean
 */
class SummaryActivity : AppCompatActivity() {

    private val TAG = "SummaryActivityLifecycle"
    private lateinit var binding: ActivitySummaryBinding

    private var queueNumber: String = ""
    private var patientName: String = ""
    private var faskesName: String = ""
    private var poliName: String = ""
    private var registeredDate: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() called - Inisialisasi SummaryActivity")

        binding = ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        extractIntentData()
        displaySummaryData()
        setupClickListeners()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            Log.d(TAG, "Navigasi kembali diklik")
            finish()
        }
    }

    private fun extractIntentData() {
        queueNumber = intent.getStringExtra("EXTRA_QUEUE_NUMBER").orEmpty()
        patientName = intent.getStringExtra("EXTRA_PATIENT_NAME").orEmpty()
        val rawNik = intent.getStringExtra("EXTRA_NIK").orEmpty()
        val phone = intent.getStringExtra("EXTRA_PHONE").orEmpty()
        val patientType = intent.getStringExtra("EXTRA_PATIENT_TYPE").orEmpty()
        faskesName = intent.getStringExtra("EXTRA_FASKES_NAME").orEmpty()
        poliName = intent.getStringExtra("EXTRA_POLI_NAME").orEmpty()
        val complaint = intent.getStringExtra("EXTRA_COMPLAINT").orEmpty()
        registeredDate = intent.getStringExtra("EXTRA_DATE").orEmpty()

        binding.tvQueueNumber.text = queueNumber
        binding.tvRegisteredDate.text = registeredDate
        binding.tvSummaryFaskes.text = faskesName
        binding.tvSummaryPoli.text = poliName
        binding.tvSummaryPatientName.text = patientName
        binding.tvSummaryPatientType.text = patientType
        binding.tvSummaryComplaint.text = complaint

        // Masking NIK untuk privasi (contoh: 181001******0002)
        binding.tvSummaryNik.text = if (rawNik.length == 16) {
            rawNik.substring(0, 6) + "******" + rawNik.substring(12)
        } else {
            rawNik
        }
    }

    private fun displaySummaryData() {
        // Data sudah diisi saat extractIntentData
    }

    private fun setupClickListeners() {
        // Kembali ke beranda utama dengan membersihkan backstack
        binding.btnBackHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
            finish()
        }

        // Implicit Intent: Membagikan bukti antrean ke aplikasi lain (WhatsApp, Catatan, dll.)
        binding.btnShare.setOnClickListener {
            val shareText = """
                *BUKTI ANTREAN DIGITAL - PRINGSEWU SEHAT*
                Dinas Kesehatan Kabupaten Pringsewu
                -----------------------------------
                Nomor Antrean : $queueNumber
                Tanggal       : $registeredDate
                Faskes Tujuan : $faskesName
                Poli/Layanan  : $poliName
                Nama Pasien   : $patientName
                
                Harap datang 15 menit sebelum pemeriksaan dengan membawa KTP dan kartu BPJS.
            """.trimIndent()

            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, shareText)
                putExtra(Intent.EXTRA_SUBJECT, "Bukti Antrean Dinkes Pringsewu - $queueNumber")
            }
            startActivity(Intent.createChooser(shareIntent, "Bagikan Bukti Antrean via"))
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }
}
