package com.example.cleanarchtestapp.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.databinding.DataBindingUtil
import com.bluelinelabs.conductor.Controller
import com.example.cleanarchtestapp.R
import com.example.cleanarchtestapp.databinding.FragmentPresentationBinding
import com.example.domain.navigator.NavigationRoutes
import com.example.presentation.PresentationScreen
import com.example.presentation2.Presentation2Screen
import org.koin.core.component.KoinComponent

class ComposableController(bundle: Bundle) : Controller(bundle), KoinComponent {

    private var screenKey: String? = bundle.getString("screenKey")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup, savedViewState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentPresentationBinding>(
            inflater, R.layout.fragment_presentation, container, false
        )
        binding.composeView.setContent {
            GetComposable(screenKey)
        }
        return binding.root
    }

    @Composable
    fun GetComposable(screenKey: String?) {
        when (screenKey) {
            NavigationRoutes.PRESENTATION -> PresentationScreen()
            NavigationRoutes.PRESENTATION2 -> Presentation2Screen()
            else -> throw IllegalArgumentException("Unknown getComposable")
        }
    }

    companion object {
        fun newInstance(screenKey: String): ComposableController {
            val bundle = Bundle().apply {
                putString("screenKey", screenKey)
            }
            return ComposableController(bundle)
        }
    }
}