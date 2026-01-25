package com.example.uthsmarttasks.model

import androidx.lifecycle.ViewModel
import com.example.uthsmarttasks.data.OnboardingData

class OnboardingViewModel : ViewModel() {
    val onboardingItems = OnboardingData.items
}
