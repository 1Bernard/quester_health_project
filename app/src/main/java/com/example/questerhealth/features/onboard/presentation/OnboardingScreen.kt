package com.example.questerhealth.features.onboard.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.questerhealth.features.onboard.presentation.components.ButtonUI
import com.example.questerhealth.features.onboard.presentation.components.IndicatorUI
import com.example.questerhealth.features.onboard.presentation.components.OnboardingGraphUI
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    event: (OnboardingEvent) -> Unit,
) {
    val pages = listOf(
        OnboardingModel.Firstpage,
        OnboardingModel.Secondpage,
        OnboardingModel.Thirdpage
    )

    val pagerState = rememberPagerState(initialPage = 0) {
        pages.size
    }

    val buttonState = remember {
        derivedStateOf {
            when (pagerState.currentPage) {
                0 -> listOf("", "Next")
                1 -> listOf("Back", "Next")
                2 -> listOf("Back", "Continue")
                else -> listOf("", "")
            }
        }
    }

    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth() // Use fillMaxWidth instead of fillMaxSize
                    .padding(10.dp, 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                    if(buttonState.value[0].isNotEmpty()) {
                        ButtonUI(
                            text = buttonState.value[0],
                            backgroundColor = Color.Transparent,
                            textColor = Color.Gray
                        ) {
                            scope.launch {
                                if (pagerState.currentPage > 0) {
                                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                }
                            }
                        }
                    }
                }

                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    IndicatorUI(pageSize = pages.size, currentPage = pagerState.currentPage)
                }

                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
                    ButtonUI(
                        text = buttonState.value[1],
                        backgroundColor = MaterialTheme.colorScheme.primary,
                        textColor = MaterialTheme.colorScheme.background
                    ) {
                        scope.launch {
                            if (pagerState.currentPage < pages.size - 1) {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            } else {
                               event(OnboardingEvent.SaveAppEntry)
                            }
                        }
                    }
                }

            }
        },
        content = { paddingValues ->
            // Ensure content respects Scaffold padding
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // Use paddingValues from Scaffold
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize() // Ensure HorizontalPager fills the remaining space
                ) { index ->
                    OnboardingGraphUI(onboardingModel = pages[index])
                }
            }
        }
    )

}
