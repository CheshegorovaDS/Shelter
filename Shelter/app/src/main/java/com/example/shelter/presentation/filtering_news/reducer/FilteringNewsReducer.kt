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
    override val updateCategories: PublishSubject<List<Pair<Category, Boolean>>> = PublishSubject.create()
    override val updateAnimalTypes: PublishSubject<List<AnimalType>> = PublishSubject.create()
    override val updateCheckedCategories: PublishSubject<List<Category>> = PublishSubject.create()
    override val updateCheckedTypes: PublishSubject<List<AnimalType>> = PublishSubject.create()
    override val applyFilters: PublishSubject<Unit> = PublishSubject.create()

    private val dispose = CompositeDisposable()
    var state = FilteringNewsState()
    private val categories = mutableListOf<Category>()
    private var checkedCategories = booleanArrayOf()
    private val animalTypes = mutableListOf<AnimalType>()
    private var checkedTypes = booleanArrayOf()
    private val c = mutableMapOf<Category, Boolean>()

    override fun downloadInfo() {
        updateState.onNext(state)

        dispose.addAll(
            categoryRepository.getCategoryList()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    it.forEach { category ->
                        categories.add(category)
                        c[category] = false
                    }
//                    checkedCategories = BooleanArray(categories.size){false}
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
//                    checkedTypes = BooleanArray(animalTypes.size){false}

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

    override fun showCategories() = updateCategories.onNext(c.toList())

    override fun showAnimalTypes() = updateAnimalTypes.onNext(animalTypes)

    override fun applyFilters(
        checkedCategories: List<Category>,
        checkedTypes: List<AnimalType>
    ) {

    }

    override fun changeCheckedCategories(categories: List<Category>) {
        updateCheckedCategories.onNext(categories)
        state = state.copy(checkedCategoriesVisibility = categories.isNotEmpty())
        updateState.onNext(state)
    }

    override fun changeCheckedTypes(types: List<AnimalType>) {
        updateCheckedTypes.onNext(types)
        state = state.copy(checkedAnimalTypesVisibility = types.isNotEmpty())
        updateState.onNext(state)
    }

    override fun clearDispose() = dispose.clear()
}