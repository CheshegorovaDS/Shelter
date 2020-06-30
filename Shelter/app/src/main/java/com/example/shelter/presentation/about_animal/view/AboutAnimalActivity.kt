package com.example.shelter.presentation.about_animal.view


import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.shelter.R
import com.example.shelter.presentation.about_animal.model.Animal
import com.example.shelter.presentation.about_animal.model.Sex
import com.example.shelter.presentation.about_animal.model.Sterilization
import com.example.shelter.presentation.about_animal.presenter.AboutAnimalPresenter
import com.example.shelter.presentation.about_animal.presenter.IAboutAnimalPresenter
import com.example.shelter.presentation.extention.toast
import kotlinx.android.synthetic.main.activity_animal_card.*


class AboutAnimalActivity: AppCompatActivity() , AboutAnimalView{
    lateinit var presenter: IAboutAnimalPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_card)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        initView()

        var animal: Animal = Animal("Bacя", "https://avatars.mds.yandex.net/get-pdb/1058492/87a96367-4ac0-42c2-9ca3-c4de8e9077ff/s1200?webp=false", "шотландская вислоухая",
            "Кот", Sex.M, "шотландская вислоухая","1","",Sterilization.NO,
            "", "Любит спать, ласковый, постоянно мурчит, любит гонять собак. Есть только перепелинные яйца.")

        presenter.showAnimal(animal)

        cardUser.setOnClickListener {
            presenter.openUser()
        }

    }

    private fun initView(){
        presenter = AboutAnimalPresenter(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_back, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showAnimalInfo() {

    }

    override fun showPhotoUser(visibility: Boolean) {
        if(visibility){
            Glide.with(imgAvatarUser.context)
                .load(Uri.parse("avatarUser")).into(imgAvatarUser)
        }
    }

    override fun showBreed(visibility: Boolean) {
        if(visibility){
            layoutBreed.visibility = View.VISIBLE
        }else{
            layoutBreed.visibility = View.GONE
        }
    }

    override fun showAge(visibility: Boolean) {
        if(visibility){
            layoutAge.visibility = View.VISIBLE
        }else{
            layoutAge.visibility = View.GONE
        }
    }

    override fun showPassport(visibility: Boolean) {
        if(visibility){
            layoutPassport.visibility = View.VISIBLE
        }else{
            layoutPassport.visibility = View.GONE
        }
    }

    override fun showSterilization(visibility: Boolean) {
        if(visibility){
            layoutSterilization.visibility = View.VISIBLE
        }else{
            layoutSterilization.visibility = View.GONE
        }
    }

    override fun showGrowth(visibility: Boolean) {
        if(visibility){
            layoutGrowth.visibility = View.VISIBLE
        }else{
            layoutGrowth.visibility = View.GONE
        }
    }

    override fun showDescription(visibility: Boolean) {
        if(visibility){
            layoutDescription.visibility = View.VISIBLE
        }else{
            layoutDescription.visibility = View.GONE
        }
    }

    override fun showPhoto(visibility: Boolean) {
        if(visibility){
            imgAnimal.visibility = View.VISIBLE
            Glide.with(imgAnimal.context)
                .load(Uri.parse("https://avatars.mds.yandex.net/get-pdb/1058492/87a96367-4ac0-42c2-9ca3-c4de8e9077ff/s1200?webp=false")).into(imgAnimal)
        }
    }

    override fun openUserHomepage() {
        toast("open user homepage")
    }
}