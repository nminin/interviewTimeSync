package com.nminin.interviewtimesync.domain.interactor

import com.nminin.interviewtimesync.domain.model.ServerTime
import java.util.*

internal interface SyncTimeInteractor {

    suspend fun invoke(serverTime: ServerTime): Date

}