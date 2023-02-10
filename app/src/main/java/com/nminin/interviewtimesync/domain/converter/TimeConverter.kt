package com.nminin.interviewtimesync.domain.converter

import com.nminin.interviewtimesync.data.model.TimeResponse
import com.nminin.interviewtimesync.domain.model.ServerTime

internal interface TimeConverter {
    fun invoke(timeResponse: TimeResponse): ServerTime
}