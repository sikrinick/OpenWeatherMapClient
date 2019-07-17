package com.sikrinick.openweathermapclient.domain

import io.reactivex.Scheduler

class Schedulers(
    val ui: Scheduler,
    val io: Scheduler,
    val computation: Scheduler
)