package edu.neo.tpfinal.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import edu.neo.tpfinal.R

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val splashFondo: ImageView = findViewById(R.id.s_fondo)
        Handler().postDelayed({startActivity(Intent(this,MainActivity::class.java))
            this.finish()},1500)
    }
}