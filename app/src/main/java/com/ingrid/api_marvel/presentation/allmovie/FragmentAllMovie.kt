package com.ingrid.api_marvel.presentation.allmovie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ingrid.api_marvel.R
import com.ingrid.api_marvel.SecondMovieDescription
import com.ingrid.api_marvel.data.repository.MoviesRepositoryImpl
import com.ingrid.api_marvel.domain.model.MovieResult
import com.ingrid.api_marvel.presentation.adapter.MovieAdapter
import com.ingrid.projetointegrador_vic.utils.setVisible

class FragmentAllMovie : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var loading: ProgressBar

    private val viewModel: MovieViewModel =
            MovieViewModel.ViewModelFactory(MoviesRepositoryImpl()).create(MovieViewModel::class.java)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(
                R.layout.fragment_all_movie,
                container,
                false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rvMovie)
        loading = view.findViewById(R.id.loading)

        setupRecyclerView()

        setObservers()
        viewModel.getMovies()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
        )

    }

    private fun setObservers() {

        viewModel.movieList.observe(viewLifecycleOwner, Observer { movieListResponse ->
            recyclerView.adapter = movieListResponse?.let { list ->
                MovieAdapter(list, clickListener = {
                    handleClick(it)
                })

            }

            viewModel.errorLiveData.observe(viewLifecycleOwner, Observer { message ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            })

            viewModel.loadingEvent.observe(viewLifecycleOwner, Observer { isVisible ->
                loading.setVisible(false)
            })
        })
    }

    private fun handleClick(movie: MovieResult) {
        val intent = Intent(context, SecondMovieDescription::class.java)
        intent.putExtra("title", movie.title)
        intent.putExtra("poster", movie.poster)
        intent.putExtra("actor", movie.actors)
        intent.putExtra("plot", movie.plot)
        intent.putExtra("genre", movie.genre)
        intent.putExtra("imgMovie", movie.poster)
        startActivity(intent)

    }
}