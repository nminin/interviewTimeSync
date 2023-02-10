package com.nminin.interviewtimesync.domain.interactor

import com.nminin.interviewtimesync.data.model.TimeZone

interface SetDefaultTimeZoneInteractor {
    suspend fun invoke(timeZone: TimeZone)
}