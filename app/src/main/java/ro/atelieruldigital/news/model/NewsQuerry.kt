package ro.atelieruldigital.news.model

open abstract class NewsQuerry {

    abstract fun setNewPage(newPage: Int)
    abstract fun setNewQ(newQ: String?)
    open fun setNewCountry(newCountry: String?) {}
    open fun setNewCategory(newCategory: String?) {}
    abstract fun setNewSources(newSources: String?)
    open fun setNewLanguage(newLanguage: String?) {}
    open fun setNewSortBy(newSortBy: String?) {}
    open fun setNewQInTitle(newQInTitle: String?) {}
    open fun setNewDomains(newDomains: String?) {}
    open fun setNewExcludeDomains(newExcludeDomains: String?) {}
    open fun setNewFrom(newFrom: String?) {}
    open fun setNewTo(newTo: String?) {}
}

class TopHeadlinesQuerry(var country: String?, var category: String?, var sources: String?,
                   var q: String?, val pageSize: Int, var page: Int) : NewsQuerry() {
    override fun setNewCountry(newCountry: String?) {
        this.country = newCountry
    }

    override fun setNewCategory(newCategory: String?) {
        this.category = newCategory
    }

    override fun setNewSources(newSources: String?) {
        this.sources = newSources
    }

    override fun setNewQ(newQ: String?) {

        this.q = newQ
    }

    override fun setNewPage(newPage: Int) {

        this.page = newPage
    }
}

class EverythingQuerry(var q: String?, var qInTitle: String?, var sources: String?, var domains: String?,
                       var excludeDomains: String?, var from: String?, var to: String?,
                       var language: String?, var sortBy: String?, var pageSize: Int,
                       var page: Int) : NewsQuerry() {

    override fun setNewSources(newSources: String?) {
        this.sources = newSources
    }

    override fun setNewQ(newQ: String?) {

        this.q = newQ
    }

    override fun setNewPage(newPage: Int) {

        this.page = newPage
    }

    override fun setNewLanguage(newLanguage: String?) {

        this.language = newLanguage
    }

    override fun setNewSortBy(newSortBy: String?) {

        this.sortBy = newSortBy
    }

    override fun setNewQInTitle(newQInTitle: String?) {

        this.qInTitle = newQInTitle
    }

    override fun setNewDomains(newDomains: String?) {

        this.domains = newDomains
    }

    override fun setNewExcludeDomains(newExcludeDomains: String?) {

        this.excludeDomains = newExcludeDomains
    }

    override fun setNewFrom(newFrom: String?) {

        this.from = newFrom
    }

    override fun setNewTo(newTo: String?) {

        this.to = newTo
    }
}