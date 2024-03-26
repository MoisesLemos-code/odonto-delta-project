<template>
    <div>
        <toolbar-view>
            <botao-voltar slot="actions" text="Voltar"/>
        </toolbar-view>
        <container-component>
            <router-view/>
        </container-component>
    </div>
</template>

<script>
import BotaoVoltar from '@/views/components/BotaoVoltar'
import ContainerComponent from '@/views/components/Container'

export default {
    name: 'Paciente',
    components: {ContainerComponent, BotaoVoltar}
}
</script>

<style scoped>

</style>