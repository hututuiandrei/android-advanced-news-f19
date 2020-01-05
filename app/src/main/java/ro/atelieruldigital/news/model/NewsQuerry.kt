package ro.atelieruldigital.news.model

open abstract class NewsQuerry {

    abstract fun setNewPage(newPage: Int)
    abstract fun setNewQ(newQ: String)
    open fun setNewCountry(newCountry: String) {}
    open fun setNewCategory(newCategory: String) {}
    abstract fun setNewSources(newSources: String)
}

class TopHeadlinesQuerry(var country: String, var category: String, var sources: String,
                   var q: String, val pageSize: Int, var page: Int) : NewsQuerry() {
    override fun setNewCountry(newCountry: String) {
        this.country = newCountry
    }

    override fun setNewCategory(newCategory: String) {
        this.category = newCategory
    }

    override fun setNewSources(newSources: String) {
        this.sources = newSources
    }

    override fun setNewQ(newQ: String) {

        this.q = newQ
    }

    override fun setNewPage(newPage: Int) {

        this.page = newPage
    }
}

class EverythingQuerry(var q: String, val qInTitle: String, var sources: String, val domains: String,
                       val excludeDomains: String, val from: String, val to: String,
                       val language: String, val sortBy: String, val pageSize: Int,
                       var page: Int) : NewsQuerry() {

    override fun setNewSources(newSources: String) {
        this.sources = newSources
    }

    override fun setNewQ(newQ: String) {

        this.q = newQ
    }

    override fun setNewPage(newPage: Int) {

        this.page = newPage
    }
}