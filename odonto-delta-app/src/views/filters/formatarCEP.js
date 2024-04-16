export default (value) => {
    if (!value) {
        return '----'
    }

    return value.replace(/(\d{2})(\d{3})(\d{3})/g, '$1.$2-$3')
}

