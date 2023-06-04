<template>
    <div class="az-container card-erro-container fb-text-color">
        <div class="mensagem-erro">
            <h1>Erro 404</h1>
            <p>Página não encontrada.</p>
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
