package com.example.shelter.presentation.creating_news.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.shelter.R
import com.example.shelter.app.ShelterManagerApp
import com.example.shelter.data.di.DaggerAnimalTypeRepositoryComponent
import com.example.shelter.data.di.DaggerCategoryRepositoryComponent
import com.example.shelter.data.di.DaggerNewsRepositoryComponent
import com.example.shelter.presentation.about_animal.model.Sex
import com.example.shelter.presentation.creating_news.di.DaggerCreatingNewsComponent
import com.example.shelter.presentation.creating_news.presenter.CreatingNewsPresenter
import com.example.shelter.presentation.fragment_menu.news.view.NewsFragment
import com.example.shelter.presentation.model.Animal
import com.example.shelter.presentation.model.AnimalType
import com.example.shelter.presentation.model.Category
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_creating_news.*
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.URI
import javax.inject.Inject


class CreatingNewsActivity: AppCompatActivity(), CreatingNewsView {
    private val resLayout = R.layout.activity_creating_news

    @Inject
    lateinit var presenter: CreatingNewsPresenter

    override val tryCreateNews: PublishSubject<Animal> = PublishSubject.create()
    override val downloadParameters: PublishSubject<Unit> = PublishSubject.create()

    private var checkedAnimalType: AnimalType? = null
    private var checkedCategory: Category? = null
    private var checkedGender = Sex.NONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(resLayout)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        initComponent()
        presenter.attachView(this)
        downloadParameters.onNext(Unit)

        findViewById<Button>(R.id.createNews).setOnClickListener {
            tryCreateNews.onNext(getAnimal())
        }

        chosePhote.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
        }
    }

    val PICK_IMAGE = 1
    var path: String? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE) {

            val inputStream: InputStream? =
                data?.data?.let { contentResolver.openInputStream(it) }
            val filePath: URI? = null
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, data!!.data)

            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
            val b: ByteArray = baos.toByteArray()
            val imageEncoded: String = Base64.encodeToString(b, Base64.DEFAULT)
            val leng = imageEncoded.length
//            imageView2.setImageBitmap(imageEncoded)
            decodeBase64(imageEncoded)
        }
    }

    fun decodeBase64(input: String) {
        val decodedByte = Base64.decode(input, 0)
        val bitmap = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)
//        imageView2.setImageBitmap(bitmap)
    }



    override fun initComponent() {
        val appComponent = (application as ShelterManagerApp).getAppComponent()
        val newsComponent = DaggerNewsRepositoryComponent.builder().build()
        val categoryRepository = DaggerCategoryRepositoryComponent.builder().build()
        val animalTypeRepository = DaggerAnimalTypeRepositoryComponent.builder().build()

        DaggerCreatingNewsComponent.builder()
            .appComponent(appComponent)
            .newsRepositoryComponent(newsComponent)
            .categoryRepositoryComponent(categoryRepository)
            .animalTypeRepositoryComponent(animalTypeRepository)
            .build()
            .inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_back, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showCategoriesDialog(list: List<Category>) {
        var array = emptyArray<String>()
        list.forEach { category ->
            array += category.title
        }
        var id = -1
        val builder = createAlertDialog(
            resources.getString(R.string.choseCategory)
        )

        builder.setPositiveButton(
            resources.getString(R.string.complete)
        ){ _, _ ->
            if (id < 0) return@setPositiveButton
            checkedCategory = list[id]
            findViewById<EditText>(R.id.category).setText(
                checkedCategory?.title ?: getString(R.string.choseCategory)
            )
        }
            .setSingleChoiceItems(array, -1) { _, index ->
                id = index
            }

        builder.show()
    }

    override fun showAnimalTypesDialog(list: List<AnimalType>) {
        var array = emptyArray<String>()
        list.forEach { type ->
            array += type.title
        }
        var id = -1
        val builder = createAlertDialog(
            resources.getString(R.string.choseAnimalType)
        )

        builder.setPositiveButton(
            resources.getString(R.string.complete)
        ){ _, _ ->
            if (id < 0) return@setPositiveButton
            checkedAnimalType = list[id]
            findViewById<EditText>(R.id.animalType).setText(
                checkedAnimalType?.title ?: getString(R.string.choseAnimalType)
            )
        }
            .setSingleChoiceItems(array, -1) { _, index ->
                id = index
            }

        builder.show()
    }

    override fun showGenderDialog() {
        val array = resources.getStringArray(R.array.gender)
        var gender = getString(R.string.none)

        val builder = createAlertDialog(
            resources.getString(R.string.choseGender)
        )

        builder.setPositiveButton(
            resources.getString(R.string.complete)
        ){ _, _ ->
            findViewById<EditText>(R.id.gender).setText(gender)
            checkedGender = when (gender) {
                getString(R.string.m) -> Sex.M
                getString(R.string.w) -> Sex.F
                else -> Sex.NONE
            }
        }
            .setSingleChoiceItems(array, -1) { _, index ->
                gender = array[index]
            }

        builder.show()
    }

    override fun closeWindow() {
        setResult(NewsFragment.RESULT_CODE_CREATING, Intent())
        finish()
    }

    private fun createAlertDialog(
        title: String
    ): AlertDialog.Builder =
         AlertDialog.Builder(this)
             .setTitle(title)
            .setCancelable(true)
            .setNegativeButton(
                resources.getString(R.string.cancel)
            ){ dialog, _ -> dialog.cancel() }

    override fun clickCategory(): Observable<Any> = RxView.clicks(category)

    override fun clickAnimalType(): Observable<Any> = RxView.clicks(animalType)

    override fun clickGender(): Observable<Any> = RxView.clicks(gender)

    override fun showException(isVisible: Boolean) {
        findViewById<TextView>(R.id.exception).visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun showProgressBar(isVisible: Boolean) {
        findViewById<ProgressBar>(R.id.progressBar).visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun showAnimalForm(isVisible: Boolean) {
        findViewById<ConstraintLayout>(R.id.animalForm).visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun showAddCard(isVisible: Boolean) {
        findViewById<Button>(R.id.createNews).visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }


    private fun getAnimal(): Animal {
        return Animal(
            findViewById<TextView>(R.id.name).text.toString(),
            "",
            checkedAnimalType,
            checkedGender,
            checkedCategory,
            getText(findViewById<TextView>(R.id.breed).text.toString()),
            getText(findViewById<TextView>(R.id.age).text.toString()),
            getText(findViewById<TextView>(R.id.passport).text.toString()),
            getText(findViewById<TextView>(R.id.description).text.toString())
        )
    }

    private fun getText(text: String): String? = if (text == "") {
            null
        } else {
            text
        }

}