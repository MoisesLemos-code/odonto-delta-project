class SearchUrlBuilder {
    build(urlPath = '', filters = {}, pagingAndSorting = {}) {
        const hasFilters = this._hasFilters(filters)
        const hasPagingAndSorting = this._hasPagingAndSorting(pagingAndSorting)
        if (hasFilters) {
            this._validateFilters(filters)
        }

        const paramFilters = this._buildFilters(filters)
        const paramPagingAndSorting = this._buildPagingAndSorting(pagingAndSorting, hasFilters)

        return `${urlPath}${hasFilters || hasPagingAndSorting ? '?' : ''}${paramFilters}${paramPagingAndSorting}`
    }

    buildAvanced(urlPath = '', filters = {}, pagingAndSorting = {}) {
        const hasFilters = this._hasFilters(filters)
        const hasPagingAndSorting = this._hasPagingAndSorting(pagingAndSorting)
        if (hasFilters) {
            this._validateFilters(filters)
        }

        const paramFilters = this._buildFilters(filters)
        const paramPagingAndSorting = this._buildPagingAndSorting(pagingAndSorting, hasFilters)

        return `${urlPath}${hasFilters || hasPagingAndSorting ? '?' : ''}${paramFilters}${paramPagingAndSorting}`
    }

    buildPagination(urlPath = '', pagingAndSorting = {}) {
        const hasPagingAndSorting = this._hasPagingAndSorting(pagingAndSorting)
        const paramPagingAndSorting = this._buildPagingAndSorting(pagingAndSorting)

        return `${urlPath}${hasPagingAndSorting ? '?' : ''}${paramPagingAndSorting}`
    }

    _validateFilters(filters) {
        const pagingAndSortingKeys = ['page', 'size', 'sort', 'direction']

        for (const key of pagingAndSortingKeys) {
            if (filters.hasOwnProperty.call(key, key)) {
                throw new TypeError(`O atributo "${key}" é utilizado como parâmetro da paginação.`)
            }
        }
    }

    _hasFilters(filters) {
        let hasValidFilters = false
        for (const [key] of Object.entries(filters)) {
            if (filters[key].value) {
                hasValidFilters = true
                break
            }
        }
        return hasValidFilters
    }

    _hasPagingAndSorting(pagingAndSorting) {
        return Object.keys(pagingAndSorting).length > 0
    }

    _buildFilters(filters) {
        const param = []

        for (const [key] of Object.entries(filters)) {
            if (filters[key].value) {

                let valorFiltro = filters[key].value
                let valorIdFiltro = filters[key].valueId

                if(valorFiltro === 'Começa com')
                    valorFiltro = 'COMECA_COM'
                if(valorFiltro === 'Contém')
                    valorFiltro = 'CONTEM'
                if(valorFiltro === 'Termina com')
                    valorFiltro = 'TERMINA_COM'
                if(valorIdFiltro != null)
                    valorFiltro = valorIdFiltro

                param.push(`${key}=${valorFiltro}`)
            }
        }
        return param.join('&')
    }

    _buildPagingAndSorting(pagingAndSorting, hasFilters) {
        let param = ''

        if (pagingAndSorting.page) {
            param += `&page=${pagingAndSorting.page}`
        }

        if (pagingAndSorting.size) {
            param += `&size=${pagingAndSorting.size}`
        }

        if (pagingAndSorting.sort) {
            param += `&sort=${pagingAndSorting.sort}`
            param += `&direction=${!pagingAndSorting.sortDesc[0] ? 'ASC' : 'DESC'}`
        }

        if (pagingAndSorting.empresaId) {
            param += `&empresaId=${pagingAndSorting.empresaId}`
        }

        if (!hasFilters) {
            param = param.length > 0 ? param.substr(1) : param
        }

        return param
    }
}

export default new SearchUrlBuilder()