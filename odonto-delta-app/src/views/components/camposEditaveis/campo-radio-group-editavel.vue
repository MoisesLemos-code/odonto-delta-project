<template>
  <div class="campo-input-edit text-edit">
    <label class="font-weight-bold label">{{ label }}</label>
    <v-tooltip top v-if="!disabled && label && !editing">
      <template v-slot:activator="{ on }">
        <v-btn v-on="on" icon class="start-edit-btn-check" @click="startEdit">
          <v-icon class="mt-1" x-small>fas fa-edit</v-icon>
        </v-btn>
      </template>
      {{ labelBtnEdit }}
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
    <div v-if="!editing">
      <v-card class="elevation-0">
        <v-card-text class="ml-0 pa-0">
            <v-radio-group v-model="model" class="checkbox_edit" row>
              <v-radio label="Sim" :value="true" disabled/>
              <v-radio label="Não" :value="false" disabled/>
            </v-radio-group>
          <v-tooltip top v-if="!disabled && !label">
            <template v-slot:activator="{ on }">
              <v-btn v-on="on" icon class="start-edit-btn" @click="startEdit">
                <v-icon class="mt-1" x-small>fas fa-edit</v-icon>
              </v-btn>
            </template>
            {{ labelBtnEdit }}
          </v-tooltip>
        </v-card-text>
      </v-card>
    </div>

    <div v-if="editing">
      <v-card class="elevation-0">
        <v-card-text class="ml-0 pa-0">
            <v-radio-group v-model="model" class="checkbox_edit" row>
              <v-radio label="Sim" :value="true"/>
              <v-radio label="Não" :value="false"/>
            </v-radio-group>
        </v-card-text>
      </v-card>
    </div>
  </div>
</template>

<script>
    export default {
        inject: ['$validator'],
        name: 'campo-radio-group-editavel',
        props: {
            value: {
                required: false
            },
            name: {
                required: true,
                type: String
            },
            label: {
                type: String,
                default: ''
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
                this.editing = true
                this.$emit('setaEditando', this.name)
            },
            async submitEdit() {
                if (this.validarDadosFormulario()) {
                    this.editing = false
                    this.$emit('retiraEditando', this.name)
                    this.$emit('input', this.model)
                }
            },
            cancelEdit() {
                this.editing = false
                this.$emit('retiraEditando', this.name)
                this.model = this.value
            },
            validarDadosFormulario() {
                const item = this.$validator._base.errors.items.find(item => item.field === this.name)
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

  .v-text-field input
    padding 8px 0 0

  .start-edit-btn-check, .edit-btn-check
    top -2px

    &:hover
      opacity 1

    .v-btn__content
      top -3px

  .start-edit-btn-check
    opacity .4

.max-8
  max-width 8vw

.checkbox_edit
    padding 0px
    .v-label
      padding-top 10px

.label
  font-size 13px
  font-weight bold
  color #777 !important


</style>
