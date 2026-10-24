package com.uts.pringsewusehat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.uts.pringsewusehat.data.Datasource
import com.uts.pringsewusehat.data.model.Faskes
import com.uts.pringsewusehat.databinding.ActivityMainBinding
import com.uts.pringsewusehat.ui.adapter.FaskesAdapter

/**
 * MainActivity: Beranda utama menampilkan daftar fasilitas kesehatan di Kabupaten Pringsewu.
 * Memenuhi seluruh kriteria UTS:
 * - ViewBinding
 * - RecyclerView + Adapter + ViewHolder
 * - Explicit Intent + putExtra
 * - Lifecycle Logging (onCreate s/d onDestroy)
 * - Filter data dengan filter { } & notifyDataSetChanged()
 * - onSaveInstanceState untuk rotasi layar
 */
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivityLifecycle"
    private val KEY_SELECTED_FILTER = "KEY_SELECTED_FILTER"

    private lateinit var binding: ActivityMainBinding
    private lateinit var faskesAdapter: FaskesAdapter

    private val allFaskesList: List<Faskes> by lazy { Datasource.loadFaskesList() }

    private var currentFilterPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() called - Inisialisasi komponen MainActivity")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            currentFilterPosition = savedInstanceState.getInt(KEY_SELECTED_FILTER, 0)
            Log.d(TAG, "onRestore: memulihkan posisi filter = $currentFilterPosition")
        }

        setupRecyclerView()
        setupCategoryFilter()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        faskesAdapter = FaskesAdapter(allFaskesList) { selectedFaskes ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("EXTRA_FASKES", selectedFaskes)
            }
            startActivity(intent)
        }

        binding.rvFaskes.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = faskesAdapter
        }

        updateCountText(allFaskesList.size)
    }

    private fun setupCategoryFilter() {
        val categories = arrayOf(
            getString(R.string.category_all),
            getString(R.string.category_rs),
            getString(R.string.category_puskesmas),
            getString(R.string.category_klinik)
        )

        val spinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            categories
        )
        binding.spinnerCategoryFilter.adapter = spinnerAdapter
        binding.spinnerCategoryFilter.setSelection(currentFilterPosition)

        binding.spinnerCategoryFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                currentFilterPosition = position
                val selectedCategory = categories[position]

                val filteredList = if (position == 0) {
                    allFaskesList
                } else {
                    allFaskesList.filter { it.category.equals(selectedCategory, ignoreCase = true) }
                }

                faskesAdapter.updateData(filteredList)
                updateCountText(filteredList.size)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setupClickListeners() {
        binding.fabRegister.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateCountText(count: Int) {
        binding.tvFilterCount.text = getString(R.string.total_faskes_found, count)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_SELECTED_FILTER, currentFilterPosition)
        Log.d(TAG, "onSaveInstanceState() called - Menyimpan posisi filter: $currentFilterPosition")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called - Activity mulai terlihat di layar")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called - Activity siap berinteraksi dengan pengguna")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called - Activity kehilangan fokus")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called - Activity tidak lagi terlihat")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart() called - Activity dimulai ulang dari keadaan stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called - Activity dihancurkan dari memori")
    }
}
