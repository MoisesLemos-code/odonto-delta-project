export default (value) => {
    if (!value) {
        return '----'
    }
    if (value.length >= 11) {
        return value.replace(/(\d{2})(\d{5})(\d)/g, '($1) $2-$3')
    }

    return value.replace(/(\d{2})(\d{4})(\d{4})/g, '($1) $2-$3')
}

