package ru.skillbranch.sbdelivery

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import ru.skillbranch.sbdelivery.repositories.DataLoadingViewModel

class SplashActivity : AppCompatActivity() {

    val activityScope = CoroutineScope(Dispatchers.Main)
    private lateinit var viewModel: DataLoadingViewModel

    private val observer: Observer<DataLoadingViewModel.LoadingDataState> =
        Observer {
            when (it) {
                DataLoadingViewModel.LoadingDataState.UNKNOWN -> {
                    viewModel.loadData()
                }
                DataLoadingViewModel.LoadingDataState.FINISHED -> {
                    var intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                DataLoadingViewModel.LoadingDataState.ERROR -> {
                    val parentLayout: View = findViewById(R.id.nav_host_fragment)
                    Snackbar
                        .make(parentLayout, viewModel.getErrorString(), Snackbar.LENGTH_INDEFINITE)
                        .show()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_splash)

//        activityScope.launch {
//            delay(3000)
//
//            var intent = Intent(this@SplashActivity, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
        viewModel =
            DataLoadingViewModel(
                application
            )
        savedInstanceState ?: viewModel.loadingDataState.observe(this, observer)
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }
}