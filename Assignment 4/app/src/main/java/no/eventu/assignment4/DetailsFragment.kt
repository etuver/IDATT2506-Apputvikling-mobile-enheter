package no.eventu.assignment4

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class DetailsFragment : Fragment() {
    private var moviesList: Array<String> = arrayOf()
    private var infoList: Array<String> = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moviesList = resources.getStringArray(R.array.movie_titles)
        infoList = resources.getStringArray(R.array.info_string_array)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        moviesList = resources.getStringArray(R.array.movie_titles)
        infoList = resources.getStringArray(R.array.info_string_array)
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    fun fillDetails(index: Int){
        val images = resources.obtainTypedArray(R.array.images)
        val image = images.getDrawable(index)
        requireView().findViewById<TextView>(R.id.details_detailstTextView).text = infoList[index]
        requireView().findViewById<ImageView>(R.id.detailsImageView).setImageDrawable(image)
        images.recycle()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fillDetails(0)
        super.onViewCreated(view, savedInstanceState)
    }


}