import LocalStorageManager from '@/core/utils/LocalStorageManager'

function usuarioTemPermissao(permissaoUsuario, permissaoNecessaria) {
    let temPermissao = false
    for (let i = 0; i < permissaoUsuario.length && !temPermissao; i++) {
        const userPermission = permissaoUsuario[i]
        if (userPermission === permissaoNecessaria) {
            temPermissao = true
        }
    }
    return temPermissao
}

function precisaDePermissao(permissao) {
    return permissao.length > 0
}

function possuiAcesso(permissaoUsuario, permissao) {
    console.log('--- possuiAcesso')
    console.log(permissaoUsuario)
    console.log(permissao)
    if (!precisaDePermissao(permissao)) {
        return true
    }
    let temPermissao = false
    for (let i = 0; i < permissao.length && !temPermissao; i++) {
        const permissaoNecessaria = permissao[i]
        temPermissao = usuarioTemPermissao(permissaoUsuario, permissaoNecessaria)
    }
    return temPermissao
}

export default function(permissao) {
    const usuario = LocalStorageManager.getItemStorage()
    const permissaoUsuario = usuario.permissao
    return possuiAcesso(permissaoUsuario, permissao)
}