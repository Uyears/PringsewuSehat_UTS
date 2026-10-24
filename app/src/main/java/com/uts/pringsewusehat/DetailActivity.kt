package com.uts.pringsewusehat

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.uts.pringsewusehat.data.model.Faskes
import com.uts.pringsewusehat.databinding.ActivityDetailBinding

/**
 * DetailActivity: Halaman detail informasi faskes dan jadwal praktik dokter.
 * Memenuhi kriteria:
 * - Menampilkan detail saat item dipilih dari RecyclerView
 * - Mengambil data yang dikirim melalui Intent putExtra
 * - ViewBinding
 * - Lifecycle Logging
 */
class DetailActivity : AppCompatActivity() {

    private val TAG = "DetailActivityLifecycle"
    private lateinit var binding: ActivityDetailBinding
    private var currentFaskes: Faskes? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() called - Inisialisasi DetailActivity")

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        extractIntentData()
        displayFaskesDetails()
        setupClickListeners()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            Log.d(TAG, "Navigasi kembali diklik")
            finish()
        }
    }

    private fun extractIntentData() {
        // Mengambil data Serializable dengan kompatibilitas versi Android
        currentFaskes = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("EXTRA_FASKES", Faskes::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("EXTRA_FASKES") as? Faskes
        }
    }

    private fun displayFaskesDetails() {
        val faskes = currentFaskes ?: return

        binding.tvDetailName.text = faskes.name
        binding.tvDetailCategory.text = faskes.category
        binding.tvDetailRating.text = getString(R.string.rating_format, faskes.rating)
        binding.tvDetailHours.text = faskes.operationalHours
        binding.tvDetailDescription.text = faskes.description
        binding.tvDetailFacilities.text = faskes.facilities
        binding.tvDetailSchedule.text = faskes.doctorSchedule
        binding.tvDetailAddress.text = faskes.address
        binding.tvDetailPhone.text = faskes.phone

        when (faskes.category) {
            "Rumah Sakit" -> {
                binding.tvDetailCategory.setTextColor(
                    ContextCompat.getColor(this, R.color.chip_hospital_text)
                )
            }
            "Puskesmas" -> {
                binding.tvDetailCategory.setTextColor(
                    ContextCompat.getColor(this, R.color.chip_puskesmas_text)
                )
            }
            else -> {
                binding.tvDetailCategory.setTextColor(
                    ContextCompat.getColor(this, R.color.chip_klinik_text)
                )
            }
        }
    }

    private fun setupClickListeners() {
        binding.btnBookAtThisFaskes.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java).apply {
                putExtra("EXTRA_PRESELECTED_FASKES", currentFaskes?.name)
            }
            startActivity(intent)
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
