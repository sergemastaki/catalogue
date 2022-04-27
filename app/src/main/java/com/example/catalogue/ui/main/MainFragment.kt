package com.example.catalogue.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.catalogue.R
import com.example.catalogue.util.onTextChanged

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        val movieAdapter = MovieAdapter()
        recyclerView.adapter = movieAdapter
        viewModel.movies.observe(viewLifecycleOwner){
            movieAdapter.submitList(it.toList())
        }
        val searchView: EditText = view.findViewById(R.id.search_view)
        searchView.onTextChanged(300) { text ->
            viewModel.updateQuery(text)
        }
    }
}