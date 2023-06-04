class EnumConverter {
    getKeyFromValue(object, value) {
        return Object.keys(object).find(key => object[key] === value)
    }

    getAllKeysFromValues(object, values) {
        let keys = []
        values.forEach(value => keys.push(Object.keys(object).find(key => object[key] === value)))
        return keys
    }

    toLowerCase(list) {
        let lowerCaseList = []
        list.forEach(element => lowerCaseList.push(element.toLowerCase()))
        return lowerCaseList
    }

    toArray(object) {
        return Object.values(object)
    }

    removeFirstDot(list) {
        list.forEach((element, index) => {
            list[index] = element.replace(/^\./, '')
        })
        return list
    }

    insertFirstDot(list) {
        list.forEach((element, index) => {
            if (!element.match(/^\./)) {
                list[index] = `.${element}`
            }
        })
        return list
    }
}

export default new EnumConverter()
