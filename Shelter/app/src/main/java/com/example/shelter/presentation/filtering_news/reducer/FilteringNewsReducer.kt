package com.example.shelter.presentation.filtering_news.reducer

import com.example.shelter.data.animal_type.repository.IAnimalTypeRepository
import com.example.shelter.data.category.repository.ICategoryRepository
import com.example.shelter.presentation.filtering_news.model.FilteringNewsException
import com.example.shelter.presentation.filtering_news.model.FilteringNewsState
import com.example.shelter.presentation.model.AnimalType
import com.example.shelter.presentation.model.Category
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class FilteringNewsReducer @Inject constructor(
    var categoryRepository: ICategoryRepository,
    var animalTypeRepository: IAnimalTypeRepository
): IFilteringNewsReducer {

    override val updateState: PublishSubject<FilteringNewsState> = PublishSubject.create()
    override val updateException: PublishSubject<FilteringNewsException> = PublishSubject.create()
    override val updateCategories: PublishSubject<List<Category>> = PublishSubject.create()
    override val updateAnimalTypes: PublishSubject<List<AnimalType>> = PublishSubject.create()
    override val applyFilters: PublishSubject<Unit> = PublishSubject.create()

    private val dispose = CompositeDisposable()
    var state = FilteringNewsState()
    private val categories = mutableListOf<Category>()
    private val animalTypes = mutableListOf<AnimalType>()

    override fun downloadInfo() {
        updateState.onNext(state)

        dispose.addAll(
            categoryRepository.getCategoryList()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    it.forEach { category ->
                        categories.add(category)
                    }
                    state = state.copy(
                        progressBarVisibility = false,
                        categoriesVisibility = true,
                        applyVisibility = true
                    )
                    updateState.onNext(state)
                }, {
                    updateException.onNext(FilteringNewsException())
                }),
            animalTypeRepository.getListAnimalType()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    it.forEach { type ->
                        animalTypes.add(type)
                    }
                    state = state.copy(
                        progressBarVisibility = false,
                        animalTypesVisibility = true,
                        applyVisibility = true
                    )
                    updateState.onNext(state)
                }, {
                    updateException.onNext(FilteringNewsException())
                })
        )
    }

    override fun showCategories() = updateCategories.onNext(categories)

    override fun showAnimalTypes() = updateAnimalTypes.onNext(animalTypes)

    override fun applyFilters() = applyFilters.onNext(Unit)

    override fun clearDispose() = dispose.clear()
}