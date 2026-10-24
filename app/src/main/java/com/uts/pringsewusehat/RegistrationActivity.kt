package com.uts.pringsewusehat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uts.pringsewusehat.data.Datasource
import com.uts.pringsewusehat.databinding.ActivityRegistrationBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random

/**
 * RegistrationActivity: Formulir pendaftaran antrean faskes & konsultasi online.
 * Memenuhi seluruh spesifikasi UTS:
 * - Komponen input: EditText (TextInputLayout), RadioGroup, Spinner, CheckBox.
 * - Validasi input lengkap agar tidak crash.
 * - Menangani lifecycle dengan onSaveInstanceState & pemulihan isian form saat rotasi layar.
 * - Pencatatan lifecycle callback ke Logcat dengan Log.d.
 * - Intent Explicit mengirim data ke SummaryActivity.
 */
class RegistrationActivity : AppCompatActivity() {

    private val TAG = "RegistrationLifecycle"

    // Keys untuk onSaveInstanceState
    private val KEY_NAME = "SAVED_NAME"
    private val KEY_NIK = "SAVED_NIK"
    private val KEY_PHONE = "SAVED_PHONE"
    private val KEY_COMPLAINT = "SAVED_COMPLAINT"
    private val KEY_PATIENT_TYPE_ID = "SAVED_PATIENT_TYPE_ID"
    private val KEY_FASKES_POS = "SAVED_FASKES_POS"
    private val KEY_POLI_POS = "SAVED_POLI_POS"
    private val KEY_AGREEMENT = "SAVED_AGREEMENT"

    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var faskesNames: List<String>
    private lateinit var poliOptions: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() called - Inisialisasi RegistrationActivity")

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupSpinners()
        setupClickListeners()

        // Pulihkan state jika ada (misal akibat rotasi layar)
        if (savedInstanceState != null) {
            restoreFormState(savedInstanceState)
        } else {
            handleIncomingIntent()
        }
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            Log.d(TAG, "Tombol kembali toolbar diklik")
            finish()
        }
    }

    private fun setupSpinners() {
        // Spinner Faskes dari Datasource
        val allFaskes = Datasource.loadFaskesList()
        faskesNames = allFaskes.map { it.name }
        val faskesAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            faskesNames
        )
        binding.spinnerTargetFaskes.adapter = faskesAdapter

        // Spinner Poli dari strings.xml
        poliOptions = resources.getStringArray(R.array.poli_options)
        val poliAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            poliOptions
        )
        binding.spinnerPoli.adapter = poliAdapter
    }

    private fun handleIncomingIntent() {
        // Cek jika ada nama faskes yang dikirim dari DetailActivity
        val preselectedFaskes = intent.getStringExtra("EXTRA_PRESELECTED_FASKES")
        if (!preselectedFaskes.isNullOrEmpty()) {
            val index = faskesNames.indexOf(preselectedFaskes)
            if (index >= 0) {
                binding.spinnerTargetFaskes.setSelection(index)
            }
        }
    }

    private fun setupClickListeners() {
        binding.btnSubmit.setOnClickListener {
            if (validateInputs()) {
                submitRegistration()
            }
        }

        binding.btnReset.setOnClickListener {
            resetForm()
        }
    }

    private fun validateInputs(): Boolean {
        var isValid = true

        val name = binding.etPatientName.text?.toString()?.trim().orEmpty()
        val nik = binding.etNik.text?.toString()?.trim().orEmpty()
        val phone = binding.etPhone.text?.toString()?.trim().orEmpty()
        val complaint = binding.etComplaint.text?.toString()?.trim().orEmpty()
        val isAgreed = binding.cbAgreement.isChecked

        if (name.isEmpty()) {
            binding.tilPatientName.error = getString(R.string.error_name_empty)
            isValid = false
        } else {
            binding.tilPatientName.error = null
        }

        if (nik.length != 16 || !nik.all { it.isDigit() }) {
            binding.tilNik.error = getString(R.string.error_nik_invalid)
            isValid = false
        } else {
            binding.tilNik.error = null
        }

        if (phone.length < 10 || !phone.all { it.isDigit() }) {
            binding.tilPhone.error = getString(R.string.error_phone_invalid)
            isValid = false
        } else {
            binding.tilPhone.error = null
        }

        if (complaint.isEmpty()) {
            binding.tilComplaint.error = getString(R.string.error_complaint_empty)
            isValid = false
        } else {
            binding.tilComplaint.error = null
        }

        if (!isAgreed) {
            Toast.makeText(this, getString(R.string.error_agreement_required), Toast.LENGTH_SHORT).show()
            isValid = false
        }

        return isValid
    }

    private fun submitRegistration() {
        val name = binding.etPatientName.text.toString().trim()
        val nik = binding.etNik.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val complaint = binding.etComplaint.text.toString().trim()
        val selectedFaskes = binding.spinnerTargetFaskes.selectedItem.toString()
        val selectedPoli = binding.spinnerPoli.selectedItem.toString()

        val patientType = if (binding.rbBpjs.isChecked) {
            getString(R.string.patient_type_bpjs)
        } else {
            getString(R.string.patient_type_general)
        }

        val randomNum = Random.nextInt(101, 999)
        val queueNumber = "PRW-A$randomNum"

        val currentDate = SimpleDateFormat("dd MMMM yyyy, HH:mm 'WIB'", Locale.forLanguageTag("id-ID"))
            .format(Date())

        Toast.makeText(this, getString(R.string.toast_form_valid), Toast.LENGTH_SHORT).show()

        val intent = Intent(this, SummaryActivity::class.java).apply {
            putExtra("EXTRA_QUEUE_NUMBER", queueNumber)
            putExtra("EXTRA_PATIENT_NAME", name)
            putExtra("EXTRA_NIK", nik)
            putExtra("EXTRA_PHONE", phone)
            putExtra("EXTRA_PATIENT_TYPE", patientType)
            putExtra("EXTRA_FASKES_NAME", selectedFaskes)
            putExtra("EXTRA_POLI_NAME", selectedPoli)
            putExtra("EXTRA_COMPLAINT", complaint)
            putExtra("EXTRA_DATE", currentDate)
        }
        startActivity(intent)
    }

    private fun resetForm() {
        binding.etPatientName.setText("")
        binding.etNik.setText("")
        binding.etPhone.setText("")
        binding.etComplaint.setText("")
        binding.tilPatientName.error = null
        binding.tilNik.error = null
        binding.tilPhone.error = null
        binding.tilComplaint.error = null
        binding.rbBpjs.isChecked = true
        binding.cbAgreement.isChecked = false
        binding.spinnerPoli.setSelection(0)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState() called - Menyimpan data isian formulir pengguna")

        outState.putString(KEY_NAME, binding.etPatientName.text?.toString())
        outState.putString(KEY_NIK, binding.etNik.text?.toString())
        outState.putString(KEY_PHONE, binding.etPhone.text?.toString())
        outState.putString(KEY_COMPLAINT, binding.etComplaint.text?.toString())
        outState.putInt(KEY_PATIENT_TYPE_ID, binding.rgPatientType.checkedRadioButtonId)
        outState.putInt(KEY_FASKES_POS, binding.spinnerTargetFaskes.selectedItemPosition)
        outState.putInt(KEY_POLI_POS, binding.spinnerPoli.selectedItemPosition)
        outState.putBoolean(KEY_AGREEMENT, binding.cbAgreement.isChecked)
    }

    private fun restoreFormState(savedInstanceState: Bundle) {
        Log.d(TAG, "restoreFormState() called - Memulihkan data isian formulir pengguna")

        binding.etPatientName.setText(savedInstanceState.getString(KEY_NAME, ""))
        binding.etNik.setText(savedInstanceState.getString(KEY_NIK, ""))
        binding.etPhone.setText(savedInstanceState.getString(KEY_PHONE, ""))
        binding.etComplaint.setText(savedInstanceState.getString(KEY_COMPLAINT, ""))

        val checkedRadioId = savedInstanceState.getInt(KEY_PATIENT_TYPE_ID, -1)
        if (checkedRadioId != -1) {
            binding.rgPatientType.check(checkedRadioId)
        }

        binding.spinnerTargetFaskes.setSelection(savedInstanceState.getInt(KEY_FASKES_POS, 0))
        binding.spinnerPoli.setSelection(savedInstanceState.getInt(KEY_POLI_POS, 0))
        binding.cbAgreement.isChecked = savedInstanceState.getBoolean(KEY_AGREEMENT, false)
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
