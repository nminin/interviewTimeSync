package com.nminin.interviewtimesync.domain.interactor

import com.nminin.interviewtimesync.data.model.TimeZone
import com.nminin.interviewtimesync.domain.model.ServerTime

internal interface GetServerTimeInteractor {

    suspend fun invoke(timeZone: TimeZone): ServerTime
}