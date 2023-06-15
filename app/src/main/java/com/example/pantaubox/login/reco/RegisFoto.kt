package com.example.pantaubox.login.reco

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.pantaubox.databinding.ActivityRegisFotoBinding
import com.example.pantaubox.di.ViewModelFactory
import com.example.pantaubox.main.MainActivity
import java.io.File

class RegisFoto : AppCompatActivity() {

    private lateinit var binding: ActivityRegisFotoBinding
    private val regisFotoViewModel: RegisFotoViewModel by viewModels { viewModelFactory }
    private lateinit var viewModelFactory: ViewModelFactory
    private var getFile: File? = null

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Tidak mendapatkan izin.",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisFotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()

        binding.btnGoFoto.setOnClickListener { startCamera() }
        //binding.buttonUpload.setOnClickListener {
        //    uploadStory()
        //}
        binding.btnVerif2.setOnClickListener { intentMain() }

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

    }

    private fun setupViewModel() {
        viewModelFactory = ViewModelFactory.getInstance(this)

        //regisFotoViewModel.isLoading.observe(this) {
        //    showLoading(it)
        //}
    }

    private fun startCamera() {
        val intent = Intent(this, CameraActivity::class.java)
        launcherIntentCamera.launch(intent)
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.data?.getSerializableExtra("picture", File::class.java)
            } else {
                @Suppress("DEPRECATION")
                it.data?.getSerializableExtra("picture")
            } as? File

            val isFrontCamera = it.data?.getBooleanExtra("isFrontCamera", true) as Boolean

            myFile?.let { file ->
                rotateFile(file, isFrontCamera)
                getFile = file
                binding.ivPreviewPhoto.setImageBitmap(BitmapFactory.decodeFile(file.path))
            }
        }
    }

    private fun intentMain() {
        startActivity(Intent(this@RegisFoto, MainActivity::class.java))
        Toast.makeText(
            this@RegisFoto,
            "Verifikasi Sukses!",
            Toast.LENGTH_SHORT
        ).show()
        finish()
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pbRegisFoto.visibility = View.VISIBLE
        } else {
            binding.pbRegisFoto.visibility = View.GONE
        }
    }

    companion object {
        const val CAMERA_X_RESULT = 200

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

}