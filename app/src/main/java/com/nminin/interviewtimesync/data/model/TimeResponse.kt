package com.nminin.interviewtimesync.data.model

internal data class TimeResponse(
    val abbreviation: String?,
    val clientIp: String?,
    val datetime: String?,
    val dayOfWeek: Int?,
    val dayOfYear: Int?,
    val rawOffset: Int,
    val timezone: String?,
    val unixtime: Long?,
    val utcDatetime: String?,
    val utcOffset: String?,
    val weekNumber: Int?,
)