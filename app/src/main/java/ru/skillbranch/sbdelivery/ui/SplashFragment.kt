package ru.skillbranch.sbdelivery.ui

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import ru.skillbranch.sbdelivery.R

class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_splash, container, false)
        val splashImage: ImageView = root.findViewById(R.id.splash_image) as ImageView
        val scaleDown: ObjectAnimator = ObjectAnimator.ofPropertyValuesHolder(
            splashImage,
            PropertyValuesHolder.ofFloat("scaleX", 1.4f),
            PropertyValuesHolder.ofFloat("scaleY", 1.4f)
        )
        scaleDown.duration = 3000

        scaleDown.repeatCount = ObjectAnimator.INFINITE
        scaleDown.repeatMode = ObjectAnimator.REVERSE

        scaleDown.start()
        return root
    }
}