<template>
    <div class="az-container card-erro-container fb-text-color">
        <div class="mensagem-erro">
            <h1>Erro 401</h1>
            <p>O seu usuário não tem acesso a esta página.</p>
        </div>
    </div>
</template>

<script>
export default {
    name: 'NaoAutorizado'
}
</script>

<style lang="stylus" scoped>
    .card-erro-container
        display flex
        justify-content center
        align-items center
        height calc(100vh - 136px)

        .mensagem-erro
            text-align center
</style>
