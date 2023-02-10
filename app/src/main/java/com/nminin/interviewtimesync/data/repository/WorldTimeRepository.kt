package com.nminin.interviewtimesync.data.repository

import com.nminin.interviewtimesync.data.model.TimeZone
import com.nminin.interviewtimesync.data.model.TimeResponse

internal interface WorldTimeRepository {

    suspend fun getTime(timeRequest: TimeZone): TimeResponse
}