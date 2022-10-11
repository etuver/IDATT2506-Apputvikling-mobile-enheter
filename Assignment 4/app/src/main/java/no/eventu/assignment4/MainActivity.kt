package no.eventu.assignment4

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast

class MainActivity : AppCompatActivity(), MovieListFragment.OnMoviesListItemClickListener {


    private var listSize = 0;
    private var selectedItem = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setConfig(resources.configuration)
        listSize = resources.getStringArray(R.array.movie_titles).size
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when( item.itemId){
            R.id.menu_next -> nextItem()
            R.id.menu_previous -> previousItem()
            else -> return false
        }
        return true
    }


    private fun nextItem(){
        if (selectedItem == listSize -1 ){
            selectedItem = 0;
        }
        else selectedItem ++;
        onMovieListClick(selectedItem)
    }


    private fun previousItem(){
        if (selectedItem == 0){
            selectedItem = listSize -1;
        } else selectedItem --;
        onMovieListClick(selectedItem)
    }

    override fun onMovieListClick(index: Int) {
        selectedItem = index
        val detailsFragment = supportFragmentManager.findFragmentById(R.id.detailsFragmentContainer) as DetailsFragment
        detailsFragment.fillDetails(index)
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setConfig(newConfig)
    }


    private fun setConfig(config: Configuration){
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE){
            //Toast.makeText(getApplicationContext(),"Landscap",Toast.LENGTH_SHORT).show();
            findViewById<LinearLayout>(R.id.mainLinearLayout).orientation = LinearLayout.HORIZONTAL
        } else{
            //Toast.makeText(getApplicationContext(),"not landscape",Toast.LENGTH_SHORT).show();
            findViewById<LinearLayout>(R.id.mainLinearLayout).orientation = LinearLayout.VERTICAL
        }
    }



}