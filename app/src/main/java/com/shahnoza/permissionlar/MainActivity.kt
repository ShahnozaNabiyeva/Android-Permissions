package com.shahnoza.permissionlar

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.single.PermissionListener
import com.karumi.dexter.listener.single.SnackbarOnDeniedPermissionListener
import com.shahnoza.permissionlar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        permissions()
    }
    private fun permissions(){
        //SnackbarPermissionListener -> Dexter orqali so'ralayotgan ruxsat javobi ruxsat berilishi yoki aksi

        val snackbarPermissionListener:PermissionListener=
            SnackbarOnDeniedPermissionListener.Builder
                .with(binding.txt1,"Ruxsatlar")
                .withOpenSettingsButton("Sozlamalar")
                .withCallback(object : Snackbar.Callback(){

                    override fun onShown(sb: Snackbar?) {
                        super.onShown(sb)
                        binding.holat.text="Ruxsat berildi"

                    }

                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                        super.onDismissed(transientBottomBar, event)
                        binding.holat.text="Ruxsat berimadi"
                    }

                }).build()

        // Foydalanuvchi uchun so'rov, permissionlardan  foydalanish uchun



        Dexter.withActivity(this)
            .withPermission(android.Manifest.permission.CAMERA) // foydalanuvchidan so`ralishi kerak bo`lgan ruxsatlar
            .withListener(snackbarPermissionListener) // ruxsat xolati
            .check() // permissionni tekshiriladi

    }



}


