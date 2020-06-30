package com.example.shelter.presentation.fragment_menu.news.model

import com.example.shelter.presentation.fragment_menu.news.adapter.NewsAdapter
import com.example.shelter.presentation.model.News

class NewsModel: INewsModel {
    var list: List<News>? = null

    fun loadNews(tableAdapter: NewsAdapter, countVisibleItem:Int, firstVisibleItem: Int, onSucess:(List<News>)->(Unit), onError:(Throwable)->(Unit)){
       /* NetworkService().getInstance()
            .getJSONApi()
            .getNewsWithID("q3F5HhydFZBae6Wft7JRmDbzFrJEa0TS135447&1561027082464", countVisibleItem, firstVisibleItem)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                    list =  response.body()!!.response
                    //  tableAdapter.list.addAll(it)
                    onSucess.invoke(list!!)

                    tableAdapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) { onError.invoke(t)}
            })*/
    }

    override fun loadNews(): List<News> {
        val tables = ArrayList<News>()
        tables.add(News("Вася", "М",  1))
        tables.add(News("Буся", "Ж",  10))
        tables.add(News("Meca","Ж",1))
        tables.add(News("Муся","Ж",1))
        tables.add(News("Симбад","М",1))
        tables.add(News("Миша","М",2))
        return tables
    }
}