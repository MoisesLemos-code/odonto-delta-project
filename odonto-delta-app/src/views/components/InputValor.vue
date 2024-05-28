<template>
  <v-text-field
      v-money="conditionalMoneyConfig"
      :value="valueFormated"
      :name="name"
      :label="label"
      :maxLength="maxLength"
      :disabled="disabled"
      :required="required"
      :placeholder="placeholder"
      class="inputValor"
      @blur="updateValue($event.target.value, 'blur')"
      @keydown="validatorNegative($event)"
      @keyup.enter="updateValue($event.target.value, 'keyupEnter')"
      @keyup="checkKey($event)"
      outlined
  />
</template>

<script>
import accounting from 'accounting'

export default {
    name: 'InputValor',
    props: {
        value: {
            required: true,
            default: null
        },
        label: {
            type: String,
            default: ''
        },
        placeholder: {
            type: String,
            default: ''
        },
        name: {
            type: String,
            default: ''
        },
        required: {
            type: Boolean,
            default: false
        },
        disabled: {
            type: Boolean,
            default: false
        },
        maxLength: {
            type: Number,
            default: 24
        },
        negative: {
            type: Boolean,
            default: false
        },
        precision: {
            type: Number,
            default: 2
        },
        prefix: {
            type: String,
            default: 'R$ '
        },
        suffix: {
            type: String,
            default: ''
        }
    },
    data() {
        return {
            moneyConfig: {
                decimal: ',',
                thousands: '.',
                prefix: this.prefix,
                suffix: this.suffix,
                precision: this.precision,
                masked: false
            },
            clickedField: false
        }
    },
    computed: {
        valueFormated() {
            if (this.value !== null) {
                return this.prefix + accounting.formatNumber(accounting.unformat(this.value, ','), this.precision, '.', ',') + this.suffix
            } else {
                return null
            }
        },
        conditionalMoneyConfig() {
            return this.value !== null || this.clickedField ? this.moneyConfig : null
        },
    },
    methods: {
        updateValue(value, event) {
            let valueNumber = value
            if (this.prefix) {
                valueNumber = valueNumber.replace(this.prefix, '')
            }
            if (this.suffix) {
                valueNumber = valueNumber.replace(this.suffix, '')
            }
            const valueFormatedSimple = accounting.unformat(valueNumber, ',')
            if (valueFormatedSimple !== this.value && this.clickedField) {
                this.$emit(event, valueFormatedSimple)
                this.clickedField = false
            }
        },
        validatorNegative($event) {
            if ($event.key === '-' && !this.negative) {
                $event.preventDefault()
            }
        },
        checkKey($event) {
            if ($event.key !== 'Tab') {
                this.clickedField = true
            }
        }
    }
}
</script>

<style scoped lang="stylus">


</style>

