export default {
        setItemStorage(login) {
            const parsed = JSON.stringify(login)
            localStorage.setItem('login', parsed)
        },

        setUpdateItem(login){
            const tokenAtual = this.getItemStorage()
            login.token = tokenAtual.token
            const parsed = JSON.stringify(login)
            localStorage.setItem('login', parsed)
        },

        getItemStorage() {
            if (localStorage.getItem('login')) {
                try {
                    const login = JSON.parse(localStorage.getItem('login'))
                    return login
                } catch (e) {
                    localStorage.removeItem('login')
                }
            }
        },

        removeItemStorage(){
            localStorage.removeItem('login')
        }
}