package ru.dmitry.belyaev.app.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.dmitry.belyaev.app.weather.fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container,
                HomeFragment.newInstance(), null)
                .commit()
        }
    }

}
