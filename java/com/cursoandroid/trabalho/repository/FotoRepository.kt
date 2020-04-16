package com.cursoandroid.trabalho.repository

import android.content.Context
import com.cursoandroid.trabalho.domain.Foto

import com.cursoandroid.trabalho.repository.dto.FotoDTO
import com.google.android.gms.common.api.internal.ApiKey
import retrofit2.Call
import retrofit2.Callback

import retrofit2.http.GET
import retrofit2.http.Query

import retrofit2.Response
import retrofit2.create
import retrofit2.http.Url
import java.util.ArrayList

const val API_KEY = "gHnKKL3j0qZwm2OSeNHVrpxu11RHXsDduC9gldpK"

interface FotoApi {

    @GET("planetary/apod/")
    fun getFotoDoDia(
        @Query("api_key") apiKey: String = "gHnKKL3j0qZwm2OSeNHVrpxu11RHXsDduC9gldpK"
        ):  Call<FotoDTO>

 /*   @GET("country")
    fun detalhePa
        @Query("paisId") paisId: Int
    ): retrofit2.Call<ReceitaDTO>
*/

}

class FotoRepository(context: Context, baseUrl: String) : RetrofitConfig(context, baseUrl ){

    private val busca = retrofit.create(FotoApi::class.java)

    fun getFotoDia(callback: (fotos: Array<Foto>) -> Unit){
        busca.getFotoDoDia().enqueue(object : Callback<FotoDTO> {

            override fun onResponse(call: Call<FotoDTO>, response: Response<FotoDTO>) {

                val fotos = response.body()?.results
                val result = mutableListOf<Foto>()

                fotos?.forEach { x ->
                    val dominio = Foto(
                        date = x.date,
                        title = x.title,
                        explanation = x.explanation,
                        hdurl = x.hdurl
                    )
                    result.add(dominio)
                }
                callback(result.toTypedArray())
            }

            override fun onFailure(call: Call<FotoDTO>, t: Throwable) {
                callback(arrayOf())
            }
        })
    }
}