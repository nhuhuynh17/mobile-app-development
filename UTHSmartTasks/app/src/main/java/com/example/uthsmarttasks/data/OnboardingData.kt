package com.example.uthsmarttasks.data

import com.example.uthsmarttasks.R
import com.example.uthsmarttasks.model.OnboardingItem

object OnboardingData {
    val items = listOf(
        OnboardingItem(
            title = "Easy Time Management",
            description = "Manage your daily tasks efficiently and easily.",
            imageRes = R.drawable.get_started_1
        ),
        OnboardingItem(
            title = "Increase Work Effectiveness",
            description = "Improve productivity and track progress better.",
            imageRes = R.drawable.get_started_2
        ),
        OnboardingItem(
            title = "Reminder Notification",
            description = "Never forget important tasks with smart reminders.",
            imageRes = R.drawable.finish
        )
    )
}