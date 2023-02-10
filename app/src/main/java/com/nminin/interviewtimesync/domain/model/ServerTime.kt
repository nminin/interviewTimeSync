package com.nminin.interviewtimesync.domain.model

import com.nminin.interviewtimesync.data.model.TimeZone
import java.util.*

internal data class ServerTime(
    val timeZone: TimeZone,
    val date: Date,
)