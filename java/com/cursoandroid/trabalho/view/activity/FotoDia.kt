package com.cursoandroid.trabalho.view.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.ktx.R
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursoandroid.trabalho.view.adapter.FotoAdapter
import com.cursoandroid.trabalho.viewmodel.FotoViewModel
import kotlinx.android.synthetic.main.foto_item.*
import kotlinx.android.synthetic.main.foto_item_lista.*

class FotoDia : AppCompatActivity() {

    private val viewModel: FotoViewModel by lazy {
        ViewModelProvider(this). get(FotoViewModel::class.java)

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.cursoandroid.trabalho.R.layout.foto_item)
        configureRecyclerView()
        showFoto()

    }

    private fun configureRecyclerView(){
        rv_foto.layoutManager = LinearLayoutManager(this)
    }

    private fun showFoto(){
        viewModel.result.observe(this, Observer { fotos ->
            val adapter = FotoAdapter(arrayListOf())
            rv_foto.adapter = adapter
        })
        viewModel.getFoto()
    }

}