package com.nminin.interviewtimesync.domain.interactor

import com.nminin.interviewtimesync.data.model.TimeZone

interface GetTimezoneListInteractor {

    suspend fun invoke(): List<TimeZone>
}