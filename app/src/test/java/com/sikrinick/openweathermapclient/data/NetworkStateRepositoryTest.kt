package com.sikrinick.openweathermapclient.data

import com.sikrinick.openweathermapclient.data.platform.NetworkInfoSupplier
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NetworkStateRepositoryTest {

    lateinit var supplier: NetworkInfoSupplier
    lateinit var networkStateRepository: NetworkStateRepository


    @Before
    fun before() {
        supplier = mockk(relaxed = true)
        networkStateRepository = NetworkStateRepository(supplier)
    }

    @Test
    fun `test should pass`() {
        every { supplier.observeNetworkConnected() } returns Observable.just(true)

        networkStateRepository.observeNetworkConnected()
            .test()
            .assertSubscribed()
            .assertResult(true)
            .assertComplete()

    }

    @Test(expected = AssertionError::class) // remove expected for exception
    fun `test should pass with exception`() {
        every { supplier.observeNetworkConnected() } returns Observable.just(false)

        networkStateRepository.observeNetworkConnected()
            .test()
            .assertSubscribed()
            .assertResult(true)
            .assertComplete()

    }
}