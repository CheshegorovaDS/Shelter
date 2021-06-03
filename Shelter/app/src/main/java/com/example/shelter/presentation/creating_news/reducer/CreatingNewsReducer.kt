package com.example.shelter.presentation.creating_news.reducer

import com.example.shelter.data.animal_type.repository.IAnimalTypeRepository
import com.example.shelter.data.category.repository.ICategoryRepository
import com.example.shelter.presentation.creating_news.model.CreatingNewsInfo
import com.example.shelter.presentation.creating_news.model.CreatingNewsState
import com.example.shelter.presentation.filtering_news.model.FilteringNewsException
import com.example.shelter.presentation.model.Animal
import com.example.shelter.presentation.model.AnimalType
import com.example.shelter.presentation.model.Category
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class CreatingNewsReducer @Inject constructor(
    var categoryRepository: ICategoryRepository,
    var animalTypeRepository: IAnimalTypeRepository
) : ICreatingNewsReducer {

    private val dispose = CompositeDisposable()
    override val updateState: PublishSubject<CreatingNewsState> = PublishSubject.create()
    override val updateCategory: PublishSubject<List<Category>> = PublishSubject.create()
    override val updateAnimalType: PublishSubject<List<AnimalType>> = PublishSubject.create()
    override val updateException: PublishSubject<FilteringNewsException> = PublishSubject.create()
    override val updateGender: PublishSubject<Unit> = PublishSubject.create()

    private val categories = mutableListOf<Category>()
    private val animalTypes = mutableListOf<AnimalType>()

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
                    updateException.onNext(FilteringNewsException())
                }),
            animalTypeRepository.getListAnimalType()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    it.forEach { type ->
                        animalTypes.add(type)
                    }
                    if ( categories.isNotEmpty() ) stateAfterDownload()
                }, {
                    updateException.onNext(FilteringNewsException())
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

    override fun clearDispose() = dispose.clear()

}