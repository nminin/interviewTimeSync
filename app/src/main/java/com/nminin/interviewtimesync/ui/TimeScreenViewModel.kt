package com.nminin.interviewtimesync.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nminin.interviewtimesync.data.model.TimeZone
import com.nminin.interviewtimesync.domain.interactor.*
import com.nminin.interviewtimesync.domain.interactor.GetDefaultTimeZoneInteractor
import com.nminin.interviewtimesync.domain.interactor.GetServerTimeInteractor
import com.nminin.interviewtimesync.domain.interactor.SyncTimeInteractor
import com.nminin.interviewtimesync.domain.model.ServerTime
import com.nminin.interviewtimesync.ui.converter.TimeZoneListUiConverter
import com.nminin.interviewtimesync.ui.model.TimeZoneUiModel
import com.nminin.interviewtimesync.ui.util.TimeTicker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.*

internal class TimeScreenViewModel(
    private val serverTimeInteractor: GetServerTimeInteractor,
    private val syncTimeInteractor: SyncTimeInteractor,
    private val getDefaultTimeZoneInteractor: GetDefaultTimeZoneInteractor,
    private val setDefaultTimeZoneInteractor: SetDefaultTimeZoneInteractor,
    private val timeZoneListInteractor: GetTimezoneListInteractor,
    private val timeZoneListUiConverter: TimeZoneListUiConverter,
    private val timeTicker: TimeTicker,
) : ViewModel() {

    private val timeZoneList = MutableStateFlow<List<TimeZoneUiModel>>(emptyList())
    private val selectedZoneTime = MutableStateFlow<ServerTime?>(null)
    private val selectedTimeZone = MutableStateFlow<TimeZone?>(null)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val defaultTimeZone = getDefaultTimeZoneInteractor.invoke()
            initTimeZoneList(defaultTimeZone)
            initSelectedTime(defaultTimeZone)
        }
    }

    fun getSelectedTime(): Flow<Date> = selectedZoneTime
        .flatMapLatest { serverTime ->
            flow {
                if (currentCoroutineContext().isActive) {
                    serverTime?.let {
                        this.emit(syncTimeInteractor.invoke(it))
                    }
                }
            }
                .flowOn(Dispatchers.Default)
        }

    fun getTimeZoneList(): Flow<List<TimeZoneUiModel>> = timeZoneList

    fun selectTimeZone(timeZone: TimeZone) {
        viewModelScope.launch(Dispatchers.IO) {
            setDefaultTimeZoneInteractor.invoke(timeZone)
            initSelectedTime(timeZone)
            initTimeZoneList(timeZone)
        }
    }

    private suspend fun initSelectedTime(timeZone: TimeZone) {
        selectedZoneTime.emit(serverTimeInteractor.invoke(timeZone))
    }

    private suspend fun initTimeZoneList(timeZone: TimeZone) {
        timeZoneList.emit(
            timeZoneListUiConverter.invoke(
                timeZone,
                timeZoneListInteractor.invoke()
            )
        )
    }
}