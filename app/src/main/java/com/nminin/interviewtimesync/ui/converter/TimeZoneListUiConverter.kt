package com.nminin.interviewtimesync.ui.converter

import com.nminin.interviewtimesync.data.model.TimeZone
import com.nminin.interviewtimesync.ui.model.TimeZoneUiModel

internal interface TimeZoneListUiConverter {

    suspend fun invoke(
        selectedTimeZone: TimeZone,
        timeZoneList: List<TimeZone>
    ): List<TimeZoneUiModel>
}