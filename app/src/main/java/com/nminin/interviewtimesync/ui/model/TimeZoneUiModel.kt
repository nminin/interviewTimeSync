package com.nminin.interviewtimesync.ui.model

import com.nminin.interviewtimesync.data.model.TimeZone

internal data class TimeZoneUiModel(
    val isSelected: Boolean,
    val timezone: TimeZone,
)