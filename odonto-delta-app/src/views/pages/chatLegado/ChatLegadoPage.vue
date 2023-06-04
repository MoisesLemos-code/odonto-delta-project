<template>
    <div style="background-color: #EEEEEE; height: 100%">
        <az-back-button :route="{ name: 'Inicial' }" text="Voltar para o início" />
        <az-container>
            <v-form class="az-form-content mt-10 pl-0 pr-0" style="background-color: #eeeeee">
                <v-container fluid>
                    <v-row class="ma-10 pl-7">
                        <v-col cols="12" lg="2">
                            <v-text-field
                                v-model="numeroPregao"
                                placeholder="Digite o número do pregão..."
                                dense
                                outlined
                                clearable
                                class="mb-0 mt-6 mr-2 input-text"
                            />
                            <v-btn
                                @click="conectarChat"
                                :disabled="!numeroPregao"
                                :class="{ 'btn-disabled': !numeroPregao }"
                                >Conectar</v-btn
                            >
                        </v-col>
                    </v-row>
                    <v-row class="ma-10 pl-7">
                        <div :key="lote.valor" v-for="lote in lotes">
                            <v-checkbox
                                v-if="lote.mostrar"
                                v-model="lote.selecionado"
                                :label="lote.nome"
                                class="mr-5"
                            />
                        </div>
                    </v-row>
                    <v-row>
                        <v-col cols="12">
                            <v-row align="center" justify="center">
                                <ChatLegado :lotes="lotes" :conexao="conexaoPregao" @conexaoRealizada="numeroPregao = null"/>
                            </v-row>
                        </v-col>
                    </v-row>
                </v-container>
            </v-form>
        </az-container>
    </div>
</template>

<script>
import ChatLegado from '@/views/components/ChatLegado'

export default {
    name: 'ChatLegadoPage',
    components: { ChatLegado },
    data() {
        return {
            lotes: [
                { sequencia: 'Não Filtrar', valor: 'Não Filtrar', selecionado: false, mostrar: false },
                { sequencia: 'LOTE 001', valor: '1', selecionado: false, mostrar: true },
                { sequencia: 'LOTE 002', valor: '2', selecionado: false, mostrar: true },
                { sequencia: 'LOTE 003', valor: '3', selecionado: false, mostrar: true },
                { sequencia: 'LOTE 004', valor: '4', selecionado: false, mostrar: true }
            ],
            numeroPregao: null,
            conexaoPregao: null
        }
    },
    methods: {
        conectarChat() {
            this.conexaoPregao = this.numeroPregao
        }
    }
}
</script>

<style scoped></style>
