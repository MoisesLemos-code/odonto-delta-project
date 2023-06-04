import accounting from 'accounting'

export default (valor, casasDecimais) => {
    if (!valor) {
        return 'R$0,00'
    }
    if (!isNaN(valor)) {
        const casasDecimaisQtd = casasDecimais ? casasDecimais : 2
        return 'R$ ' + accounting.formatNumber(accounting.unformat(valor, ','), casasDecimaisQtd, '.', ',')
    }
    return valor
}

