<template>
  <div v-on:mouseover="formatTime" class="auto-save">
    <v-tooltip bottom>
      <template v-slot:activator="{ on }">
        <span v-on="on" v-html="message"></span>
      </template>

      <span>{{ description }}</span>
    </v-tooltip>
  </div>
</template>

<script>
import moment from 'moment'

export default {
    name: 'AutoSave',
    data() {
        return {
            formattedDate: null,
        }
    },
    computed: {
        message() {
            if (this.$store.state.autoSave.saving) {
                return this.$store.state.autoSave.savingMessage
            }
            if (!this.$store.state.autoSave.saving && this.$store.state.autoSave.dateLastSaved) {
                return this.$store.state.autoSave.autoSavedMessage
            }
            return ''
        },
        description() {
            return this.$store.state.autoSave.autoSavedDescription.replace('${date}', this.formattedDate)
        },
    },
    methods: {
        formatTime() {
            const end = moment(this.$store.state.autoSave.dateLastSaved)
            const duration = moment.duration(end.diff(moment()))
            const data = duration.humanize(true)
            if (data === 'há segundos') {
                this.formattedDate = 'segundos'
            } else {
                this.formattedDate = data
            }
        },
    },
}
</script>

<style lang="stylus" scoped>
.auto-save
  color #7c9934
  margin-right 1rem
  font-size 12px
</style>
