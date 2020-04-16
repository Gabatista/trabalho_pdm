package com.cursoandroid.trabalho.viewmodel

import android.app.Application
import android.database.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cursoandroid.trabalho.domain.Foto
import com.cursoandroid.trabalho.interactor.FotoInteractor
import com.cursoandroid.trabalho.repository.FotoRepository

class FotoViewModel (val app: Application): AndroidViewModel(app){
    private val interactor = FotoInteractor(app.applicationContext)


    //val result = MutableLiveData<Array<Foto>>()
    val result = MutableLiveData<Array<Foto>>()

   fun getFoto(){
        interactor.getFoto { fotos ->
            val fotosImagem = mutableListOf<Foto>()

            fotos.forEach { x ->
                val novaFoto = Foto(
                    date = x.date,
                    title = x.title,
                    explanation = x.explanation,
                    hdurl = x.hdurl
                )
                fotosImagem.add(novaFoto)
            }

            result.value = fotosImagem.toTypedArray()
        }

   }

}