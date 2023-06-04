<script>
import {actionTypes, mutationTypes} from '@/core/constants'
const ROTA_PREGOEIRO = 'pregoeiro'
const ROTA_FORNECEDOR = 'licitante'

export default {
    name: 'login',
    async created() {
        await this.autenticarUsuario()
    },
    methods: {
        async autenticarUsuario() {
            try {
                const token = this.pegarTokenRota()
                this.armazenarToken(token)
                this.armazenarRedirect()
                await this.autenticarTokenAcesso(token)
                this.redirecionarUsuario()
            } catch (e) {
                this.redirecionarUsuarioNaoAutenticado()
            }
        },
        armazenarToken(token) {
            sessionStorage.setItem('jwt', token)
            this.$store.commit(mutationTypes.COMUM.SET_TOKEN_AUTENTICACAO, token)
        },
        armazenarRedirect() {
            sessionStorage.setItem('redirect', this.pegarRedirectRota())
        },
        async autenticarTokenAcesso(token) {
            await this.$store.dispatch(actionTypes.COMUM.AUTENTICAR_USUARIO, token)
        },
        ehRotaPregoeiro() {
            return this.$route.query.redirect === ROTA_PREGOEIRO
        },
        ehRotaFornecedor() {
            return this.$route.query.redirect === ROTA_FORNECEDOR
        },
        pegarTokenRota() {
            return this.$route.query.token
        },
        pegarRedirectRota() {
            return this.$route.query.redirect
        },
        redirecionarUsuario() {
            const authorities = this.$store.state.comum.dadosAutenticacao.payload.authorities
            if (this.ehRotaPregoeiro() && authorities === 'Pregao.pregoeiro') {
                return this.redirecionarParaPregoeiro()
            }

            if (this.ehRotaFornecedor()&& authorities === 'Pregao.licitante') {
                return this.redirecionarParaFornecedor()
            }

            else {
                return this.redirecionarUsuarioNaoAutenticado()
            }
        },
        redirecionarParaPregoeiro() {
            this.$router.push({name: ROTA_PREGOEIRO})
        },
        redirecionarParaFornecedor() {
            this.$router.push({name: ROTA_FORNECEDOR})
        },
        redirecionarUsuarioNaoAutenticado() {
            this.$router.push({name: 'PaginaNaoAutorizado'})
        }
    },
    render: h => h()
}
</script>