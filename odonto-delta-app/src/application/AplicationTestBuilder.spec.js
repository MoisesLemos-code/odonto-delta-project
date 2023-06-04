import ApplicationTestBuilder from './ApplicationTestBuilder'

describe('ApplicationTestBuilder',() =>{
    it('Deve retornar a Store',()=>{
        expect(ApplicationTestBuilder.getStore()).toBeTruthy()
    })
    it('Deve retornar a Route',()=>{
        expect(ApplicationTestBuilder.getRouter()).toBeTruthy()
    })
    it('Deve adicionar um elemento com data',()=>{
        expect(ApplicationTestBuilder._addElemWithDataAppToBody).toBeDefined()
    })

    it('Deve criar o VueRouter',()=>{
        expect(ApplicationTestBuilder._createLocalVueRouter).toBeDefined()
    })
})
