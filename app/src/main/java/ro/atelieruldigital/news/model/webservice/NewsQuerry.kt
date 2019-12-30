package ro.atelieruldigital.news.model.webservice

open class NewsQuerry

class TopHeadlinesQuerry(val country: String, val category: String, val sources: String,
                   val q: String, val pageSize: Int, var page: Int) : NewsQuerry()

class EverythingQuerry(val q: String, val qInTitle: String, val sources: String, val domains: String,
                       val excludeDomains: String, val from: String, val to: String,
                       val language: String, val sortBy: String, val pageSize: Int,
                       var page: Int) : NewsQuerry()