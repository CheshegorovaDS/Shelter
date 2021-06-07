package com.example.shelter.presentation.creating_news.reducer

import com.example.shelter.data.animal_type.repository.IAnimalTypeRepository
import com.example.shelter.data.category.repository.ICategoryRepository
import com.example.shelter.data.news.repository.INewsRepository
import com.example.shelter.presentation.creating_news.model.CreatingNewsException
import com.example.shelter.presentation.creating_news.model.CreatingNewsState
import com.example.shelter.presentation.model.Animal
import com.example.shelter.presentation.model.AnimalType
import com.example.shelter.presentation.model.Category
import com.example.shelter.presentation.model.News
import com.example.shelter.presentation.storage.LoggedUserProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.regex.Pattern
import javax.inject.Inject

class CreatingNewsReducer @Inject constructor(
    var categoryRepository: ICategoryRepository,
    var animalTypeRepository: IAnimalTypeRepository,
    var newsRepository: INewsRepository,
    var loggedUserProvider: LoggedUserProvider
) : ICreatingNewsReducer {

    private val dispose = CompositeDisposable()
    override val updateState: PublishSubject<CreatingNewsState> = PublishSubject.create()
    override val updateDestination: PublishSubject<Unit> = PublishSubject.create()
    override val updateCategory: PublishSubject<List<Category>> = PublishSubject.create()
    override val updateAnimalType: PublishSubject<List<AnimalType>> = PublishSubject.create()
    override val updateException: PublishSubject<CreatingNewsException> = PublishSubject.create()
    override val updateGender: PublishSubject<Unit> = PublishSubject.create()


    private val categories = mutableListOf<Category>()
    private val animalTypes = mutableListOf<AnimalType>()
    private var news: News? = null
    private var state = CreatingNewsState()

    override fun download() {
        state = CreatingNewsState()
        updateState.onNext(state)

        dispose.addAll(
            categoryRepository.getCategoryList()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    it.forEach { category ->
                        categories.add(category)
                    }
                    if (animalTypes.isNotEmpty()) stateAfterDownload()
                }, {
                    updateException.onNext(CreatingNewsException())
                }),
            animalTypeRepository.getListAnimalType()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    it.forEach { type ->
                        animalTypes.add(type)
                    }
                    if ( categories.isNotEmpty() ) stateAfterDownload()
                }, {
                    updateException.onNext(CreatingNewsException())
                })
        )
    }

    override fun tryAddNews(animal: Animal) {
        if (!checkNews(animal) || loggedUserProvider.getId() == null) {
            updateException.onNext(CreatingNewsException())
            return
        }

        news = News(
            id = 0,
            name = animal.name,
            photo = animal.photo,
            idCategory = animal.category!!.id,
            category = animal.category.title,
            idUser = loggedUserProvider.getId()!!,
            idAnimalType = animal.type?.id,
            age = animal.age?.toInt(),
            sex = animal.sex.name,
            breed = animal.breed,
            passport = animal.passport,
            description = animal.description
        )

        dispose.add(
            newsRepository.addNews(news!!)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    updateDestination.onNext(Unit)
                }, {
                    updateException.onNext(CreatingNewsException())
                })
        )


    }

    override fun showCategories() = updateCategory.onNext(categories.toList())

    override fun showAnimalTypes() = updateAnimalType.onNext(animalTypes.toList())

    override fun showGender() = updateGender.onNext(Unit)

    private fun stateAfterDownload() {
        state = state.copy(
            animalFormVisibility = true,
            addCardVisibility = true,
            progressBarVisibility = false
        )
        updateState.onNext(state)
    }

    private fun checkNews(animal: Animal) :Boolean {
        val patternText = Pattern.compile(PATTERN_TEXT)

        if (!patternText.matcher(animal.name).find()) {
            return false
        }

        if (animal.type == null) {
            return false
        }

        if (animal.category == null) {
            return false
        }

//        if (!patternText.matcher(animal.photo).find()) {
//            return false
//        }

        if (checkField(animal.age, PATTERN_INT)) {
            return false
        }

        if (checkField(animal.breed, PATTERN_TEXT)) {
            return false
        }

        if (checkField(animal.passport, PATTERN_TEXT)) {
            return false
        }

        if (checkField(animal.description, PATTERN_TEXT)) {
            return false
        }

        return true
    }

    private fun checkField(field: String?, regex: String): Boolean {
        val pattern = Pattern.compile(regex)
        return (field != null && !pattern.matcher(field).find())
    }

    override fun clearDispose() = dispose.clear()

    companion object {
        const val PATTERN_TEXT = "^[а-яА-Яa-zA-Z]+$"
        const val PATTERN_INT = "^[1-9]+[0-9]*$"
    }

}