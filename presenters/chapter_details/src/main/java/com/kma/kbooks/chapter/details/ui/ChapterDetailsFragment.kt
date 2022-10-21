package com.kma.kbooks.chapter.details.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.kma.kbooks.chapter.details.injection.component.DaggerChapterDetailsComponent
import com.kma.kbooks.chapter.details.ui.component.ActionBar
import com.kma.kbooks.chapter.details.ui.component.ChapterContent
import com.kma.kbooks.chapter.details.ui.component.SettingsBottomSheet
import com.kma.kbooks.resources.ui.theme.KBooksTheme
import com.kma.kbooks.ui.main.MainActivity
import com.kma.kbooks.util.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChapterDetailsFragment : Fragment() {
    private val viewModel by viewModels<ChapterDetailsViewModel> { factory }

    @Inject
    lateinit var factory: ViewModelFactory<ChapterDetailsViewModel>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val mainComponent = (activity as? MainActivity)?.component
        val args by navArgs<ChapterDetailsFragmentArgs>()

        DaggerChapterDetailsComponent.builder()
            .mainComponent(mainComponent)
            .savedStateHandle(args.toSavedStateHandle())
            .build()
            .inject(this)
    }

    @ExperimentalMaterialApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        fitsSystemWindows = true
        setContent {
            KBooksTheme {
                this@ChapterDetailsFragment.Content()
            }
        }
    }

    @ExperimentalMaterialApi
    @Composable
    private fun Content() {
        val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
        val coroutineScope = rememberCoroutineScope()
        val fontSize = rememberSaveable { mutableStateOf(value = viewModel.fontSize) }

        ModalBottomSheetLayout(
            modifier = Modifier.fillMaxSize(),
            sheetState = sheetState,
            sheetShape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp
            ),
            sheetElevation = 8.dp,
            sheetContent = {
                SettingsBottomSheet(
                    fontSize = fontSize,
                    onClose = { coroutineScope.launch { sheetState.hide() } },
                    onFontSizeChanged = { viewModel.updateFontSize(it) }
                )
            },
            content = {
                val chapterDetails by viewModel.chapterDetails.observeAsState()

                Scaffold(
                    topBar = {
                        ActionBar(
                            onBackPress = { activity?.onBackPressedDispatcher?.onBackPressed() },
                            onSettingsClick = { coroutineScope.launch { sheetState.show() } },
                        )
                    }
                ) { padding ->
                    ChapterContent(
                        modifier = Modifier.padding(padding),
                        fontSize = fontSize.value.sp,
                        chapterDetails = chapterDetails
                    )
                }
            }
        )
    }
}
