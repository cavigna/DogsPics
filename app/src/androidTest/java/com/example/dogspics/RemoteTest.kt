package com.example.dogspics

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.dogspics.dao.PerroDao
import com.example.dogspics.db.BaseDeDatos
import com.example.dogspics.model.PerroRandom
import com.example.dogspics.network.DogApiService
import com.example.dogspics.repository.Repositorio
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@MediumTest
class RemoteTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockWebServer = MockWebServer()
    private val MOCK_WEBSERVER_PORT = 8000
    private lateinit var repositorioTest: Repositorio
    private lateinit var repositorio: Repositorio

    private lateinit var database: BaseDeDatos
    private lateinit var dao: PerroDao

    private val dispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(dispatcher)


    @Before
    fun setup() {
        mockWebServer.start(MOCK_WEBSERVER_PORT)
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, BaseDeDatos::class.java)
            .allowMainThreadQueries()
            .build()

        dao = database.dao()

        val retroClientTest by lazy {
            Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DogApiService::class.java)
        }


        repositorioTest = Repositorio(retroClientTest, dao)
        repositorio = Repositorio(retroClientTest, dao)


    }


    @After
    fun teardown() {
        mockWebServer.shutdown()
        database.close()
    }

    @Test
    fun perroTestRandom() {

        val perroOriginal =
            PerroRandom("https://images.dog.ceo/breeds/keeshond/n02112350_9580.jpg", "success")

        mockWebServer.apply {
            enqueue(
                MockResponse()
                    .setResponseCode(200)
                    .setBody(
                        FileReader.readStringFromFile("respuesta_falsa_perro_random.json")
                    )
            )

            testScope.launch {
                val perro = repositorioTest.perroRandomTest().body()


                assertThat(perro).isEqualTo(perroOriginal)


            }

        }
    }


    @Test
    fun listadoRazasTest(){


        mockWebServer.apply{
            enqueue(
                MockResponse()
                    .setBody(
                        FileReader.readStringFromFile("mock_listado.json")
                    )
            )

            testScope.launch {
                val listadoApi = repositorio.listadoRazaAPI().message
                val listadoMock = repositorioTest.listadoRazaAPI().message

                assertThat(listadoApi).isEqualTo(listadoMock)
            }
        }
    }

    @Test
    fun listadoImagenesRaza(){
        mockWebServer.apply {
            MockResponse()
                .setBody(
                    FileReader.readStringFromFile("mock_raza.json")
                )

           testScope.launch {
                val listadoApi = repositorio.imagenesPorRaza("labrador").imagenes
                val listadoMock = repositorioTest.imagenesPorRaza("hound").imagenes

                assertThat(listadoApi).isEqualTo(listadoMock)
            }


        }
    }

}


