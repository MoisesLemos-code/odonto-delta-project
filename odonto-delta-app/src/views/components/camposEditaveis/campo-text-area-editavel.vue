<template>
  <div class="campo-input-edit text-edit">
    <label class="font-weight-bold label">{{ label }}</label>
    <v-tooltip top v-if="!disabled && label && !editing">
      <template v-slot:activator="{ on }">
        <v-btn v-on="on" icon class="start-edit-btn" @click="startEdit">
          <v-icon class="mt-1" x-small>fas fa-edit</v-icon>
        </v-btn>
      </template>
      {{labelBtnEdit}}
    </v-tooltip>
    <v-tooltip top v-if="editing">
      <template v-slot:activator="{ on }">
        <v-btn v-on="on" icon @click="submitEdit" class="edit-btn-check">
          <v-icon small class="primary--text">fas fa-check</v-icon>
        </v-btn>
      </template>
      {{ labelBtnSave }}
    </v-tooltip>
    <v-tooltip top v-if="editing">
      <template v-slot:activator="{ on }">
        <v-btn v-on="on" icon @click="cancelEdit" class="edit-btn-check">
          <v-icon small>fas fa-times</v-icon>
        </v-btn>
      </template>
      {{ labelBtnCancel }}
    </v-tooltip>
    <div v-if="!editing" >
      <v-card class="elevation-0">
        <v-card-text class="pa-0">
          <p class="d-inline-block text-truncate max-text-input" >
            {{ value | textoSemValor }}
          </p>
        </v-card-text>
      </v-card>
    </div>

    <v-textarea
        v-show="editing"
        v-model="model"
        :name="name"
        :label="labelInput"
        :placeholder="placeholder"
        v-validate="validate"
        :error-messages="errors.collect(name)"
        counter
        filled
        auto-grow
        @keyup.enter="submitEdit"
        @keyup.esc="cancelEdit"
        @click:append="cancelEdit"
        @click:clear="submitEdit"
    />
  </div>
</template>

<script>
export default {
    inject: ['$validator'],
    name: 'campo-text-area-editavel',
    props: {
        value: {
            required: true
        },
        idObjeto: {
            type: Number
        },
        name: {
            required: true,
            type: String
        },
        label: {
            type: String,
            default: ''
        },
        labelInput:{
            type: String,
            default: null
        },
        labelBtnSave: {
            type: String,
            default: 'Salvar'
        },
        labelBtnCancel: {
            type: String,
            default: 'Cancelar'
        },
        labelBtnEdit: {
            type: String,
            default: 'Editar'
        },
        maxlength: {
            default: 255
        },
        validate: {
            type: Object
        },
        counter: {
            type: Number
        },
        placeholder: {
            type: String
        },
        mask: {
            type: String,
            default: ''
        },
        estaAdicionando: {
            type: Boolean,
            default: false
        },
        disabled: {
            type: Boolean,
            default: false
        }
    },
    data() {
        return {
            model: undefined,
            editing: false
        }
    },
    watch: {
        value: {
            handler(val) {
                this.model = val
            },
            immediate: true
        }
    },
    methods: {
        startEdit() {
            if (this.estaAdicionando) {
                return this.$emit('estaAdicionando')
            }
            this.editing = true
            this.$emit('setaEditando', {nome: this.name, id: this.idObjeto})
        },
        async submitEdit() {
            if (this.validarDadosFormulario()) {
                this.editing = false
                this.$emit('retiraEditando', {nome: this.name, id: this.idObjeto})
                this.$emit('input', this.model)
            }
        },
        cancelEdit() {
            this.editing = false
            this.$emit('retiraEditando', {nome: this.name, id: this.idObjeto})
            this.model = this.value
        },
        validarDadosFormulario() {
            const item = this.$validator._base.errors.items.find( item => item.field === this.name)
            return !item
        },
    }
}
</script>

<style lang="stylus">
.text-edit
  label
    top -3px
    position relative

  .v-text-field
    max-width 50%
    padding 0px
    margin-top -5px

  .v-text-field input
    color #838383
    padding 8px 0 0

  .start-edit-btn
    top -2px
    opacity .4

    &:hover
      opacity 1

    .v-btn__content
      top -3px
.max-text-input
  max-width 90%

@media (max-width: 720px)
  .text-edit
    .v-text-field
      max-width 100%

</style>
