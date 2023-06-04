import EnumConverter from './EnumConverter'

describe('EnumConverter', () => {
    it('getKeyFromValue', async () => {
        const objetoTeste = { chave: 'valor' }

        const resultado = EnumConverter.getKeyFromValue(objetoTeste, 'valor')
        expect(resultado).toEqual('chave')
    })

    it('getAllKeysFromValues', async () => {
        const objetoTeste = { chave: 'valor', chave2: 'valor2' }

        const resultado = EnumConverter.getAllKeysFromValues(objetoTeste, ['valor', 'valor2'])
        expect(resultado).toEqual(['chave', 'chave2'])
    })

    it('toLowerCase', async () => {
        const listaTeste = ['TESTE1', 'TESTE2']

        const resultado = EnumConverter.toLowerCase(listaTeste)
        expect(resultado).toEqual(['teste1', 'teste2'])
    })

    it('toArray', async () => {
        const objetoTeste = { chave: 'valor' }

        const resultado = EnumConverter.toArray(objetoTeste)
        expect(resultado[0]).toEqual('valor')
    })

    it('removeFirstDot', async () => {
        const listaTeste = ['.TESTE1', '.TESTE2']

        const resultado = EnumConverter.removeFirstDot(listaTeste)
        expect(resultado).toEqual(['TESTE1', 'TESTE2'])
    })

    it('insertFirstDot', async () => {
        const listaTeste = ['TESTE1', 'TESTE2', '.TESTE3']

        const resultado = EnumConverter.insertFirstDot(listaTeste)
        expect(resultado).toEqual(['.TESTE1', '.TESTE2', '.TESTE3'])
    })
})
