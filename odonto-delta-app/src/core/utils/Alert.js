import {mutationTypes} from '@/core/constants'

export default class Alert {

    constructor(store) {
        this.store = store
    }

    showError(mensagem) {
        if (!mensagem) {
            mensagem = 'Não foi possível realizar a operação!'
        }
        this.store.commit(mutationTypes.COMUM.SET_NOTIFICACAO, {
            mensagem,
            cor: 'error',
            mostrar: true
        })
    }

    showAlert(mensagem) {
        if (!mensagem) {
            mensagem = 'Oops, há algo errado!'
        }
        this.store.commit(mutationTypes.COMUM.SET_NOTIFICACAO, {
            mensagem,
            cor: 'warning',
            mostrar: true
        })
    }

    showInfo(mensagem) {
        if (!mensagem) {
            mensagem = 'Operação realizada com sucesso!'
        }
        this.store.commit(mutationTypes.COMUM.SET_NOTIFICACAO, {
            mensagem,
            cor: 'success',
            mostrar: true
        })
    }
}
