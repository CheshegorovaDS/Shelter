package com.example.shelter.presentation.edit_user.view

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
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.shelter.R
import com.example.shelter.app.ShelterManagerApp
import com.example.shelter.data.di.DaggerUserRepositoryComponent
import com.example.shelter.presentation.edit_user.di.DaggerEditUserComponent
import com.example.shelter.presentation.edit_user.model.EditUserErrorCode
import com.example.shelter.presentation.edit_user.presenter.EditUserPresenter
import com.example.shelter.presentation.model.Human
import com.example.shelter.presentation.model.Organisation
import com.example.shelter.presentation.model.User
import com.example.shelter.presentation.onBoarding.registration.model.UserType
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_creating_news.*
import kotlinx.android.synthetic.main.activity_edit_user.*
import kotlinx.android.synthetic.main.activity_edit_user.chosePhote
import kotlinx.android.synthetic.main.activity_edit_user.toolbar
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.URI
import javax.inject.Inject

class EditUserActivity: AppCompatActivity(), EditUserView {
    private val resLayout = R.layout.activity_edit_user

    @Inject
    lateinit var presenter: EditUserPresenter

    override val downloadInfo: PublishSubject<Unit> = PublishSubject.create()
    override val editUser: PublishSubject<User> = PublishSubject.create()

    private var type: UserType? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(resLayout)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        initComponent()
        presenter.attachView(this)
        downloadInfo.onNext(Unit)

        findViewById<Button>(R.id.apply).setOnClickListener {
            editUser.onNext(getUser())
        }

        chosePhote.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
        }
    }

    val PICK_IMAGE = 1

    private var array: ByteArray? = null

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
            array = b
            val imageEncoded: String = Base64.encodeToString(b, Base64.DEFAULT)
            val leng = imageEncoded.length
//            imageView2.setImageBitmap(imageEncoded)
//            decodeBase64(imageEncoded)
            editUser.onNext(getUser())
        }
    }

    fun decodeBase64(input: String) {
        val decodedByte = Base64.decode(input, 0)
        val bitmap = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)
//        imageView2.setImageBitmap(bitmap)
    }

    override fun initComponent() {
        val appComponent = (application as ShelterManagerApp).getAppComponent()
        val userRepository = DaggerUserRepositoryComponent.builder().build()

        DaggerEditUserComponent.builder()
            .appComponent(appComponent)
            .userRepositoryComponent(userRepository)
            .build()
            .inject(this)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
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

    private fun getUser(): User {
        var human: Human? = null
        var organisation: Organisation? = null
        when (type) {

            UserType.HUMAN -> {
                human = Human(
                    firstName = firstName.text.toString(),
                    lastName = lastName.text.toString(),
                    patronymic = patronymic.text.toString(),
                    photo = array
                )
            }

            UserType.ORGANIZATION -> {
                organisation = Organisation(title = organisationName.text.toString())
            }
        }
        return User(
            id = 0,
            email = email.text.toString(),
            phone = phone.text.toString(),
            city = city.text.toString(),
            country = country.text.toString(),
            human = human,
            organisation = organisation
        )
    }

    override fun showProgressBar(visibility: Boolean) {
        findViewById<ProgressBar>(R.id.progressBar).visibility = if (visibility) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun showFields(typeUser: UserType) {
        this.type = typeUser
        when (typeUser) {
            UserType.HUMAN ->  {
                lastName.visibility = View.VISIBLE
                firstName.visibility = View.VISIBLE
                patronymic.visibility = View.VISIBLE
            }
            UserType.ORGANIZATION -> {
                organisationName.visibility = View.VISIBLE
            }
        }

        city.visibility = View.VISIBLE
        country.visibility = View.VISIBLE
        phone.visibility = View.VISIBLE
        email.visibility = View.VISIBLE
    }

    override fun showApply(visibility: Boolean) {
        findViewById<Button>(R.id.apply).visibility = if (visibility) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun enabledApply(isEnabled: Boolean) {
        findViewById<Button>(R.id.apply).isEnabled = isEnabled
    }

    override fun hideFields() {
        city.visibility = View.GONE
        country.visibility = View.GONE
        phone.visibility = View.GONE
        email.visibility = View.GONE
        lastName.visibility = View.GONE
        firstName.visibility = View.GONE
        patronymic.visibility = View.GONE
        organisationName.visibility = View.GONE
    }

    override fun showException(visibility: Boolean) {
        findViewById<TextView>(R.id.error).visibility = if (visibility) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun setHumanInfo(user: User) {
        setUserInfo(user)
        lastName.setText(user.human?.lastName)
        firstName.setText(user.human?.firstName)
        patronymic.setText(user.human?.patronymic ?: EMPTY_TEXT)
    }

    override fun setOrganisationInfo(user: User) {
        setUserInfo(user)
        organisationName.setText(user.organisation?.title)
    }

    private fun setUserInfo(user: User) {
        city.setText(user.city)
        country.setText(user.country)
        phone.setText(user.phone)
        email.setText(user.email)
    }

    override fun setException(code: EditUserErrorCode) {
        val text = when (code) {
            else -> R.id.error
        }
        findViewById<TextView>(R.id.error).setText(text)
    }

    override fun exit() {
        finish()
    }

    companion object {
        const val EMPTY_TEXT = ""
    }
}
