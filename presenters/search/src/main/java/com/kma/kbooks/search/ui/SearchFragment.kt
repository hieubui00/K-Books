package com.kma.kbooks.search.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.kma.kbooks.resources.ui.theme.KBooksTheme
import com.kma.kbooks.search.injection.component.DaggerSearchComponent
import com.kma.kbooks.ui.main.MainActivity

class SearchFragment : Fragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val mainComponent = (activity as? MainActivity)?.component

        DaggerSearchComponent.builder()
            .mainComponent(mainComponent)
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        fitsSystemWindows = true
        setContent {
            KBooksTheme {
                this@SearchFragment.Content()
            }
        }
    }

    @Composable
    private fun Content() {
        // TODO("Create UI components for Search screen")
    }
}