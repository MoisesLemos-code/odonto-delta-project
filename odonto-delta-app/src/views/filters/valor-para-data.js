import moment from 'moment-timezone'
import state from '@/core/store/state'

const filter = (date, format = 'DD/MM/YYYY') => {
    if (date) {
        return moment(date).tz(state.timezone).format(format)
    }
    return '-'
}

export default filter
