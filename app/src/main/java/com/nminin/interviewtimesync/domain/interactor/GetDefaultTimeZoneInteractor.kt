package com.nminin.interviewtimesync.domain.interactor

import com.nminin.interviewtimesync.data.model.TimeZone

internal interface GetDefaultTimeZoneInteractor {

    fun invoke(): TimeZone
}