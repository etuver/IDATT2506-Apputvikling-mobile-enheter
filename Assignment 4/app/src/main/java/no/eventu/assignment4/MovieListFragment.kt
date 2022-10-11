package no.eventu.assignment4

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.ListFragment


class MovieListFragment : ListFragment() {

    private var moviesList: Array<String> = arrayOf()
    private var infoList: Array<String> = arrayOf()

    private var mListener: OnMoviesListItemClickListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesList = resources.getStringArray(R.array.movie_titles)
        infoList = resources.getStringArray(R.array.info_string_array)
        listAdapter = activity?.let {
            ArrayAdapter(it, android.R.layout.simple_list_item_1, android.R.id.text1, moviesList)
        }
    }


    interface OnMoviesListItemClickListener {
        fun onMovieListClick(index: Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = try {
            activity as OnMoviesListItemClickListener
        } catch (e: java.lang.ClassCastException){
            throw java.lang.ClassCastException(
                "$activity must implement OnFragmentInteractionListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        mListener!!.onMovieListClick(position)
    }

}